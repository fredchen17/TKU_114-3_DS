import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入一個整數:");
        int number = sc.nextInt();

        while (number != 0) {
            System.out.println("你輸入的數字是: " + number);            
            
        }       
        
        sc.close();
    }
}