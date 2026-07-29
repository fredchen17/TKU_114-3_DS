public class RecursiveDigitCounter {

    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            throw new IllegalArgumentException("Target must be between 0 and 9");
        }
        
        number = Math.abs(number);

        if (number < 10) {
            return (number == target) ? 1 : 0;
        }

        int lastDigit = number % 10;
        int currentMatch = (lastDigit == target) ? 1 : 0;

        return currentMatch + countDigit(number / 10, target);
    }

    public static void main(String[] args) {
        System.out.println("====== 遞迴數字出現次數統計測試 ======");

        // 1. 測試一般重複數字
        System.out.println("1. countDigit(737271, 7) = " + countDigit(737271, 7));

        // 2. 測試目標不存在
        System.out.println("2. countDigit(12345, 9) = " + countDigit(12345, 9));

        // 3. 測試數字 0 的處理
        System.out.println("3. countDigit(0, 0) = " + countDigit(0, 0));

        // 4. 測試包含多個 0 的數字
        System.out.println("4. countDigit(1020300, 0) = " + countDigit(1020300, 0));

        // 5. 測試單一數字（命中）
        System.out.println("5. countDigit(5, 5) = " + countDigit(5, 5));

        // 6. 測試所有位數皆相同的數字
        System.out.println("6. countDigit(88888, 8) = " + countDigit(88888, 8));
    }
}