# MySQL – Database Selection, Table Renaming and Column Modification

## 1. SHOW DATABASES

### What is SHOW DATABASES?

`SHOW DATABASES` is used to display all databases available in the MySQL server.

### Syntax

```sql
SHOW DATABASES;
```

### Example

```sql
SHOW DATABASES;
```

### Output (Example)

```text
information_schema
mysql
performance_schema
sys
college
day1
```

**Remember:**
- It displays the databases available to your MySQL user.
- It does not create or modify any database.

---

## 2. USE – Select a Database

### What is USE?

`USE` is used to select a database in which you want to perform SQL operations.

### Syntax

```sql
USE database_name;
```

### Example

```sql
USE day1;
```

Now, `day1` becomes the currently selected database.

### How to Check the Selected Database?

```sql
SELECT DATABASE();
```

Output:

```text
day1
```

**Important:**

Before creating a table, make sure you have selected the correct database.

---

## 3. SHOW TABLES

### What is SHOW TABLES?

`SHOW TABLES` is used to display all tables present inside the currently selected database.

### Syntax

```sql
SHOW TABLES;
```

### Example

```sql
USE day1;

SHOW TABLES;
```

Output (Example):

```text
student
employee
department
```

**Remember:**

- `SHOW DATABASES` displays databases.
- `SHOW TABLES` displays tables inside the selected database.

### Difference

| SHOW DATABASES | SHOW TABLES |
|---|---|
| Displays databases | Displays tables |
| Shows databases available to the user | Shows tables in the selected database |
| Does not require selecting a database | Requires a selected database |

---

## 4. RENAME TABLE – Rename a Table

### What is RENAME TABLE?

`RENAME TABLE` is a DDL command used to change the name of an existing table.

It changes only the table name. It does not change the table's columns or data.

### Syntax

```sql
RENAME TABLE old_table_name TO new_table_name;
```

### Example

Initially, the table name is `student`.

```sql
RENAME TABLE student TO users;
```

Now the table name becomes `users`.

### Before

```text
student
```

### After

```text
users
```

### Verify the Change

```sql
SHOW TABLES;
```

**Important Points:**

- The old table must exist.
- The new table name must not conflict with an existing table name in the same database.
- The table's data and column definitions are preserved.

---

## 5. ALTER TABLE – Modify an Existing Table

### What is ALTER TABLE?

`ALTER TABLE` is a DDL command used to change the structure of an existing table.

Using ALTER TABLE, we can:

1. Add a new column.
2. Remove an existing column.
3. Rename a column.
4. Change a column's data type.
5. Rename a table.
6. Modify column definitions.

### General Syntax

```sql
ALTER TABLE table_name
operation;
```

### Example

```sql
ALTER TABLE student
RENAME TO students;
```

This renames the table `student` to `students`.

---

## 6. Rename a Table Using ALTER TABLE

MySQL provides two ways to rename a table.

### Method 1: Using RENAME TABLE

```sql
RENAME TABLE students TO student;
```

### Method 2: Using ALTER TABLE

```sql
ALTER TABLE student
RENAME TO students;
```

Both commands can rename an existing table.

### Difference

| RENAME TABLE | ALTER TABLE ... RENAME TO |
|---|---|
| Directly renames a table | Renames a table using ALTER TABLE |
| DDL command | DDL command |
| Can rename multiple tables in one statement | Renames one table per statement |

**Example of renaming multiple tables:**

```sql
RENAME TABLE
students TO student_details,
employees TO employee_details;
```

---

## 7. CHANGE COLUMN – Rename and Change Data Type

### What is CHANGE COLUMN?

`CHANGE COLUMN` is used with `ALTER TABLE`.

It can perform two operations:

1. Rename an existing column.
2. Change the data type of an existing column.

### Syntax

```sql
ALTER TABLE table_name
CHANGE COLUMN old_column_name new_column_name new_data_type;
```

### Example

Initially, our table contains:

```sql
CREATE TABLE students (
    id INT,
    name VARCHAR(50),
    email VARCHAR(50)
);
```

Current table structure:

| Column | Data Type |
|---|---|
| id | INT |
| name | VARCHAR(50) |
| email | VARCHAR(50) |

Now execute:

```sql
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;
```

### What Happens?

**Operation 1: Rename the column**

```text
id → student_id
```

**Operation 2: Change the data type**

```text
INT → BIGINT
```

### Final Table Structure

| Column | Data Type |
|---|---|
| student_id | BIGINT |
| name | VARCHAR(50) |
| email | VARCHAR(50) |

**Important:**

`CHANGE COLUMN` requires:
- Old column name
- New column name
- New column definition

You can also use it only to rename a column or only to change its data type.

### Example: Only Rename a Column

```sql
ALTER TABLE students
CHANGE COLUMN student_id id BIGINT;
```

The name changes back to `id`, while the data type remains `BIGINT`.

---

## 8. MODIFY COLUMN – Change Data Type Without Renaming

### What is MODIFY COLUMN?

`MODIFY COLUMN` is used to change the definition of an existing column without changing its name.

### Syntax

```sql
ALTER TABLE table_name
MODIFY COLUMN column_name new_data_type;
```

### Example

```sql
ALTER TABLE students
MODIFY COLUMN student_id INT;
```

Here:

- Column name remains `student_id`.
- Data type changes from `BIGINT` to `INT`.

### Before

| Column | Data Type |
|---|---|
| student_id | BIGINT |

