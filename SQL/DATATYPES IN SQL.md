# MySQL Data Types

## 1. What Are Data Types in MySQL?

A **data type** specifies what kind of value a column can store in a database table.

For example:
- `INT` stores whole numbers.
- `VARCHAR` stores text.
- `DATE` stores dates.
- `DECIMAL` stores exact decimal numbers.
- `BOOLEAN` stores true or false values.

**Example:**

```sql
CREATE TABLE students (
    student_id INT,
    student_name VARCHAR(100),
    age INT,
    percentage DECIMAL(5, 2),
    date_of_birth DATE
);
```

Here, each column has a different data type based on the kind of information it stores.

---

## 2. Types of Data Types in MySQL

MySQL data types are mainly classified into five categories:

1. Numeric Data Types
2. String Data Types
3. Date and Time Data Types
4. Boolean Data Type
5. Spatial Data Types

---

## 3. Numeric Data Types

Numeric data types are used to store numbers.

They are divided into two categories:

- Integer types – Store whole numbers.
- Fixed-point and floating-point types – Store decimal numbers.

### 3.1 Integer Data Types

| Data Type | Description | Storage |
|---|---|---|
| TINYINT | Very small integer | 1 byte |
| SMALLINT | Small integer | 2 bytes |
| MEDIUMINT | Medium-sized integer | 3 bytes |
| INT / INTEGER | Standard integer | 4 bytes |
| BIGINT | Large integer | 8 bytes |

**Example:**

```sql
CREATE TABLE employees (
    employee_id INT,
    age TINYINT,
    experience SMALLINT,
    salary_grade BIGINT
);
```

**Important: Signed vs Unsigned**

- `SIGNED` allows negative and positive values.
- `UNSIGNED` allows only zero and positive values.

```sql
CREATE TABLE products (
    product_id INT UNSIGNED,
    quantity INT UNSIGNED
);
```

For example, an `INT UNSIGNED` can store values from 0 to 4,294,967,295.

### 3.2 Decimal and Floating-Point Data Types

These data types are used to store numbers containing decimal values.

| Data Type | Description |
|---|---|
| DECIMAL(M,D) | Stores exact decimal values |
| NUMERIC(M,D) | Synonym for DECIMAL |
| FLOAT | Stores approximate decimal values |
| DOUBLE | Stores approximate decimal values with greater precision than FLOAT |

**DECIMAL example:**

```sql
CREATE TABLE bank_accounts (
    account_id INT,
    balance DECIMAL(10, 2)
);
```

Here:
- `10` = Maximum total number of digits.
- `2` = Number of digits after the decimal point.

Example: `12345678.90`

**FLOAT and DOUBLE example:**

```sql
CREATE TABLE measurements (
    temperature FLOAT,
    distance DOUBLE
);
```

**Important:**

Use `DECIMAL` for financial values such as salary, account balance, and product prices because it stores exact decimal values.

Use `FLOAT` or `DOUBLE` for scientific calculations and approximate measurements.

---

## 4. String Data Types

String data types are used to store characters, words, sentences, and large text.

### 4.1 CHAR

- Stores fixed-length strings.
- Maximum length is 255 characters.
- Suitable for values that have a consistent length.

```sql
CREATE TABLE employees (
    employee_code CHAR(6)
);
```

Example:

```sql
INSERT INTO employees VALUES ('EMP101');
```

### 4.2 VARCHAR

- Stores variable-length strings.
- Maximum length is 65,535 bytes, subject to row-size and character-set limits.
- Suitable for names, email addresses, and phone numbers.

```sql
CREATE TABLE students (
    student_name VARCHAR(100),
    email VARCHAR(150)
);
```

### CHAR vs VARCHAR

| CHAR | VARCHAR |
|---|---|
| Fixed-length string | Variable-length string |
| Uses fixed-length storage | Uses storage based on actual data plus length information |
| Suitable for fixed-size values | Suitable for variable-size values |
| Maximum 255 characters | Maximum depends on byte and row-size limits |

**Example:**

```sql
CREATE TABLE user_details (
    gender_code CHAR(1),
    user_name VARCHAR(50)
);
```

