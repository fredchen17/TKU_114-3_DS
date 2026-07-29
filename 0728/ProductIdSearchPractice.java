import java.util.Scanner;

public class ProductIdSearchPractice {
    public static void main(String[] args) {
        String[] productIds = {
            "P103", "P205", "P118", "P009", "P402", "P311", "P507", "P220"
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的商品編號:");
        String targetId = scanner.nextLine().trim();

        int foundIndex = -1;
        int compareCount = 0;

        for (int i = 0; i < productIds.length; i++) {
            compareCount++;
            if (productIds[i].equalsIgnoreCase(targetId)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("成功找到商品 索引位置為: " + foundIndex);
        } else {
            System.out.println("找不到該商品編號: " + targetId);
        }

        System.out.println("實際比較次數: " + compareCount);

        scanner.close();
    }
}