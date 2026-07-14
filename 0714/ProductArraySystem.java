import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int purchaseCount = 0;
        int restockCount = 0;

        while (true) {
            showMenu();
            int choice = readChoice(sc);

            if (choice == 7) {
                showSummary(purchaseCount, restockCount);
                break;
            }

            switch (choice) {
                case 1:
                    showAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProduct(sc, names, prices, stocks);
                    break;
                case 3:
                    if (buyProduct(sc, names, prices, stocks)) {
                        purchaseCount++;
                    }
                    break;
                case 4:
                    if (restockProduct(sc, names, stocks)) {
                        restockCount++;
                    }
                    break;
                case 5:
                    showLowStock(names, prices, stocks);
                    break;
                case 6:
                    showTotalValue(prices, stocks);
                    break;
                default:
                    System.out.println("【錯誤】無效的選項，請輸入 1 到 7 之間的整數");
            }
        }
        sc.close();
    }

    public static void showMenu() {
        System.out.println("\n===== 商品管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束系統");
        System.out.print("請輸入選項 (1-7)：");
    }

    public static int readChoice(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.next();
            return -1;
        }
    }

    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 全部商品列表 ---");
        System.out.printf("%-6s %-12s %-8s %-6s\n", "編號", "商品名稱", "價格", "庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-8d %-15s %-10d %-6d\n", i + 1, names[i], prices[i], stocks[i]);
        }
    }

    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入商品編號:");
        try {
            int id = sc.nextInt();
            int index = id - 1;
            if (index >= 0 && index < names.length) {
                System.out.printf("\n【商品編號 %d 資訊】\n", id);
                System.out.printf("名稱：%s\n單價：%d\n庫存：%d\n", names[index], prices[index], stocks[index]);
            } else {
                System.out.println("【錯誤】找不到該編號之商品");
            }
        } catch (InputMismatchException e) {
            System.out.println("【錯誤】請輸入有效的整數編號");
            sc.next();
        }
    }

    public static boolean buyProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲購買的商品編號:");
        try {
            int id = sc.nextInt();
            int index = id - 1;
            if (index < 0 || index >= names.length) {
                System.out.println("【錯誤】商品編號不存在");
                return false;
            }

            System.out.printf("商品名稱：%s，目前庫存:%d。請輸入欲購買數量:", names[index], stocks[index]);
            int quantity = sc.nextInt();

            if (quantity <= 0) {
                System.out.println("【錯誤】購買數量必須大於 0");
                return false;
            }

            if (quantity > stocks[index]) {
                System.out.println("【錯誤】庫存不足，購買失敗");
                return false;
            }

            stocks[index] -= quantity;
            int cost = prices[index] * quantity;
            System.out.printf("購買成功！商品：%s，數量:%d，總花費:%d 元，剩餘庫存：%d。\n", names[index], quantity, cost, stocks[index]);
            return true;

        } catch (InputMismatchException e) {
            System.out.println("【錯誤】輸入格式錯誤，請輸入整數");
            sc.next();
            return false;
        }
    }

    public static boolean restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入欲補貨的商品編號:");
        try {
            int id = sc.nextInt();
            int index = id - 1;
            if (index < 0 || index >= names.length) {
                System.out.println("【錯誤】商品編號不存在");
                return false;
            }

            System.out.printf("商品名稱：%s，目前庫存：%d。請輸入補貨數量：", names[index], stocks[index]);
            int quantity = sc.nextInt();

            if (quantity <= 0) {
                System.out.println("【錯誤】補貨數量必須大於 0");
                return false;
            }

            stocks[index] += quantity;
            System.out.printf("補貨成功！商品：%s，新庫存為：%d。\n", names[index], stocks[index]);
            return true;

        } catch (InputMismatchException e) {
            System.out.println("【錯誤】輸入格式錯誤，請輸入整數");
            sc.next();
            return false;
        }
    }

    public static void showLowStock(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 低庫存商品清單 (庫存 < 10) ---");
        System.out.printf("%-6s %-12s %-8s %-6s\n", "編號", "商品名稱", "價格", "庫存");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%-8d %-15s %-10d %-6d\n", i + 1, names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前無低庫存商品。");
        }
    }

    public static void showTotalValue(int[] prices, int[] stocks) {
        int totalValue = 0;
        for (int i = 0; i < prices.length; i++) {
            totalValue += prices[i] * stocks[i];
        }
        System.out.printf("\n全部商品目前庫存總價值為：%d 元\n", totalValue);
    }

    public static void showSummary(int purchaseCount, int restockCount) {
        System.out.println("\n--- 系統操作摘要 ---");
        System.out.printf("本次執行期間累計成功購買：%d 次\n", purchaseCount);
        System.out.printf("本次執行期間累計成功補貨：%d 次\n", restockCount);
    }
}