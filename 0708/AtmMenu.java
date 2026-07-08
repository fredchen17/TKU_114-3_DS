import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int balance = 1000;
        boolean running = true;

        while (running) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1：查詢餘額");
            System.out.println("2：存款");
            System.out.println("3：提款");
            System.out.println("0：離開");
            System.out.print("請選擇操作選項:");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("目前餘額為:" + balance + " 元");
                    break;

                case 2:
                    System.out.print("請輸入存款金額:");
                    int deposit = scanner.nextInt();
                    
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("存款成功！目前餘額為: " + balance + " 元");
                    } else {
                        System.out.println("錯誤:存款金額必須大於 0");
                    }
                    break;

                case 3:
                    System.out.print("請輸入提款金額:");
                    int withdraw = scanner.nextInt();
                    
                    if (withdraw <= 0) {
                        System.out.println("錯誤:提款金額必須大於 0");
                    } else if (withdraw > balance) {
                        System.out.println("錯誤:提款金額不能大於目前餘額");
                    } else {
                        balance -= withdraw;
                        System.out.println("提款成功，目前餘額為: " + balance + " 元");
                    }
                    break;

                case 0:
                    System.out.println("感謝您的使用，謝謝");
                    running = false;
                    break;

                default:
                    System.out.println("錯誤:無效的選項，請重新選擇");
                    break;
            }
        }

        scanner.close();
    }
}