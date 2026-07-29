import java.util.Arrays;

public class SelectionSortPractice {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或只有一個元素，無需排序。");
            return;
        }

        int compareCount = 0;
        int swapCount = 0;
        int n = arr.length;

        System.out.println("初始陣列: " + Arrays.toString(arr));
        System.out.println("--- 開始選擇排序 ---");

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }

            System.out.printf("第 %d 輪 -> start: %d, 選中的最小值索引: %d, 當前陣列: %s%n",
                    (i + 1), i, minIndex, Arrays.toString(arr));
        }

        System.out.println("--------------------");
        System.out.println("比較次數: " + compareCount);
        System.out.println("交換次數: " + swapCount);
    }

    public static void main(String[] args) {
        System.out.println("====== 測試 1: 一般未排序陣列 ======");
        int[] numbers = {42, 18, 35, 7, 29, 14};
        selectionSort(numbers);

        System.out.println("\n====== 測試 2: 空陣列 ======");
        int[] emptyArray = {};
        selectionSort(emptyArray);

        System.out.println("\n====== 測試 3: 單一元素陣列 ======");
        int[] singleElementArray = {99};
        selectionSort(singleElementArray);
    }
}