### 4.3 TEXT Data Types

Used to store large amounts of text.

| Data Type | Maximum Storage |
|---|---:|
| TINYTEXT | 255 bytes |
| TEXT | 65,535 bytes |
| MEDIUMTEXT | 16,777,215 bytes |
| LONGTEXT | 4,294,967,295 bytes |

**Example:**

```sql
CREATE TABLE articles (
    article_id INT,
    article_title VARCHAR(200),
    article_content TEXT
);
```

### 4.4 BINARY and VARBINARY

Used to store binary data rather than normal text.

| Data Type | Description |
|---|---|
| BINARY | Fixed-length binary data |
| VARBINARY | Variable-length binary data |

```sql
CREATE TABLE file_data (
    file_id INT,
    file_hash BINARY(32),
    file_content VARBINARY(500)
);
```

---

## 5. Date and Time Data Types

Date and time data types are used to store dates, times, and timestamps.

| Data Type | Description | Example |
|---|---|---|
| DATE | Stores date | 2026-11-10 |
| TIME | Stores time | 14:30:00 |
| DATETIME | Stores date and time | 2026-11-10 14:30:00 |
| TIMESTAMP | Stores date and time as a Unix-based timestamp value | 2026-11-10 14:30:00 |
| YEAR | Stores year | 2026 |

### Example

```sql
CREATE TABLE employee_details (
    employee_id INT,
    joining_date DATE,
    login_time TIME,
    created_at DATETIME,
    updated_at TIMESTAMP,
    birth_year YEAR
);
```

### DATE vs DATETIME vs TIMESTAMP

| DATE | DATETIME | TIMESTAMP |
|---|---|---|
| Stores only date | Stores date and time | Stores date and time |
| Format: YYYY-MM-DD | Format: YYYY-MM-DD HH:MM:SS | Format: YYYY-MM-DD HH:MM:SS |
| No automatic time-zone conversion | No automatic time-zone conversion | Converted between session time zone and UTC |
| Range: 1000–9999 | Range: 1000–9999 | Range: 1970–2038 in MySQL 8.4 |

**Example:**

```sql
INSERT INTO employee_details
(employee_id, joining_date, login_time, created_at, birth_year)
VALUES
(101, '2026-01-10', '09:30:00', '2026-01-10 09:30:00', 2002);
```

---

## 6. Boolean Data Type

MySQL supports `BOOLEAN` and `BOOL` as synonyms for `TINYINT(1)`.

- `0` represents FALSE.
- `1` represents TRUE.

**Example:**

```sql
CREATE TABLE users (
    user_id INT,
    user_name VARCHAR(50),
    is_active BOOLEAN
);
```

Insert data:

```sql
INSERT INTO users VALUES
(1, 'Latha', TRUE),
(2, 'Ravi', FALSE);
```

Retrieve data:

```sql
SELECT * FROM users;
```

**Important:** MySQL does not have a separate native Boolean storage type. `BOOLEAN` is treated as `TINYINT(1)`.

---

## 7. Spatial Data Types

Spatial data types are used to store geographical and geometric information.

| Data Type | Description |
|---|---|
| GEOMETRY | Stores any type of geometric value |
| POINT | Stores a single point |
| LINESTRING | Stores a line made of points |
| POLYGON | Stores a polygon |
| MULTIPOINT | Stores multiple points |
| MULTILINESTRING | Stores multiple lines |
| MULTIPOLYGON | Stores multiple polygons |
| GEOMETRYCOLLECTION | Stores a collection of geometry values |

**Example:**

```sql
CREATE TABLE locations (
    location_id INT,
    coordinates POINT
);
```

These types are useful in mapping applications, GPS systems, and location-based applications.

---

## 8. Real-Time Example: Student Database

Let's create a student table using different MySQL data types.

