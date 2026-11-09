# Database Technologies for Java Developers

## Learning Roadmap

As a Java developer, we need to understand how applications store, retrieve, update, and manage data.

The following technologies are important:

1. MySQL – Relational Database
2. JDBC – Connecting Java with Databases
3. Spring Data JPA – Simplifying Database Operations
4. MongoDB – NoSQL Database
5. Redis – Caching and Fast Data Access

---

# 1. MySQL – Relational Database

## What is MySQL?

MySQL is a Relational Database Management System (RDBMS) that stores data in tables consisting of rows and columns.

It uses SQL (Structured Query Language) to communicate with the database.

### Example

Imagine we are developing a student management application.

We need to store:
- Student ID.
- Student name.
- Email.
- Department.
- Age.

We can create a table to store this information.

### Student Table

| Student_ID | Name | Email | Department |
|---|---|---|---|
| 101 | Ravi | ravi@gmail.com | CSE |
| 102 | Priya | priya@gmail.com | AI&DS |
| 103 | Rahul | rahul@gmail.com | ECE |

### Important MySQL Concepts

**1. Database**

A database is an organized collection of related data.

```sql
CREATE DATABASE college;
```

**2. Table**

A table stores data in rows and columns.

```sql
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    department VARCHAR(50)
);
```

**3. Insert**

Used to insert new records.

```sql
INSERT INTO students
VALUES (101, 'Ravi', 'ravi@gmail.com', 'CSE');
```

**4. Select**

Used to retrieve data.

```sql
SELECT * FROM students;
```

**5. Update**

Used to modify existing records.

```sql
UPDATE students
SET department = 'AI&DS'
WHERE student_id = 101;
```

**6. Delete**

Used to delete records.

```sql
DELETE FROM students
WHERE student_id = 101;
```

### Important Topics to Learn in MySQL

| Topic | Purpose |
|---|---|
| DDL | Create and modify database structures |
| DML | Insert, update and delete data |
| DQL | Retrieve data |
| DCL | Manage permissions |
| TCL | Manage transactions |
| Primary Key | Uniquely identifies a record |
| Foreign Key | Establishes relationships between tables |
| Joins | Retrieve related data from multiple tables |
| Constraints | Maintain data validity |
| Normalization | Reduce unnecessary data redundancy |
| Transactions | Manage reliable database operations |

### Why Do Java Developers Need MySQL?

Suppose we build a banking application using Java.

Java handles the application logic, but customer account information and transaction records need permanent storage.

MySQL stores that information.

**Remember:**

MySQL = Where application data is stored.

---

# 2. JDBC – Java Database Connectivity

## What is JDBC?

JDBC stands for **Java Database Connectivity**.

It is a Java API that allows Java applications to communicate with relational databases such as MySQL.

In simple words:

JDBC acts as a bridge between Java and the database.

### Why Do We Need JDBC?

Consider a Java application that accepts student information.

The application receives:
- Student ID.
- Student name.
- Email.

Now we need to save this information in MySQL.

Java cannot directly execute database operations without a database connectivity mechanism.

JDBC provides the required API.

### Architecture

```text
       Java Application
              |
              |
             JDBC
              |
              |
       MySQL JDBC Driver
              |
              |
          MySQL DB
              |
              |
       Student Records
```

### How JDBC Works

1. Java application establishes a database connection.
2. JDBC driver communicates with MySQL.
3. Java sends SQL queries.
4. MySQL executes the queries.
5. Results are returned to Java.

### Important JDBC Components

| Component | Purpose |
|---|---|
| DriverManager | Establishes database connections |
| Connection | Represents a database connection |
| Statement | Executes SQL statements |
| PreparedStatement | Executes parameterized SQL statements |
| ResultSet | Stores query results |
| SQLException | Handles database-related errors |

### JDBC Example

First, add the MySQL JDBC driver dependency to your Java project.

