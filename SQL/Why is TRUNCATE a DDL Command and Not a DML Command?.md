# Why is TRUNCATE a DDL Command and Not a DML Command?

## 1. Introduction

In SQL, commands are classified into different categories based on their purpose.

The two important categories are:

1. **DDL – Data Definition Language**
2. **DML – Data Manipulation Language**

A common interview question is:

**Why is TRUNCATE classified as DDL instead of DML when it deletes all records from a table?**

To understand this, we need to understand the difference between DDL and DML.

---

## 2. What is DDL?

DDL stands for **Data Definition Language**.

It is used to create, modify, and remove database structures.

DDL mainly deals with the structure of database objects.

### DDL Commands

| Command | Purpose |
|---|---|
| CREATE | Creates a database or table |
| ALTER | Modifies table structure |
| DROP | Removes a database object |
| TRUNCATE | Removes all records while keeping the table structure |
| RENAME | Changes the name of a database object |

### Example

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    age INT
);
```

Here, CREATE creates the structure of the students table.

---

## 3. What is DML?

DML stands for **Data Manipulation Language**.

It is used to insert, update, and delete records stored inside database tables.

DML mainly deals with table data.

### DML Commands

| Command | Purpose |
|---|---|
| INSERT | Inserts new records |
| UPDATE | Modifies existing records |
| DELETE | Deletes records |

### Example

```sql
INSERT INTO students
VALUES (101, 'Ravi', 21);
```

Here, INSERT adds a new record to the existing table.

---

## 4. What is TRUNCATE?

TRUNCATE is a SQL command used to remove all records from a table while preserving its structure.

### Syntax

```sql
TRUNCATE TABLE students;
```

### Before TRUNCATE

| student_id | student_name | age |
|---|---|---|
| 101 | Ravi | 21 |
| 102 | Priya | 22 |
| 103 | Arun | 20 |

### After TRUNCATE

| student_id | student_name | age |
|---|---|---|
| No records | | |

The table structure remains, but all records are removed.

---

## 5. Why is TRUNCATE Classified as DDL?

There are several reasons why TRUNCATE is classified as DDL in MySQL and traditional SQL command classifications.

### Reason 1: It Works Like a Table-Level Operation

DELETE removes records using row-level conditions.

TRUNCATE removes all records from the table as a whole.

Example of DELETE:

```sql
DELETE FROM students
WHERE student_id = 101;
```

This removes a specific record.

Example of TRUNCATE:

```sql
TRUNCATE TABLE students;
```

This removes all records without selecting individual rows.

### Reason 2: It Does Not Support WHERE

DELETE supports the WHERE condition.

```sql
DELETE FROM students
WHERE age > 20;
```

TRUNCATE does not support WHERE.

```sql
TRUNCATE TABLE students;
```

It always removes all records.

### Reason 3: It Causes an Implicit Commit in MySQL

In MySQL, TRUNCATE is treated as a DDL statement and causes an implicit commit.

Example:

```sql
START TRANSACTION;

DELETE FROM students;

ROLLBACK;
```

If the table uses InnoDB, the DELETE can be rolled back before COMMIT.

But:

```sql
START TRANSACTION;

TRUNCATE TABLE students;

ROLLBACK;
```

The TRUNCATE operation causes an implicit commit in MySQL. Therefore, the normal ROLLBACK cannot restore the deleted records.

### Reason 4: It Resets AUTO_INCREMENT in MySQL

TRUNCATE resets the AUTO_INCREMENT counter in MySQL.

Example:

```sql
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100)
);
```

Insert records:

```sql
INSERT INTO students (student_name)
VALUES ('Ravi'), ('Priya'), ('Arun');
```

The generated IDs are:

| student_id | student_name |
|---|---|
| 1 | Ravi |
| 2 | Priya |
| 3 | Arun |

Now execute:

```sql
TRUNCATE TABLE students;
```

Insert a new record:

```sql
INSERT INTO students (student_name)
VALUES ('Kiran');
```

In this example, the new student ID starts again at 1.

---

## 6. Difference Between DELETE and TRUNCATE

| Feature | DELETE | TRUNCATE |
|---|---|---|
| Category | DML | DDL |
| Purpose | Removes records | Removes all records |
| WHERE condition | Supported | Not supported |
| Selected rows | Yes | No |
| All records | Yes, without WHERE | Yes |
| Table structure | Preserved | Preserved |
| AUTO_INCREMENT | Not reset normally | Reset in MySQL |
| MySQL transaction | Can be rolled back before COMMIT with InnoDB | Causes implicit commit |
| Operation | Row-level deletion | Table-level operation |

---

## 7. Important Concept: Does TRUNCATE Modify Table Structure?

No. TRUNCATE does not remove the table structure.

For example:

```sql
TRUNCATE TABLE students;
```

The following remain:

- Table name
- Column names
- Data types
- Primary key definition
- Other table constraints

Only the records are removed.

So, why is it DDL?

Because SQL classification is based on the nature and transaction behavior of the command, not only on whether it changes table structure.

TRUNCATE is treated as a table-level DDL operation in MySQL.

---

## 8. Easy Way to Remember

| Command | Category | Main Purpose |
|---|---|---|
| INSERT | DML | Add records |
| UPDATE | DML | Modify records |
| DELETE | DML | Remove selected or all records |
| TRUNCATE | DDL | Empty the entire table |
| DROP | DDL | Remove the entire table |

### Simple Memory Trick

- **DML:** Works with individual records and their values.
- **DDL:** Works with database objects and table-level operations.

---

## 9. Interview Questions

**Q1. Why is TRUNCATE a DDL command?**

TRUNCATE is classified as DDL because it is treated as a table-level operation rather than a row-by-row data manipulation operation. In MySQL, it causes an implicit commit and resets the AUTO_INCREMENT counter.

**Q2. Does TRUNCATE delete the table structure?**

No. It removes all records but preserves the table structure.

**Q3. Can we use WHERE with TRUNCATE?**

No. TRUNCATE removes all records and does not support WHERE.

**Q4. Can we rollback TRUNCATE in MySQL?**

No. TRUNCATE causes an implicit commit in MySQL, so a normal ROLLBACK cannot undo it.

**Q5. What is the difference between DELETE and TRUNCATE?**

DELETE is a DML command that removes records and supports WHERE. TRUNCATE is a DDL command that removes all records without WHERE.

**Q6. Does TRUNCATE reset AUTO_INCREMENT?**

Yes. In MySQL, TRUNCATE resets the AUTO_INCREMENT counter.

---

## 10. Final Summary

```text
DDL – Data Definition Language
     CREATE
     ALTER
     DROP
     TRUNCATE
     RENAME

DML – Data Manipulation Language
     INSERT
     UPDATE
     DELETE
```

### Final Interview Answer

TRUNCATE is classified as a DDL command because it performs a table-level operation rather than deleting individual rows. In MySQL, it causes an implicit commit and resets the AUTO_INCREMENT counter. Although it removes all records, it preserves the table structure.

**Remember:** TRUNCATE does not delete the table. It only empties the table.
