# ROLLBACK in SQL

## 1. What is ROLLBACK?

ROLLBACK is a **TCL (Transaction Control Language)** command in SQL.

It is used to undo changes made during the current transaction before those changes are committed.

In simple words:

**ROLLBACK = Undo the uncommitted database changes.**

### Example

Imagine you accidentally update a student's age from 21 to 30.

If you have not committed the transaction, you can use ROLLBACK to undo that update.

---

## 2. Syntax of ROLLBACK

```sql
ROLLBACK;
```

---

## 3. How Does ROLLBACK Work?

Consider a students table.

### Initial Table

| student_id | student_name | age |
|---|---|---|
| 101 | Ravi | 21 |
| 102 | Priya | 22 |
| 103 | Arun | 20 |

### Step 1: Start a Transaction

```sql
START TRANSACTION;
```

This starts a transaction.

### Step 2: Update a Record

```sql
UPDATE students
SET age = 30
WHERE student_id = 101;
```

The age of Ravi is now 30 within the current transaction.

### Step 3: Check the Updated Data

```sql
SELECT * FROM students;
```

Result within the same session:

| student_id | student_name | age |
|---|---|---|
| 101 | Ravi | 30 |
| 102 | Priya | 22 |
| 103 | Arun | 20 |

### Step 4: Execute ROLLBACK

```sql
ROLLBACK;
```

The uncommitted update is undone.

### Step 5: Check the Data Again

```sql
SELECT * FROM students;
```

Result:

| student_id | student_name | age |
|---|---|---|
| 101 | Ravi | 21 |
| 102 | Priya | 22 |
| 103 | Arun | 20 |

**Result:** Ravi's age is restored to 21.

---

## 4. Real-Time Example – Banking Transaction

Imagine Ravi wants to transfer ₹1,000 to Priya.

Initially:

| Account Holder | Account Number | Balance |
|---|---|---:|
| Ravi | 101 | ₹5,000 |
| Priya | 102 | ₹3,000 |

### Step 1: Start Transaction

```sql
START TRANSACTION;
```

### Step 2: Deduct Money from Ravi

```sql
UPDATE accounts
SET balance = balance - 1000
WHERE account_id = 101;
```

Ravi's balance becomes ₹4,000 within the transaction.

### Step 3: Add Money to Priya

```sql
UPDATE accounts
SET balance = balance + 1000
WHERE account_id = 102;
```

Priya's balance becomes ₹4,000 within the transaction.

### Step 4: If an Error Occurs

Suppose the transfer fails before it is completed.

Execute:

```sql
ROLLBACK;
```

### Final Result

| Account Holder | Original Balance | Balance After ROLLBACK |
|---|---:|---:|
| Ravi | ₹5,000 | ₹5,000 |
| Priya | ₹3,000 | ₹3,000 |

Both changes are undone.

**Important:** In a real banking application, the application should validate the transfer and commit only when all required operations succeed.

---

## 5. Difference Between COMMIT and ROLLBACK

| COMMIT | ROLLBACK |
|---|---|
| Saves transaction changes | Undoes uncommitted changes |
| Makes changes permanent | Restores the previous committed state |
| Cannot normally undo changes afterward using ROLLBACK | Works before COMMIT |
| TCL command | TCL command |
| Used when the transaction succeeds | Used when the transaction fails or must be cancelled |

### COMMIT Example

```sql
START TRANSACTION;

UPDATE students
SET age = 25
WHERE student_id = 101;

COMMIT;
```

The updated age is saved.

### ROLLBACK Example

```sql
START TRANSACTION;

UPDATE students
SET age = 30
WHERE student_id = 101;

ROLLBACK;
```

The update is undone.

---

## 6. ROLLBACK with SAVEPOINT

SAVEPOINT allows us to roll back to a particular point inside a transaction instead of undoing the entire transaction.

### Example

```sql
START TRANSACTION;

UPDATE students
SET age = 25
WHERE student_id = 101;

SAVEPOINT sp1;

UPDATE students
SET age = 28
WHERE student_id = 102;

SAVEPOINT sp2;

UPDATE students
SET age = 30
WHERE student_id = 103;

ROLLBACK TO SAVEPOINT sp1;

COMMIT;
```

