# What is RDBMS?

## 1. Definition

RDBMS stands for **Relational Database Management System**.

It is software used to store, organize, retrieve, and manage data in the form of related tables.

Each table contains:
- **Rows:** Records
- **Columns:** Attributes or fields

### Example: Student Table

| Student_ID | Name | Age |
|---|---|---|
| 101 | Ravi | 21 |
| 102 | Priya | 22 |
| 103 | Arun | 20 |

Here:
- Each row represents one student.
- Each column represents student information.
- Student_ID can uniquely identify each student.

---

## 2. How Does RDBMS Work?

An RDBMS stores data in multiple tables and connects related tables using keys.

### Example: College Management System

**Students Table**

| Student_ID (PK) | Name |
|---|---|
| 101 | Ravi |
| 102 | Priya |

**Courses Table**

| Course_ID (PK) | Course_Name |
|---|---|
| C01 | Java |
| C02 | Python |

**Enrollments Table**

| Enrollment_ID (PK) | Student_ID (FK) | Course_ID (FK) |
|---|---|---|
| 1 | 101 | C01 |
| 2 | 102 | C02 |

The Enrollments table connects students with courses using foreign keys.

This is why it is called a **Relational Database Management System**.

---

## 3. Important Concepts in RDBMS

### 1. Table
A table stores data in rows and columns.

### 2. Primary Key (PK)
A primary key uniquely identifies every record in a table.

Example:
```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100)
);
```

### 3. Foreign Key (FK)
A foreign key creates a relationship between two tables.

Example:
```sql
CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY,
    student_id INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);
```

### 4. Relationships

There are three commonly taught relationship types:

- One-to-One (1:1)
- One-to-Many (1:M)
- Many-to-Many (M:M)

### 5. Constraints

Constraints enforce rules on table data.

Examples:
- PRIMARY KEY
- FOREIGN KEY
- NOT NULL
- UNIQUE
- CHECK
- DEFAULT

---

## 4. Features of RDBMS

1. Stores data in tables.
2. Supports relationships between tables.
3. Uses SQL to communicate with the database.
4. Supports primary and foreign keys.
5. Provides data integrity through constraints.
6. Supports transactions.
7. Helps reduce data duplication through normalization.
8. Supports multiple users and concurrent operations.

---

## 5. Advantages of RDBMS

- **Data organization:** Stores information systematically in tables.
- **Data integrity:** Maintains accurate and consistent data.
- **Reduced duplication:** Normalization helps avoid unnecessary repeated data.
- **Security:** Supports permissions and access control.
- **Relationships:** Connects related information using keys.
- **Transactions:** Supports reliable operations using ACID properties.

## 6. Disadvantages of RDBMS

- Complex database design for large applications.
- Schema changes may require careful planning.
- Scaling across multiple servers can be more complex than in some NoSQL systems.
- Not always the best fit for highly flexible or unstructured data.

---

## 7. Examples of RDBMS

| RDBMS | Description |
|---|---|
| MySQL | Popular open-source relational database |
| PostgreSQL | Open-source relational database with advanced features |
| Oracle Database | Enterprise relational database |
| Microsoft SQL Server | Microsoft's relational database |
| SQLite | Lightweight, embedded relational database |

---

## 8. DBMS vs RDBMS

| Feature | DBMS | RDBMS |
|---|---|---|
| Full Form | Database Management System | Relational Database Management System |
| Data storage | Depends on the DBMS | Primarily tables |
| Relationships | Not necessarily based on relational tables | Supports relationships between tables |
| Keys | Depends on the system | Supports primary and foreign keys |
| Data integrity | Depends on the system | Enforces relational constraints |
| Examples | File-based database systems and other DBMS types | MySQL, PostgreSQL, Oracle |

**Note:** RDBMS is a type of DBMS. The distinction in this table is conceptual; modern DBMS products can share many features.

---

## 9. SQL vs MySQL vs DBMS vs RDBMS

| Term | Meaning | Example |
|---|---|---|
| SQL | Query language | SELECT, INSERT |
| DBMS | Software for managing databases | Database management systems |
| RDBMS | DBMS based on relational tables | MySQL, PostgreSQL |
| MySQL | A specific RDBMS product | MySQL Server |

### Understand the Relationship

```text
                 DBMS
                   |
          +--------+--------+
          |                 |
       Other DBMS         RDBMS
                            |
                   +--------+--------+
                   |        |        |
                 MySQL  PostgreSQL  Oracle
                            |
                         SQL
                    (Query Language)
```

---

## 10. Interview Questions

**Q1. What is RDBMS?**

RDBMS stands for Relational Database Management System. It stores data in tables and establishes relationships between tables using keys.

**Q2. Is MySQL an RDBMS?**

Yes. MySQL is a relational database management system.

**Q3. Is SQL an RDBMS?**

No. SQL is a query language used to communicate with relational databases.

**Q4. What is the difference between DBMS and RDBMS?**

DBMS is the general category of database management software, whereas RDBMS specifically organizes data using relational tables and relationships.

**Q5. What are primary keys and foreign keys?**

A primary key uniquely identifies a record in a table. A foreign key references a key in another table to establish a relationship.

---

## 11. Final Summary

Remember:

- **DBMS** → Software used to manage databases.
- **RDBMS** → DBMS that manages relational data using tables.
- **SQL** → Language used to communicate with relational databases.
- **MySQL** → A specific RDBMS that uses SQL.
