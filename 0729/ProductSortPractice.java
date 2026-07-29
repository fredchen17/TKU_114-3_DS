public class ProductSortPractice {

    public static void insertionSortByPrice(Product[] products) {
        if (products == null || products.length <= 1) {
            return;
        }

        int n = products.length;

        for (int i = 1; i < n; i++) {
            Product key = products[i];
            int j = i - 1;

            while (j >= 0 && products[j].getPrice() > key.getPrice()) {
                products[j + 1] = products[j];
                j--;
            }
            products[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Keyboard A", 1290, 50),
            new Product("P002", "Mouse Alpha", 650, 100),
            new Product("P003", "Monitor 24in", 5200, 15),
            new Product("P004", "Mouse Beta", 650, 80),
            new Product("P005", "Headset X", 1290, 30),
            new Product("P006", "Webcam HD", 1500, 20),
            new Product("P007", "Mouse Gamma", 650, 45),
            new Product("P008", "USB Hub", 450, 200)
        };

        System.out.println("====== 排序前商品清單 ======");
        for (Product p : products) {
            System.out.println(p);
        }

        insertionSortByPrice(products);

        System.out.println("\n====== 依價格升冪排序後商品清單 (保持穩定排序) ======");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}