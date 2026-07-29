public class Book {
    private String id;
    private String title;
    private String category;
    private int borrowCount;

    public Book(String id, String title, String category, int borrowCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    @Override
    public String toString() {
        return "Book{id='" + id + "', title='" + title + "', category='" + category + "', borrowCount=" + borrowCount + "}";
    }
}