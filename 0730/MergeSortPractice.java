public class MergeSortPractice {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        System.out.println("Split: [" + left + " .. " + right + "]");

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }

        System.out.print("Merged [" + left + " .. " + right + "]: ");
        printRange(arr, left, right);
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printRange(int[] arr, int left, int right) {
        System.out.print("[");
        for (int i = left; i <= right; i++) {
            System.out.print(arr[i]);
            if (i < right) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void testAndRun(String title, int[] arr) {
        System.out.println("=== " + title + " ===");
        System.out.print("Original: ");
        printArray(arr);

        if (arr != null && arr.length > 0) {
            mergeSort(arr, 0, arr.length - 1);
        }

        System.out.print("Sorted: ");
        printArray(arr);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {41, 12, 35, 8, 27, 19, 50, 3};
        testAndRun("Main Test", arr1);

        int[] arrEmpty = {};
        testAndRun("Empty Array Test", arrEmpty);

        int[] arrSingle = {15};
        testAndRun("Single Element Test", arrSingle);

        int[] arrSorted = {1, 2, 3, 4, 5};
        testAndRun("Already Sorted Test", arrSorted);

        int[] arrReversed = {9, 7, 5, 3, 1};
        testAndRun("Reversed Array Test", arrReversed);
    }
}