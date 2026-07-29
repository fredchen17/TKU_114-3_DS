public class RecursiveNameSearchPractice {

    public static int search(String[] names, String target, int index) {
        if (names == null || index >= names.length) {
            return -1;
        }
        if (names[index] != null && names[index].equals(target)) {
            return index;
        }
        return search(names, target, index + 1);
    }

    public static void main(String[] args) {
        String[] emptyArray = {};
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma"};

        System.out.println("====== 遞迴姓名搜尋測試 ======");

        System.out.println("1. 空陣列搜尋 'Alice': " + search(emptyArray, "Alice", 0));

        System.out.println("2. 搜尋第一筆 'Alice': " + search(names, "Alice", 0));

        System.out.println("3. 搜尋最後一筆 'Emma': " + search(names, "Emma", 0));

        System.out.println("4. 搜尋不存在資料 'Frank': " + search(names, "Frank", 0));
    }
}