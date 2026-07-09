import java.util.Scanner;

class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("存款成功！目前餘額為: " + balance + " 元");
        } else {
            System.out.println("錯誤:存款金額必須大於 0");
        }
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("錯誤:提款金額必須大於 0");
        } else if (amount > balance) {
            System.out.println("錯誤:提款金額不能大於目前餘額");
        } else {
            balance -= amount;
            System.out.println("提款成功，目前餘額為: " + balance + " 元");
        }
    }
}

public class AtmMenuRefactored {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        boolean running = true;

        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            running = handleChoice(choice, account);
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1：查詢餘額");
        System.out.println("2：存款");
        System.out.println("3：提款");
        System.out.println("0：離開");
        System.out.print("請選擇操作選項:");
    }

    private static boolean handleChoice(int choice, BankAccount account) {
        switch (choice) {
            case 1:
                System.out.println("目前餘額為:" + account.getBalance() + " 元");
                break;
            case 2:
                System.out.print("請輸入存款金額:");
                account.deposit(scanner.nextInt());
                break;
            case 3:
                System.out.print("請輸入提款金額:");
                account.withdraw(scanner.nextInt());
                break;
            case 0:
                System.out.println("感謝您的使用，謝謝");
                return false;
            default:
                System.out.println("錯誤:無效的選項，請重新選擇");
                break;
        }
        return true;
    }
}