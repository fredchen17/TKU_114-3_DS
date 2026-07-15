import java.util.Scanner;

public class ProductManagementSystem {

    public static void main(String[] args) {
        Product[] products = new Product[10];
        int count = initProducts(products);
        
        Scanner scanner = new Scanner(System.in);
        int sellCount = 0, restockCount = 0, priceChangeCount = 0;
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showAllProducts(products, count);
                    break;
                case "2":
                    searchProductByName(scanner, products, count);
                    break;
                case "3":
                    int newCount = handleAddProduct(scanner, products, count);
                    if (newCount > count) count = newCount;
                    break;
                case "4":
                    if (handleSellProduct(scanner, products, count)) sellCount++;
                    break;
                case "5":
                    if (handleRestockProduct(scanner, products, count)) restockCount++;
                    break;
                case "6":
                    if (handleChangePrice(scanner, products, count)) priceChangeCount++;
                    break;
                case "7":
                    showLowStockProducts(products, count);
                    break;
                case "8":
                    showTotalInventoryValue(products, count);
                    break;
                case "9":
                    showSummary(sellCount, restockCount, priceChangeCount);
                    running = false;
                    break;
                default:
                    System.out.println("無效選項，請重新輸入\n");
            }
        }
        scanner.close();
    }

    public static int initProducts(Product[] products) {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        return 5;
    }

    public static void printMenu() {
        System.out.println("=== 商品管理系統選單 ===");
        System.out.println("1. 顯示全部商品  2. 依名稱搜尋  3. 新增商品");
        System.out.println("4. 出售商品      5. 補充庫存    6. 修改價格");
        System.out.println("7. 低庫存商品    8. 庫存總價值  9. 結束系統");
        System.out.print("請選擇功能：");
    }

    public static void showAllProducts(Product[] products, int count) {
        System.out.println("\n--- 全部商品列表 ---");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
        System.out.println();
    }

    public static int findProductIndex(String name, Product[] products, int count) {
        if (name == null) return -1;
        String cleanName = name.trim();
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(cleanName)) {
                return i;
            }
        }
        return -1;
    }

    public static void searchProductByName(Scanner scanner, Product[] products, int count) {
        System.out.print("請輸入要搜尋的完整商品名稱:");
        String name = scanner.nextLine();
        int index = findProductIndex(name, products, count);
        
        System.out.println("\n--- 搜尋結果 ---");
        if (index != -1) {
            System.out.println("找到商品 -> " + products[index]);
        } else {
            System.out.println("找不到該商品");
        }
        System.out.println();
    }

    public static int handleAddProduct(Scanner scanner, Product[] products, int count) {
        if (count >= products.length) {
            System.out.println("【失敗】容量已滿，無法新增商品\n");
            return count;
        }

        System.out.print("請輸入新商品名稱:");
        String name = scanner.nextLine();
        
        if (findProductIndex(name, products, count) != -1) {
            System.out.println("【失敗】名稱重複，不可新增\n");
            return count;
        }

        System.out.print("請輸入價格:");
        int price = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("請輸入初始庫存:");
        int stock = Integer.parseInt(scanner.nextLine().trim());

        products[count] = new Product(name, price, stock);
        System.out.println("【成功】商品已成功新增\n");
        return count + 1;
    }

    public static boolean handleSellProduct(Scanner scanner, Product[] products, int count) {
        System.out.print("請輸入欲出售的商品名稱:");
        String name = scanner.nextLine();
        int index = findProductIndex(name, products, count);

        if (index == -1) {
            System.out.println("【失敗】找不到該商品\n");
            return false;
        }

        System.out.print("請輸入欲出售的數量:");
        int qty = Integer.parseInt(scanner.nextLine().trim());

        if (products[index].sell(qty)) {
            System.out.println("【成功】商品扣庫存成功\n");
            return true;
        } else {
            System.out.println("【失敗】數量無效或庫存不足\n");
            return false;
        }
    }

    public static boolean handleRestockProduct(Scanner scanner, Product[] products, int count) {
        System.out.print("請輸入欲補貨的商品名稱:");
        String name = scanner.nextLine();
        int index = findProductIndex(name, products, count);

        if (index == -1) {
            System.out.println("【失敗】找不到該商品\n");
            return false;
        }

        System.out.print("請輸入補貨數量:");
        int qty = Integer.parseInt(scanner.nextLine().trim());

        if (products[index].restock(qty)) {
            System.out.println("【成功】補貨成功\n");
            return true;
        } else {
            System.out.println("【失敗】補貨數量必須大於 0\n");
            return false;
        }
    }

    public static boolean handleChangePrice(Scanner scanner, Product[] products, int count) {
        System.out.print("請輸入欲修改價格的商品名稱:");
        String name = scanner.nextLine();
        int index = findProductIndex(name, products, count);

        if (index == -1) {
            System.out.println("【失敗】找不到該商品\n");
            return false;
        }

        System.out.print("請輸入新價格:");
        int newPrice = Integer.parseInt(scanner.nextLine().trim());

        if (products[index].setPrice(newPrice)) {
            System.out.println("【成功】價格修改成功\n");
            return true;
        } else {
            System.out.println("【失敗】價格必須大於 0\n");
            return false;
        }
    }

    public static void showLowStockProducts(Product[] products, int count) {
        System.out.println("\n--- 低庫存商品列表 (<10) ---");
        boolean hasLowStock = false;
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) System.out.println("無低庫存商品");
        System.out.println();
    }

    public static void showTotalInventoryValue(Product[] products, int count) {
        long totalValue = 0;
        for (int i = 0; i < count; i++) {
            totalValue += products[i].getInventoryValue();
        }
        System.out.println("\n>> 系統當前總庫存價值為:" + totalValue + " 元\n");
    }

    public static void showSummary(int sell, int restock, int priceChange) {
        System.out.println("\n========================");
        System.out.println("系統已關閉。今日操作摘要:");
        System.out.println("成功出售操作：" + sell + " 次");
        System.out.println("成功進貨操作：" + restock + " 次");
        System.out.println("成功改價操作：" + priceChange + " 次");
        System.out.println("========================");
    }
}

/*
 * 10組測試案例紀錄：
 * 1. 輸入 1 -> 正常印出 5 筆初始商品列表。
 * 2. 輸入 2 後輸入 "  kEyBoArD  " -> 忽略空白大小寫，成功搜尋。
 * 3. 輸入 3 後輸入 "Speaker", 1500, 10 -> 新增成功，剩餘 4 格空間。
 * 4. 輸入 3 後輸入 "Mouse" -> 觸發重複名稱限制，拒絕新增。
 * 5. 輸入 4 後輸入 "Monitor", 數量 2 -> 成功出售，庫存剩下 3。
 * 6. 輸入 4 後輸入 "Mouse", 數量 50 -> 庫存不足，觸發出售失敗。
 * 7. 輸入 5 後輸入 "Keyboard", 數量 10 -> 成功補貨，庫存變為 22。
 * 8. 輸入 6 後輸入 "Headset", 價格 -10 -> 觸發防呆，修改價格失敗。
 * 9. 輸入 7 -> 成功抓出庫存少於 10 的商品 (如 Monitor 剩 3)。
 * 10. 輸入 9 -> 順利結束，且精確結算各項操作的成功次數。
 */