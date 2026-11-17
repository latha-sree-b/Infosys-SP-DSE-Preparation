# How to Change AUTO_INCREMENT Starting Value and Increment Step in MySQL

## 1. Introduction

Yes! In MySQL, we can change the AUTO_INCREMENT starting value and also control how much the value increases.

There are two different concepts:

1. **AUTO_INCREMENT starting value:** The value from which automatic numbering begins.
2. **AUTO_INCREMENT increment step:** The amount added between generated values.

For example:

- Normal AUTO_INCREMENT: 1, 2, 3, 4, 5
- Increment by 2: 1, 3, 5, 7, 9
- Increment by 3: 1, 4, 7, 10, 13

---

## 2. How to Change the Starting Value

We can use `ALTER TABLE` with `AUTO_INCREMENT`.

### Syntax

```sql
ALTER TABLE table_name
AUTO_INCREMENT = starting_value;
```

### Example

```sql
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);
```

Set the next AUTO_INCREMENT value to 10:

```sql
ALTER TABLE students
AUTO_INCREMENT = 10;
```

Insert records:

```sql
INSERT INTO students (name)
VALUES ('Latha'), ('Ravi'), ('Meera');
```

Output:

| id | name |
|---:|---|
| 10 | Latha |
| 11 | Ravi |
| 12 | Meera |

The numbering starts from 10 instead of 1.

**Important:** MySQL will not lower the next AUTO_INCREMENT value below the current maximum ID plus one.

---

## 3. How to Increase AUTO_INCREMENT by 2

If we want automatic values to increase by 2:

```text
1, 3, 5, 7, 9, 11
```

Use the MySQL system variable:

```sql
SET SESSION auto_increment_increment = 2;
```

Now insert records:

```sql
INSERT INTO students (name)
VALUES ('Asha'), ('Ravi'), ('Meera');
```

The generated IDs follow an increment step of 2, subject to the current counter and session settings.

---

## 4. How to Increase AUTO_INCREMENT by 3

If we want automatic values to increase by 3:

```text
1, 4, 7, 10, 13, 16
```

Use:

```sql
SET SESSION auto_increment_increment = 3;
```

Now newly generated AUTO_INCREMENT values use an increment step of 3.

---

## 5. Difference Between Starting Value and Increment Step

| Starting value | Increment step |
|---|---|
| Controls where numbering begins | Controls how much numbering increases |
| Uses `ALTER TABLE` | Uses `auto_increment_increment` |
| Example: Start at 10 | Example: Increase by 2 |
| 10, 11, 12, 13 | 1, 3, 5, 7 |

---

## 6. Complete Practical Example

```sql
CREATE DATABASE auto_increment_lab;

USE auto_increment_lab;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

-- Set the starting value
ALTER TABLE students
AUTO_INCREMENT = 5;

-- Set the increment step to 2 for this session
SET SESSION auto_increment_increment = 2;

-- Insert records
INSERT INTO students (name)
VALUES
('Latha'),
('Ravi'),
('Meera'),
('Asha');

-- Display records
SELECT * FROM students;
```

The IDs will follow the configured starting value and increment step.

---

## 7. Important Notes

1. `AUTO_INCREMENT` normally increases by 1.
2. `ALTER TABLE ... AUTO_INCREMENT = 10` changes the next starting value, subject to the existing maximum ID.
3. `auto_increment_increment = 2` configures the increment step as 2.
4. `auto_increment_increment = 3` configures the increment step as 3.
5. `SET SESSION` affects the current MySQL session only.
6. The actual generated sequence can be affected by existing IDs, explicit inserts, and MySQL's allocation behavior.
7. In multi-server or replicated environments, `auto_increment_increment` and `auto_increment_offset` can be used to avoid ID collisions.

---

## 8. Interview Questions

**Q1. Can we change the AUTO_INCREMENT starting value?**

Yes. Use:

```sql
ALTER TABLE students AUTO_INCREMENT = 10;
```

**Q2. Can we increase AUTO_INCREMENT by 2 instead of 1?**

Yes. Use:

```sql
SET SESSION auto_increment_increment = 2;
```

**Q3. Can we increase AUTO_INCREMENT by 3?**

Yes. Use:

```sql
SET SESSION auto_increment_increment = 3;
```

**Q4. What is the default AUTO_INCREMENT increment value?**

The default value of `auto_increment_increment` is 1.

**Q5. Does changing AUTO_INCREMENT starting value delete existing records?**

No. It changes the next generated value, subject to the current maximum ID.

---

## 9. Quick Revision

| Requirement | SQL command |
|---|---|
| Start numbering from 10 | `ALTER TABLE students AUTO_INCREMENT = 10;` |
| Increase by 2 | `SET SESSION auto_increment_increment = 2;` |
| Increase by 3 | `SET SESSION auto_increment_increment = 3;` |
| Check increment setting | `SELECT @@auto_increment_increment;` |
| Check offset setting | `SELECT @@auto_increment_offset;` |

**Remember:**

- `AUTO_INCREMENT = 10` → Controls the starting value.
- `auto_increment_increment = 2` → Controls the increment step.
- `auto_increment_increment = 3` → Controls the increment step.
