import java.util.Scanner;

public class AtmMethodHomework {

    public static void printMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1：查詢餘額");
        System.out.println("2：存款");
        System.out.println("3：提款");
        System.out.println("0：離開");
        System.out.print("請選擇操作選項:");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            int amount = sc.nextInt();
            if (amount > 0) {
                return amount;
            }
            System.out.println("錯誤:金額必須大於 0");
        }
    }

    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("存款成功！目前餘額為: " + balance + " 元");
        return balance;
    }

    public static int withdraw(int balance, int amount) {
        if (amount > balance) {
            System.out.println("錯誤:提款金額不能大於目前餘額");
            return balance;
        }
        balance -= amount;
        System.out.println("提款成功，目前餘額為: " + balance + " 元");
        return balance;
    }

    public static void printBalance(int balance) {
        System.out.println("目前餘額為:" + balance + " 元");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        boolean running = true;

        while (running) {
            printMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額:");
                    balance = deposit(balance, depositAmount);
                    break;
                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額:");
                    balance = withdraw(balance, withdrawAmount);
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

        sc.close();
    }
}