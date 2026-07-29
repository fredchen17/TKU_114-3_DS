public class ProductSortingSystem {

    public static StoreProduct[] copyArray(StoreProduct[] source) {
        if (source == null) return null;
        StoreProduct[] copy = new StoreProduct[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = new StoreProduct(
                source[i].getId(),
                source[i].getName(),
                source[i].getPrice(),
                source[i].getStock()
            );
        }
        return copy;
    }

    public static void sortByPriceAscending(StoreProduct[] products) {
        if (products == null || products.length <= 1) return;
        int n = products.length;
        for (int i = 1; i < n; i++) {
            StoreProduct key = products[i];
            int j = i - 1;
            while (j >= 0 && products[j].getPrice() > key.getPrice()) {
                products[j + 1] = products[j];
                j--;
            }
            products[j + 1] = key;
        }
    }

    public static void sortByPriceDescending(StoreProduct[] products) {
        if (products == null || products.length <= 1) return;
        int n = products.length;
        for (int i = 1; i < n; i++) {
            StoreProduct key = products[i];
            int j = i - 1;
            while (j >= 0 && products[j].getPrice() < key.getPrice()) {
                products[j + 1] = products[j];
                j--;
            }
            products[j + 1] = key;
        }
    }

    public static void sortByStockDescending(StoreProduct[] products) {
        if (products == null || products.length <= 1) return;
        int n = products.length;
        for (int i = 1; i < n; i++) {
            StoreProduct key = products[i];
            int j = i - 1;
            while (j >= 0 && products[j].getStock() < key.getStock()) {
                products[j + 1] = products[j];
                j--;
            }
            products[j + 1] = key;
        }
    }

    public static void displayProducts(StoreProduct[] products, String field, String direction) {
        System.out.println("================================================================");
        System.out.println("排序欄位: " + field + " | 排序方向: " + direction);
        System.out.println("================================================================");
        for (StoreProduct p : products) {
            System.out.println(p);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StoreProduct[] originalProducts = {
            new StoreProduct("P001", "Gaming Mouse", 1290, 45),
            new StoreProduct("P002", "Mechanical KB", 3200, 12),
            new StoreProduct("P003", "27in Monitor", 7500, 8),
            new StoreProduct("P004", "USB-C Cable", 290, 150),
            new StoreProduct("P005", "Webcam 4K", 2800, 25),
            new StoreProduct("P006", "Headset Pro", 2200, 30),
            new StoreProduct("P007", "Mouse Pad XL", 450, 80),
            new StoreProduct("P008", "Bluetooth Mic", 1800, 18),
            new StoreProduct("P009", "Laptop Stand", 890, 60),
            new StoreProduct("P10", "RAM 16GB", 1500, 40)
        };

        StoreProduct[] test1 = copyArray(originalProducts);
        sortByPriceAscending(test1);
        displayProducts(test1, "價格 (Price)", "升冪 (Ascending)");

        StoreProduct[] test2 = copyArray(originalProducts);
        sortByPriceDescending(test2);
        displayProducts(test2, "價格 (Price)", "降冪 (Descending)");

        StoreProduct[] test3 = copyArray(originalProducts);
        sortByStockDescending(test3);
        displayProducts(test3, "庫存 (Stock)", "降冪 (Descending)");
    }
}