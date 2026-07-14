import java.util.Scanner;

public class ArrayInput {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入資料筆數：");
        int count = sc.nextInt();

        int[] values = new int[count];

        for (int i = 0; i < values.length; i++) {
            System.out.print("第 " + (i + 1) + " 筆：");
            values[i] = sc.nextInt();
        }
        for (int i = 0; i < values.length; i++) {
            System.out.println("索引 " + i + "：" + values[i]);
        }
    }
}
