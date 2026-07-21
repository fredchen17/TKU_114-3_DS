import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;

public class DeliveryProcessingSystem {

    private Queue<DeliveryTask> pendingQueue = new LinkedList<>();
    private Stack<DeliveryTask> completedStack = new Stack<>();
    private ArrayList<String> logs = new ArrayList<>();

    public void addTask(String taskId, String address) {
        DeliveryTask task = new DeliveryTask(taskId, address);
        pendingQueue.add(task);
        logs.add("新增配送工作: " + task);
        System.out.println("新增成功: " + task);
    }

    public void processNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("目前沒有等待配送的工作！");
            return;
        }
        DeliveryTask task = pendingQueue.poll();
        completedStack.push(task);
        logs.add("完成配送工作: " + task);
        System.out.println("完成配送: " + task);
    }

    public void peekNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("目前沒有等待配送的工作。");
        } else {
            System.out.println("下一筆待配送工作: " + pendingQueue.peek());
        }
    }

    public void undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("沒有已完成的工作可以復原！");
            return;
        }
        DeliveryTask task = completedStack.pop();
        pendingQueue.add(task);
        logs.add("復原最近完成工作(回到隊列尾端): " + task);
        System.out.println("復原成功: " + task + " 已重新加入等待隊列尾端");
    }

    public void printStatsAndLogs() {
        System.out.println("\n=== 系統狀態與統計 ===");
        System.out.println("等待配送數量: " + pendingQueue.size());
        System.out.println("已完成數量   : " + completedStack.size());

        System.out.println("\n--- 所有處理紀錄 ---");
        if (logs.isEmpty()) {
            System.out.println("尚無處理紀錄。");
        } else {
            for (int i = 0; i < logs.size(); i++) {
                System.out.println((i + 1) + ". " + logs.get(i));
            }
        }
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("=== 測試 1: 空結構操作 ===");
        system.peekNext();
        system.processNext();
        system.undoLastCompleted();
        system.printStatsAndLogs();

        System.out.println("\n=== 測試 2: 新增與配送 ===");
        system.addTask("D01", "台北市信義區路一段1號");
        system.addTask("D02", "新北市淡水區中正路2號");
        system.addTask("D03", "台中市西區台灣大道3號");

        System.out.println();
        system.peekNext();
        system.processNext();
        system.processNext();

        System.out.println("\n=== 測試 3: 復原最近完成 ===");
        system.undoLastCompleted();

        System.out.println();
        system.peekNext();

        System.out.println("\n=== 測試 4: 最終處理與狀態統計 ===");
        system.processNext();
        system.processNext();
        system.printStatsAndLogs();
    }
}