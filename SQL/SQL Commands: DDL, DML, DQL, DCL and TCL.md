# SQL Commands: DDL, DML, DQL, DCL and TCL

## 1. What is SQL?

SQL stands for **Structured Query Language**.

It is used to communicate with relational databases such as MySQL, PostgreSQL, Oracle, and SQL Server.

Using SQL, we can:
- Create databases and tables.
- Insert records.
- Retrieve records.
- Update existing records.
- Delete records.
- Control database permissions.
- Manage transactions.

SQL commands are classified into five major categories:

1. DDL – Data Definition Language
2. DML – Data Manipulation Language
3. DQL – Data Query Language
4. DCL – Data Control Language
5. TCL – Transaction Control Language

---

# 2. DDL – Data Definition Language

## What is DDL?

DDL stands for **Data Definition Language**.

It is used to define, create, and modify the structure of database objects such as databases, tables, and indexes.

In simple words:

DDL is used to create or change the structure of a database.

### Important DDL Commands

| Command | Purpose |
|---|---|
| CREATE | Creates database objects |
| ALTER | Modifies the structure of existing objects |
| DROP | Deletes database objects |
| TRUNCATE | Removes all records from a table |
| RENAME | Changes the name of a database object |

### 1. CREATE

Used to create a database or table.

**Create Database**

```sql
CREATE DATABASE college;
```

