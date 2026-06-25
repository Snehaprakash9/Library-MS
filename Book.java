/**
 * Book.java
 * Represents a single book in the library system.
 * Demonstrates: class, attributes, constructor, encapsulation, getters/setters
 */
public class Book {

    // ── Attributes (private = encapsulation) ──────────────────────────────────
    private int    bookId;
    private String title;
    private String author;
    private boolean issued;

    // ── Constructor ───────────────────────────────────────────────────────────
    /**
     * Creates a new Book with the given details.
     * issued is always false when a book is first added.
     */
    public Book(int bookId, String title, String author) {
        this.bookId  = bookId;
        this.title   = title;
        this.author  = author;
        this.issued  = false;          // default: not issued
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public int     getBookId()  { return bookId;  }
    public String  getTitle()   { return title;   }
    public String  getAuthor()  { return author;  }
    public boolean isIssued()   { return issued;  }

    // ── Setters ───────────────────────────────────────────────────────────────
    public void setTitle(String title)    { this.title  = title;  }
    public void setAuthor(String author)  { this.author = author; }
    public void setIssued(boolean issued) { this.issued = issued; }

    // ── Display Method ────────────────────────────────────────────────────────
    /**
     * Prints a formatted summary of this book to the console.
     */
    public void display() {
        System.out.println("  ┌─────────────────────────────────────────┐");
        System.out.printf ("  │  Book ID : %-30d│%n", bookId);
        System.out.printf ("  │  Title   : %-30s│%n", title);
        System.out.printf ("  │  Author  : %-30s│%n", author);
        System.out.printf ("  │  Status  : %-30s│%n",
                           issued ? "Issued (Not Available)" : "Available");
        System.out.println("  └─────────────────────────────────────────┘");
    }
}
