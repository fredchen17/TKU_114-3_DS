import java.util.Arrays;

public class SortingDebugReport {

    public static void buggyInnerRange(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void buggyKeyNotSaved(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = arr[i];
        }
    }

    public static void buggyWrongDirection(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void fixedSelectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void fixedInsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("                     演算法錯誤除錯與修正報告");
        System.out.println("==========================================================================");

        System.out.println("\n【 錯誤類型 1: 內層範圍錯誤 (Selection Sort) 】");
        System.out.println("說明: 內層迴圈應該從 i + 1 開始，若誤從 i 開始，雖然結果無誤，但會產生不必要的自我比較。");
        int[] test1_bug = {29, 10, 14, 37, 13};
        int[] test1_fix = Arrays.copyOf(test1_bug, test1_bug.length);
        System.out.println("測試原始資料: " + Arrays.toString(test1_bug));
        buggyInnerRange(test1_bug);
        System.out.println("錯誤版本執行結果: " + Arrays.toString(test1_bug));
        fixedSelectionSort(test1_fix);
        System.out.println("修正版本執行結果: " + Arrays.toString(test1_fix));

        System.out.println("\n【 錯誤類型 2: key 未暫存/被覆蓋 (Insertion Sort) 】");
        System.out.println("說明: 未把 arr[i] 複製到暫存變數 key，當右移 arr[j+1] = arr[j] 時，arr[i] 的值會被覆蓋掉導致資料毀損。");
        int[] test2_bug = {5, 2, 4, 6, 1, 3};
        int[] test2_fix = Arrays.copyOf(test2_bug, test2_bug.length);
        System.out.println("測試原始資料: " + Arrays.toString(test2_bug));
        buggyKeyNotSaved(test2_bug);
        System.out.println("錯誤版本執行結果: " + Arrays.toString(test2_bug));
        fixedInsertionSort(test2_fix);
        System.out.println("修正版本執行結果: " + Arrays.toString(test2_fix));

        System.out.println("\n【 錯誤類型 3: 比較方向錯誤 (Insertion Sort) 】");
        System.out.println("說明: 欲執行升冪排序卻使用了 arr[j] < key，導致最後排成降冪排序。");
        int[] test3_bug = {12, 11, 13, 5, 6};
        int[] test3_fix = Arrays.copyOf(test3_bug, test3_bug.length);
        System.out.println("測試原始資料: " + Arrays.toString(test3_bug));
        buggyWrongDirection(test3_bug);
        System.out.println("錯誤版本執行結果: " + Arrays.toString(test3_bug));
        fixedInsertionSort(test3_fix);
        System.out.println("修正版本執行結果: " + Arrays.toString(test3_fix));
        System.out.println("==========================================================================");
    }
}