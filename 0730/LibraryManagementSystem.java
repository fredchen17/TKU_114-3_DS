import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibraryManagementSystem {

    private List<Book> bookList = new ArrayList<>();
    private Set<String> existingIds = new HashSet<>();
    private boolean sortedById = false;

    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        if (existingIds.contains(book.getId())) {
            System.out.println("Error: Duplicate book ID '" + book.getId() + "'!");
            return false;
        }
        existingIds.add(book.getId());
        bookList.add(book);
        sortedById = false;
        System.out.println("Added: " + book);
        return true;
    }

    public void sortByIdAsc() {
        if (bookList.isEmpty()) {
            System.out.println("Book list is empty. Nothing to sort.");
            return;
        }
        Book[] arr = bookList.toArray(new Book[0]);
        BookAlgorithms.mergeSortByIdAsc(arr, 0, arr.length - 1);
        bookList.clear();
        for (Book b : arr) {
            bookList.add(b);
        }
        sortedById = true;
        System.out.println("Books sorted by ID (Ascending).");
    }

    public void sortByBorrowCountDesc() {
        if (bookList.isEmpty()) {
            System.out.println("Book list is empty. Nothing to sort.");
            return;
        }
        Book[] arr = bookList.toArray(new Book[0]);
        BookAlgorithms.mergeSortByBorrowCountDesc(arr, 0, arr.length - 1);
        bookList.clear();
        for (Book b : arr) {
            bookList.add(b);
        }
        sortedById = false;
        System.out.println("Books sorted by Borrow Count (Descending).");
    }

    public void searchByIdBinary(String id) {
        if (bookList.isEmpty()) {
            System.out.println("Search failed: Book list is empty.");
            return;
        }
        if (!sortedById) {
            sortByIdAsc();
        }

        Book[] arr = bookList.toArray(new Book[0]);
        int index = BookAlgorithms.binarySearchById(arr, id);

        if (index != -1) {
            System.out.println("Binary Search found ID '" + id + "' at index " + index + ": " + arr[index]);
        } else {
            System.out.println("Binary Search: Book ID '" + id + "' not found.");
        }
    }

    public void searchByCategorySequential(String category) {
        if (bookList.isEmpty()) {
            System.out.println("Search failed: Book list is empty.");
            return;
        }

        Book[] arr = bookList.toArray(new Book[0]);
        List<Book> result = BookAlgorithms.sequentialSearchByCategory(arr, category);

        System.out.println("Sequential Search results for category '" + category + "':");
        if (result.isEmpty()) {
            System.out.println("  No books found in this category.");
        } else {
            for (Book b : result) {
                System.out.println("  " + b);
            }
        }
    }

    public void displayBooks() {
        System.out.println("--- Current Books ---");
        if (bookList.isEmpty()) {
            System.out.println("(Empty)");
        } else {
            for (Book b : bookList) {
                System.out.println(b);
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        System.out.println("=== 1. Test Empty Data ===");
        library.displayBooks();
        library.searchByIdBinary("BK001");
        library.searchByCategorySequential("CS");
        System.out.println();

        System.out.println("=== 2. Add Books & Test Duplicate ID ===");
        library.addBook(new Book("BK103", "Java Programming", "CS", 45));
        library.addBook(new Book("BK101", "Data Structures", "CS", 80));
        library.addBook(new Book("BK105", "Calculus", "Math", 12));
        library.addBook(new Book("BK102", "Algorithms", "CS", 95));
        library.addBook(new Book("BK101", "Duplicate Book Test", "CS", 5));
        library.addBook(new Book("BK104", "Linear Algebra", "Math", 30));
        System.out.println();

        System.out.println("=== 3. Merge Sort Tests ===");
        System.out.println("Before Sort:");
        library.displayBooks();

        library.sortByIdAsc();
        library.displayBooks();

        library.sortByBorrowCountDesc();
        library.displayBooks();
        System.out.println();

        System.out.println("=== 4. Binary Search Tests (Sorts by ID automatically if needed) ===");
        library.searchByIdBinary("BK102");
        library.searchByIdBinary("BK999");
        System.out.println();

        System.out.println("=== 5. Sequential Search Tests (Category) ===");
        library.searchByCategorySequential("CS");
        library.searchByCategorySequential("Math");
        library.searchByCategorySequential("History");
    }
}