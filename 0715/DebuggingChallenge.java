/*
 * 錯誤修正紀錄：
 * 1. 錯誤位置：for 迴圈條件 `i <= scores.length` -> 類型：陣列越界 -> 原因：超出了陣列範圍 -> 修正：改成 `i < scores.length`。
 * 2. 錯誤位置：`total / scores.length` -> 類型：邏輯錯誤 -> 原因：整數除法會吃掉小數點 -> 修正：加上 `(double)` 轉型。
 * 3. 錯誤位置：`sc.nextInt()` 後方 -> 類型：換行殘留 -> 原因：`nextInt()` 沒吃掉換行符號，導致後面的 `nextLine()` 直接跳過 -> 修正：在中間加一行 `sc.nextLine()`。
 * 4. 錯誤位置：`command == "exit"` -> 類型：字串比較錯誤 -> 原因：`==` 在字串是比記憶體位址 -> 修正：改成 `command.equals("exit")`。
 * 5. 錯誤位置：`System.out.println(...)` 尾端 -> 類型：編譯錯誤 -> 原因：漏掉分號 -> 修正：補上 `;`。
 * * 偵錯變數紀錄 (Breakpoint Log)：
 * - 當機前一刻 (迴圈內)：i = 3, scores.length = 3, total = 247 (即將發生越界)
 * - 計算平均時：total = 247, 修正後 average 成功取得 82.33
 * - 輸入指令時：未修正前 age = 18, command = "" (被換行符直接填入)；修正後 command 順利取得 "exit"
 */

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();

        sc.nextLine(); 

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if (command.equals("exit")) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}