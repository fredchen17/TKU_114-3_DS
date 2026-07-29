import java.util.Arrays;

public class InsertionSortPractice {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或只有一個元素，無需排序。");
            return;
        }

        int compareCount = 0;
        int shiftCount = 0;
        int n = arr.length;

        System.out.println("初始陣列: " + Arrays.toString(arr));

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                compareCount++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shiftCount++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;

            System.out.printf("第 %d 輪 -> key: %-2d, 插入位置: %-2d, 陣列: %s%n",
                    i, key, (j + 1), Arrays.toString(arr));
        }

        System.out.println("----------------------------------------");
        System.out.println("總比較次數: " + compareCount);
        System.out.println("總元素右移次數: " + shiftCount);
    }

    public static void main(String[] args) {
        System.out.println("====== 測試 1: 原始未排序陣列 ======");
        int[] arr1 = {30, 10, 20, 50, 40, 5};
        insertionSort(arr1);

        System.out.println("\n====== 測試 2: 已排序陣列 ======");
        int[] arr2 = {5, 10, 20, 30, 40, 50};
        insertionSort(arr2);

        System.out.println("\n====== 測試 3: 反向排序陣列 ======");
        int[] arr3 = {50, 40, 30, 20, 10, 5};
        insertionSort(arr3);

        System.out.println("\n====== 移動次數比較與說明 ======");
        System.out.println("【反向排序陣列】的元素移動（右移）次數最多。");
        System.out.println("原因：在完全反向排序的狀況下，每一個要插入的 key 都比左側已排序區域的所有元素還要小，");
        System.out.println("因此左側的所有元素都必須向右移動一格為 key 騰出位置，達到最壞時間複雜度 O(N^2) 的最大右移次數。");
    }
}