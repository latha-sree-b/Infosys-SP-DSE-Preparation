# MySQL ALTER TABLE – Complete Guide to Column Definitions

## 1. What is ALTER TABLE?

`ALTER TABLE` is a DDL (Data Definition Language) command used to modify the structure of an existing table.

Using `ALTER TABLE`, we can:

1. ADD a new column.
2. DROP an existing column.
3. RENAME a column.
4. MODIFY a column's data type.
5. CHANGE a column name and its definition.
6. Add or remove constraints.
7. Set or remove default values.
8. Rename a table.

### General Syntax

```sql
ALTER TABLE table_name
operation;
```

---

## 2. First, Understand Column Definitions

A column definition specifies the properties of a column in a table.

Example:

```sql
CREATE TABLE students (
    student_id INT NOT NULL AUTO_INCREMENT,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE,
    age INT DEFAULT 18,
    percentage DECIMAL(5,2),
    is_active BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (student_id)
);
```

Let's understand each part.

### General Column Definition Syntax

```sql
column_name DATA_TYPE [constraints] [default_value] [extra_attributes]
```

Example:

```sql
age INT NOT NULL DEFAULT 18
```

| Part | Meaning |
|---|---|
| age | Column name |
| INT | Data type |
| NOT NULL | NULL values are not allowed |
| DEFAULT 18 | Uses 18 if no value is supplied |

---

# PART 1: ADD COLUMN

## 3. ADD COLUMN – Add a New Column

### What is ADD COLUMN?

`ADD COLUMN` is used to add a new column to an existing table.

### Syntax

```sql
ALTER TABLE table_name
ADD COLUMN column_name DATA_TYPE;
```

### Example

Initially:

```sql
CREATE TABLE students (
    student_id INT,
    student_name VARCHAR(100)
);
```

Now add an age column:

```sql
ALTER TABLE students
ADD COLUMN age INT;
```

Final structure:

| Column | Data Type |
|---|---|
| student_id | INT |
| student_name | VARCHAR(100) |
| age | INT |

### Add a Column with Constraints

```sql
ALTER TABLE students
ADD COLUMN email VARCHAR(100) NOT NULL;
```

**Important:** If the table already contains records, adding a `NOT NULL` column without a suitable default can fail. Plan for existing rows by adding a default or using a nullable column first.

### Add Multiple Columns

```sql
ALTER TABLE students
ADD COLUMN age INT,
ADD COLUMN email VARCHAR(100),
ADD COLUMN joining_date DATE;
```

---

# PART 2: DROP COLUMN

## 4. DROP COLUMN – Remove a Column

### What is DROP COLUMN?

`DROP COLUMN` is used to permanently remove a column from an existing table.

### Syntax

```sql
ALTER TABLE table_name
DROP COLUMN column_name;
```

### Example

```sql
ALTER TABLE students
DROP COLUMN age;
```

The `age` column is removed.

**Important:**
- The column structure is removed.
- All data stored in that column is also deleted.
- This is different from DELETE, which removes rows.

### Drop Multiple Columns

```sql
ALTER TABLE students
DROP COLUMN email,
DROP COLUMN joining_date;
```

---

# PART 3: RENAME COLUMN

## 5. RENAME COLUMN – Change Only the Column Name

### What is RENAME COLUMN?

It changes the name of an existing column without changing its data type or other column attributes.

### Syntax

```sql
ALTER TABLE table_name
RENAME COLUMN old_name TO new_name;
```

### Example

```sql
ALTER TABLE students
RENAME COLUMN name TO student_name;
```

Before:

| Column | Data Type |
|---|---|
| name | VARCHAR(100) |

After:

| Column | Data Type |
|---|---|
| student_name | VARCHAR(100) |

**Important:** RENAME COLUMN is available in MySQL 8.0 and later.

---

# PART 4: CHANGE COLUMN

## 6. CHANGE COLUMN – Rename and Modify a Column

### What is CHANGE COLUMN?

`CHANGE COLUMN` is used to:
- Rename an existing column.
- Change its data type.
- Change its other column attributes.

