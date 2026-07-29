import java.util.ArrayList;
import java.util.List;

public class SystemDesignExplanation {

    public static class FeatureDesign {
        private final int featureNumber;
        private final String featureName;
        private final String fileName;
        private final String methodName;
        private final String techniqueUsed;
        private final String choiceReason;
        private final String alternativeRejectedReason;

        public FeatureDesign(int featureNumber, String featureName, String fileName, 
                             String methodName, String techniqueUsed, 
                             String choiceReason, String alternativeRejectedReason) {
            this.featureNumber = featureNumber;
            this.featureName = featureName;
            this.fileName = fileName;
            this.methodName = methodName;
            this.techniqueUsed = techniqueUsed;
            this.choiceReason = choiceReason;
            this.alternativeRejectedReason = alternativeRejectedReason;
        }

        public int getFeatureNumber() { return featureNumber; }
        public String getFeatureName() { return featureName; }
        public String getFileName() { return fileName; }
        public String getMethodName() { return methodName; }
        public String getTechniqueUsed() { return techniqueUsed; }
        public String getChoiceReason() { return choiceReason; }
        public String getAlternativeRejectedReason() { return alternativeRejectedReason; }
    }

    public static class ComplexityComparison {
        private final String technique;
        private final String timeComplexity;
        private final String spaceComplexity;
        private final String mainAdvantage;

        public ComplexityComparison(String technique, String timeComplexity, String spaceComplexity, String mainAdvantage) {
            this.technique = technique;
            this.timeComplexity = timeComplexity;
            this.spaceComplexity = spaceComplexity;
            this.mainAdvantage = mainAdvantage;
        }

        public String getTechnique() { return technique; }
        public String getTimeComplexity() { return timeComplexity; }
        public String getSpaceComplexity() { return spaceComplexity; }
        public String getMainAdvantage() { return mainAdvantage; }
    }

    private final List<FeatureDesign> designs = new ArrayList<>();
    private final List<ComplexityComparison> comparisons = new ArrayList<>();

    public SystemDesignExplanation() {
        initDesigns();
        initComparisons();
    }

    private void initDesigns() {
        designs.add(new FeatureDesign(
                1,
                "保存全體報名資料",
                "EventRegistrationSystem.java",
                "register(), displayStatus()",
                "ArrayList",
                "提供 O(1) 的隨機索引存取，連續記憶體結構在轉為陣列給 Merge Sort 及遍歷輸出時效能最佳，並能自動動態擴充。",
                "未採用 LinkedList：LinkedList 每個節點有額外指標開銷，且隨機存取或轉陣列需 O(N) 走訪，會降低後續搜尋與排序效率。"
        ));

        designs.add(new FeatureDesign(
                2,
                "額滿後的候補管理",
                "EventRegistrationSystem.java",
                "register(), cancel()",
                "Queue (LinkedList 實作)",
                "嚴格遵循 FIFO（先進先出）原則，保證先登記候補者優先遞補；隊頭 poll() 與隊尾 offer() 操作時間複雜度皆為 O(1)。",
                "未採用 PriorityQueue：候補僅需依據時間順序，使用 PriorityQueue 需額外維護時間戳或權重，增加不必要的運算開銷。"
        ));

        designs.add(new FeatureDesign(
                3,
                "取消紀錄管理與復原",
                "EventRegistrationSystem.java",
                "cancel(), undoLastCancellation()",
                "Stack",
                "符合 LIFO（後進先出）的復原邏輯，使用者執行 Undo 時可直接取回最近一次取消的紀錄；push() 與 pop() 為 O(1)。",
                "未採用 ArrayList：Stack 具有明確的語意約束，可防止外部程式碼誤修改中間的歷史紀錄。"
        ));

        designs.add(new FeatureDesign(
                4,
                "報名名單按編號排序",
                "EventRegistrationSystem.java / RegistrationAlgorithms.java",
                "sortRegistrationsById(), mergeSortById()",
                "Merge Sort",
                "時間複雜度穩定維持在 O(N log N)，且為穩定排序（Stable Sort），在鍵值相同時可保留資料原始相對順序。",
                "未採用 Quick Sort / Selection Sort：Quick Sort 最壞情況可能退化至 O(N^2) 且非穩定排序；選擇排序時間複雜度固定為 O(N^2) 效率太低。"
        ));

        designs.add(new FeatureDesign(
                5,
                "依報名編號快速查詢",
                "EventRegistrationSystem.java / RegistrationAlgorithms.java",
                "searchById(), binarySearchById()",
                "Binary Search",
                "在已排序陣列中搜尋時間複雜度為 O(log N)，每次比較皆可排除一半範圍，萬筆資料最多僅需約 14 次比較。",
                "未採用 Sequential Search：Sequential Search 時間複雜度為 O(N)，當資料量較大時搜尋效率顯著低於 Binary Search。"
        ));

        designs.add(new FeatureDesign(
                6,
                "依特定姓名尋找所有紀錄",
                "EventRegistrationSystem.java / RegistrationAlgorithms.java",
                "searchByName(), sequentialSearchByName()",
                "Sequential Search",
                "名單僅針對「編號」排序而非「姓名」，且可能存在同名同姓資料。Sequential Search 可完整走訪 O(N) 收集所有符合條件的紀錄。",
                "未採用 Binary Search：Binary Search 要求資料必須先針對搜尋欄位排序。若每次搜姓名前都重新排序，成本遠高於直接循序搜尋。"
        ));
    }

