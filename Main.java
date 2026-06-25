/**
 * Main.java
 * Entry point of the Library Management System.
 *
 * How to compile and run:
 *   javac Book.java Library.java Main.java
 *   java Main
 *
 * Requirements: Java 8 or higher (no external libraries needed)
 */
public class Main {

    public static void main(String[] args) {
        Library library = new Library();   // create the library (loads sample data)
        library.start();                   // hand control to the menu loop
    }
}
