import java.util.InputMismatchException;
import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        showMatrix(sales);
        showProductTotals(sales);
        showDailyTotals(sales);
        showTopProduct(sales);

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    try {
                        System.out.printf("請輸入商品 %d 第 %d 天的銷售量 (>=0)：", i + 1, j + 1);
                        int value = sc.nextInt();
                        if (value >= 0) {
                            sales[i][j] = value;
                            break;
                        }
                        System.out.println("【錯誤】銷售量不得小於 0，請重新輸入！");
                    } catch (InputMismatchException e) {
                        System.out.println("【錯誤】請輸入有效的整數！");
                        sc.next();
                    }
                }
            }
        }
    }

    public static void showMatrix(int[][] sales) {
        System.out.println("\n--- 銷售量矩陣表 ---");
        System.out.println("\tDay 1\tDay 2\tDay 3\tDay 4");
        for (int i = 0; i < sales.length; i++) {
            System.out.printf("商品 %d\t", i + 1);
            for (int j = 0; j < sales[i].length; j++) {
                System.out.print(sales[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void showProductTotals(int[][] sales) {
        System.out.println("\n--- 每項商品銷售總量 ---");
        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            System.out.printf("商品 %d 銷售總量：%d\n", i + 1, total);
        }
    }

    public static void showDailyTotals(int[][] sales) {
        System.out.println("\n--- 每天商品銷售總量 ---");
        for (int j = 0; j < sales[0].length; j++) {
            int total = 0;
            for (int i = 0; i < sales.length; i++) {
                total += sales[i][j];
            }
            System.out.printf("第 %d 天全部商品銷售總量：%d\n", j + 1, total);
        }
    }

    public static void showTopProduct(int[][] sales) {
        System.out.println("\n--- 暢銷商品統計 ---");
        int maxSales = -1;
        int topProductIndex = -1;

        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            if (total > maxSales) {
                maxSales = total;
                topProductIndex = i;
            }
        }
        System.out.printf("總銷售量最高的商品是：商品 %d (總銷售量：%d)\n", topProductIndex + 1, maxSales);
    }
}