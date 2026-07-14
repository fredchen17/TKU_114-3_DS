public class ArrayStatisticsDemo {

    public static void main(String[] args) {
        int[] scores = {66, 74, 91, 78, 80};

        int total = 0;
        for (int score : scores) {
            total += score;
        }

        double average = 0.0;
        if (scores.length > 0) {
            average = (double) total / scores.length;
        }

        int max = scores[0];
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
            if (scores[i] < min) {
                min = scores[i];
            }
        }

        int passCount = 0;
        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }

        System.out.println("總分：" + total);
        System.out.println("平均：" + average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("及格人數：" + passCount);
    }
}