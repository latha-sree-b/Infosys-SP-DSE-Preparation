## Relationship Between PRIMARY KEY and FOREIGN KEY in SQL

## 1. Introduction

PRIMARY KEY and FOREIGN KEY are two important constraints in SQL that help establish relationships between tables.

- **PRIMARY KEY:** Uniquely identifies each record in a table.
- **FOREIGN KEY:** Creates a relationship by referencing a key in another table.

The PRIMARY KEY is commonly defined in the parent table, and the FOREIGN KEY is defined in the child table.

## 2. How Are They Related?

The PRIMARY KEY of one table can be referenced by the FOREIGN KEY of another table.

For example, consider a college database:

1. `departments` table stores department details.
2. `students` table stores student details.

Each student belongs to a department.

The `department_id` in the departments table is the PRIMARY KEY, while `department_id` in the students table is the FOREIGN KEY.

## 3. Practical Example

### Step 1: Create the Parent Table

```sql
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);
```

| department_id (PK) | department_name |
|---:|---|
| 101 | CSE |
| 102 | ECE |
| 103 | ISE |

Here, `department_id` is the PRIMARY KEY.

### Step 2: Create the Child Table

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    department_id INT,

    FOREIGN KEY (department_id)
    REFERENCES departments(department_id)
);
```

| student_id (PK) | student_name | department_id (FK) |
|---:|---|---:|
| 1 | Latha | 101 |
| 2 | Ravi | 102 |
| 3 | Meera | 101 |
| 4 | Arun | 103 |

Here, `department_id` is the FOREIGN KEY.

## 4. Visual Representation

```text
       PARENT TABLE
        departments
    ---------------------
    department_id (PK)
    department_name
    ---------------------
           |
           |
           | References
           |
           v
        CHILD TABLE
          students
    ---------------------
    student_id (PK)
    student_name
    department_id (FK)
    ---------------------
```

The FOREIGN KEY in the child table references the PRIMARY KEY in the parent table.

## 5. How Does the Relationship Work?

### Case 1: Valid Data

```sql
INSERT INTO departments
VALUES (101, 'CSE');

INSERT INTO students
VALUES (1, 'Latha', 101);
```

**Result:** Successful.

Why?

Because department ID `101` exists in the parent table.

### Case 2: Invalid Data

```sql
INSERT INTO students
VALUES (2, 'Ravi', 999);
```

**Result:** Foreign-key constraint error.

Why?

Because department ID `999` does not exist in the parent table.

The FOREIGN KEY prevents invalid references.

## 6. Relationship Between PRIMARY KEY and FOREIGN KEY

| PRIMARY KEY | FOREIGN KEY |
|---|---|
| Uniquely identifies a record | Establishes a relationship |
| Defined in the parent table in this example | Defined in the child table |
| Does not allow NULL | Can allow NULL |
| Does not allow duplicate values | Allows duplicate values |
| One PRIMARY KEY constraint per table | Multiple FOREIGN KEY constraints can exist in a table |
| Parent key | References the parent key |

## 7. Types of Relationships

### A. One-to-One Relationship (1:1)

One record in the first table is related to at most one record in the second table.

Example: One person has one passport record.

A UNIQUE constraint on the foreign key can enforce this relationship.

### B. One-to-Many Relationship (1:N)

One record in the parent table can be related to multiple records in the child table.

Example: One department can have many students.

```text
Department 101
      |
      |------ Student 1
      |
      |------ Student 2
      |
      |------ Student 3
