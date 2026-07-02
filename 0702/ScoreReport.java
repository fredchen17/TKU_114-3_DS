import java.util.Scanner;

public class ScoreReport {
    public static void main(String[] args) {
        // 建立 Scanner 物件以讀取鍵盤輸入
        Scanner scanner = new Scanner(System.in);

        // 1. 讀取使用者輸入的個人資料與成績
        System.out.print("請輸入姓名：");
        String name = scanner.nextLine();

        System.out.print("請輸入 Java 成績：");
        int javaScore = scanner.nextInt();

        System.out.print("請輸入 English 成績：");
        int englishScore = scanner.nextInt();

        System.out.print("請輸入 Math 成績：");
        int mathScore = scanner.nextInt();

        // 2. 計算平均分數 
        double average = (javaScore + englishScore + mathScore) / 3.0;

        // 3. 輸出成績報表
        System.out.println("\n=== 成績報表 ===");
        System.out.println("姓名：" + name);
        System.out.println("Java：" + javaScore);
        System.out.println("English：" + englishScore);
        System.out.println("Math：" + mathScore);
        System.out.println("平均：" + average);

        scanner.close();
    }
}