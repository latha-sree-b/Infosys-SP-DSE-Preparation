# Difference Between ALTER TABLE and CHANGE COLUMN in MySQL

## 1. What is ALTER TABLE?

`ALTER TABLE` is a DDL (Data Definition Language) command used to modify the structure of an existing table.

Using `ALTER TABLE`, we can:

1. Add a column.
2. Drop a column.
3. Rename a column.
4. Change a column's data type.
5. Modify column constraints.
6. Rename a table.

### Syntax

```sql
ALTER TABLE table_name
operation;
```

### Example 1: Add a Column

```sql
ALTER TABLE students
ADD COLUMN age INT;
```

Adds a new column named `age`.

### Example 2: Drop a Column

```sql
ALTER TABLE students
DROP COLUMN age;
```

Removes the `age` column.

### Example 3: Rename a Column

```sql
ALTER TABLE students
RENAME COLUMN name TO student_name;
```

Renames `name` to `student_name`.

### Example 4: Modify a Column

```sql
ALTER TABLE students
MODIFY COLUMN age BIGINT;
```

Changes the data type of `age` from `INT` to `BIGINT`.

---

## 2. What is CHANGE COLUMN?

`CHANGE COLUMN` is an operation used with `ALTER TABLE`.

It is used to:

1. Rename an existing column.
2. Change its data type.
3. Modify its other column attributes.

### Syntax

```sql
ALTER TABLE table_name
CHANGE COLUMN old_column_name new_column_name new_data_type;
```

### Example

Initially, our table contains:

| Column Name | Data Type |
|---|---|
| id | INT |
| name | VARCHAR(50) |
| email | VARCHAR(100) |

Now execute:

```sql
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;
```

### What happens?

**Operation 1: Rename the column**

```text
id → student_id
```

**Operation 2: Change the data type**

```text
INT → BIGINT
```

### Final Table Structure

| Column Name | Data Type |
|---|---|
| student_id | BIGINT |
| name | VARCHAR(50) |
| email | VARCHAR(100) |

---

## 3. Difference Between ALTER TABLE and CHANGE COLUMN

| ALTER TABLE | CHANGE COLUMN |
|---|---|
| Main SQL command used to modify table structure | An operation used inside ALTER TABLE |
| Can add columns | Can rename columns |
| Can drop columns | Can change data types |
| Can rename columns using RENAME COLUMN | Can modify column attributes |
| Can modify column definitions using MODIFY COLUMN | Requires old name, new name and complete new definition |
| Used for many table structure operations | Used specifically to change a column definition |

**Important:** ALTER TABLE is the main command. CHANGE COLUMN is one of the operations that can be performed using ALTER TABLE.

---

## 4. Difference Between CHANGE COLUMN and MODIFY COLUMN

This is another important interview question.

| CHANGE COLUMN | MODIFY COLUMN |
|---|---|
| Can rename a column | Cannot rename a column |
| Can change data type | Can change data type |
| Can modify column attributes | Can modify column attributes |
| Requires old name and new name | Requires only the existing column name |

### CHANGE COLUMN Example

```sql
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;
```

Here:
- Column name changes from `id` to `student_id`.
- Data type changes from `INT` to `BIGINT`.

### MODIFY COLUMN Example

```sql
ALTER TABLE students
MODIFY COLUMN id BIGINT;
```

Here:
- Column name remains `id`.
- Data type changes from `INT` to `BIGINT`.

---

## 5. Difference Between RENAME COLUMN and CHANGE COLUMN

| RENAME COLUMN | CHANGE COLUMN |
|---|---|
| Changes only the column name | Can change the name and definition |
| Does not require a data type | Requires the complete new column definition |
| Simple syntax | More detailed syntax |

### RENAME COLUMN

```sql
ALTER TABLE students
RENAME COLUMN name TO student_name;
```

### CHANGE COLUMN

```sql
ALTER TABLE students
CHANGE COLUMN name student_name VARCHAR(100);
```

Both rename `name` to `student_name`.

---

## 6. Complete Practical Example

### Step 1: Create Database

```sql
CREATE DATABASE college;

USE college;
```

### Step 2: Create Table

```sql
CREATE TABLE students (
    id INT,
    name VARCHAR(50),
    email VARCHAR(100)
);
```

### Step 3: Add a Column

```sql
ALTER TABLE students
ADD COLUMN age INT;
```

### Step 4: Rename a Column Using CHANGE

```sql
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;
```

### Step 5: Change Data Type Using MODIFY

```sql
ALTER TABLE students
MODIFY COLUMN age TINYINT;
```

### Step 6: Rename a Column Using RENAME COLUMN

```sql
ALTER TABLE students
RENAME COLUMN name TO student_name;
```

### Step 7: Display Final Structure

```sql
DESC students;
```

---

## 7. Interview Questions

**1. Is ALTER TABLE a DDL command?**

Yes. ALTER TABLE is a DDL command.

**2. Is CHANGE COLUMN a separate SQL command?**

No. CHANGE COLUMN is an operation used inside ALTER TABLE.

**3. Can CHANGE COLUMN rename a column?**

Yes.

**4. Can CHANGE COLUMN change the data type?**

Yes.

**5. Can MODIFY COLUMN rename a column?**

No. MODIFY COLUMN changes the column definition without renaming it.

**6. What is the difference between ALTER TABLE and CHANGE COLUMN?**

ALTER TABLE is the main command used to modify table structure. CHANGE COLUMN is one operation performed through ALTER TABLE to change a column's name and/or definition.

---

## 8. Quick Revision

```sql
-- ALTER TABLE: Add a column
ALTER TABLE students
ADD COLUMN age INT;

-- ALTER TABLE: Drop a column
ALTER TABLE students
DROP COLUMN age;

-- CHANGE COLUMN: Rename and change data type
ALTER TABLE students
CHANGE COLUMN id student_id BIGINT;

-- MODIFY COLUMN: Change data type only
ALTER TABLE students
MODIFY COLUMN student_id INT;

-- RENAME COLUMN: Rename only
ALTER TABLE students
RENAME COLUMN name TO student_name;
```

## Conclusion

Remember this simple concept:

- **ALTER TABLE** = Main command to modify table structure.
- **CHANGE COLUMN** = Rename a column and/or modify its definition.
- **MODIFY COLUMN** = Modify a column definition without renaming.
- **RENAME COLUMN** = Change only the column name.

`ALTER TABLE` is the command; `CHANGE COLUMN` is one of its operations.
