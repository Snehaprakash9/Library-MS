import java.util.ArrayList;
import java.util.Scanner;

/**
 * Library.java
 * Manages a collection of Book objects and all library operations.
 * Demonstrates: ArrayList, methods, loops, conditionals, OOP design
 */
public class Library {

    // ── Fields ────────────────────────────────────────────────────────────────
    private ArrayList<Book> books;   // stores all books
    private Scanner scanner;

    // ── Constructor ───────────────────────────────────────────────────────────
    public Library() {
        books   = new ArrayList<>();
        scanner = new Scanner(System.in);
        seedSampleData();             // add a few demo books on startup
    }

    // ── Private helper: pre-load sample data ──────────────────────────────────
    private void seedSampleData() {
        books.add(new Book(101, "The Great Gatsby",        "F. Scott Fitzgerald"));
        books.add(new Book(102, "To Kill a Mockingbird",   "Harper Lee"));
        books.add(new Book(103, "1984",                    "George Orwell"));
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  A. Add Book
    // ══════════════════════════════════════════════════════════════════════════
    public void addBook() {
        printSectionHeader("ADD NEW BOOK");

        System.out.print("  Enter Book ID   : ");
        int id = readInt();

        // Check for duplicate ID
        if (findBookById(id) != null) {
            System.out.println("  [!] A book with ID " + id + " already exists. Aborting.\n");
            return;
        }

        scanner.nextLine();  // consume leftover newline after readInt()

        System.out.print("  Enter Title     : ");
        String title = scanner.nextLine().trim();

        System.out.print("  Enter Author    : ");
        String author = scanner.nextLine().trim();

        // Validate non-empty input
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("  [!] Title and Author cannot be blank. Aborting.\n");
            return;
        }

        books.add(new Book(id, title, author));
        System.out.println("  [✓] Book \"" + title + "\" added successfully!\n");
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  B. View All Books
    // ══════════════════════════════════════════════════════════════════════════
    public void viewBooks() {
        printSectionHeader("ALL BOOKS IN LIBRARY");

        if (books.isEmpty()) {
            System.out.println("  No books found in the library.\n");
            return;
        }

        System.out.println("  Total books: " + books.size() + "\n");
        // for-each loop to iterate over all books
        for (Book book : books) {
            book.display();
            System.out.println();
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  C. Search Book by ID
    // ══════════════════════════════════════════════════════════════════════════
    public void searchBookById() {
        printSectionHeader("SEARCH BOOK BY ID");

        System.out.print("  Enter Book ID to search: ");
        int id = readInt();

        Book found = findBookById(id);
        if (found != null) {
            System.out.println("\n  [✓] Book found:\n");
            found.display();
        } else {
            System.out.println("  [!] No book found with ID " + id + ".");
        }
        System.out.println();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  D. Search Book by Title
    // ══════════════════════════════════════════════════════════════════════════
    public void searchBookByTitle() {
        printSectionHeader("SEARCH BOOK BY TITLE");

        scanner.nextLine();  // consume leftover newline
        System.out.print("  Enter title (or part of it): ");
        String keyword = scanner.nextLine().trim();

        boolean anyFound = false;

        // normal for-loop with index; case-insensitive comparison
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                if (!anyFound) {
                    System.out.println("\n  [✓] Matching books:\n");
                    anyFound = true;
                }
                b.display();
                System.out.println();
            }
        }

        if (!anyFound) {
            System.out.println("  [!] No book found with title containing \"" + keyword + "\".\n");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  E. Issue Book
    // ══════════════════════════════════════════════════════════════════════════
    public void issueBook() {
        printSectionHeader("ISSUE BOOK");

        System.out.print("  Enter Book ID to issue: ");
        int id = readInt();

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("  [!] Book with ID " + id + " not found.\n");
            return;
        }

        if (book.isIssued()) {
            System.out.println("  [!] \"" + book.getTitle() + "\" is already issued. Not available right now.\n");
        } else {
            book.setIssued(true);
            System.out.println("  [✓] \"" + book.getTitle() + "\" has been issued successfully!\n");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  F. Return Book
    // ══════════════════════════════════════════════════════════════════════════
    public void returnBook() {
        printSectionHeader("RETURN BOOK");

        System.out.print("  Enter Book ID to return: ");
        int id = readInt();

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("  [!] Book with ID " + id + " not found.\n");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("  [!] \"" + book.getTitle() + "\" was not issued. No action taken.\n");
        } else {
            book.setIssued(false);
            System.out.println("  [✓] \"" + book.getTitle() + "\" returned successfully. It is now available.\n");
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  G. Delete Book
    // ══════════════════════════════════════════════════════════════════════════
    public void deleteBook() {
        printSectionHeader("DELETE BOOK");

        System.out.print("  Enter Book ID to delete: ");
        int id = readInt();

        // Use a normal for-loop with index so we can call remove(i)
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == id) {
                String title = books.get(i).getTitle();
                books.remove(i);
                System.out.println("  [✓] \"" + title + "\" (ID: " + id + ") deleted successfully!\n");
                return;
            }
        }
        System.out.println("  [!] Book with ID " + id + " not found.\n");
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  H. Count Total Books
    // ══════════════════════════════════════════════════════════════════════════
    public void countBooks() {
        printSectionHeader("BOOK COUNT");

        int total    = books.size();
        int issued   = 0;
        int available= 0;

        for (Book b : books) {
            if (b.isIssued()) issued++;
            else              available++;
        }

        System.out.println("  Total Books   : " + total);
        System.out.println("  Available     : " + available);
        System.out.println("  Issued        : " + issued);
        System.out.println();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  Private helper methods
    // ══════════════════════════════════════════════════════════════════════════

    /** Finds a book by its ID; returns null if not found. */
    private Book findBookById(int id) {
        for (Book b : books) {
            if (b.getBookId() == id) return b;
        }
        return null;
    }

    /** Prints a consistent section header banner. */
    private void printSectionHeader(String title) {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.printf ("  ║  %-40s║%n", title);
        System.out.println("  ╚══════════════════════════════════════════╝");
    }

    /**
     * Safely reads an integer from stdin.
     * Discards any non-integer tokens and asks again.
     */
    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("  [!] Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  Main menu loop
    // ══════════════════════════════════════════════════════════════════════════
    public void start() {
        printWelcomeBanner();

        boolean running = true;

        // while loop keeps the menu alive until the user exits
        while (running) {
            printMenu();
            System.out.print("  Enter your choice: ");
            int choice = readInt();

            // switch-case for menu dispatch
            switch (choice) {
                case 1:  addBook();           break;
                case 2:  viewBooks();         break;
                case 3:  searchBookById();    break;
                case 4:  searchBookByTitle(); break;
                case 5:  issueBook();         break;
                case 6:  returnBook();        break;
                case 7:  deleteBook();        break;
                case 8:  countBooks();        break;
                case 9:
                    System.out.println("\n  Thank you for using the Library Management System. Goodbye!\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n  [!] Invalid choice. Please enter a number between 1 and 9.\n");
            }
        }

        scanner.close();
    }

    // ── UI helpers ────────────────────────────────────────────────────────────
    private void printWelcomeBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║      LIBRARY MANAGEMENT SYSTEM               ║");
        System.out.println("  ║      Built with Core Java                    ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
        System.out.println("  3 sample books have been pre-loaded for you.\n");
    }

    private void printMenu() {
        System.out.println("  ┌─────────────────────────────────────────┐");
        System.out.println("  │              MAIN MENU                  │");
        System.out.println("  ├─────────────────────────────────────────┤");
        System.out.println("  │  1. Add Book                            │");
        System.out.println("  │  2. View All Books                      │");
        System.out.println("  │  3. Search Book by ID                   │");
        System.out.println("  │  4. Search Book by Title                │");
        System.out.println("  │  5. Issue Book                          │");
        System.out.println("  │  6. Return Book                         │");
        System.out.println("  │  7. Delete Book                         │");
        System.out.println("  │  8. Count Total Books                   │");
        System.out.println("  │  9. Exit                                │");
        System.out.println("  └─────────────────────────────────────────┘");
    }
}
