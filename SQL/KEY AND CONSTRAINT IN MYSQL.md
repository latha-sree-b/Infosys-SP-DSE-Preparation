# KEY AND CONSTRAINT IN MYSQL

## 1. What Is a KEY?

A KEY is a column or a combination of columns used to identify records, establish relationships between tables, or improve data retrieval.

Keys help us identify rows and connect tables.

### Types of Keys in SQL

1. Primary Key
2. Foreign Key
3. Candidate Key
4. Super Key
5. Alternate Key
6. Composite Key
7. Unique Key

---

## 2. Types of Keys

### 2.1 Primary Key

A Primary Key uniquely identifies every row in a table.

Rules:
- Cannot contain duplicate values.
- Cannot contain NULL values.
- Only one PRIMARY KEY constraint per table.
- Can contain multiple columns (composite primary key).

Example:

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50)
);
```

Here, `student_id` uniquely identifies each student.

### 2.2 Foreign Key

A Foreign Key establishes a relationship between two tables.

It refers to a key in another table, usually its PRIMARY KEY.

Example:

```sql
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    department_id INT,
    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);
```

Here:
- `departments` is the parent table.
- `employees` is the child table.
- `department_id` in employees is the foreign key.

### 2.3 Candidate Key

A Candidate Key is a minimal set of columns that can uniquely identify a row.

Example:

Suppose a student table has:
- `student_id`
- `email`
- `student_name`

If both `student_id` and `email` are unique and NOT NULL, either can identify a student.

Therefore, both are candidate keys.

### 2.4 Super Key

A Super Key is any set of one or more columns that uniquely identifies a row.

Example:

Suppose `student_id` uniquely identifies a student.

These can all be super keys:

- `student_id`
- `student_id + student_name`
- `student_id + email`

A candidate key is a minimal super key.

### 2.5 Alternate Key

An Alternate Key is a candidate key that was not selected as the primary key.

Example:

- `student_id` is selected as PRIMARY KEY.
- `email` is another candidate key.

Therefore, `email` is an alternate key.

In MySQL, we commonly enforce this using UNIQUE and NOT NULL.

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL
);
```

### 2.6 Composite Key

A Composite Key consists of two or more columns that together uniquely identify a row.

Example:

```sql
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    PRIMARY KEY (student_id, course_id)
);
```

Here, the combination of `student_id` and `course_id` must be unique.

### 2.7 Unique Key

A UNIQUE key prevents duplicate non-NULL values in a column or combination of columns.

Example:

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    email VARCHAR(100) UNIQUE
);
```

In MySQL, a UNIQUE column can contain multiple NULL values.

---

## 3. What Is a CONSTRAINT?

A constraint is a rule applied to a table or column to maintain data accuracy and integrity.

Constraints control which values can be inserted, updated, or deleted.

### Types of Constraints

| Constraint | Purpose |
|---|---|
| NOT NULL | Prevents NULL values |
| UNIQUE | Prevents duplicate non-NULL values |
| PRIMARY KEY | Uniquely identifies rows |
| FOREIGN KEY | Maintains relationships between tables |
| CHECK | Validates a condition |
| DEFAULT | Provides a default value |

### 3.1 NOT NULL

Prevents a column from storing NULL.

```sql
CREATE TABLE students (
    student_id INT,
    student_name VARCHAR(50) NOT NULL
);
```

### 3.2 UNIQUE

Prevents duplicate non-NULL values.

```sql
CREATE TABLE students (
    student_id INT,
    email VARCHAR(100) UNIQUE
);
```

### 3.3 PRIMARY KEY

Uniquely identifies each record.

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY
);
```

### 3.4 FOREIGN KEY

Connects two tables.

```sql
FOREIGN KEY (department_id)
REFERENCES departments(department_id);
```

### 3.5 CHECK

Ensures that values satisfy a condition.

```sql
CREATE TABLE students (
    age INT CHECK (age >= 18)
);
```

### 3.6 DEFAULT

Provides a default value when a value is omitted.

```sql
CREATE TABLE employees (
    status VARCHAR(20) DEFAULT 'Active'
);
```

---

## 4. Difference Between KEY and CONSTRAINT

| KEY | CONSTRAINT |
|---|---|
| Helps identify records and establish relationships | Defines rules for data |
| Used for identification and relationships | Used for data integrity |
| Includes primary, foreign, candidate, super and composite keys | Includes NOT NULL, UNIQUE, PRIMARY KEY, FOREIGN KEY, CHECK and DEFAULT |
| A key may be implemented using a constraint or index | A constraint enforces a rule |
| Example: student_id | Example: NOT NULL |

