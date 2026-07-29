public class ScoreRankingPractice {

    public static void selectionSortDescending(int[] scores) {
        int n = scores.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (scores[j] > scores[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = scores[i];
                scores[i] = scores[maxIndex];
                scores[maxIndex] = temp;
            }
        }
    }

    public static void printRankings(int[] scores) {
        if (scores == null || scores.length == 0) {
            System.out.println("無成績資料。");
            return;
        }

        System.out.printf("%-6s | %-6s | %-6s%n", "名次", "分數", "是否及格");
        System.out.println("-------------------------");

        int currentRank = 1;
        for (int i = 0; i < scores.length; i++) {
            if (i > 0 && scores[i] != scores[i - 1]) {
                currentRank = i + 1;
            }
            String passStatus = (scores[i] >= 60) ? "及格" : "不及格";
            System.out.printf("第 %-3d 名 | %-8d | %-6s%n", currentRank, scores[i], passStatus);
        }
    }

    public static void main(String[] args) {
        int[] scores = {85, 92, 58, 85, 74, 92, 45, 60, 85};

        System.out.println("====== 成績排序與名次統計 ======");
        selectionSortDescending(scores);
        printRankings(scores);
    }
}