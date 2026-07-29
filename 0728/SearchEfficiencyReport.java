public class SearchEfficiencyReport {

    public static int[] sequentialSearch(int[] data, int target) {
        int compareCount = 0;
        int foundIndex = -1;

        for (int i = 0; i < data.length; i++) {
            compareCount++;
            if (data[i] == target) {
                foundIndex = i;
                break;
            }
        }
        return new int[]{foundIndex, compareCount};
    }

    public static int[] binarySearch(int[] data, int target) {
        int compareCount = 0;
        int foundIndex = -1;
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            compareCount++;
            int mid = low + (high - low) / 2;

            if (data[mid] == target) {
                foundIndex = mid;
                break;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new int[]{foundIndex, compareCount};
    }

    public static int[] generateSortedData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (i + 1) * 2;
        }
        return data;
    }

    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};

        System.out.println("==============================");
        System.out.println("搜尋演算法效率比較報告 (比較次數)");
        System.out.println("==============================");
        for (int size : sizes) {
            int[] data = generateSortedData(size);
            int firstTarget = data[0];
            int lastTarget = data[size - 1];
            int notFoundTarget = -999;

            System.out.println("\n【 資料筆數: " + size + " 】");
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("%-12s | %-20s | %-20s%n", "搜尋目標", "Sequential Search 比較次數", "Binary Search 比較次數");
            System.out.println("--------------------------------------------------------------------------");

            int[] seqFirst = sequentialSearch(data, firstTarget);
            int[] binFirst = binarySearch(data, firstTarget);
            System.out.printf("%-12s | %-25d | %-20d%n", "第一筆 (" + firstTarget + ")", seqFirst[1], binFirst[1]);

            int[] seqLast = sequentialSearch(data, lastTarget);
            int[] binLast = binarySearch(data, lastTarget);
            System.out.printf("%-12s | %-25d | %-20d%n", "最後一筆 (" + lastTarget + ")", seqLast[1], binLast[1]);

            int[] seqNotFound = sequentialSearch(data, notFoundTarget);
            int[] binNotFound = binarySearch(data, notFoundTarget);
            System.out.printf("%-12s | %-25d | %-20d%n", "不存在 (" + notFoundTarget + ")", seqNotFound[1], binNotFound[1]);

            System.out.println("--------------------------------------------------------------------------");
        }

        System.out.println("\n============");
        System.out.println(" 分析與觀察結果");
        System.out.println("==============");
        System.out.println("1. 時間複雜度差距 (Time Complexity Growth):");
        System.out.println("   - Sequential Search 的最壞與平均比較次數呈線性成長 O(N)。資料量擴增 8 倍（128 到 1024）時，最壞比較次數也增加 8 倍。");
        System.out.println("   - Binary Search 的比較次數呈對數成長 O(log N)。資料量即使放大到 1024 筆，最壞情況下的比較次數也僅需 10~11 次。");
        System.out.println("\n2. 特殊案例分析 (Special Cases):");
        System.out.println("   - 搜尋【第一筆】時，Sequential Search 展現最佳情況 O(1)，只需 1 次比對；Binary Search 則視中間值分割位置而定。");
        System.out.println("   - 搜尋【最後一筆】與【不存在資料】時，Sequential Search 必須掃描完整陣列，比較次數達到最大值 N；Binary Search 則始終保持在 log2(N) 次以內。");
        System.out.println("\n3. 結論:");
        System.out.println("   - 對於已排序的大量資料，Binary Search 相比 Sequential Search 能夠大幅減少比對次數，具有極高的檢索效率。");
        System.out.println("===============");
    }
}