Then:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/college";
        String username = "root";
        String password = "your_password";

        String query =
            "INSERT INTO students VALUES (?, ?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(
                url, username, password
            );

            PreparedStatement ps = con.prepareStatement(query)
        ) {

            ps.setInt(1, 101);
            ps.setString(2, "Ravi");
            ps.setString(3, "ravi@gmail.com");
            ps.setString(4, "CSE");

            int rows = ps.executeUpdate();

            System.out.println(rows + " record inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Explanation

- `DriverManager.getConnection()` establishes a connection with MySQL.
- `PreparedStatement` prepares the SQL query.
- `setInt()` and `setString()` supply values to the query.
- `executeUpdate()` executes INSERT, UPDATE, or DELETE operations.

### Why Learn JDBC?

- Understand how Java communicates with databases.
- Learn how SQL queries are executed from Java.
- Understand database connections.
- Learn PreparedStatement and SQL injection prevention.
- Build a foundation for Spring Data JPA.

**Remember:**

JDBC = Bridge between Java and relational databases.

---

# 3. Spring Data JPA – Database Operations Using Spring Boot

## What is JPA?

JPA stands for **Java Persistence API**. It is a Java specification that defines how Java objects can be mapped to relational database tables.

Spring Data JPA is a Spring project that simplifies working with JPA repositories.

Hibernate is a commonly used JPA implementation.

### Why Do We Need Spring Data JPA?

Consider inserting 100 student records.

Using JDBC, we may need to write SQL queries and handle connections and result sets manually.

Spring Data JPA reduces much of this repetitive code.

It allows us to work with Java objects and repository methods.

### JDBC vs Spring Data JPA

| JDBC | Spring Data JPA |
|---|---|
| Lower-level database API | Higher-level data access abstraction |
| Write SQL manually for most operations | Provides repository methods |
| Handle ResultSet manually | Maps database records to Java objects |
| More repetitive code | Less repetitive code |
| More control over SQL execution | Simplifies common database operations |

### Important Concepts

**1. Entity**

An entity is a Java class mapped to a database table.

```java
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    private String email;

    private String department;
}
```

**2. Repository**

A repository provides methods to perform database operations.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

}
```

Here:
- `Student` represents the entity.
- `Long` represents the primary key type.
- `JpaRepository` provides ready-made database methods.

**3. Service**

The service layer contains business logic.

```java
@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }
}
```

### Common Repository Methods

| Method | Purpose |
|---|---|
| save() | Insert or update |
| findById() | Find record by ID |
| findAll() | Retrieve all records |
| deleteById() | Delete by ID |
| existsById() | Check whether a record exists |
| count() | Count records |

### How Spring Data JPA Works

```text
       Java Application
              |
              |
        Spring Boot
              |
              |
        Service Layer
              |
              |
       Repository Layer
              |
              |
         JPA / Hibernate
              |
              |
             JDBC
              |
              |
            MySQL
```

**Important:** Spring Data JPA does not replace JDBC completely. Underneath, Hibernate commonly uses JDBC to communicate with relational databases.

**Remember:**

Spring Data JPA = Simplifies database operations using Java objects and repositories.

---

# 4. MongoDB – NoSQL Database

## What is MongoDB?

MongoDB is a NoSQL document-oriented database.

Unlike MySQL, which stores data in relational tables, MongoDB stores data as BSON documents (a binary representation of JSON-like documents).

### Why Do We Need MongoDB?

Consider an e-commerce application.

We sell different types of products:

**Mobile Phone**

- Name.
- Brand.
- RAM.
- Storage.
- Battery.

**Shirt**

- Name.
- Brand.
- Size.
- Color.
- Material.

These products have different attributes.

In MongoDB, we can represent them using flexible document structures.

### Example: MongoDB Document

```json
{
  "_id": 101,
  "name": "Samsung Galaxy",
  "category": "Mobile",
  "specifications": {
    "ram": "8GB",
    "storage": "128GB",
    "battery": "5000mAh"
  }
}
```

Another product:

```json
{
  "_id": 102,
  "name": "Cotton Shirt",
  "category": "Clothing",
  "specifications": {
    "size": "L",
    "color": "Blue",
    "material": "Cotton"
  }
}
```

Notice that the two documents have different fields.

### MySQL vs MongoDB

| MySQL | MongoDB |
|---|---|
| Relational database | NoSQL document database |
| Tables | Collections |
| Rows | Documents |
| Columns | Fields |
| SQL | MongoDB Query API |
| Schema generally defined in advance | Flexible document schema |

### Important MongoDB Terms

| MySQL | MongoDB |
|---|---|
| Database | Database |
| Table | Collection |
| Row | Document |
| Column | Field |
| Primary Key | `_id` field |
| JOIN | `$lookup` aggregation stage |

### Basic MongoDB Operations

**Insert**

```javascript
db.students.insertOne({
    name: "Ravi",
    age: 21,
    department: "CSE"
});
```

**Find**

```javascript
db.students.find();
```

**Update**

```javascript
db.students.updateOne(
    { name: "Ravi" },
    { $set: { age: 22 } }
);
```

**Delete**

```javascript
db.students.deleteOne({
    name: "Ravi"
});
```

### Why Learn MongoDB?

- Understand NoSQL databases.
- Handle flexible document structures.
- Work with JSON-like data.
- Build applications with changing data requirements.
- Understand how Java applications connect to NoSQL databases.

**Remember:**

MongoDB = Stores data in flexible documents instead of traditional relational tables.

---

# 5. Redis – Caching and Fast Data Access

## What is Redis?

Redis is an in-memory data store commonly used for caching, session management, and other fast data access use cases.

It stores frequently accessed data in memory, which can reduce repeated database queries.

### Why Do We Need Redis?

Imagine an e-commerce application.

10,000 users request the same popular product.

Without caching:

```text
10,000 Requests
       |
       |
      MySQL
       |
       |
  Repeated Queries
