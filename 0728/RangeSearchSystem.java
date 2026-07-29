import java.util.Arrays;

public class RangeSearchSystem {

    public static int findFirst(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int findLast(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                result = mid;
                low = mid + 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findLast(nums, target);

        return new int[]{first, last};
    }

    public static void printSearchResult(int[] nums, int target) {
        int[] range = searchRange(nums, target);
        System.out.println("搜尋目標: " + target);
        System.out.println("索引範圍: " + Arrays.toString(range));
        if (range[0] != -1) {
            int count = range[1] - range[0] + 1;
            System.out.println("出現次數: " + count);
        } else {
            System.out.println("出現次數: 0 (目標不存在)");
        }
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 20, 20, 30, 40, 40, 50, 50, 50, 50, 60};

        System.out.println("測試陣列: " + Arrays.toString(numbers));
        System.out.println("================================\n");

        printSearchResult(numbers, 20);
        printSearchResult(numbers, 50);
        printSearchResult(numbers, 10);
        printSearchResult(numbers, 60);
        printSearchResult(numbers, 99);

        int[] emptyArray = {};
        System.out.println("測試空陣列:");
        printSearchResult(emptyArray, 20);
    }
}