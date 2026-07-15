import java.util.Scanner;

public class ProductSearchSystem {

    private static final String[] PRODUCTS = {"iPhone 15", "MacBook Pro M3", "iPad Air", "AirPods Pro", "Apple Watch Ultra", "Studio Display"};
    private static final int[] PRICES = {29900, 54900, 19900, 74900, 25900, 45900};
    private static final int[] STOCKS = {50, 20, 35, 100, 15, 8};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作功能 (1-6)：");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showAllProducts();
                    break;
                case "2":
                    exactSearch(scanner);
                    break;
                case "3":
                    partialSearch(scanner);
                    break;
                case "4":
                    showLongestProductName();
                    break;
                case "5":
                    showKeywordIndex(scanner);
                    break;
                case "6":
                    System.out.println("系統已結束，謝謝使用！");
                    running = false;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。\n");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== 商品搜尋系統選單 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (可顯示多筆結果)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與搜尋關鍵字第一次出現的位置");
        System.out.println("6. 結束");
        System.out.println("========================");
    }

    private static void showAllProducts() {
        System.out.println("\n--- 全部商品列表 ---");
        for (int i = 0; i < PRODUCTS.length; i++) {
            System.out.printf("商品: %-18s | 價格: %-5d | 庫存: %-3d\n", PRODUCTS[i], PRICES[i], STOCKS[i]);
        }
        System.out.println();
    }

    private static void exactSearch(Scanner scanner) {
        System.out.print("請輸入完整商品名稱：");
        String input = scanner.nextLine().trim();
        boolean found = false;

        System.out.println("\n--- 搜尋結果 ---");
        for (int i = 0; i < PRODUCTS.length; i++) {
            if (PRODUCTS[i].trim().equalsIgnoreCase(input)) {
                System.out.printf("找到商品 -> 名稱: %s, 價格: %d, 庫存: %d\n", PRODUCTS[i], PRICES[i], STOCKS[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到完全符合的商品。");
        }
        System.out.println();
    }

    private static void partialSearch(Scanner scanner) {
        System.out.print("請輸入部分商品關鍵字：");
        String input = scanner.nextLine().trim().toLowerCase();
        boolean found = false;

        System.out.println("\n--- 搜尋結果 ---");
        for (int i = 0; i < PRODUCTS.length; i++) {
            if (PRODUCTS[i].toLowerCase().contains(input)) {
                System.out.printf("找到商品 -> 名稱: %s, 價格: %d, 庫存: %d\n", PRODUCTS[i], PRICES[i], STOCKS[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到包含該關鍵字的商品。");
        }
        System.out.println();
    }

    private static void showLongestProductName() {
        System.out.println("\n--- 名稱最長的商品 ---");
        int longestIndex = 0;
        for (int i = 1; i < PRODUCTS.length; i++) {
            if (PRODUCTS[i].length() > PRODUCTS[longestIndex].length()) {
                longestIndex = i;
            }
        }
        System.out.printf("名稱最長: %s (長度: %d 包含空白), 價格: %d, 庫存: %d\n\n", 
                PRODUCTS[longestIndex], PRODUCTS[longestIndex].length(), PRICES[longestIndex], STOCKS[longestIndex]);
    }

    private static void showKeywordIndex(Scanner scanner) {
        System.out.print("請輸入要尋找位置的關鍵字：");
        String input = scanner.nextLine(); 
        boolean found = false;

        System.out.println("\n--- 關鍵字第一次出現位置 (區分大小寫) ---");
        for (String product : PRODUCTS) {
            int index = product.indexOf(input);
            if (index != -1) {
                System.out.printf("商品名稱: %-18s | 關鍵字 \"%s\" 首次出現索引位置: %d\n", product, input, index);
                found = true;
            }
        }
        if (!found) {
            System.out.println("所有商品名稱中皆未包含該關鍵字字串。");
        }
        System.out.println();
    }
}

/*
自己測試過的 6 個 Case：

測試1: 看全部商品有沒有正常印出來
- 輸入 1
- 有出來，排版用 printf 蠻整齊的。

測試2: 查完整名字（故意打很多空白跟小寫）
- 輸入 2 之後輸入 "   macbook pro m3  "
- 有抓到！代表 trim() 跟 equalsIgnoreCase() 有成功防呆。

測試3: 亂打完整名字看會怎樣
- 輸入 2 之後輸入 "iPhone 14"
- 印出 "找不到完全符合的商品"，沒當機。

測試4: 用部分名字查看看，故意打小寫 "pro"
- 輸入 3 之後輸入 "pro"
- 跑出兩筆：MacBook Pro M3 跟 AirPods Pro。

測試5: 找名字最長的是誰
- 輸入 4
- 跑出 "MacBook Pro M3 (長度: 17 包含空白)"。

測試6: 找關鍵字在名字的第幾個位置（區分大小寫）
- 輸入 5 之後輸入 "Pro"
- 結果顯示：
  - MacBook Pro M3 的 Pro 在第 8 個位置
  - AirPods Pro 的 Pro 在第 8 個位置
  - 其他沒中。
*/