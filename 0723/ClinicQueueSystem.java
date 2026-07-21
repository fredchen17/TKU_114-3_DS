import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public class ClinicQueueSystem {

    private Queue<Patient> queue = new LinkedList<>();
    private int servedCount = 0;

    public boolean register(int number, String name, String department) {
        for (Patient p : queue) {
            if (p.number == number) {
                System.out.println("掛號失敗：號碼 [" + number + "] 已存在！");
                return false;
            }
        }
        Patient p = new Patient(number, name, department);
        queue.add(p);
        System.out.println("掛號成功: " + p);
        return true;
    }

    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("目前沒有等待叫號的病患！");
            return;
        }
        Patient p = queue.poll();
        servedCount++;
        System.out.println("叫號成功: " + p + " 請進診間看診");
    }

    public void peekNext() {
        if (queue.isEmpty()) {
            System.out.println("目前沒有下一位等待的病患。");
        } else {
            System.out.println("下一位看診病患: " + queue.peek());
        }
    }

    public void printWaitingList() {
        if (queue.isEmpty()) {
            System.out.println("目前等待清單是空的。");
            return;
        }
        System.out.println("--- 目前等待看診清單 ---");
        for (Patient p : queue) {
            System.out.println(p);
        }
    }

    public void printStats() {
        System.out.println("\n=== 診所統計數據 ===");
        System.out.println("總服務人數: " + servedCount + " 人");

        HashMap<String, Integer> deptMap = new HashMap<>();
        for (Patient p : queue) {
            deptMap.put(p.department, deptMap.getOrDefault(p.department, 0) + 1);
        }

        System.out.println("各科別等待人數:");
        if (deptMap.isEmpty()) {
            System.out.println("  (無人等待)");
        } else {
            for (String dept : deptMap.keySet()) {
                System.out.println("  - " + dept + ": " + deptMap.get(dept) + " 人");
            }
        }
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("=== 測試 1: 空 Queue 操作 ===");
        clinic.peekNext();
        clinic.callNext();
        clinic.printWaitingList();
        clinic.printStats();

        System.out.println("\n=== 測試 2: 掛號與號碼重複檢查 ===");
        clinic.register(101, "王小明", "內科");
        clinic.register(102, "李大華", "外科");
        clinic.register(101, "張阿姨", "眼科");
        clinic.register(103, "陳小姐", "內科");

        System.out.println();
        clinic.printWaitingList();

        System.out.println("\n=== 測試 3: 看診叫號流程 ===");
        clinic.peekNext();
        clinic.callNext();
        clinic.callNext();

        System.out.println();
        clinic.printWaitingList();
        clinic.printStats();
    }
}