**Create Table**

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    department VARCHAR(50)
);
```

Explanation:
- `CREATE DATABASE` creates a database.
- `CREATE TABLE` creates a table.
- `student_id`, `name`, `age`, and `department` are columns.

### 2. ALTER

Used to modify the structure of an existing table.

**Add a Column**

```sql
ALTER TABLE students
ADD email VARCHAR(100);
```

**Modify a Column**

```sql
ALTER TABLE students
MODIFY name VARCHAR(150);
```

**Drop a Column**

```sql
ALTER TABLE students
DROP COLUMN email;
```

### 3. DROP

Used to permanently remove a database object.

**Drop Table**

```sql
DROP TABLE students;
```

**Drop Database**

```sql
DROP DATABASE college;
```

Important: DROP removes the table or database structure along with its stored data.

### 4. TRUNCATE

Used to remove all records from a table while keeping the table structure.

```sql
TRUNCATE TABLE students;
```

Before:

| ID | Name |
|---|---|
| 101 | Ravi |
| 102 | Priya |
| 103 | Rahul |

After:

| ID | Name |
|---|---|
| | |
 
The table still exists, but its records are removed.

### 5. RENAME

Used to change the name of a table.

```sql
RENAME TABLE students TO student_details;
```

### Advantages of DDL

- Helps create database structures.
- Allows modification of existing tables.
- Makes database organization easier.
- Supports table and index management.

### Important Point

DDL mainly deals with the **structure of the database**, not individual records.

---

# 3. DML – Data Manipulation Language

## What is DML?

DML stands for **Data Manipulation Language**.

It is used to insert, modify, and delete records stored inside database tables.

In simple words:

DML is used to work with the actual data inside tables.

### Important DML Commands

| Command | Purpose |
|---|---|
| INSERT | Adds new records |
| UPDATE | Modifies existing records |
| DELETE | Deletes selected records |

### 1. INSERT

Used to insert new records into a table.

```sql
INSERT INTO students
(student_id, name, age, department)
VALUES
(101, 'Ravi', 21, 'CSE');
```

Result:

| student_id | name | age | department |
|---|---|---|---|
| 101 | Ravi | 21 | CSE |

### 2. UPDATE

Used to modify existing records.

```sql
UPDATE students
SET age = 22
WHERE student_id = 101;
```

Result:

| student_id | name | age | department |
|---|---|---|---|
| 101 | Ravi | 22 | CSE |

Important: Always use a suitable WHERE condition when you want to update specific records.

Without WHERE, all records may be updated.

### 3. DELETE

Used to remove specific records from a table.

```sql
DELETE FROM students
WHERE student_id = 101;
```

This deletes the student whose ID is 101.

Important: Without WHERE, all records in the table will be deleted.

### Advantages of DML

- Allows insertion of new records.
- Helps modify existing information.
- Allows removal of unwanted records.
- Helps maintain updated database information.

### Important Point

DML mainly deals with the **actual records stored inside tables**.

---

# 4. DQL – Data Query Language

## What is DQL?

DQL stands for **Data Query Language**.

It is used to retrieve or fetch data from database tables.

In simple words:

DQL is used to read data from a database.

### Important DQL Command

| Command | Purpose |
|---|---|
| SELECT | Retrieves records from a table |

### 1. SELECT

Used to retrieve all records.

```sql
SELECT * FROM students;
```

The `*` means all columns.

### Retrieve Specific Columns

```sql
SELECT name, age
FROM students;
```

### Retrieve Records Using WHERE

```sql
SELECT *
FROM students
WHERE department = 'CSE';
```

### Retrieve Records Using ORDER BY

```sql
SELECT *
FROM students
ORDER BY age DESC;
```

- ASC → Ascending order.
- DESC → Descending order.

### Retrieve Records Using LIMIT

```sql
SELECT *
FROM students
LIMIT 5;
```

Returns a maximum of five records.

### Retrieve Records Using DISTINCT

```sql
SELECT DISTINCT department
FROM students;
```

Returns unique department names.

### Advantages of DQL

- Retrieves required information.
- Filters records using conditions.
- Sorts data.
- Supports aggregate functions such as COUNT, SUM, AVG, MIN, and MAX.
- Helps generate reports.

### Important Point

DQL mainly deals with **reading and retrieving data** without changing the stored records.

---

# 5. DCL – Data Control Language

## What is DCL?

DCL stands for **Data Control Language**.

It is used to manage user permissions and access privileges in a database.

In simple words:

DCL controls who can access the database and what operations they are allowed to perform.

### Important DCL Commands

| Command | Purpose |
|---|---|
| GRANT | Gives permissions to users |
| REVOKE | Removes previously granted permissions |

### 1. GRANT

Used to provide permissions to a database user.

```sql
GRANT SELECT, INSERT
ON college.students
TO 'student_user'@'localhost';
```

Explanation:

The user can:
- View student records.
- Insert new student records.

The user cannot automatically update or delete records.

### 2. REVOKE

Used to remove permissions from a user.

```sql
REVOKE INSERT
ON college.students
FROM 'student_user'@'localhost';
```

Now the user no longer has the INSERT permission granted by that privilege.

### Advantages of DCL

- Controls database access.
- Improves database security.
- Restricts unauthorized operations.
- Allows different permissions for different users.

### Real-World Example

Consider a college management system.

| User | Permission |
|---|---|
| Admin | SELECT, INSERT, UPDATE, DELETE |
| Faculty | SELECT, UPDATE (as authorized) |
| Student | SELECT (as authorized) |

### Important Point

DCL mainly deals with **database security and user permissions**.

---

# 6. TCL – Transaction Control Language

## What is TCL?

TCL stands for **Transaction Control Language**.

It is used to manage transactions in a database.

A transaction is a group of database operations treated as one logical unit of work.

In simple words:

TCL helps decide whether database changes should be permanently saved or undone.

### Important TCL Commands

| Command | Purpose |
|---|---|
| COMMIT | Permanently saves transaction changes |
| ROLLBACK | Undoes uncommitted transaction changes |
| SAVEPOINT | Creates a point to which we can roll back |

### 1. COMMIT

Used to permanently save changes made in the current transaction.

```sql
START TRANSACTION;

INSERT INTO students
VALUES (101, 'Ravi', 21, 'CSE');

COMMIT;
```

After COMMIT, the transaction's changes are saved.

### 2. ROLLBACK

Used to undo changes made in the current uncommitted transaction.

```sql
START TRANSACTION;

UPDATE students
SET age = 25
WHERE student_id = 101;

ROLLBACK;
```

The update is undone, provided it has not already been committed.

### 3. SAVEPOINT

Used to create a named point within a transaction.

```sql
START TRANSACTION;

INSERT INTO students
VALUES (101, 'Ravi', 21, 'CSE');

SAVEPOINT point1;

INSERT INTO students
VALUES (102, 'Priya', 22, 'AI&DS');

