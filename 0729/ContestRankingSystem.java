public class ContestRankingSystem {

    public static void insertionSortContestants(Contestant[] list) {
        if (list == null || list.length <= 1) {
            return;
        }

        int n = list.length;
        for (int i = 1; i < n; i++) {
            Contestant key = list[i];
            int j = i - 1;

            while (j >= 0 && shouldSwap(list[j], key)) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
    }

    private static boolean shouldSwap(Contestant current, Contestant key) {
        if (current.getScore() < key.getScore()) {
            return true;
        }
        if (current.getScore() == key.getScore() && current.getSeconds() > key.getSeconds()) {
            return true;
        }
        return false;
    }

    public static void printRankings(Contestant[] list) {
        if (list == null || list.length == 0) {
            System.out.println("無參賽者資料。");
            return;
        }

        System.out.println("==========================================================");
        System.out.println("                     競賽名次排行榜");
        System.out.println("==========================================================");

        int rank = 1;
        for (int i = 0; i < list.length; i++) {
            if (i > 0) {
                Contestant prev = list[i - 1];
                Contestant curr = list[i];
                if (prev.getScore() != curr.getScore() || prev.getSeconds() != curr.getSeconds()) {
                    rank = i + 1;
                }
            }
            System.out.printf("第 %-2d 名 | %s%n", rank, list[i]);
        }
        System.out.println("==========================================================");
    }

    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C101", "張小明", 85, 120),
            new Contestant("C102", "李美麗", 95, 110),
            new Contestant("C103", "王大衛", 85, 95),   
            new Contestant("C104", "陳志強", 95, 105),  
            new Contestant("C105", "林怡君", 70, 150),
            new Contestant("C106", "黃大飛", 85, 95),  
            new Contestant("C107", "趙小龍", 100, 130)
        };

        insertionSortContestants(contestants);
        printRankings(contestants);
    }
}