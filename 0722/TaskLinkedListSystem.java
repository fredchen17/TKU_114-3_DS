public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("=== 測試 1: 空串列狀態 ===");
        taskList.printUncompleted();
        taskList.printCounts();

        System.out.println("\n=== 測試 2: 新增工作 (一般 & 緊急) ===");
        taskList.addLast("T01", "寫 Java 作業");
        taskList.addLast("T02", "整理房間");
        taskList.addFirst("T99", "緊急：準備明天小考");
        
        System.out.println();
        taskList.printUncompleted();
        taskList.printCounts();

        System.out.println("\n=== 測試 3: 完成工作 ===");
        taskList.completeTask("T99");
        taskList.completeTask("T88");

        System.out.println();
        taskList.printUncompleted();
        taskList.printCounts();

        System.out.println("\n=== 測試 4: 刪除工作 ===");
        taskList.removeTask("T01");
        taskList.removeTask("T88");

        System.out.println();
        taskList.printUncompleted();
        taskList.printCounts();
    }
}