### Syntax

```sql
ALTER TABLE table_name
CHANGE COLUMN old_name new_name new_data_type;
```

### Example 1: Rename a Column

```sql
ALTER TABLE students
CHANGE COLUMN name student_name VARCHAR(100);
```

Here:
- Old name = name
- New name = student_name
- Data type = VARCHAR(100)

### Example 2: Change Data Type

```sql
ALTER TABLE students
CHANGE COLUMN student_id student_id BIGINT;
```

Here:
- Column name remains student_id.
- Data type changes from INT to BIGINT.

### Example 3: Rename and Change Data Type

```sql
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;
```

Here:
- `id` becomes `student_id`.
- `INT` becomes `BIGINT`.

**Important:** With CHANGE COLUMN, write the complete new column definition. If you omit an existing attribute such as `NOT NULL`, `DEFAULT`, or `UNSIGNED`, that attribute may be lost.

---

# PART 5: MODIFY COLUMN

## 7. MODIFY COLUMN – Change Column Definition

### What is MODIFY COLUMN?

`MODIFY COLUMN` is used to change the data type or other attributes of an existing column without changing its name.

### Syntax

```sql
ALTER TABLE table_name
MODIFY COLUMN column_name new_data_type;
```

### Example 1: Change Data Type

```sql
ALTER TABLE students
MODIFY COLUMN student_id BIGINT;
```

Before:

```text
student_id INT
```

After:

```text
student_id BIGINT
```

### Example 2: Change VARCHAR Length

```sql
ALTER TABLE students
MODIFY COLUMN student_name VARCHAR(200);
```

Before:

```text
student_name VARCHAR(100)
```

After:

```text
student_name VARCHAR(200)
```

### Example 3: Add NOT NULL

```sql
ALTER TABLE students
MODIFY COLUMN student_name VARCHAR(100) NOT NULL;
```

Now the column cannot store NULL values.

### Example 4: Add UNSIGNED

```sql
ALTER TABLE students
MODIFY COLUMN age INT UNSIGNED;
```

Now the column accepts only zero and positive integer values.

---

# PART 6: COLUMN CONSTRAINTS AND ATTRIBUTES

## 8. NOT NULL

### What is NOT NULL?

`NOT NULL` ensures that a column cannot store a NULL value.

### Example

```sql
CREATE TABLE employees (
    employee_id INT,
    employee_name VARCHAR(100) NOT NULL
);
```

This is valid:

```sql
INSERT INTO employees
VALUES (101, 'Latha');
```

This is invalid:

```sql
INSERT INTO employees
VALUES (102, NULL);
```

### Add NOT NULL to an Existing Column

```sql
ALTER TABLE employees
MODIFY COLUMN employee_name VARCHAR(100) NOT NULL;
```

**Remember:** An empty string (`''`) is not the same as NULL.

---

## 9. DEFAULT

### What is DEFAULT?

`DEFAULT` specifies a value that MySQL uses when an INSERT statement does not supply a value for that column.

### Example

```sql
CREATE TABLE employees (
    employee_id INT,
    employee_name VARCHAR(100),
    city VARCHAR(50) DEFAULT 'Bengaluru'
);
```

Insert without specifying city:

```sql
INSERT INTO employees (employee_id, employee_name)
VALUES (101, 'Latha');
```

The city will be:

```text
Bengaluru
```

### Add DEFAULT to an Existing Column

```sql
ALTER TABLE employees
ALTER COLUMN city SET DEFAULT 'Mysuru';
```

### Remove DEFAULT

```sql
ALTER TABLE employees
ALTER COLUMN city DROP DEFAULT;
```

**Important:** DEFAULT is used when a value is omitted. Explicitly inserting NULL does not automatically use the default.

---

## 10. UNIQUE

### What is UNIQUE?

`UNIQUE` ensures that duplicate values are not allowed in a column or combination of columns.

### Example

```sql
CREATE TABLE employees (
    employee_id INT,
    email VARCHAR(100) UNIQUE
);
```

The following duplicate email values are not allowed:

