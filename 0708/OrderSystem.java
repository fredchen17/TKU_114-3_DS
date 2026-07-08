import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalItems = 0;
        int totalAmount = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入選項：");
            
            int choice = scanner.nextInt();
            
            int price = 0;
            String itemName = "";

            switch (choice) {
                case 1:
                    price = 30;
                    itemName = "Black tea";
                    break;
                case 2:
                    price = 35;
                    itemName = "Green tea";
                    break;
                case 3:
                    price = 50;
                    itemName = "Coffee";
                    break;
                case 0:
                    running = false;
                    continue;
                default:
                    System.out.println("無效的選項，請重新輸入！");
                    continue;
            }

            int quantity = 0;
            while (true) {
                System.out.print("請輸入數量：");
                quantity = scanner.nextInt();
                
                if (quantity > 0) {
                    break;
                } else {
                    System.out.println("數量必須大於 0，請重新輸入！");
                }
            }

            int subtotal = price * quantity;
            System.out.println("Subtotal: " + subtotal);

            totalItems += quantity;
            totalAmount += subtotal;
        }

        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);

        scanner.close();
    }
}