ROLLBACK TO SAVEPOINT point1;

COMMIT;
```

Explanation:

1. First student record is inserted.
2. A savepoint named `point1` is created.
3. Second student record is inserted.
4. Rollback to `point1` undoes the second insertion.
5. COMMIT saves the first insertion.

### Advantages of TCL

- Maintains transaction consistency.
- Helps undo unwanted changes.
- Supports reliable database operations.
- Helps manage multiple SQL operations as one transaction.

### Real-World Example: Banking Application

Suppose Ravi transfers ₹1,000 to Priya.

Two operations must happen:

1. Deduct ₹1,000 from Ravi's account.
2. Add ₹1,000 to Priya's account.

If the second operation fails, the first operation should also be undone.

```sql
START TRANSACTION;

UPDATE accounts
SET balance = balance - 1000
WHERE account_id = 101;

UPDATE accounts
SET balance = balance + 1000
WHERE account_id = 102;

COMMIT;
```

If an error occurs before COMMIT, the application can execute ROLLBACK.

This helps maintain transaction consistency.

---

# 7. Comparison of DDL, DML, DQL, DCL and TCL

| Category | Full Form | Main Purpose | Commands |
|---|---|---|---|
| DDL | Data Definition Language | Defines database structure | CREATE, ALTER, DROP, TRUNCATE, RENAME |
| DML | Data Manipulation Language | Modifies table records | INSERT, UPDATE, DELETE |
| DQL | Data Query Language | Retrieves data | SELECT |
| DCL | Data Control Language | Controls user permissions | GRANT, REVOKE |
| TCL | Transaction Control Language | Manages transactions | COMMIT, ROLLBACK, SAVEPOINT |

---

# 8. Easy Way to Remember

| Category | Remember As |
|---|---|
| DDL | Structure |
| DML | Modify data |
| DQL | Read data |
| DCL | Permissions |
| TCL | Transactions |

### Simple Real-World Example

Imagine a college management database.

- DDL → Create the students table.
- DML → Insert a new student.
- DQL → Retrieve student details.
- DCL → Give faculty permission to view student details.
- TCL → Commit or roll back student record changes.

---

# 9. Important Interview Questions

**Q1. What is the difference between DDL and DML?**

DDL manages database structures, whereas DML manages the records stored inside those structures.

**Q2. What is the difference between DELETE, TRUNCATE, and DROP?**

- DELETE removes selected or all records.
- TRUNCATE removes all records while keeping the table structure.
- DROP removes the table structure and its data.

**Q3. What is the difference between DELETE and TRUNCATE?**

DELETE is a DML command and supports filtering with WHERE. TRUNCATE is generally classified as DDL and removes all records without a WHERE condition.

**Q4. What is the difference between COMMIT and ROLLBACK?**

COMMIT saves transaction changes, whereas ROLLBACK undoes uncommitted transaction changes.

**Q5. What is a SAVEPOINT?**

SAVEPOINT creates a named point inside a transaction, allowing us to roll back part of the transaction.

**Q6. What is the difference between GRANT and REVOKE?**

GRANT provides database permissions, whereas REVOKE removes granted permissions.

**Q7. Which category does SELECT belong to?**

SELECT is commonly classified under DQL.

---

# 10. Important Notes for MySQL

1. DDL statements such as CREATE, ALTER, DROP, and TRUNCATE generally cause implicit commits in MySQL.
2. DELETE, UPDATE, and INSERT can be rolled back when used in an active transaction with a transactional storage engine such as InnoDB, before COMMIT.
3. DQL is a commonly used educational classification; SELECT is also sometimes grouped under DML in broader SQL classifications.
4. Transaction behavior depends on the database system and storage engine.

---

# 11. Final Revision

Remember these five concepts:

- DDL → Defines the database structure.
- DML → Manipulates the records.
- DQL → Retrieves the records.
- DCL → Controls user permissions.
- TCL → Controls database transactions.

**One-line interview answer:**

SQL commands are classified into DDL, DML, DQL, DCL, and TCL based on their purpose: defining structures, manipulating data, retrieving data, controlling permissions, and managing transactions.
