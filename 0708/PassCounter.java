public class PassCounter {
    public static void main(String[] args) {
        int score1 = 80;
        int score2 = 55;
        int score3 = 70;

        int Count = 0;

        if (score1 >= 60) {
            Count++;
        }

        if (score2 >= 60) {
            Count++;
        }

        if (score3 >= 60) {
            Count++;
        }

        System.out.println("Pass count: " + Count);
    }
}