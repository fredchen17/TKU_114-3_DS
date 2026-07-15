public class RectangleDemo {
    public static void printResult(Rectangle rectangle) {
        System.out.println(rectangle);
        System.out.printf("面積：%.2f%n", rectangle.calculateArea());
        System.out.printf("周長：%.2f%n", rectangle.calculatePerimeter());
        System.out.println("正方形：" + rectangle.isSquare());
        System.out.println();
    }

    public static void main(String[] args) {
        Rectangle first = new Rectangle(5.0, 3.0);
        Rectangle second = new Rectangle(4.0, 4.0);
        Rectangle third = new Rectangle(8.5, 2.0);

        printResult(first);
        printResult(second);
        printResult(third);
    }
}