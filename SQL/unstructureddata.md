Skip to content
latha-sree-b
Infosys-SP-DSE-Preparation
Repository navigation
Code
Issues
Pull requests
Agents
Actions
Projects
Wiki
Security and quality
Insights
Settings
Comparing changes
Choose two branches to see what’s changed or to start a new pull request. If you need to, you can also  or learn more about diff comparisons.
...
There isn’t anything to compare.
main and master are entirely different commit histories.

 Showing  with 344 additions and 0 deletions.
 258 changes: 258 additions & 0 deletions258  
TypesofDataBase.md
Original file line number	Diff line number	Diff line change
@@ -0,0 +1,258 @@
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
 86 changes: 86 additions & 0 deletions86  
unstructured.md
Original file line number	Diff line number	Diff line change
@@ -0,0 +1,86 @@
# Why Do We Store Data in an Unstructured Format?

## 1. What is Unstructured Data?

**Unstructured data** is data that does not follow a fixed table structure or predefined schema.

It can contain text, images, videos, audio, documents, and other types of information.

### Examples
- Images and videos
- PDF documents
- Emails and messages
- Audio recordings
- Social media posts

## 2. Why Do We Store Data in an Unstructured Format?

We store data in an unstructured format when the data is complex, flexible, or does not fit naturally into rows and columns.

### 1. To Store Different Types of Data

A relational database is designed mainly for structured data.

Unstructured storage can handle different types of content, such as images, videos, documents, and audio files.

**Example:** A hospital stores patient details in a relational database but stores X-rays and medical reports as files.

### 2. To Handle Flexible Data

Unstructured data does not require a fixed schema.

Different records can contain different information.

**Example:** A social media post may contain text, an image, a video, or a combination of them.

### 3. To Store Large Files Efficiently

Large files such as videos, images, and audio recordings are often stored in file systems or object storage rather than directly inside relational database tables.

**Example:** YouTube stores video files separately from information about the video, such as its title and upload date.

### 4. To Preserve Original Information

Some data is valuable in its original form and may lose information if converted into tables.

**Example:** A recorded customer support call preserves the customer's voice, tone, and conversation.

### 5. To Support Big Data and AI Applications

Unstructured data is useful for machine learning, natural language processing, computer vision, and other AI applications.

**Example:** An AI system can analyze images to identify objects or analyze text to understand customer feedback.

---

## 3. Structured vs Unstructured Data

| Feature | Structured Data | Unstructured Data |
|---|---|---|
| Organization | Fixed structure | No fixed table structure |
| Storage | Tables | Files, object storage, or databases |
| Examples | Student records, bank transactions | Images, videos, documents |
| Searching | Often easier with SQL | May require specialized tools |
| Schema | Usually predefined | Flexible or absent |

## 4. Real-World Example: E-Commerce Application

An e-commerce application may store different types of data.

| Data | Storage |
|---|---|
| Customer name and ID | Relational database |
| Product price and stock | Relational database |
| Product images | Object storage |
| Customer reviews | Relational or document database |
| Product demonstration videos | Object storage |

The application can store the product details in a database and save the image or video files separately. The database then stores the file location.

## 5. Important Point

Unstructured data does not mean that the data has no organization at all.

For example, an image file has a format such as JPEG or PNG, and a video file has a format such as MP4. However, the content itself does not naturally fit into a relational table.

**Conclusion:** We store data in unstructured formats to handle different types of information, support flexible data, preserve original content, and manage large files efficiently.
Footer
© 2026 GitHub, Inc.
Footer navigation
Terms
Privacy
Security
Status
Community
Docs
Contact
Manage cookies
Do not share my personal information
