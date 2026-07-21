import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {

    private static ArrayList<CartItem> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== 購物車管理系統 ===");
            System.out.println("1. 加入商品");
            System.out.println("2. 修改商品數量");
            System.out.println("3. 移除商品");
            System.out.println("4. 檢視購物車與計算總額");
            System.out.println("5. 離開系統");
            System.out.print("請選擇操作選項: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addItem();
                    break;
                case "2":
                    updateQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    viewCart();
                    break;
                case "5":
                    System.out.println("感謝使用購物車系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效選項，請重新輸入。");
            }
        }
    }

    private static CartItem findById(String id) {
        for (CartItem item : cart) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    private static void addItem() {
        System.out.print("請輸入商品代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("【錯誤】商品代碼不可空白！");
            return;
        }

        CartItem existingItem = findById(id);

        if (existingItem != null) {
            System.out.println("【提示】購物車已有此商品 [" + existingItem.getName() + "]，目前數量: " + existingItem.getQuantity());
            System.out.print("請輸入欲累加的數量: ");
            try {
                int addQty = Integer.parseInt(scanner.nextLine().trim());
                if (addQty <= 0) {
                    System.out.println("【錯誤】增加的數量必須大於 0！");
                    return;
                }
                existingItem.setQuantity(existingItem.getQuantity() + addQty);
                System.out.println("【成功】已成功更新數量，目前總數量: " + existingItem.getQuantity());
            } catch (NumberFormatException e) {
                System.out.println("【錯誤】請輸入有效的整數數量！");
            }
        } else {
            System.out.print("請輸入商品名稱: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("【錯誤】商品名稱不可空白！");
                return;
            }

            System.out.print("請輸入商品單價: ");
            double price;
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price < 0) {
                    System.out.println("【錯誤】單價不可小於 0！");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("【錯誤】請輸入有效的單價金額！");
                return;
            }

            System.out.print("請輸入數量: ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println("【錯誤】數量必須大於 0！");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("【錯誤】請輸入有效的整數數量！");
                return;
            }

            cart.add(new CartItem(id, name, price, quantity));
            System.out.println("【成功】已成功加入購物車！");
        }
    }

    private static void updateQuantity() {
        System.out.print("請輸入要修改數量的商品代碼: ");
        String id = scanner.nextLine().trim();
        CartItem item = findById(id);

        if (item == null) {
            System.out.println("【錯誤】購物車中找不到代碼為 [" + id + "] 的商品！");
            return;
        }

        System.out.println("當前商品: " + item.getName() + "，當前數量: " + item.getQuantity());
        System.out.print("請輸入新的數量: ");
        try {
            int newQty = Integer.parseInt(scanner.nextLine().trim());
            if (newQty <= 0) {
                System.out.println("【錯誤】數量必須大於 0！（如欲移除商品請使用選單 3）");
                return;
            }
            item.setQuantity(newQty);
            System.out.println("【成功】商品數量已更新為 " + newQty + "！");
        } catch (NumberFormatException e) {
            System.out.println("【錯誤】請輸入有效的整數數量！");
        }
    }

    private static void removeItem() {
        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();
        CartItem item = findById(id);

        if (item == null) {
            System.out.println("【錯誤】購物車中找不到代碼為 [" + id + "] 的商品！");
            return;
        }

        cart.remove(item);
        System.out.println("【成功】已將商品 [" + item.getName() + "] 從購物車移除！");
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("【提示】購物車目前是空的。");
            return;
        }

        System.out.println("\n=== 購物車清單 ===");
        double grandTotal = 0.0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            System.out.println((i + 1) + ". " + item);
            grandTotal += item.getTotalPrice();
        }
        System.out.println("----------------------------------------");
        System.out.printf("購物車總金額: $%.2f\n", grandTotal);
    }
}