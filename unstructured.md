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