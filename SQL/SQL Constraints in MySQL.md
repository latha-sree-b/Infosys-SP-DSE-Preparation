# SQL Constraints in MySQL

## 1. What Are Constraints?

Constraints are rules applied to table columns to control the type of data that can be stored in a table.

They help us maintain **data accuracy, consistency, and integrity**.

For example:
- A student must have a unique student ID.
- A student's name cannot be empty (`NULL`).
- An email address should not be duplicated.
- A student's age should be within an allowed range.

We use SQL constraints to enforce these rules.

---

## 2. Types of SQL Constraints

The commonly used SQL constraints are:

| Constraint | Purpose |
|---|---|
| NOT NULL | Prevents NULL values |
| UNIQUE | Prevents duplicate values |
| PRIMARY KEY | Uniquely identifies each row |
| FOREIGN KEY | Connects tables and maintains referential integrity |
| CHECK | Ensures values satisfy a condition |
| DEFAULT | Provides a value when one is not supplied |

---

## 3. NOT NULL Constraint

### Definition

The `NOT NULL` constraint prevents a column from storing `NULL` values.

It means that a value must be provided for that column.

### Syntax

```sql
CREATE TABLE students (
    student_id INT,
    student_name VARCHAR(50) NOT NULL
);
```

Here, `student_name` cannot contain a NULL value.

### Example

```sql
INSERT INTO students
VALUES (1, 'Latha');

-- This will fail because student_name is NULL
INSERT INTO students
VALUES (2, NULL);
```

**Important:** An empty string (`''`) is different from `NULL`. `NOT NULL` allows an empty string.

---

## 4. UNIQUE Constraint

### Definition

The `UNIQUE` constraint ensures that duplicate values are not allowed in a column or combination of columns.

### Syntax

```sql
CREATE TABLE students (
    student_id INT,
    email VARCHAR(100) UNIQUE
);
```

### Example

```sql
INSERT INTO students
VALUES (1, 'latha@gmail.com');

-- Duplicate email: this will fail
INSERT INTO students
VALUES (2, 'latha@gmail.com');
```

**Important in MySQL:**
- A UNIQUE constraint prevents duplicate non-NULL values.
- A UNIQUE column can contain multiple NULL values.

---

## 5. PRIMARY KEY Constraint

### Definition

A `PRIMARY KEY` uniquely identifies every row in a table.

A primary key has two important properties:

1. It does not allow duplicate values.
2. It does not allow NULL values.

A table can have only one primary key constraint, but that key can contain multiple columns (a composite primary key).

### Syntax

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50)
);
```

### Example

```sql
INSERT INTO students
VALUES (1, 'Latha');

-- Duplicate primary key: this will fail
INSERT INTO students
VALUES (1, 'Ravi');

-- NULL primary key: this will fail
INSERT INTO students
VALUES (NULL, 'Priya');
```

### Composite Primary Key

A composite primary key uses two or more columns together to uniquely identify a row.

```sql
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    PRIMARY KEY (student_id, course_id)
);
```

Here, the combination of `student_id` and `course_id` must be unique.

---

## 6. FOREIGN KEY Constraint

### Definition

A `FOREIGN KEY` connects two tables.

It ensures that a value in the child table refers to an existing key value in the parent table.

### Example

Suppose we have two tables:

**Parent table: departments**

```sql
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);
```

**Child table: employees**

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    department_id INT,
    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);
```

### Insert Data

```sql
INSERT INTO departments
VALUES (101, 'IT');

INSERT INTO employees
VALUES (1, 'Latha', 101);
```

This works because department `101` exists in the parent table.

```sql
-- This will fail if department 999 does not exist
INSERT INTO employees
VALUES (2, 'Ravi', 999);
```

### Important Points

- The referenced parent column must be indexed. It is commonly a PRIMARY KEY or UNIQUE key.
- The parent and child columns must have compatible data types.
- A foreign key can contain NULL if the child column allows it.
- Foreign keys help maintain referential integrity.

