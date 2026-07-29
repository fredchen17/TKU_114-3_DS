import java.util.Scanner;

public class SeatNumberSearchPractice {
    public static void main(String[] args) {
        int[] seatNumbers = {101, 102, 105, 108, 112, 115, 120, 125, 130, 135, 140, 150};

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的座位編號: ");
        if (!scanner.hasNextInt()) {
            System.out.println("輸入格式錯誤，請輸入數字編號。");
            scanner.close();
            return;
        }
        int target = scanner.nextInt();

        int low = 0;
        int high = seatNumbers.length - 1;
        int foundIndex = -1;
        int round = 1;

        System.out.println("\n--- 開始執行二元搜尋 (Binary Search) ---");
        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.printf("第 %d 輪 -> low: %d, mid: %d, high: %d (目前比對值: %d)%n", round++, low, mid, high, seatNumbers[mid]);

            if (seatNumbers[mid] == target) {
                foundIndex = mid;
                break;
            } else if (seatNumbers[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("---------------------------------------");
        if (foundIndex != -1) {
            System.out.println("成功找到座位！索引位置為: " + foundIndex);
        } else {
            System.out.println("找不到該座位編號: " + target);
        }

        scanner.close();
    }
}