### After

| Column | Data Type |
|---|---|
| student_id | INT |

---

## 9. Difference Between CHANGE COLUMN and MODIFY COLUMN

This is an important MySQL interview question.

| CHANGE COLUMN | MODIFY COLUMN |
|---|---|
| Can rename a column | Cannot rename a column |
| Can change the data type | Can change the data type |
| Requires old and new column names | Requires existing column name |
| Requires a new column definition | Requires a new column definition |

### CHANGE Example

```sql
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;
```

### MODIFY Example

```sql
ALTER TABLE students
MODIFY COLUMN student_id INT;
```

**Easy way to remember:**

- CHANGE = Change column name and/or definition.
- MODIFY = Modify column definition without renaming it.

---

## 10. DESC – Describe Table Structure

### What is DESC?

`DESC` is short for `DESCRIBE`.

It is used to display the structure of a table.

### Syntax

```sql
DESC table_name;
```

### Example

```sql
DESC students;
```

Output (Example):

| Field | Type | Null | Key | Default | Extra |
|---|---|---|---|---|---|
| student_id | bigint | YES | | NULL | |
| name | varchar(50) | YES | | NULL | |
| email | varchar(50) | YES | | NULL | |

### What Does Each Column Mean?

| Field | Meaning |
|---|---|
| Field | Column name |
| Type | Data type |
| Null | Whether NULL values are allowed |
| Key | Key information, such as PRIMARY KEY |
| Default | Default value |
| Extra | Additional information, such as AUTO_INCREMENT |

**Remember:**

`DESC` displays the table structure. It does not display the actual records stored in the table.

To display records:

```sql
SELECT * FROM students;
```

---

## 11. Complete Practical Example

Let's understand all the concepts using one example.

### Step 1: Display Databases

```sql
SHOW DATABASES;
```

### Step 2: Create a Database

```sql
CREATE DATABASE ddl;
```

### Step 3: Select the Database

```sql
USE ddl;
```

### Step 4: Create a Table

```sql
CREATE TABLE students (
    id INT,
    name VARCHAR(50),
    email VARCHAR(50)
);
```

### Step 5: Display Tables

```sql
SHOW TABLES;
```

### Step 6: Rename the Table

```sql
RENAME TABLE students TO student;
```

### Step 7: Rename the Table Again Using ALTER

```sql
ALTER TABLE student
RENAME TO students;
```

### Step 8: Rename the Column and Change Its Data Type

```sql
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;
```

### Step 9: Describe the Table

```sql
DESC students;
```

### Step 10: Change the Data Type Only

```sql
ALTER TABLE students
MODIFY COLUMN student_id INT;
```

### Step 11: Verify Again

```sql
DESC students;
```

---

## 12. Important SQL Commands – Quick Revision

| Command | Purpose |
|---|---|
| SHOW DATABASES | Display available databases |
| USE | Select a database |
| SHOW TABLES | Display tables |
| RENAME TABLE | Rename an existing table |
| ALTER TABLE | Modify table structure |
| CHANGE COLUMN | Rename a column and/or change its definition |
| MODIFY COLUMN | Change a column definition without renaming |
| DESC | Display table structure |
| SELECT | Retrieve records |

---

## 13. Frequently Asked Interview Questions

**1. Which command is used to display all databases?**

`SHOW DATABASES;`

**2. Which command is used to select a database?**

`USE database_name;`

**3. What is the difference between SHOW DATABASES and SHOW TABLES?**

SHOW DATABASES displays databases, whereas SHOW TABLES displays tables in the selected database.

**4. How do you rename a table in MySQL?**

```sql
RENAME TABLE old_name TO new_name;
```

**5. Can we rename a table using ALTER TABLE?**

Yes.

```sql
ALTER TABLE old_name RENAME TO new_name;
```

**6. What is the purpose of CHANGE COLUMN?**

It can rename a column and change its definition, including its data type.

**7. What is the purpose of MODIFY COLUMN?**

It changes the column definition without renaming the column.

**8. What is the difference between CHANGE and MODIFY?**

CHANGE accepts both the old and new column names, whereas MODIFY uses the existing column name.

**9. What is the purpose of DESC?**

It displays the structure of a table.

**10. Are RENAME TABLE and ALTER TABLE DDL commands?**

Yes. Both are used to change database structure.

---

## 14. Practice Questions

Try writing the SQL commands yourself.

1. Display all databases.
2. Create a database called `company`.
3. Select the `company` database.
4. Create a table called `employees`.
5. Display all tables.
6. Rename `employees` to `employee_details`.
7. Rename `employee_details` back to `employees` using ALTER TABLE.
8. Rename the column `id` to `employee_id`.
9. Change the data type of `employee_id` from INT to BIGINT.
10. Change the data type of `employee_id` back to INT using MODIFY COLUMN.
11. Display the final table structure using DESC.

---

## Conclusion

Understand these five important concepts:

1. `SHOW DATABASES` – See available databases.
2. `USE` – Select a database.
3. `SHOW TABLES` – See tables in the selected database.
4. `RENAME TABLE` / `ALTER TABLE` – Rename tables.
5. `CHANGE COLUMN` / `MODIFY COLUMN` – Modify column definitions.

**MySQL learning sequence:**

Database → Table → Column → Data Types → ALTER TABLE → Constraints → INSERT → SELECT → UPDATE → DELETE → JOINS.
