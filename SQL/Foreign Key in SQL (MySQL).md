# Foreign Key in SQL (MySQL)

## 1. What Is a Foreign Key?

A **FOREIGN KEY** is a constraint in SQL that establishes a relationship between two tables.

It ensures that a value in one table matches an existing value in another table.

A foreign key helps maintain **referential integrity**, which means preventing invalid relationships between tables.

### Simple Example

Consider a college database.

We have two tables:

1. `departments` – stores department details.
2. `students` – stores student details.

A student belongs to a department.

Instead of storing the department name repeatedly, we store its `department_id` in the students table.

The `department_id` in the students table acts as a FOREIGN KEY.

---

## 2. Parent Table and Child Table

### Parent Table

The table containing the referenced key is called the parent table.

```sql
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);
```

### Child Table

The table containing the foreign key is called the child table.

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    department_id INT,
    FOREIGN KEY (department_id)
        REFERENCES departments(department_id)
);
```

Here:

- `departments` is the parent table.
- `students` is the child table.
- `department_id` in departments is the PRIMARY KEY.
- `department_id` in students is the FOREIGN KEY.

---

## 3. How Does a Foreign Key Work?

### Step 1: Insert Data into the Parent Table

```sql
INSERT INTO departments
VALUES
(101, 'CSE'),
(102, 'ECE'),
(103, 'ISE');
```

### Step 2: Insert Data into the Child Table

```sql
INSERT INTO students
VALUES
(1, 'Latha', 101),
(2, 'Ravi', 102),
(3, 'Meera', 103);
```

These records are valid because department IDs 101, 102, and 103 exist in the parent table.

### Step 3: Try Inserting an Invalid Department ID

```sql
INSERT INTO students
VALUES (4, 'Arun', 999);
```

**Result:** MySQL generates a foreign-key constraint error because department ID 999 does not exist in the parent table.

This is how a foreign key prevents invalid relationships.

---

## 4. Foreign Key Syntax

```sql
CREATE TABLE child_table (
    column_name DATA_TYPE,
    FOREIGN KEY (column_name)
        REFERENCES parent_table(parent_column)
);
```

Example:

```sql
FOREIGN KEY (department_id)
REFERENCES departments(department_id);
```

---

## 5. Foreign Key with a Constraint Name

We can give a name to a foreign-key constraint.

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    department_id INT,

    CONSTRAINT fk_student_department
    FOREIGN KEY (department_id)
    REFERENCES departments(department_id)
);
```

Here, `fk_student_department` is the foreign-key constraint name.

Giving meaningful names makes constraints easier to identify and manage.

---

## 6. ON DELETE and ON UPDATE

These options define what happens to child records when a referenced parent record is deleted or updated.

| Option | Meaning |
|---|---|
| CASCADE | Automatically deletes or updates matching child records |
| SET NULL | Sets the child foreign-key value to NULL |
| RESTRICT | Prevents the parent change when child records reference it |
| NO ACTION | In MySQL, behaves like RESTRICT |

### ON DELETE CASCADE

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    department_id INT,

    FOREIGN KEY (department_id)
    REFERENCES departments(department_id)
    ON DELETE CASCADE
);
```

If a department is deleted, its related student records are also deleted.

### ON UPDATE CASCADE

```sql
FOREIGN KEY (department_id)
REFERENCES departments(department_id)
ON UPDATE CASCADE;
```

If the referenced department ID changes, the corresponding foreign-key values are automatically updated.

### ON DELETE SET NULL

```sql
FOREIGN KEY (department_id)
REFERENCES departments(department_id)
ON DELETE SET NULL;
```

If a department is deleted, the related students' `department_id` becomes NULL.

**Important:** The child foreign-key column must allow NULL for `SET NULL` to work.

---

## 7. How to Add a Foreign Key Using ALTER TABLE

We can add a foreign key after creating a table.

```sql
ALTER TABLE students
ADD CONSTRAINT fk_student_department
FOREIGN KEY (department_id)
REFERENCES departments(department_id);
```

The existing values in the child table must satisfy the relationship.

---

## 8. How to Remove a Foreign Key

First, identify the constraint name.

```sql
SHOW CREATE TABLE students;
```

Then remove it:

```sql
ALTER TABLE students
DROP FOREIGN KEY fk_student_department;
```

This removes the foreign-key constraint, but it does not delete the `department_id` column.

---

## 9. Difference Between PRIMARY KEY and FOREIGN KEY

| PRIMARY KEY | FOREIGN KEY |
|---|---|
| Uniquely identifies each row | Establishes a relationship between tables |
| Does not allow NULL | Can allow NULL |
| Does not allow duplicate values | Can contain duplicate values |
| One primary key constraint per table | Multiple foreign-key constraints are allowed |
| Identifies a record | References a key in another table |

---

## 10. Important Rules of Foreign Key

1. A foreign key references a key in another table, commonly a PRIMARY KEY or UNIQUE key.
2. The referenced and referencing columns must have compatible data types.
3. The parent key must be indexed.
4. A foreign key can contain duplicate values.
5. A foreign key can contain NULL if the column allows it.
6. MySQL InnoDB supports foreign-key constraints.
7. A parent record generally cannot be deleted while matching child records exist unless an appropriate ON DELETE action is specified.

---

## 11. Real-World Examples

| Parent Table | Child Table | Foreign Key |
|---|---|---|
| Departments | Students | department_id |
| Customers | Orders | customer_id |
| Categories | Products | category_id |
| Employees | Attendance | employee_id |
| Users | Payments | user_id |

For example, in an online shopping application:

- One customer can place many orders.
- `customer_id` is the primary key in the customers table.
- `customer_id` is the foreign key in the orders table.

---

## 12. Interview Questions

**1. What is a foreign key?**

A foreign key is a constraint that connects two tables and maintains referential integrity.

**2. What are parent and child tables?**

The parent table contains the referenced key, while the child table contains the foreign key.

**3. Can a foreign key contain duplicate values?**

Yes. Multiple child records can reference the same parent record.

**4. Can a foreign key contain NULL?**

Yes, if the foreign-key column allows NULL.

**5. Can we create multiple foreign keys in one table?**

Yes. A table can have multiple foreign-key constraints.

**6. What is ON DELETE CASCADE?**

It automatically deletes related child records when the referenced parent record is deleted.

**7. What happens if we insert a foreign-key value that does not exist in the parent table?**

MySQL rejects the insert because it violates the foreign-key constraint.

---

## 13. Quick Revision

- FOREIGN KEY connects two tables.
- Parent table contains the referenced key.
- Child table contains the foreign key.
- PRIMARY KEY identifies a record.
- FOREIGN KEY establishes a relationship.
- CASCADE automatically propagates specified changes.
- SET NULL sets the child foreign-key value to NULL.
- RESTRICT prevents deletion or modification that would break the relationship.

**Remember:**

A PRIMARY KEY identifies a record, whereas a FOREIGN KEY connects records between tables.