### Explanation

1. Start the transaction.
2. Update Ravi's age to 25.
3. Create savepoint `sp1`.
4. Update Priya's age to 28.
5. Create savepoint `sp2`.
6. Update Arun's age to 30.
7. Roll back to `sp1`.
8. Priya's and Arun's updates are undone.
9. Ravi's update is committed.

### Final Result

| Student | Age |
|---|---:|
| Ravi | 25 |
| Priya | Original age |
| Arun | Original age |

---

## 7. Important Rules of ROLLBACK in MySQL

1. ROLLBACK is a TCL command.
2. It undoes uncommitted transactional changes.
3. It works with transactional storage engines such as InnoDB.
4. Once COMMIT is executed, a normal ROLLBACK cannot undo those changes.
5. ROLLBACK without a SAVEPOINT undoes the current transaction.
6. ROLLBACK TO SAVEPOINT undoes changes made after the specified savepoint.
7. DDL statements such as CREATE, ALTER, DROP, and TRUNCATE generally cause implicit commits in MySQL.
8. ROLLBACK cannot restore changes made by statements that have already been committed.

---

## 8. Important Difference: DELETE, TRUNCATE and ROLLBACK

### DELETE with ROLLBACK

For an InnoDB table:

```sql
START TRANSACTION;

DELETE FROM students
WHERE student_id = 101;

ROLLBACK;
```

The deleted record is restored because the DELETE operation was not committed.

### TRUNCATE with ROLLBACK

```sql
START TRANSACTION;

TRUNCATE TABLE students;

ROLLBACK;
```

In MySQL, TRUNCATE causes an implicit commit. Therefore, the normal ROLLBACK cannot restore the deleted records.

---

## 9. Interview Questions

**Q1. What is ROLLBACK in SQL?**

ROLLBACK is a TCL command used to undo uncommitted changes made during a transaction.

**Q2. What is the difference between COMMIT and ROLLBACK?**

COMMIT saves transaction changes permanently, whereas ROLLBACK undoes uncommitted changes.

**Q3. Can we use ROLLBACK after COMMIT?**

No. Once changes are committed, a normal ROLLBACK cannot undo them.

**Q4. Can we rollback a DELETE statement?**

Yes, if the table uses a transactional storage engine such as InnoDB and the DELETE has not been committed.

**Q5. Can we rollback TRUNCATE in MySQL?**

No. TRUNCATE causes an implicit commit in MySQL.

**Q6. What is the purpose of SAVEPOINT?**

SAVEPOINT creates a point inside a transaction so that we can roll back part of the transaction.

**Q7. Which category does ROLLBACK belong to?**

TCL – Transaction Control Language.

---

## 10. Practice Questions

Try to predict the final output.

**Question 1:**

```sql
START TRANSACTION;

UPDATE students
SET age = 25
WHERE student_id = 101;

ROLLBACK;

SELECT * FROM students;
```

What will be Ravi's age if his original age was 21?

**Question 2:**

```sql
START TRANSACTION;

UPDATE students
SET age = 25
WHERE student_id = 101;

COMMIT;

ROLLBACK;

SELECT * FROM students;
```

Will Ravi's age return to 21?

**Question 3:**

```sql
START TRANSACTION;

DELETE FROM students
WHERE student_id = 102;

ROLLBACK;

SELECT * FROM students;
```

Will Priya's record be restored?

---

## 11. Final Summary

| Command | Meaning |
|---|---|
| START TRANSACTION | Starts a transaction |
| COMMIT | Saves changes |
| ROLLBACK | Undoes uncommitted changes |
| SAVEPOINT | Creates a rollback point |
| ROLLBACK TO SAVEPOINT | Undoes changes after a savepoint |

### Remember This

```sql
START TRANSACTION;

UPDATE students
SET age = 25
WHERE student_id = 101;

-- Undo the update
ROLLBACK;

-- Or save the update
-- COMMIT;
```

**Interview-ready answer:**

ROLLBACK is a Transaction Control Language (TCL) command used to undo uncommitted changes made during a transaction. It helps maintain data consistency when an operation fails or needs to be cancelled.