    private void initComparisons() {
        comparisons.add(new ComplexityComparison("ArrayList", "存取 O(1) / 插入 O(1)", "O(N)", "連續記憶體，支援快速隨機存取與動態擴充"));
        comparisons.add(new ComplexityComparison("Queue (LinkedList)", "入列/出列 O(1)", "O(N)", "嚴格維持先進先出（FIFO）公平性"));
        comparisons.add(new ComplexityComparison("Stack", "推入/彈出 O(1)", "O(N)", "嚴格維持後進先出（LIFO）復原順序"));
        comparisons.add(new ComplexityComparison("Merge Sort", "O(N log N)", "O(N)", "效能極其穩定，且具備穩定排序特性"));
        comparisons.add(new ComplexityComparison("Binary Search", "O(log N)", "O(1)", "對數級高效查詢，大幅減少比較次數"));
        comparisons.add(new ComplexityComparison("Sequential Search", "O(N)", "O(1)", "不需要事先排序，支援一對多與未排序欄位查詢"));
    }

    public void printSummaryTable() {
        System.out.println("=========================================================================================================");
        System.out.println("                               活動報名系統 - 功能與資料結構/演算法對照表");
        System.out.println("=========================================================================================================");
        System.out.printf("%-4s | %-18s | %-28s | %-32s | %-18s%n", "編號", "功能名稱", "對應程式檔名", "對應 Method 名稱", "採用資料結構/演算法");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        for (FeatureDesign d : designs) {
            System.out.printf("%-4d | %-18s | %-28s | %-32s | %-18s%n",
                    d.getFeatureNumber(),
                    d.getFeatureName(),
                    d.getFileName(),
                    d.getMethodName(),
                    d.getTechniqueUsed());
        }
        System.out.println("=========================================================================================================\n");
    }

    public void printDetailedExplanations() {
        System.out.println("=========================================================================================================");
        System.out.println("                                  詳細選型原因與替代方案比較分析");
        System.out.println("=========================================================================================================");

        for (FeatureDesign d : designs) {
            System.out.println("【功能 " + d.getFeatureNumber() + "】" + d.getFeatureName());
            System.out.println("  * 對應程式與 Method: " + d.getFileName() + " -> " + d.getMethodName());
            System.out.println("  * 採用技術: " + d.getTechniqueUsed());
            System.out.println("  * 選擇原因: " + d.getChoiceReason());
            System.out.println("  * 未採用替代方案原因: " + d.getAlternativeRejectedReason());
            System.out.println("---------------------------------------------------------------------------------------------------------");
        }
        System.out.println();
    }

    public void printComplexityComparisonTable() {
        System.out.println("=========================================================================================================");
        System.out.println("                                    核心資料結構與演算法複雜度總覽");
        System.out.println("=========================================================================================================");
        System.out.printf("%-20s | %-22s | %-12s | %-35s%n", "資料 structures / 演算法", "時間複雜度 (平均/最壞)", "空間複雜度", "主要優勢");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        for (ComplexityComparison c : comparisons) {
            System.out.printf("%-20s | %-22s | %-12s | %-35s%n",
                    c.getTechnique(),
                    c.getTimeComplexity(),
                    c.getSpaceComplexity(),
                    c.getMainAdvantage());
        }
        System.out.println("=========================================================================================================\n");
    }

    public void generateFullReport() {
        printSummaryTable();
        printDetailedExplanations();
        printComplexityComparisonTable();
    }

    public static void main(String[] args) {
        SystemDesignExplanation explanation = new SystemDesignExplanation();
        explanation.generateFullReport();
    }
}