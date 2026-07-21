import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class CounterServiceSystem {

    static class Customer {
        int number;
        String name;

        public Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
            return "號碼: " + number + " (" + name + ")";
        }
    }

    private Queue<Customer> queue = new LinkedList<>();
    private ArrayList<Customer> history = new ArrayList<>();
    private int nextNumber = 1;

    public void takeNumber(String name) {
        Customer c = new Customer(nextNumber++, name);
        queue.add(c);
        System.out.println("取號成功: " + c);
    }

    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("目前沒有等待的客人！");
            return;
        }
        Customer c = queue.poll();
        history.add(c);
        System.out.println("叫號: " + c + " 請到櫃台辦理");
    }

    public void peekNext() {
        if (queue.isEmpty()) {
            System.out.println("目前沒有下一位客人。");
        } else {
            System.out.println("下一位等待者: " + queue.peek());
        }
    }

    public void showWaitingCount() {
        System.out.println("目前等待人數: " + queue.size() + " 人");
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("尚無處理紀錄。");
            return;
        }
        System.out.println("--- 歷史服務紀錄 ---");
        for (Customer c : history) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("=== 測試 1: 空 Queue 操作 ===");
        system.showWaitingCount();
        system.peekNext();
        system.callNext();

        System.out.println("\n=== 測試 2: 取號與叫號流程 ===");
        system.takeNumber("張三");
        system.takeNumber("李四");
        system.takeNumber("王五");

        system.showWaitingCount();
        system.peekNext();

        system.callNext();
        system.callNext();

        system.showWaitingCount();
        system.peekNext();

        system.takeNumber("趙六");
        system.callNext();
        system.callNext();
        system.callNext();

        System.out.println("\n=== 測試 3: 檢視叫號紀錄 ===");
        system.showHistory();
    }
}