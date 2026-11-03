public class Book {
    private int bookNumber;
    private String title;

    Book(int bookNumber, String title) {
        this.bookNumber = bookNumber;
        this.title = title;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }
}

class Main {
    public static void main(String[] args) {
        Book book = new Book(101, "Java Basics");
        System.out.println(book.getBookNumber());
        System.out.println(book.getTitle());
        book.setTitle("Java Programming");
        System.out.println(book.getTitle());
    }
}