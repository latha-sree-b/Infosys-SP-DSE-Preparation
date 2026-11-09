# Types of Databases

## 1. What is a Database?

A **database** is an organized collection of data that can be stored, accessed, managed, and updated efficiently.

Examples:
- A college database stores student details.
- A banking database stores customer accounts and transactions.
- An e-commerce database stores products, orders, and customer information.

## 2. Types of Databases

Databases are commonly classified into the following types:

1. Relational Database (RDBMS)
2. NoSQL Database
3. Hierarchical Database
4. Network Database
5. Object-Oriented Database
6. Distributed Database
7. Centralized Database

---

## 3. Relational Database (RDBMS)

A **Relational Database Management System (RDBMS)** stores data in tables consisting of rows and columns.

Each table represents an entity, such as students or employees.

### Example

**Student Table**

| Student_ID | Name | Department |
|---|---|---|
| 101 | Ravi | CSE |
| 102 | Priya | AI&DS |
| 103 | Rahul | ECE |

### Features
- Stores data in tables.
- Uses SQL (Structured Query Language).
- Supports relationships between tables.
- Maintains data integrity using constraints.
- Supports transactions and ACID properties.

### Examples
- MySQL
- PostgreSQL
- Oracle Database
- Microsoft SQL Server

### Best Used For
Banking systems, college management systems, and applications requiring structured data and reliable transactions.

---

## 4. NoSQL Database

A **NoSQL database** stores data using models other than the traditional relational table structure.

It is useful for handling large amounts of flexible or rapidly changing data.

### Types of NoSQL Databases

| Type | Description | Examples |
|---|---|---|
| Document | Stores data as documents, usually JSON-like | MongoDB |
| Key-Value | Stores data as key-value pairs | Redis |
| Column-Family | Stores data in column families | Cassandra |
| Graph | Stores data as nodes and relationships | Neo4j |

### Example: Document Database

```json
{
  "studentId": 101,
  "name": "Ravi",
  "department": "CSE",
  "skills": ["Java", "SQL"]
}
```

### Features
- Flexible data models.
- Supports horizontal scaling.
- Suitable for large and distributed applications.
- Often handles unstructured or semi-structured data efficiently.

### Best Used For
Real-time applications, social networks, product catalogs, and applications with changing data structures.

---

## 5. Hierarchical Database

A hierarchical database organizes data in a **tree-like structure**.

Each child record generally has only one parent.

### Example

```text
College
├── Department
│   ├── Student 1
│   └── Student 2
└── Department
    ├── Student 3
    └── Student 4
```

### Features
- Uses parent-child relationships.
- Supports one-to-many relationships naturally.
- Data is accessed through predefined paths.

### Example
IBM IMS.

---

## 6. Network Database

A network database organizes data as records connected through links.

Unlike a hierarchical database, a record can have multiple parent records.

### Example

```text
Student 1 ─── Course 1
    │             │
    └──────── Course 2

Student 2 ─── Course 1
```

### Features
- Supports many-to-many relationships.
- Uses links or pointers between records.
- More flexible than the hierarchical model.

### Example
Integrated Data Store (IDS).

---

## 7. Object-Oriented Database

An object-oriented database stores data in the form of objects, similar to objects in object-oriented programming languages such as Java.

### Example

```java
class Student {
    int id;
    String name;
}
```

A database can store student objects directly, including their attributes and relationships.

### Features
- Supports objects, classes, and inheritance.
- Stores complex data structures.
- Can reduce the need for object-relational mapping.

### Examples
ObjectDB, db4o.

---

## 8. Distributed Database

A distributed database stores data across multiple computers or locations while presenting it as a unified database.

### Example

```text
              Application
                   |
          Distributed Database
             /           \
       Server 1         Server 2
       Bengaluru        Mumbai
```

### Features
- Data is distributed across multiple locations.
- Supports scalability and availability.
- Can use replication and partitioning.
- Requires coordination between distributed systems.

### Examples
Google Spanner, CockroachDB.

---

## 9. Centralized Database

A centralized database stores data at a single central location.

Multiple users or applications can access the same database.

### Example

```text
Student 1 ──┐
Student 2 ──┼── Central Database
Student 3 ──┘
```

### Features
- Data is managed at one central location.
- Easier to maintain and administer.
- Can become a single point of failure without proper backup and recovery.

---

## 10. SQL vs NoSQL

| Feature | SQL Database | NoSQL Database |
|---|---|---|
| Data model | Tables | Documents, key-value, graph, etc. |
| Schema | Usually predefined | Often flexible |
| Query language | SQL | Varies by database |
| Relationships | Supports joins | Depends on database model |
| Scaling | Commonly vertical; horizontal scaling is also possible | Often designed for horizontal scaling |
| Examples | MySQL, PostgreSQL | MongoDB, Cassandra |

**Important:** SQL and NoSQL are broad categories. The best choice depends on the application's data structure, consistency requirements, scalability, and workload.

---

## 11. Quick Revision

| Database Type | Main Idea |
|---|---|
| Relational | Data stored in tables |
| NoSQL | Flexible, non-relational data models |
| Hierarchical | Tree structure |
| Network | Records connected through links |
| Object-Oriented | Data stored as objects |
| Distributed | Data stored across multiple locations |
| Centralized | Data stored at one central location |

### Interview Question

**Q: What is the difference between SQL and NoSQL databases?**

SQL databases store structured data in tables and typically use predefined schemas. NoSQL databases support different data models, such as documents, key-value pairs, and graphs, and often provide more flexible schemas.

**Q: Which database is used in Java applications?**

Java applications can use both SQL and NoSQL databases. MySQL and PostgreSQL are common relational databases, while MongoDB is a common document database. Java applications can connect to these databases using JDBC, database drivers, or frameworks such as Spring Data.