```sql
INSERT INTO employees VALUES (101, 'latha@gmail.com');

INSERT INTO employees VALUES (102, 'latha@gmail.com');
```

The second statement fails because the email already exists.

### Add UNIQUE to an Existing Column

```sql
ALTER TABLE employees
ADD CONSTRAINT unique_email UNIQUE (email);
```

### Remove UNIQUE

```sql
ALTER TABLE employees
DROP INDEX unique_email;
```

**Important:** MySQL UNIQUE indexes allow multiple NULL values in a single-column unique index.

---

## 11. PRIMARY KEY

### What is PRIMARY KEY?

A PRIMARY KEY uniquely identifies every record in a table.

Properties:
- Does not allow duplicate values.
- Does not allow NULL values.
- A table can have only one PRIMARY KEY constraint.
- A PRIMARY KEY can consist of multiple columns (composite key).

### Example

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(100)
);
```

### Add PRIMARY KEY

```sql
ALTER TABLE employees
ADD PRIMARY KEY (employee_id);
```

### Remove PRIMARY KEY

```sql
ALTER TABLE employees
DROP PRIMARY KEY;
```

**Important:** The column must not contain NULL or duplicate values when you add a primary key.

---

## 12. AUTO_INCREMENT

### What is AUTO_INCREMENT?

`AUTO_INCREMENT` automatically generates a new numeric value when a record is inserted without providing a value for that column.

### Example

```sql
CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100)
);
```

Insert records:

```sql
INSERT INTO employees (employee_name)
VALUES ('Latha'), ('Ravi'), ('Anu');
```

Example result:

| employee_id | employee_name |
|---|---|
| 1 | Latha |
| 2 | Ravi |
| 3 | Anu |

### Add AUTO_INCREMENT to an Existing Column

```sql
ALTER TABLE employees
MODIFY COLUMN employee_id INT NOT NULL AUTO_INCREMENT;
```

The column must be indexed, typically as a PRIMARY KEY or UNIQUE key, and must not contain NULL values.

---

## 13. UNSIGNED

### What is UNSIGNED?

`UNSIGNED` means a numeric column cannot store negative values.

Example:

```sql
CREATE TABLE products (
    product_id INT UNSIGNED,
    quantity INT UNSIGNED
);
```

### Add UNSIGNED to an Existing Column

```sql
ALTER TABLE products
MODIFY COLUMN quantity INT UNSIGNED;
```

Example:

- 10 – Valid
- 0 – Valid
- -5 – Invalid

---

## 14. COMMENT

### What is COMMENT?

`COMMENT` is used to add a description to a table or column.

### Example

```sql
CREATE TABLE employees (
    employee_id INT COMMENT 'Unique employee identifier',
    salary DECIMAL(10,2) COMMENT 'Monthly salary'
);
```

### Add a Comment to an Existing Column

```sql
ALTER TABLE employees
MODIFY COLUMN salary DECIMAL(10,2)
COMMENT 'Monthly employee salary';
```

---

# PART 7: RENAME TABLE

## 15. Rename an Existing Table

There are two ways to rename a table in MySQL.

### Method 1: RENAME TABLE

```sql
RENAME TABLE employees TO employee_details;
```

### Method 2: ALTER TABLE

```sql
ALTER TABLE employee_details
RENAME TO employees;
```

Both commands rename an existing table.

---

# PART 8: COMPLETE COMPARISON

## 16. Difference Between ALTER TABLE Operations

| Operation | Purpose |
|---|---|
| ADD COLUMN | Add a new column |
| DROP COLUMN | Remove a column |
| RENAME COLUMN | Change only the column name |
| CHANGE COLUMN | Rename and/or modify a column definition |
| MODIFY COLUMN | Modify a column definition without renaming |
| ALTER COLUMN SET DEFAULT | Set a default value |
| ALTER COLUMN DROP DEFAULT | Remove a default value |
| ADD PRIMARY KEY | Add a primary key |
| DROP PRIMARY KEY | Remove a primary key |
| ADD CONSTRAINT UNIQUE | Add a unique constraint |
| DROP INDEX | Remove a named unique index |
| RENAME TO | Rename a table |

---

# PART 9: COMPLETE PRACTICAL EXAMPLE

## 17. Employee Management Table

### Step 1: Create Database

```sql
CREATE DATABASE company;

