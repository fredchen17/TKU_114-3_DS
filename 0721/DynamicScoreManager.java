import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> scores = new ArrayList<>();

        System.out.println("=== 動態成績管理系統 ===");
        System.out.println("請持續輸入成績 (0 - 100)，輸入 -1 結束輸入：");

        while (true) {
            System.out.print("請輸入成績: ");
            String input = scanner.nextLine().trim();

            try {
                double score = Double.parseDouble(input);

                if (score == -1) {
                    break;
                }

                if (score < 0 || score > 100) {
                    System.out.println("【錯誤】無效成績！成績必須介於 0 到 100 之間（或輸入 -1 結束）。");
                    continue;
                }

                scores.add(score);
            } catch (NumberFormatException e) {
                System.out.println("【錯誤】請輸入有效的數字格式！");
            }
        }

        System.out.println("\n----------------------------------------");
        if (scores.isEmpty()) {
            System.out.println("未輸入任何有效成績，程式結束");
        } else {
            printStatistics(scores);
        }

        scanner.close();
    }

    public static void printStatistics(List<Double> scores) {
        int count = getCount(scores);
        double average = getAverage(scores);
        double max = getMax(scores);
        double min = getMin(scores);
        List<Double> passingScores = getPassingScores(scores);

        System.out.println("=== 成績統計結果 ===");
        System.out.println("總筆數 : " + count + " 筆");
        System.out.printf("平均分數: %.2f 分\n", average);
        System.out.printf("最高分  : %.2f 分\n", max);
        System.out.printf("最低分  : %.2f 分\n", min);
        System.out.println("及格分數 (>= 60 分): " + passingScores);
    }

    public static int getCount(List<Double> scores) {
        return scores.size();
    }

    public static double getAverage(List<Double> scores) {
        if (scores.isEmpty()) return 0.0;
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }

    public static double getMax(List<Double> scores) {
        if (scores.isEmpty()) return 0.0;
        double max = scores.get(0);
        for (double score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public static double getMin(List<Double> scores) {
        if (scores.isEmpty()) return 0.0;
        double min = scores.get(0);
        for (double score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    public static List<Double> getPassingScores(List<Double> scores) {
        List<Double> passing = new ArrayList<>();
        for (double score : scores) {
            if (score >= 60) {
                passing.add(score);
            }
        }
        return passing;
    }
}