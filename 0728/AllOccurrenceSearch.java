import java.util.Scanner;

public class AllOccurrenceSearch {
    public static void main(String[] args) {
        int[] numbers = {15, 42, 8, 23, 42, 91, 42, 10, 5, 8};

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的整數: ");
        if (!scanner.hasNextInt()) {
            System.out.println("輸入格式錯誤，請輸入整數。");
            scanner.close();
            return;
        }
        int target = scanner.nextInt();

        int matchCount = 0;
        int compareCount = 0;

        System.out.println("\n--- 開始線性搜尋 ---");
        System.out.print("符合的索引位置: ");
        
        for (int i = 0; i < numbers.length; i++) {
            compareCount++;
            if (numbers[i] == target) {
                System.out.print(i + " ");
                matchCount++;
            }
        }

        System.out.println(); 
        if (matchCount == 0) {
            System.out.println("找不到目標數值: " + target);
        } else {
            System.out.println("成功找到數字 " + target + "，共出現 " + matchCount + " 次");
        }

        System.out.println("實際比較次數: " + compareCount);

        scanner.close();
    }
}