import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("請輸入姓名:");
        String name = scanner.next();

        System.out.println("請輸入Java成績");
        int javascore = scanner.nextInt(); 

        System.out.print("請輸入 English 成績：");
        int englishScore = scanner.nextInt();

        System.out.print("請輸入 Math 成績：");
        int mathScore = scanner.nextInt();

        double avgscore = (mathScore + englishScore + javascore) / 3.0;
        String status = "";
        if (avgscore >= 60) {
            status = "及格";
        } else {
            status = "不及格";
        }

        char Grade;        
        if (avgscore >= 90) {
            Grade = 'A';           
        } else if (avgscore >= 80) {
            Grade = 'B';
        } else if (avgscore >= 70) {
            Grade = 'C';
        } else if (avgscore >= 60) {
            Grade = 'D';
        } else {
            Grade = 'F';
        }

        int option = -1;
        while (option != 0) {
            System.out.println("1：顯示平均分數");
            System.out.println("2：顯示及格狀態");
            System.out.println("3：顯示等第");
            System.out.println("0：離開");
            System.out.print("請選擇操作項目 (0-3)：");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("平均成績:" + avgscore);
                    break;
                case 2: 
                    System.out.println("是否及格:" + status);
                    break;
                case 3:
                    System.out.println("等第為:" + Grade);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n[錯誤] 輸入無效，請輸入 0 到 3 之間的數字。");
                    break;
            }
        }
        
        scanner.close();
    }
}