```sql
CREATE DATABASE college;

USE college;

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(100),
    age TINYINT UNSIGNED,
    gender CHAR(1),
    email VARCHAR(150),
    percentage DECIMAL(5, 2),
    date_of_birth DATE,
    is_active BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

Insert records:

```sql
INSERT INTO students
(student_name, age, gender, email, percentage, date_of_birth, is_active)
VALUES
('Latha', 22, 'F', 'latha@gmail.com', 88.50, '2003-05-15', TRUE),
('Ravi', 23, 'M', 'ravi@gmail.com', 85.75, '2002-08-20', TRUE);
```

Retrieve records:

```sql
SELECT * FROM students;
```

Check the table structure:

```sql
DESCRIBE students;
```

---

## 9. Difference Between All Major Data Type Categories

| Category | Purpose | Examples |
|---|---|---|
| Numeric | Store numbers | INT, BIGINT, DECIMAL, FLOAT |
| String | Store text and binary data | CHAR, VARCHAR, TEXT, BINARY |
| Date and Time | Store dates and times | DATE, TIME, DATETIME, TIMESTAMP |
| Boolean | Store true/false values | BOOLEAN, BOOL |
| Spatial | Store geographical information | POINT, POLYGON, GEOMETRY |

---

## 10. Frequently Asked Interview Questions

**1. What is a data type in MySQL?**

A data type defines what kind of values a column can store, such as numbers, text, dates, or Boolean values.

**2. What are the main categories of MySQL data types?**

Numeric, String, Date and Time, Boolean, and Spatial.

**3. What is the difference between CHAR and VARCHAR?**

CHAR stores fixed-length strings, whereas VARCHAR stores variable-length strings.

**4. What is the difference between INT and BIGINT?**

INT uses 4 bytes, whereas BIGINT uses 8 bytes and supports a larger range of integers.

**5. What is the difference between FLOAT and DECIMAL?**

FLOAT stores approximate decimal values, whereas DECIMAL stores exact decimal values.

**6. Which data type is suitable for storing salary?**

DECIMAL is recommended because financial values require exact decimal precision.

**7. What is the difference between DATE and DATETIME?**

DATE stores only the date, whereas DATETIME stores both date and time.

**8. Is BOOLEAN a separate data type in MySQL?**

No. BOOLEAN is an alias for TINYINT(1).

**9. What is the difference between TEXT and VARCHAR?**

VARCHAR is generally suitable for shorter variable-length strings, while TEXT is designed for larger text content.

**10. Which data type is suitable for storing an email address?**

VARCHAR is commonly used.

---

## 11. Practice Questions

Try answering these questions:

1. Which data type would you use to store a student's name?
2. Which data type would you use to store salary?
3. Which data type stores only the date?
4. What is the difference between INT and TINYINT?
5. Which data type stores large amounts of text?
6. What does UNSIGNED mean?
7. Why is DECIMAL preferred for financial calculations?
8. What is the difference between FLOAT and DOUBLE?
9. Which data type would you use to store a user's active status?
10. What is the difference between DATETIME and TIMESTAMP?

---

## 12. Quick Revision

| Requirement | Recommended Data Type |
|---|---|
| Student ID | INT |
| Age | TINYINT UNSIGNED |
| Name | VARCHAR(100) |
| Gender code | CHAR(1) |
| Salary | DECIMAL(10, 2) |
| Temperature | FLOAT |
| Scientific measurement | DOUBLE |
| Email | VARCHAR(150) |
| Description | TEXT |
| Date of birth | DATE |
| Login time | TIME |
| Created date and time | TIMESTAMP |
| Active status | BOOLEAN |
| Geographic location | POINT |

## Conclusion

MySQL data types help us store data in the correct format.

Remember these important points:

- `INT` – Whole numbers.
- `DECIMAL` – Exact decimal numbers.
- `FLOAT` and `DOUBLE` – Approximate decimal numbers.
- `CHAR` – Fixed-length strings.
- `VARCHAR` – Variable-length strings.
- `TEXT` – Large text.
- `DATE` – Date only.
- `DATETIME` – Date and time.
- `TIMESTAMP` – Date and time with time-zone conversion.
- `BOOLEAN` – True or false (stored as `TINYINT(1)` in MySQL).

**Important:** Always choose a data type based on the actual data you want to store. This helps maintain data accuracy and use database storage efficiently.
