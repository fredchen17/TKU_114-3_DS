import java.util.ArrayList;
import java.util.List;

public class BookAlgorithms {

    public static void mergeSortByIdAsc(Book[] books, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByIdAsc(books, left, mid);
        mergeSortByIdAsc(books, mid + 1, right);
        mergeByIdAsc(books, left, mid, right);
    }

    private static void mergeByIdAsc(Book[] books, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Book[] leftArr = new Book[n1];
        Book[] rightArr = new Book[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = books[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = books[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i].getId().compareTo(rightArr[j].getId()) <= 0) {
                books[k] = leftArr[i];
                i++;
            } else {
                books[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            books[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            books[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void mergeSortByBorrowCountDesc(Book[] books, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByBorrowCountDesc(books, left, mid);
        mergeSortByBorrowCountDesc(books, mid + 1, right);
        mergeByBorrowCountDesc(books, left, mid, right);
    }

    private static void mergeByBorrowCountDesc(Book[] books, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Book[] leftArr = new Book[n1];
        Book[] rightArr = new Book[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = books[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = books[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i].getBorrowCount() >= rightArr[j].getBorrowCount()) {
                books[k] = leftArr[i];
                i++;
            } else {
                books[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            books[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            books[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static int binarySearchById(Book[] books, String targetId) {
        if (books == null || targetId == null) {
            return -1;
        }

        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books[mid].getId().compareTo(targetId);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static List<Book> sequentialSearchByCategory(Book[] books, String category) {
        List<Book> result = new ArrayList<>();
        if (books == null || category == null) {
            return result;
        }

        for (Book book : books) {
            if (book != null && book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }

        return result;
    }
}