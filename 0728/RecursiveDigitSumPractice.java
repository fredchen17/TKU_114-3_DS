public class RecursiveDigitSumPractice {

    public static int digitSum(int number) {
        if (number < 10) {
            return number;
        }
        return (number % 10) + digitSum(number / 10);
    }

    public static void main(String[] args) {
        int[] testCases = {5729, 0, 7, 12345, 99999};
        
        for (int num : testCases) {
            System.out.println("digitSum(" + num + ") = " + digitSum(num));
        }
    }
}