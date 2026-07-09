public class PricePrinter {
    public static void main(String[] args) {
        printItem("Apple",15);
        printItem("Banana",30);
    }   
    public static void printItem(String itemName, int price){ 
        System.out.println("itemName:" + itemName);
        System.out.println("price:" + price);

    }
}
