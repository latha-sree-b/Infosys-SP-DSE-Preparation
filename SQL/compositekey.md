# Composite Key in MySQL

## 1. What is a Composite Key?

A **Composite Key** is a key that consists of two or more columns together to uniquely identify each row in a table.

- A single column may not uniquely identify a record.
- Combining two or more columns can uniquely identify a record.
- The combination of columns must contain unique values.

**Definition:**

A Composite Key is a combination of two or more columns that together uniquely identify a record in a database table.

---

## 2. Why Do We Need a Composite Key?

Consider a `student_course` table.

| student_id | course_id | student_name |
|---|---|---|
| 101 | 1 | Rahul |
| 101 | 2 | Rahul |
| 102 | 1 | Priya |
| 102 | 2 | Priya |

Here:

- `student_id` alone is not unique because one student can enroll in multiple courses.
- `course_id` alone is not unique because multiple students can enroll in the same course.
- But the combination of `student_id` and `course_id` is unique.

Therefore, we use a Composite Key.

---

## 3. How to Create a Composite Key?

### Syntax

```sql
CREATE TABLE table_name (
    column1 datatype,
    column2 datatype,
    column3 datatype,
    PRIMARY KEY (column1, column2)
);
```

The `PRIMARY KEY` constraint is defined using multiple columns.

### Example

```sql
CREATE TABLE student_course (
    student_id INT,
    course_id INT,
    student_name VARCHAR(30),
    PRIMARY KEY (student_id, course_id)
);
```

Here:

- `student_id` is the first column of the composite key.
- `course_id` is the second column of the composite key.
- Both columns together uniquely identify each record.

---

## 4. Inserting Records

```sql
INSERT INTO student_course
(student_id, course_id, student_name)
VALUES
(101, 1, 'Rahul'),
(101, 2, 'Rahul'),
(102, 1, 'Priya'),
(102, 2, 'Priya');
```

### Output

| student_id | course_id | student_name |
|---|---|---|
| 101 | 1 | Rahul |
| 101 | 2 | Rahul |
| 102 | 1 | Priya |
| 102 | 2 | Priya |

All records are valid because each combination of `student_id` and `course_id` is unique.

---

## 5. What Happens If We Insert Duplicate Values?

```sql
INSERT INTO student_course
(student_id, course_id, student_name)
VALUES
(101, 1, 'Rahul');
```

**Output:**

```text
ERROR: Duplicate entry for primary key.
```

**Why?**

Because the combination `(101, 1)` already exists in the table.

Even though individual column values can repeat, their combined values cannot repeat.

---

## 6. Important Rules of Composite Key

1. A Composite Key contains two or more columns.
2. Each individual column can contain duplicate values.
3. The combination of all columns must be unique.
4. Composite Primary Key columns cannot contain NULL values.
5. Only one PRIMARY KEY constraint is allowed per table.
6. A Primary Key can consist of multiple columns.
7. Composite Keys are commonly used in mapping and junction tables.

---

## 7. Composite Key vs Single Primary Key

| Single Primary Key | Composite Key |
|---|---|
| Uses one column. | Uses two or more columns. |
| One column uniquely identifies a row. | Combination of columns uniquely identifies a row. |
| Example: student_id | Example: student_id + course_id |
| Simpler to reference. | May require multiple columns when referencing it. |

---

## 8. Composite Key vs Foreign Key

| Composite Key | Foreign Key |
|---|---|
| Combination of multiple columns used as a primary key. | Column or combination of columns that references a key in another table. |
| Uniquely identifies a record. | Establishes a relationship between tables. |
| Does not allow NULL values in its primary key columns. | Can allow NULL values unless restricted by NOT NULL. |
| Example: (student_id, course_id) | Example: student_id referencing students(id). |

**Note:** A Composite Key can also be used as a Composite Foreign Key in another table.

---

## 9. Real-Time Examples

Composite Keys are commonly used in:

1. Student-Course enrollment systems.
2. Employee-Project assignment systems.
3. Order-Product details.
4. Bank account transaction systems.
5. Library book borrowing systems.

For example, in an order details table:

```sql
CREATE TABLE order_details (
    order_id INT,
    product_id INT,
    quantity INT,
    PRIMARY KEY (order_id, product_id)
);
```

Here, the combination of `order_id` and `product_id` uniquely identifies each product entry in an order.

---

## 10. Interview Questions

**Q1. What is a Composite Key?**

A Composite Key is a combination of two or more columns that together uniquely identify a record in a table.

**Q2. Can individual columns in a Composite Key contain duplicate values?**

Yes. Individual columns can contain duplicate values, but their combined values must be unique.

**Q3. Can a Composite Key contain NULL values?**

No. A Composite Primary Key cannot contain NULL values in any of its columns.

**Q4. Can we have multiple Primary Keys in a table?**

No. A table can have only one PRIMARY KEY constraint, but that primary key can contain multiple columns.

**Q5. Can a Composite Key contain three columns?**

Yes. A Composite Key can contain two or more columns.

**Q6. Is a Composite Key always a Primary Key?**

A Composite Key is commonly used to describe a key made of multiple columns. When those columns are declared as the PRIMARY KEY, it is a Composite Primary Key.

---

## 11. Practice Questions

1. Create a `student_subject` table with `student_id` and `subject_id` as a Composite Primary Key.
2. Insert five records into the table.
3. Insert a duplicate combination and observe the error.
4. Insert the same `student_id` with a different `subject_id`.
5. Insert the same `subject_id` with a different `student_id`.

---

## 12. Key Takeaway

**Composite Key = Combination of Two or More Columns**

Remember:

- Individual column values can repeat.
- The combined values must be unique.
- It helps uniquely identify records when one column is not sufficient.
