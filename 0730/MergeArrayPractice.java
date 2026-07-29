public class MergeArrayPractice {

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        if (arr1 == null) arr1 = new int[0];
        if (arr2 == null) arr2 = new int[0];

        int i = 0, j = 0, k = 0;
        int[] temp = new int[arr1.length + arr2.length];

        while (i < arr1.length && j < arr2.length) {
            int val;
            if (arr1[i] < arr2[j]) {
                val = arr1[i];
                i++;
            } else if (arr1[i] > arr2[j]) {
                val = arr2[j];
                j++;
            } else {
                val = arr1[i];
                i++;
                j++;
            }

            if (k == 0 || temp[k - 1] != val) {
                temp[k] = val;
                k++;
            }
        }

        while (i < arr1.length) {
            int val = arr1[i];
            i++;
            if (k == 0 || temp[k - 1] != val) {
                temp[k] = val;
                k++;
            }
        }

        while (j < arr2.length) {
            int val = arr2[j];
            j++;
            if (k == 0 || temp[k - 1] != val) {
                temp[k] = val;
                k++;
            }
        }

        int[] result = new int[k];
        for (int m = 0; m < k; m++) {
            result[m] = temp[m];
        }

        return result;
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] a1 = {-5, -2, 0, 3, 3, 7};
        int[] a2 = {-3, -2, 1, 3, 8, 10, 12};
        System.out.print("Test 1 Result: ");
        printArray(mergeSortedArrays(a1, a2));

        int[] emptyArr = {};
        int[] a3 = {-1, 0, 5};
        System.out.print("Test 2 (Empty) Result: ");
        printArray(mergeSortedArrays(emptyArr, a3));
    }
}