### ON DELETE and ON UPDATE

We can specify what happens when a referenced parent row is deleted or updated.

| Option | Meaning |
|---|---|
| CASCADE | Automatically deletes or updates matching child rows |
| SET NULL | Sets the child foreign-key value to NULL |
| RESTRICT | Prevents the parent change when matching child rows exist |
| NO ACTION | In MySQL, behaves like RESTRICT |

Example:

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    department_id INT,
    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
```

With `ON DELETE CASCADE`, deleting a department also deletes its related employee rows.

Use CASCADE carefully because it can delete multiple related records.

---

## 7. CHECK Constraint

### Definition

The `CHECK` constraint ensures that values satisfy a specified condition.

### Syntax

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    age INT CHECK (age >= 18)
);
```

Here, the age must be at least 18.

### Example

```sql
INSERT INTO students
VALUES (1, 'Latha', 22);

-- This will fail because age is less than 18
INSERT INTO students
VALUES (2, 'Ravi', 15);
```

MySQL enforces CHECK constraints in MySQL 8.0.16 and later.

**Important:** A CHECK condition that evaluates to UNKNOWN because of NULL does not reject the row. Use `NOT NULL` as well if NULL must be prohibited.

---

## 8. DEFAULT Constraint

### Definition

The `DEFAULT` constraint provides a predefined value when a column value is omitted from an INSERT statement.

### Syntax

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    status VARCHAR(20) DEFAULT 'Active'
);
```

### Example

```sql
INSERT INTO employees
(employee_id, employee_name)
VALUES (1, 'Latha');
```

The `status` column automatically receives `'Active'`.

To explicitly request the default:

```sql
INSERT INTO employees
VALUES (2, 'Ravi', DEFAULT);
```

**Important:** If you explicitly insert `NULL` into a nullable column, the DEFAULT value is not used.

---

## 9. AUTO_INCREMENT

### Definition

`AUTO_INCREMENT` automatically generates the next numeric value when a row is inserted without an explicit value for that column.

It is commonly used with a primary key.

### Example

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(50) NOT NULL
);
```

Insert records:

```sql
INSERT INTO students (student_name)
VALUES ('Latha');

INSERT INTO students (student_name)
VALUES ('Ravi');

SELECT * FROM students;
```

The IDs are generated automatically, typically as `1` and `2` for a fresh table.

**Remember:** `AUTO_INCREMENT` is a column attribute, not a separate SQL constraint.

---

## 10. How to Add Constraints Using ALTER TABLE

We can add constraints to an existing table using `ALTER TABLE`.

### Add NOT NULL

```sql
ALTER TABLE students
MODIFY COLUMN student_name VARCHAR(50) NOT NULL;
```

Existing rows must satisfy the new rule.

### Add UNIQUE

```sql
ALTER TABLE students
ADD CONSTRAINT unique_email UNIQUE (email);
```

### Add PRIMARY KEY

```sql
ALTER TABLE students
ADD CONSTRAINT pk_students PRIMARY KEY (student_id);
```

The column must not contain NULL or duplicate values.

### Add FOREIGN KEY

```sql
ALTER TABLE employees
ADD CONSTRAINT fk_department
FOREIGN KEY (department_id)
REFERENCES departments(department_id);
```

Existing values must satisfy the foreign-key relationship.

### Add CHECK

```sql
ALTER TABLE students
ADD CONSTRAINT chk_age CHECK (age >= 18);
```

### Add DEFAULT

```sql
ALTER TABLE students
ALTER COLUMN status SET DEFAULT 'Active';
```

---

## 11. How to Remove Constraints

### Remove UNIQUE

```sql
ALTER TABLE students
DROP INDEX unique_email;
```

### Remove PRIMARY KEY

```sql
ALTER TABLE students
DROP PRIMARY KEY;
```

### Remove FOREIGN KEY