```

The database handles repeated requests for the same information.

With Redis:

```text
10,000 Requests
       |
       |
      Redis
       |
       |
  Cached Product Data
       |
       |
   MySQL (if cache miss)
```

Redis can return cached product information without querying MySQL every time.

### What is Caching?

Caching means temporarily storing frequently accessed information so that it can be retrieved faster later.

### Real-World Example

Suppose a product's details are stored in MySQL.

**First Request:**

1. User requests product ID 101.
2. Application checks Redis.
3. Product is not available in Redis.
4. Application retrieves product details from MySQL.
5. Application stores the result in Redis.
6. Application returns the result to the user.

**Second Request:**

1. User requests product ID 101 again.
2. Application checks Redis.
3. Product is already available.
4. Redis returns the cached data.
5. MySQL does not need to be queried for that request.

### Basic Redis Commands

**Set**

```redis
SET student:101 Ravi
```

Stores a value with a key.

**Get**

```redis
GET student:101
```

Retrieves the value.

**Set with Expiration**

```redis
SET student:101 Ravi EX 60
```

Stores the value for 60 seconds.

After 60 seconds, the key expires.

### Advantages of Redis

1. Fast in-memory data access.
2. Reduces repeated database queries.
3. Helps improve application response time.
4. Supports expiration of cached data.
5. Useful for session and temporary data management.

### Disadvantages of Redis

1. Memory can be expensive.
2. Cached data can become outdated if not managed properly.
3. Requires cache invalidation strategies.
4. Adds another component to maintain.

**Remember:**

Redis = Fast access to frequently used data through caching.

---

# 6. Comparison of All Five Technologies

| Technology | Category | Main Purpose |
|---|---|---|
| MySQL | RDBMS | Store relational data |
| JDBC | Java API | Connect Java with databases |
| Spring Data JPA | Spring data access framework | Simplify relational database operations |
| MongoDB | NoSQL database | Store document-oriented data |
| Redis | In-memory data store | Caching and fast data access |

---

# 7. How Do All Five Work Together?

Let's consider a Java-based e-commerce application.

### Application Architecture

```text
                 USER
                   |
                   |
             FRONTEND
          (HTML CSS JavaScript)
                   |
                   |
              REST API
                   |
                   |
          JAVA SPRING BOOT
                   |
         +---------+----------+
         |         |          |
         |         |          |
       MySQL    MongoDB      Redis
         |         |          |
      Orders    Products     Cache
      Customers Details      Sessions
         |
         |
        JDBC
         |
         |
     Hibernate / JPA
         |
         |
       Database
```

### Understanding the Flow

1. The user sends a request from the frontend.
2. Spring Boot receives the request through a REST API.
3. The service layer processes the business logic.
4. Spring Data JPA uses Hibernate and JDBC to communicate with MySQL.
5. MongoDB stores flexible product information.
6. Redis provides cached information when available.
7. The backend returns the response to the frontend.

**Note:** JDBC and Spring Data JPA are not separate databases. They are technologies used to access databases. MySQL, MongoDB, and Redis are storage systems.

---

# 8. Recommended Learning Order for Java Developers

| Order | Technology | What to Learn |
|---|---|---|
| 1 | MySQL | SQL, joins, keys, constraints, normalization, transactions |
| 2 | JDBC | Connections, PreparedStatement, ResultSet |
| 3 | Spring Data JPA | Entity, Repository, Service, relationships |
| 4 | MongoDB | Database, collection, documents, CRUD |
| 5 | Redis | Keys, values, expiration, caching |

---

# 9. Interview Questions

**Q1. What is the difference between JDBC and JPA?**

JDBC is a low-level Java API for database connectivity and SQL execution. JPA is a specification for mapping Java objects to relational database tables.

**Q2. Is Hibernate the same as JPA?**

No. JPA is a specification, whereas Hibernate is an implementation of that specification.

**Q3. Is Spring Data JPA a database?**

No. It is a Spring framework project that simplifies database access through repository abstractions.

**Q4. Is MongoDB a relational database?**

No. MongoDB is a NoSQL document-oriented database.

**Q5. Is Redis a database?**

Yes. Redis is an in-memory data store that can also provide persistence. It is commonly used for caching.

**Q6. Why do we use Redis with MySQL?**

MySQL stores permanent relational data, while Redis can cache frequently accessed data and reduce repeated queries to MySQL.

**Q7. Can one Java application connect to MySQL and MongoDB?**

Yes. Java applications can connect to both using their respective drivers and Spring Data integrations.

---

# 10. Final Revision

Remember these five statements:

- **MySQL:** Stores data in relational tables.
- **JDBC:** Connects Java applications to databases.
- **Spring Data JPA:** Simplifies relational database operations.
- **MongoDB:** Stores data in flexible documents.
- **Redis:** Provides fast data access through in-memory storage and caching.

**Final Concept:**

A Java application uses these technologies for different responsibilities. They work together to build applications that can store, manage, retrieve, and process data efficiently.
