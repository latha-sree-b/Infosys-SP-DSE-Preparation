## Structured vs Semi-Structured vs Unstructured Data

Data can be classified into three main types based on how it is organized.

### 1. Structured Data

Structured data follows a fixed schema and is organized into rows and columns.

**Example:**

| Student_ID | Name | Age |
|---|---|---|
| 101 | Ravi | 21 |
| 102 | Priya | 22 |

- Easy to store, search, and query using SQL.
- Commonly stored in relational databases.
- Examples: MySQL, PostgreSQL, Oracle Database.

### 2. Semi-Structured Data

Semi-structured data does not follow a rigid table structure but contains tags, keys, or metadata that organize the information.

**Example: JSON**

```json
{
  "studentId": 101,
  "name": "Ravi",
  "skills": ["Java", "SQL"]
}
```

- Does not require every record to have the same fields.
- Contains organizational elements such as keys and tags.
- Commonly used in APIs, configuration files, and document databases.
- Examples: JSON, XML, YAML.

### 3. Unstructured Data

Unstructured data does not follow a predefined data model or a fixed tabular structure.

**Examples:**
- Images
- Videos
- Audio recordings
- PDF documents
- Emails and text documents

- Can contain large amounts of complex information.
- Often stored in file systems or object storage.
- May require specialized tools to search or analyze.

---

## Comparison Table

| Feature | Structured | Semi-Structured | Unstructured |
|---|---|---|---|
| Organization | Fixed rows and columns | Flexible structure using keys or tags | No predefined tabular structure |
| Schema | Fixed | Flexible or self-describing | Usually no predefined schema |
| Data format | Tables | JSON, XML, YAML | Images, videos, audio, documents |
| Querying | SQL | SQL-like or document queries | Specialized search or analysis tools |
| Example | Student records | JSON student data | Student photograph |
| Common storage | Relational databases | Document databases, files | File systems, object storage |

## Real-World Example: Student Application

A student application can store all three types of data.

| Data | Type | Example |
|---|---|---|
| Student ID, name, age | Structured | Database table |
| Student skills and hobbies | Semi-structured | JSON document |
| Student photograph | Unstructured | JPEG image file |

**Key takeaway:** Structured data has a fixed schema, semi-structured data has a flexible structure, and unstructured data has no predefined tabular structure.