```

This is the relationship in our college example.

### C. Many-to-Many Relationship (M:N)

Many records in one table can be related to many records in another table.

Example:
- One student can enroll in multiple courses.
- One course can have multiple students.

We use a third table called a junction table.

```sql
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,

    PRIMARY KEY (student_id, course_id),

    FOREIGN KEY (student_id)
        REFERENCES students(student_id),

    FOREIGN KEY (course_id)
        REFERENCES courses(course_id)
);
```

Here, the junction table contains two foreign keys and a composite primary key.

## 8. Important Rules

1. A foreign key can reference a PRIMARY KEY in another table.
2. A foreign key can also reference a suitable UNIQUE key.
3. The referenced parent key must be indexed.
4. Parent and child columns must have compatible data types.
5. A foreign key can contain duplicate values.
6. A foreign key can contain NULL if the column allows it.
7. A parent record cannot normally be deleted while child records reference it, unless an appropriate ON DELETE action is defined.

## 9. Interview Questions

**Q1. What is the relationship between PRIMARY KEY and FOREIGN KEY?**

A PRIMARY KEY uniquely identifies a record in the parent table. A FOREIGN KEY references that key from another table to establish a relationship.

**Q2. Can one table's PRIMARY KEY become another table's FOREIGN KEY?**

Yes. This is a common way to establish relationships between tables.

**Q3. Can a FOREIGN KEY contain duplicate values?**

Yes. Multiple child records can reference the same parent record.

**Q4. Can a FOREIGN KEY contain NULL?**

Yes, provided the foreign-key column allows NULL.

**Q5. What happens when we insert a foreign-key value that does not exist in the parent table?**

MySQL rejects the insert because it violates referential integrity.

**Q6. Can a FOREIGN KEY reference a UNIQUE column instead of a PRIMARY KEY?**

Yes, a suitable UNIQUE key can also be referenced.

## 10. Quick Revision

| Concept | Meaning |
|---|---|
| PRIMARY KEY | Uniquely identifies a row |
| FOREIGN KEY | References a key in another table |
| Parent Table | Contains the referenced key |
| Child Table | Contains the foreign key |
| Referential Integrity | Ensures valid relationships |
| One-to-Many | One parent record can have many child records |
| Many-to-Many | Uses a junction table with foreign keys |

### Remember This Simple Concept

**PRIMARY KEY → FOREIGN KEY**

The PRIMARY KEY identifies a record in one table, and the FOREIGN KEY connects another table to that record.

Example:

`departments.department_id (PRIMARY KEY)`

↓

`students.department_id (FOREIGN KEY)`

## 1. Introduction

PRIMARY KEY and FOREIGN KEY are two important constraints in SQL that help establish relationships between tables.

- **PRIMARY KEY:** Uniquely identifies each record in a table.
- **FOREIGN KEY:** Creates a relationship by referencing a key in another table.

The PRIMARY KEY is commonly defined in the parent table, and the FOREIGN KEY is defined in the child table.

## 2. How Are They Related?

The PRIMARY KEY of one table can be referenced by the FOREIGN KEY of another table.

For example, consider a college database:

1. `departments` table stores department details.
2. `students` table stores student details.

Each student belongs to a department.

The `department_id` in the departments table is the PRIMARY KEY, while `department_id` in the students table is the FOREIGN KEY.

## 3. Practical Example

### Step 1: Create the Parent Table

```sql
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);
```

| department_id (PK) | department_name |
|---:|---|
| 101 | CSE |
| 102 | ECE |
| 103 | ISE |

Here, `department_id` is the PRIMARY KEY.

### Step 2: Create the Child Table

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    department_id INT,

    FOREIGN KEY (department_id)
    REFERENCES departments(department_id)
);
```

| student_id (PK) | student_name | department_id (FK) |
|---:|---|---:|
| 1 | Latha | 101 |
| 2 | Ravi | 102 |
| 3 | Meera | 101 |
| 4 | Arun | 103 |

Here, `department_id` is the FOREIGN KEY.

## 4. Visual Representation

```text
       PARENT TABLE
        departments
    ---------------------
    department_id (PK)
    department_name
    ---------------------
           |
           |
           | References
           |
           v
        CHILD TABLE
          students
    ---------------------
    student_id (PK)
    student_name
    department_id (FK)
    ---------------------
```

The FOREIGN KEY in the child table references the PRIMARY KEY in the parent table.

## 5. How Does the Relationship Work?

### Case 1: Valid Data

