// 程式功能：讀取使用者輸入的個人資料，並計算與顯示 BMI 健康數據
import java.util.Scanner;

public class HealthCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = scanner.nextLine(); 

        System.out.print("請輸入年齡：");
        int age = scanner.nextInt();       

        System.out.print("請輸入身高（公尺）：");
        double height = scanner.nextDouble();

        System.out.print("請輸入體重（公斤）：");
        double weight = scanner.nextDouble(); 

        double bmi = weight / (height * height);

        System.out.println("\n=== 個人健康資料 ===");
        System.out.println("姓名：" + name);
        System.out.println("年齡：" + age);
        System.out.println("身高：" + height);
        System.out.println("體重：" + weight);
        System.out.println("BMI：" + bmi);

        scanner.close();
    }
}