**Important:** Keys and constraints overlap. PRIMARY KEY, UNIQUE and FOREIGN KEY are both key concepts and SQL constraints.

---

## 5. Difference Between KEY and INDEX

A KEY and an INDEX are related but have different purposes.

| KEY | INDEX |
|---|---|
| Represents a logical role in identifying or relating records | Provides a data structure to speed up searching |
| May enforce uniqueness or relationships | May or may not enforce uniqueness |
| PRIMARY KEY and UNIQUE KEY enforce uniqueness | A normal index does not enforce uniqueness |
| FOREIGN KEY maintains referential integrity | An index alone does not maintain referential integrity |

Example:

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    email VARCHAR(100) UNIQUE
);
```

MySQL creates indexes to support the PRIMARY KEY and UNIQUE constraints.

---

## 6. How to Name Constraints in MySQL

We can give names to constraints using `CONSTRAINT`.

Example:

```sql
CREATE TABLE students (
    student_id INT,
    email VARCHAR(100),

    CONSTRAINT pk_students
        PRIMARY KEY (student_id),

    CONSTRAINT uq_student_email
        UNIQUE (email)
);
```

Here:
- `pk_students` is the primary key constraint name.
- `uq_student_email` is the unique constraint name.

Naming constraints makes them easier to identify and manage.

### Adding a Constraint Using ALTER TABLE

```sql
ALTER TABLE students
ADD CONSTRAINT uq_student_email
UNIQUE (email);
```

### Removing a Constraint

Remove a UNIQUE constraint:

```sql
ALTER TABLE students
DROP INDEX uq_student_email;
```

Remove a PRIMARY KEY:

```sql
ALTER TABLE students
DROP PRIMARY KEY;
```

Remove a FOREIGN KEY:

```sql
ALTER TABLE employees
DROP FOREIGN KEY fk_department;
```

---

## 7. Complete Practical Example

```sql
CREATE DATABASE college;

USE college;

CREATE TABLE departments (
    department_id INT,
    department_name VARCHAR(50) NOT NULL,

    CONSTRAINT pk_departments
        PRIMARY KEY (department_id),

    CONSTRAINT uq_department_name
        UNIQUE (department_name)
);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT,
    student_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT,
    department_id INT,

    CONSTRAINT pk_students
        PRIMARY KEY (student_id),

    CONSTRAINT uq_student_email
        UNIQUE (email),

    CONSTRAINT chk_student_age
        CHECK (age >= 18),

    CONSTRAINT fk_student_department
        FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);
```

This example demonstrates:
- PRIMARY KEY for unique identification.
- UNIQUE for preventing duplicate email addresses.
- NOT NULL for mandatory values.
- CHECK for age validation.
- FOREIGN KEY for connecting students and departments.

---

## 8. Interview Questions

**1. What is a key in SQL?**

A key is a column or combination of columns used to identify records or establish relationships between tables.

**2. What is a constraint?**

A constraint is a rule applied to a table or column to maintain data integrity.

**3. Is PRIMARY KEY a key or a constraint?**

It is both a key concept and a SQL constraint.

**4. Is UNIQUE a key or a constraint?**

It is both a key concept and a SQL constraint.

**5. What is the difference between a primary key and a foreign key?**

A primary key identifies records in its own table. A foreign key references a key in another table to establish a relationship.

**6. Can a table have multiple primary keys?**

No. A table can have only one PRIMARY KEY constraint, but it can contain multiple columns.

**7. Can a table have multiple UNIQUE constraints?**

Yes. A table can have multiple UNIQUE constraints.

**8. What is a composite key?**

A composite key uses two or more columns together to uniquely identify a record.

---

## 9. Quick Revision

| Concept | Remember |
|---|---|
| KEY | Identifies records or establishes relationships |
| CONSTRAINT | Enforces rules on data |
| PRIMARY KEY | Unique + NOT NULL |
| FOREIGN KEY | Connects tables |
| UNIQUE | Prevents duplicate non-NULL values |
| CANDIDATE KEY | Minimal column set that can uniquely identify a row |
| SUPER KEY | Any column set that uniquely identifies a row |
| ALTERNATE KEY | Candidate key not selected as primary key |
| COMPOSITE KEY | Key made from multiple columns |
| INDEX | Helps improve data retrieval speed |

**Final takeaway:**

A KEY helps identify or relate records. A CONSTRAINT enforces rules to maintain data integrity. Some SQL keys, such as PRIMARY KEY and FOREIGN KEY, are also constraints.