USE company;
```

### Step 2: Create Table

```sql
CREATE TABLE employees (
    id INT,
    name VARCHAR(50)
);
```

### Step 3: Add Columns

```sql
ALTER TABLE employees
ADD COLUMN age INT,
ADD COLUMN email VARCHAR(100);
```

### Step 4: Rename Column

```sql
ALTER TABLE employees
RENAME COLUMN name TO employee_name;
```

### Step 5: Change Data Type

```sql
ALTER TABLE employees
MODIFY COLUMN age TINYINT UNSIGNED;
```

### Step 6: Rename and Change Data Type

```sql
ALTER TABLE employees
CHANGE COLUMN id employee_id BIGINT;
```

### Step 7: Add NOT NULL

```sql
ALTER TABLE employees
MODIFY COLUMN employee_name VARCHAR(50) NOT NULL;
```

### Step 8: Add DEFAULT

```sql
ALTER TABLE employees
ALTER COLUMN age SET DEFAULT 18;
```

### Step 9: Add UNIQUE Constraint

```sql
ALTER TABLE employees
ADD CONSTRAINT unique_employee_email UNIQUE (email);
```

### Step 10: Describe Table

```sql
DESC employees;
```

---

# PART 10: IMPORTANT POINTS TO REMEMBER

1. `ALTER TABLE` is a DDL command.
2. `ADD COLUMN` adds a new column.
3. `DROP COLUMN` permanently removes a column and its data.
4. `RENAME COLUMN` changes only the column name.
5. `CHANGE COLUMN` can rename a column and modify its definition.
6. `MODIFY COLUMN` modifies a column definition without renaming it.
7. `NOT NULL` prevents NULL values.
8. `DEFAULT` provides a value when a column is omitted from an INSERT.
9. `UNIQUE` prevents duplicate non-NULL values.
10. `PRIMARY KEY` uniquely identifies each record.
11. `AUTO_INCREMENT` generates numeric values automatically.
12. `UNSIGNED` prevents negative numeric values.

**Important MySQL note:** ALTER TABLE is DDL and generally causes an implicit commit. Do not assume you can undo an ALTER TABLE operation using ROLLBACK. Changing a data type can also affect existing values, so inspect your data and back it up before making important changes.

---

# PART 11: INTERVIEW QUESTIONS

1. What is ALTER TABLE?
2. What is the difference between CHANGE and MODIFY?
3. What is the difference between RENAME COLUMN and CHANGE COLUMN?
4. How do you add multiple columns?
5. How do you delete a column?
6. What is the difference between DELETE and DROP COLUMN?
7. What is the purpose of NOT NULL?
8. What is the purpose of DEFAULT?
9. What is the difference between PRIMARY KEY and UNIQUE?
10. What is AUTO_INCREMENT?
11. What does UNSIGNED mean?
12. Can we add a PRIMARY KEY to an existing table?
13. How do you remove a UNIQUE constraint?
14. Can ALTER TABLE be rolled back in MySQL?

---

# CONCLUSION

Understand the basic structure first:

```sql
ALTER TABLE table_name
ADD COLUMN column_name DATA_TYPE;

ALTER TABLE table_name
DROP COLUMN column_name;

ALTER TABLE table_name
RENAME COLUMN old_name TO new_name;

ALTER TABLE table_name
CHANGE COLUMN old_name new_name DATA_TYPE;

ALTER TABLE table_name
MODIFY COLUMN column_name DATA_TYPE;
```

**Learning order:**

ADD COLUMN → DROP COLUMN → RENAME COLUMN → CHANGE COLUMN → MODIFY COLUMN → NOT NULL → DEFAULT → UNIQUE → PRIMARY KEY → AUTO_INCREMENT.

Practise each operation using an `employees` table before moving to SQL JOINS.
