# Can We Rename a Database in MySQL?

## 1. Introduction

Yes, we can achieve a database rename in MySQL, but **MySQL does not provide a direct `RENAME DATABASE` command**.

For example, the following command is invalid:

```sql
RENAME DATABASE old_db TO new_db;
```

Instead, we create a new database, migrate the tables and data, verify everything, and then delete the old database if it is no longer needed.

---

## 2. Why Doesn't MySQL Support Direct Database Renaming?

MySQL does not provide a supported direct database-renaming command.

A database may contain:
- Tables
- Views
- Stored procedures
- Functions
- Triggers
- Events
- Foreign-key relationships

Renaming only the database name could affect database objects and their references.

Therefore, we use a database migration approach.

---

## 3. Steps to Rename a Database

Suppose we have:

- Old database: `college`
- New database: `university`

### Step 1: Check Existing Databases

```sql
SHOW DATABASES;
```

This displays the databases available to your MySQL user.

### Step 2: Create a New Database

```sql
CREATE DATABASE university;
```

This creates a new database named `university`.

### Step 3: Copy the Table Structure

Suppose the old database contains a table named `students`.

```sql
CREATE TABLE university.students
LIKE college.students;
```

This copies the table structure, including its column definitions and indexes.

It does not copy the table data.

### Step 4: Copy the Table Data

```sql
INSERT INTO university.students
SELECT * FROM college.students;
```

This copies all rows from the old table into the new table.

### Step 5: Verify the New Database

```sql
USE university;

SHOW TABLES;

SELECT * FROM students;
```

Check whether the table structure and data have been copied correctly.

### Step 6: Delete the Old Database

Only after verifying the migration and taking a backup, you can remove the old database.

```sql
DROP DATABASE college;
```

**Warning:** This permanently deletes the old database and its objects. Do not execute this command until you are certain that the migration is complete.

---

## 4. Complete Example

Assume the existing database is `college`.

```sql
-- Step 1: Display databases
SHOW DATABASES;

-- Step 2: Create a new database
CREATE DATABASE university;

-- Step 3: Copy table structure
CREATE TABLE university.students
LIKE college.students;

-- Step 4: Copy table data
INSERT INTO university.students
SELECT * FROM college.students;

-- Step 5: Verify copied data
SELECT * FROM university.students;

-- Step 6: Display tables in the new database
SHOW TABLES FROM university;
```

After verifying all required objects and data, you can delete the old database:

```sql
DROP DATABASE college;
```

---

## 5. Important: Does This Copy Everything?

No. The `CREATE TABLE ... LIKE` and `INSERT ... SELECT` method copies a table's structure and rows, but it does not automatically migrate every database object or dependency.

| Database object | Automatically copied by this method? |
|---|---|
| Table structure | Yes |
| Table data | Yes, using INSERT |
| Indexes | Yes, through CREATE TABLE LIKE |
| Views | No |
| Stored procedures | No |
| Functions | No |
| Triggers | No |
| Events | No |
| Foreign-key relationships | Do not assume they are copied |

For a complete database migration, use a backup and restore approach, such as MySQL `mysqldump`, and verify the restored database before deleting the original.

---

## 6. Difference Between Database and Table Renaming

| Operation | MySQL command |
|---|---|
| Rename a database | No direct command |
| Rename a table | `RENAME TABLE` |
| Rename a column | `ALTER TABLE ... RENAME COLUMN` |
| Modify a column | `ALTER TABLE ... MODIFY COLUMN` |

### Rename a Table

```sql
RENAME TABLE students TO users;
```

### Rename a Column

```sql
ALTER TABLE students
RENAME COLUMN name TO student_name;
```

### Modify a Column

```sql
ALTER TABLE students
MODIFY COLUMN student_name VARCHAR(100);
```

---

## 7. Interview Questions

**1. Can we directly rename a database in MySQL?**

No. MySQL does not provide a direct `RENAME DATABASE` command.

**2. How can we achieve database renaming?**

Create a new database, migrate all required database objects and data, verify the migration, and then remove the old database if appropriate.

**3. Which command is used to rename a table?**

```sql
RENAME TABLE old_table TO new_table;
```

**4. Does `CREATE TABLE ... LIKE` copy table data?**

No. It copies the table structure and indexes, not the rows.

**5. Which command permanently deletes a database?**

```sql
DROP DATABASE database_name;
```

---

## 8. Quick Revision

- MySQL does not support direct database renaming.
- `RENAME DATABASE` is not a valid MySQL command.
- Create a new database to use the desired name.
- Migrate tables, data, and other required objects.
- Verify the new database before deleting the old one.
- Use `DROP DATABASE` carefully because it permanently deletes the database.

**Remember:**

Database migration is the safe way to achieve a database rename in MySQL.
