import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {

    public static class SortMetrics {
        long comparisons = 0;
        long swaps = 0;
        long moves = 0;

        public void reset() {
            comparisons = 0;
            swaps = 0;
            moves = 0;
        }
    }

    public static void selectionSort(int[] arr, SortMetrics metrics) {
        metrics.reset();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                metrics.comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                metrics.swaps++;
                metrics.moves += 3;
            }
        }
    }

    public static void insertionSort(int[] arr, SortMetrics metrics) {
        metrics.reset();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            metrics.moves++;
            int j = i - 1;

            while (j >= 0) {
                metrics.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    metrics.moves++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            metrics.moves++;
        }
    }

    public static int[] generateSortedData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i + 1;
        }
        return data;
    }

    public static int[] generateReversedData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = size - i;
        }
        return data;
    }

    public static int[] generateRandomData(int size, long seed) {
        int[] data = generateSortedData(size);
        Random rand = new Random(seed);
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
        return data;
    }

    public static void runExperiment(String dataTypeLabel, int[] baseData) {
        SortMetrics metrics = new SortMetrics();

        int[] selectionData = Arrays.copyOf(baseData, baseData.length);
        selectionSort(selectionData, metrics);
        long selComparisons = metrics.comparisons;
        long selSwaps = metrics.swaps;
        long selMoves = metrics.moves;

        int[] insertionData = Arrays.copyOf(baseData, baseData.length);
        insertionSort(insertionData, metrics);
        long insComparisons = metrics.comparisons;
        long insSwaps = metrics.swaps;
        long insMoves = metrics.moves;

        System.out.println("【 資料形態: " + dataTypeLabel + " (筆數: " + baseData.length + ") 】");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-18s | %-15s | %-15s | %-15s%n", "演算法", "比較次數", "交換次數", "移動次數");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-18s | %-19d | %-19d | %-15d%n", "Selection Sort", selComparisons, selSwaps, selMoves);
        System.out.printf("%-18s | %-19d | %-19d | %-15d%n", "Insertion Sort", insComparisons, insSwaps, insMoves);
        System.out.println("----------------------------------------------------------------------------------\n");
    }

    public static void main(String[] args) {
        int size = 100;

        int[] sortedData = generateSortedData(size);
        int[] reversedData = generateReversedData(size);
        int[] randomData = generateRandomData(size, 42);

        System.out.println("==================================================================================");
        System.out.println("                Selection Sort vs. Insertion Sort 效能實驗報告");
        System.out.println("==================================================================================\n");

        runExperiment("已排序資料 (Sorted)", sortedData);
        runExperiment("反向排序資料 (Reversed)", reversedData);
        runExperiment("隨機排列資料 (Random)", randomData);

        System.out.println("==================================================================================");
        System.out.println("                              各組資料實驗觀察結論");
        System.out.println("==================================================================================");
        System.out.println("1. 已排序資料 (Sorted):");
        System.out.println("   - Insertion Sort 表現最優 (最佳時間複雜度 O(N))，僅需 N-1 次比較，0 次交換，移動次數僅為必要的 key 讀寫 (2*(N-1))。");
        System.out.println("   - Selection Sort 仍需要固定進行 N*(N-1)/2 次比較 (O(N^2))，雖然 0 次交換。");
        System.out.println("\n2. 反向排序資料 (Reversed):");
        System.out.println("   - Insertion Sort 達到最壞時間複雜度 O(N^2)，比較與元素移動次數達到最大值。");
        System.out.println("   - Selection Sort 的比較次數固定，但交換次數與移動次數為其最壞狀況。");
        System.out.println("\n3. 隨機排列資料 (Random):");
        System.out.println("   - Selection Sort 比較次數始終固定，交換次數極少 (最多 N-1 次)，適合「寫入/交換成本極高」的硬體環境。");
        System.out.println("   - Insertion Sort 在平均狀況下的比較與移動次數約為最壞狀況的一半，對於近乎排序或小規模資料集效率顯著高於 Selection Sort。");
        System.out.println("==================================================================================");
    }
}