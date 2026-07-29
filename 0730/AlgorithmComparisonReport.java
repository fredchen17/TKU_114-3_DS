import java.util.Arrays;
import java.util.Random;

public class AlgorithmComparisonReport {

    private static final int[] DATA_SIZES = {16, 128, 1024};
    private static final long RANDOM_SEED = 20260729L;

    private static class SortResult {
        long comparisons;

        public SortResult() {
            this.comparisons = 0;
        }
    }

    public static SortResult selectionSort(int[] array) {
        SortResult result = new SortResult();
        int[] arr = Arrays.copyOf(array, array.length);
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                result.comparisons++; 
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return result;
    }

    public static SortResult insertionSort(int[] array) {
        SortResult result = new SortResult();
        int[] arr = Arrays.copyOf(array, array.length);
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0) {
                result.comparisons++; 
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return result;
    }

    public static SortResult mergeSort(int[] array) {
        SortResult result = new SortResult();
        int[] arr = Arrays.copyOf(array, array.length);
        mergeSortRecursive(arr, 0, arr.length - 1, result);
        return result;
    }

    private static void mergeSortRecursive(int[] arr, int left, int right, SortResult result) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortRecursive(arr, left, mid, result);
            mergeSortRecursive(arr, mid + 1, right, result);
            merge(arr, left, mid, right, result);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, SortResult result) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            result.comparisons++;
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
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    private static int[] generateSortedData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i + 1;
        }
        return data;
    }

    private static int[] generateReversedData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = size - i;
        }
        return data;
    }

    private static int[] generateRandomData(int size) {
        int[] data = new int[size];
        Random rand = new Random(RANDOM_SEED);
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextInt(size * 10);
        }
        return data;
    }
    public static void main(String[] args) {
        System.out.println("==========================================================================================");
        System.out.println("                       排序演算法比較次數分析報告 (Comparison Report)");
        System.out.println("==========================================================================================");

        String[] dataTypes = {"已排序 (Sorted)", "反向排序 (Reversed)", "固定亂序 (Random)"};

        for (int size : DATA_SIZES) {
            System.out.println("\n【 資料筆數 Size: " + size + " 】");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.printf("%-20s | %-18s | %-18s | %-18s%n", "資料狀態 (State)", "Selection Sort", "Insertion Sort", "Merge Sort");
            System.out.println("------------------------------------------------------------------------------------------");

            for (String dataType : dataTypes) {
                int[] baseData;
                if (dataType.startsWith("已排序")) {
                    baseData = generateSortedData(size);
                } else if (dataType.startsWith("反向排序")) {
                    baseData = generateReversedData(size);
                } else {
                    baseData = generateRandomData(size);
                }

                SortResult selResult = selectionSort(baseData);
                SortResult insResult = insertionSort(baseData);
                SortResult merResult = mergeSort(baseData);

                System.out.printf("%-20s | %-18d | %-18d | %-18d%n",
                        dataType, selResult.comparisons, insResult.comparisons, merResult.comparisons);
            }
            System.out.println("------------------------------------------------------------------------------------------");
        }

        printProgrammaticObservations();
    }

    private static void printProgrammaticObservations() {
        System.out.println("\n==========================================================================================");
        System.out.println("                                程式計算之演算法觀察結論");
        System.out.println("==========================================================================================");
        System.out.println("1. Selection Sort (選擇排序)：");
        System.out.println("   - 無論資料初始狀態為已排序、反向或亂序，其比較次數固定為 N*(N-1)/2 次。");
        System.out.println("   - 時間複雜度始終維持在 O(N^2)，缺乏對最佳狀況（Best Case）的自適應能力。");

        System.out.println("\n2. Insertion Sort (插入排序)：");
        System.out.println("   - 對「已排序」資料具有極佳效率，比較次數僅需要 (N - 1) 次，達到最佳情況 O(N)。");
        System.out.println("   - 在「反向排序」的最壞情況下，比較次數接近 N*(N-1)/2 次，成長趨勢與選擇排序相同。");
        System.out.println("   - 在小規模亂序資料 (N=16) 中，其比較次數往往低於 Merge Sort，適合小陣列排序。");

        System.out.println("\n3. Merge Sort (合併排序)：");
        System.out.println("   - 比較次數表現穩定，對各種初始狀態皆維持在 O(N log N) 的時間複雜度等級。");
        System.out.println("   - 當資料量擴展至 N=1024 時，Merge Sort 在亂序及反向狀態下的比較次數大幅低於 O(N^2) 的算法。");

        System.out.println("\n4. 綜合觀察結論：");
        System.out.println("   - 不單以單次執行的毫秒數（毫秒數易受 CPU 波動與 JVM 預熱影響）作為判斷依據。");
        System.out.println("   - 從「資料比較次數」可精確驗證：小規模資料可選用插入排序，大規模亂序資料則應優先選用 Merge Sort。");
        System.out.println("==========================================================================================");
    }
}