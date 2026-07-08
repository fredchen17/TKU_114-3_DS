import java.util.Scanner;

public class QuantityValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入商品數量(必須大於0)：");
        int Q = sc.nextInt();

        while (Q < 0 || Q ==0) {
            System.out.print("數量不合法，請重新輸入（0以上的數字）：");
            Q = sc.nextInt();
        }

        System.out.println("Quantity: " + Q);

        sc.close();
    }
}