```sql
ALTER TABLE employees
DROP FOREIGN KEY fk_department;
```

### Remove CHECK

```sql
ALTER TABLE students
DROP CHECK chk_age;
```

### Remove DEFAULT

```sql
ALTER TABLE students
ALTER COLUMN status DROP DEFAULT;
```

**Note:** To remove a constraint, you may need its actual name. Use `SHOW CREATE TABLE table_name;` to inspect the table definition.

---

## 12. Difference Between PRIMARY KEY and UNIQUE

| PRIMARY KEY | UNIQUE |
|---|---|
| Uniquely identifies each row | Prevents duplicate values |
| Does not allow NULL | Allows multiple NULL values in MySQL |
| Only one primary key constraint per table | Multiple UNIQUE constraints are allowed |
| Commonly used for row identification | Commonly used for email, phone number, etc. |

---

## 13. Complete Practical Example

Let's create a college database with departments, students, and enrollments.

```sql
CREATE DATABASE college;

USE college;

-- Parent table
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50) NOT NULL UNIQUE
);

-- Student table
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    age INT CHECK (age >= 18),
    status VARCHAR(20) DEFAULT 'Active',
    department_id INT,
    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);

-- Insert department
INSERT INTO departments
VALUES (101, 'Computer Science');

-- Insert students
INSERT INTO students
(student_name, email, age, department_id)
VALUES
('Latha', 'latha@gmail.com', 22, 101),
('Ravi', 'ravi@gmail.com', 23, 101);

-- Display student details
SELECT * FROM students;
```

This example uses:

- PRIMARY KEY for student and department identification.
- AUTO_INCREMENT to generate student IDs.
- NOT NULL to require student names.
- UNIQUE to prevent duplicate email addresses and department names.
- CHECK to validate age.
- DEFAULT to set student status.
- FOREIGN KEY to connect students with departments.

---

## 14. How to View Constraints in MySQL

Use:

```sql
SHOW CREATE TABLE students;
```

This displays the table's complete creation statement, including its constraints.

You can also inspect indexes:

```sql
SHOW INDEX FROM students;
```

---

## 15. Interview Questions

**1. What are constraints in SQL?**

Constraints are rules applied to table columns to maintain data accuracy and integrity.

**2. What is the difference between PRIMARY KEY and UNIQUE?**

A primary key uniquely identifies a row and does not allow NULL. A UNIQUE constraint prevents duplicate non-NULL values and allows multiple NULL values in MySQL.

**3. Can a table have multiple primary keys?**

No. A table can have only one PRIMARY KEY constraint, but it can be a composite key containing multiple columns.

**4. What is a foreign key?**

A foreign key connects a child table to a parent table and maintains referential integrity.

**5. What is the purpose of CHECK?**

CHECK validates whether inserted or updated values satisfy a specified condition.

**6. What is the purpose of DEFAULT?**

DEFAULT supplies a predefined value when a column value is omitted or DEFAULT is explicitly requested.

**7. Can a foreign key contain NULL?**

Yes, if the foreign-key column allows NULL.

**8. Can we add constraints after creating a table?**

Yes. We can use `ALTER TABLE` to add supported constraints.

---

## 16. Quick Revision

| Constraint / Attribute | Main purpose |
|---|---|
| NOT NULL | Prevents NULL values |
| UNIQUE | Prevents duplicate non-NULL values |
| PRIMARY KEY | Uniquely identifies a row |
| FOREIGN KEY | Maintains relationships between tables |
| CHECK | Validates a condition |
| DEFAULT | Supplies a default value |
| AUTO_INCREMENT | Generates numeric values automatically |

### Final Remembering Points

- Constraints maintain data integrity.
- PRIMARY KEY = UNIQUE + NOT NULL.
- FOREIGN KEY connects two tables.
- CHECK validates conditions.
- DEFAULT supplies a value when omitted.
- AUTO_INCREMENT generates numeric IDs.
- We can add or remove many constraints using ALTER TABLE.