```sql
INSERT INTO departments
VALUES (101, 'CSE');

INSERT INTO students
VALUES (1, 'Latha', 101);
```

**Result:** Successful.

Why?

Because department ID `101` exists in the parent table.

### Case 2: Invalid Data

```sql
INSERT INTO students
VALUES (2, 'Ravi', 999);
```

**Result:** Foreign-key constraint error.

Why?

Because department ID `999` does not exist in the parent table.

The FOREIGN KEY prevents invalid references.

## 6. Relationship Between PRIMARY KEY and FOREIGN KEY

| PRIMARY KEY | FOREIGN KEY |
|---|---|
| Uniquely identifies a record | Establishes a relationship |
| Defined in the parent table in this example | Defined in the child table |
| Does not allow NULL | Can allow NULL |
| Does not allow duplicate values | Allows duplicate values |
| One PRIMARY KEY constraint per table | Multiple FOREIGN KEY constraints can exist in a table |
| Parent key | References the parent key |

## 7. Types of Relationships

### A. One-to-One Relationship (1:1)

One record in the first table is related to at most one record in the second table.

Example: One person has one passport record.

A UNIQUE constraint on the foreign key can enforce this relationship.

### B. One-to-Many Relationship (1:N)

One record in the parent table can be related to multiple records in the child table.

Example: One department can have many students.

```text
Department 101
      |
      |------ Student 1
      |
      |------ Student 2
      |
      |------ Student 3
```

This is the relationship in our college example.

### C. Many-to-Many Relationship (M:N)

Many records in one table can be related to many records in another table.

Example:
- One student can enroll in multiple courses.
- One course can have multiple students.

We use a third table called a junction table.

```sql
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,

    PRIMARY KEY (student_id, course_id),

    FOREIGN KEY (student_id)
        REFERENCES students(student_id),

    FOREIGN KEY (course_id)
        REFERENCES courses(course_id)
);
```

Here, the junction table contains two foreign keys and a composite primary key.

## 8. Important Rules

1. A foreign key can reference a PRIMARY KEY in another table.
2. A foreign key can also reference a suitable UNIQUE key.
3. The referenced parent key must be indexed.
4. Parent and child columns must have compatible data types.
5. A foreign key can contain duplicate values.
6. A foreign key can contain NULL if the column allows it.
7. A parent record cannot normally be deleted while child records reference it, unless an appropriate ON DELETE action is defined.

## 9. Interview Questions

**Q1. What is the relationship between PRIMARY KEY and FOREIGN KEY?**

A PRIMARY KEY uniquely identifies a record in the parent table. A FOREIGN KEY references that key from another table to establish a relationship.

**Q2. Can one table's PRIMARY KEY become another table's FOREIGN KEY?**

Yes. This is a common way to establish relationships between tables.

**Q3. Can a FOREIGN KEY contain duplicate values?**

Yes. Multiple child records can reference the same parent record.

**Q4. Can a FOREIGN KEY contain NULL?**

Yes, provided the foreign-key column allows NULL.

**Q5. What happens when we insert a foreign-key value that does not exist in the parent table?**

MySQL rejects the insert because it violates referential integrity.

**Q6. Can a FOREIGN KEY reference a UNIQUE column instead of a PRIMARY KEY?**

Yes, a suitable UNIQUE key can also be referenced.

## 10. Quick Revision

| Concept | Meaning |
|---|---|
| PRIMARY KEY | Uniquely identifies a row |
| FOREIGN KEY | References a key in another table |
| Parent Table | Contains the referenced key |
| Child Table | Contains the foreign key |
| Referential Integrity | Ensures valid relationships |
| One-to-Many | One parent record can have many child records |
| Many-to-Many | Uses a junction table with foreign keys |

### Remember This Simple Concept

**PRIMARY KEY → FOREIGN KEY**

The PRIMARY KEY identifies a record in one table, and the FOREIGN KEY connects another table to that record.

Example:

`departments.department_id (PRIMARY KEY)`

↓

`students.department_id (FOREIGN KEY)`
