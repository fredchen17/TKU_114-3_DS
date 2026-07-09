import java.util.Scanner;

public class OrderSystemRefactor {
    public static void printMenu() {
        System.out.println("\n=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
        System.out.print("請輸入選項：");
    }

    public static int getQuantity(Scanner scanner) {
        while (true) {
            System.out.print("請輸入數量：");
            int quantity = scanner.nextInt();
            if (quantity > 0) {
                return quantity;
            }
            System.out.println("數量必須大於 0，請重新輸入！");
        }
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalItems = 0;
        int totalAmount = 0;
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            
            int price = 0;
            switch (choice) {
                case 1:
                    price = 30;
                    break;
                case 2:
                    price = 35;
                    break;
                case 3:
                    price = 50;
                    break;
                case 0:
                    running = false;
                    continue;
                default:
                    System.out.println("無效的選項，請重新輸入！");
                    continue;
            }

            int quantity = getQuantity(scanner);
            int subtotal = calculateSubtotal(price, quantity);
            System.out.println("Subtotal: " + subtotal);

            totalItems += quantity;
            totalAmount += subtotal;
        }

        printReceipt(totalItems, totalAmount);
        scanner.close();
    }
}