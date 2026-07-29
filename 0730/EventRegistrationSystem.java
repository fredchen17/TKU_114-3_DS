import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class EventRegistrationSystem {

    private final int capacity;
    private final List<Registration> registrations = new ArrayList<>();
    private final Queue<Registration> waitlist = new LinkedList<>();
    private final Stack<Registration> cancellationHistory = new Stack<>();
    private final Set<String> registeredIds = new HashSet<>();
    private boolean isSorted = false;

    public EventRegistrationSystem(int capacity) {
        this.capacity = capacity;
    }

    public boolean register(Registration reg) {
        if (reg == null || reg.getId() == null) {
            System.out.println("報名失敗：無效的報名資料。");
            return false;
        }

        if (registeredIds.contains(reg.getId())) {
            System.out.println("報名失敗：編號重複 [" + reg.getId() + "]");
            return false;
        }

        if (registrations.size() < capacity) {
            registrations.add(reg);
            registeredIds.add(reg.getId());
            isSorted = false;
            System.out.println("報名成功：" + reg);
            return true;
        } else {
            waitlist.offer(reg);
            registeredIds.add(reg.getId());
            System.out.println("活動已額滿，已加入候補名單：" + reg);
            return false;
        }
    }

    public boolean cancel(String id) {
        if (id == null) {
            return false;
        }

        Registration target = null;
        for (Registration reg : registrations) {
            if (reg.getId().equals(id)) {
                target = reg;
                break;
            }
        }

        if (target != null) {
            registrations.remove(target);
            registeredIds.remove(target.getId());
            cancellationHistory.push(target);
            isSorted = false;
            System.out.println("成功取消報名：" + target);

            if (!waitlist.isEmpty()) {
                Registration promoted = waitlist.poll();
                registrations.add(promoted);
                isSorted = false;
                System.out.println("從候補遞補成功：" + promoted);
            } else {
                System.out.println("目前無候補人員。");
            }
            return true;
        }

        Registration waitlistTarget = null;
        for (Registration reg : waitlist) {
            if (reg.getId().equals(id)) {
                waitlistTarget = reg;
                break;
            }
        }

        if (waitlistTarget != null) {
            waitlist.remove(waitlistTarget);
            registeredIds.remove(waitlistTarget.getId());
            cancellationHistory.push(waitlistTarget);
            System.out.println("成功取消候補紀錄：" + waitlistTarget);
            return true;
        }

        System.out.println("取消失敗：找不到編號為 [" + id + "] 的報名資料。");
        return false;
    }

    public boolean undoLastCancellation() {
        if (cancellationHistory.isEmpty()) {
            System.out.println("復原失敗：目前沒有取消紀錄可供復原。");
            return false;
        }

        Registration restored = cancellationHistory.pop();
        if (registeredIds.contains(restored.getId())) {
            System.out.println("復原失敗：編號 [" + restored.getId() + "] 已存在於系統中。");
            return false;
        }

        if (registrations.size() < capacity) {
            registrations.add(restored);
            registeredIds.add(restored.getId());
            isSorted = false;
            System.out.println("成功復原至正式報名名單：" + restored);
        } else {
            Queue<Registration> newWaitlist = new LinkedList<>();
            newWaitlist.offer(restored);
            newWaitlist.addAll(waitlist);
            waitlist.clear();
            waitlist.addAll(newWaitlist);
            registeredIds.add(restored.getId());
            System.out.println("正式名單已滿，成功復原至候補名單最前順位：" + restored);
        }
        return true;
    }

    public void sortRegistrationsById() {
        if (registrations.isEmpty()) {
            System.out.println("沒有可供排序的報名資料。");
            return;
        }
        Registration[] arr = registrations.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(arr, 0, arr.length - 1);
        registrations.clear();
        for (Registration reg : arr) {
            registrations.add(reg);
        }
        isSorted = true;
        System.out.println("正式報名名單已依編號完成 Merge Sort 排序。");
    }

    public Registration searchById(String id) {
        if (registrations.isEmpty()) {
            System.out.println("查詢失敗：名單為空。");
            return null;
        }

        if (!isSorted) {
            sortRegistrationsById();
        }

        Registration[] arr = registrations.toArray(new Registration[0]);
        int index = RegistrationAlgorithms.binarySearchById(arr, id);

        if (index != -1) {
            System.out.println("二元搜尋成功（Binary Search），找到資料：" + arr[index]);
            return arr[index];
        } else {
            System.out.println("二元搜尋（Binary Search）：正式名單中未找到編號 [" + id + "]。");
            return null;
        }
    }

    public List<Registration> searchByName(String name) {
        List<Registration> results = RegistrationAlgorithms.sequentialSearchByName(registrations, name);
        System.out.println("順序搜尋（Sequential Search）姓名 [" + name + "]，共找到 " + results.size() + " 筆資料：");
        for (Registration reg : results) {
            System.out.println("  " + reg);
        }
        return results;
    }

    public void displayStatus() {
        System.out.println("\n=== 系統當前狀態統計 ===");
        System.out.println("正式報名人數：" + registrations.size() + " / " + capacity);
        for (Registration reg : registrations) {
            System.out.println("  [正式] " + reg);
        }
        System.out.println("候補人數：" + waitlist.size());
        for (Registration reg : waitlist) {
            System.out.println("  [候補] " + reg);
        }
        System.out.println("已取消紀錄數量（Stack）：" + cancellationHistory.size());
        System.out.println("=========================\n");
    }

    public static void main(String[] args) {
        EventRegistrationSystem system = new EventRegistrationSystem(3);

        System.out.println("--- 1. 測試邊界條件（空狀態處理） ---");
        system.displayStatus();
        system.cancel("R999");
        system.undoLastCancellation();

        System.out.println("\n--- 2. 測試正常報名與重複編號 ---");
        system.register(new Registration("R03", "張小明", "ming@example.com"));
        system.register(new Registration("R01", "李大華", "hua@example.com"));
        system.register(new Registration("R04", "張小明", "ming2@example.com"));
        system.register(new Registration("R01", "重複測試", "dup@example.com"));

        System.out.println("\n--- 3. 測試額滿與候補佇列（Queue） ---");
        system.register(new Registration("R02", "王美麗", "mei@example.com"));
        system.register(new Registration("R05", "陳阿吉", "ji@example.com"));
        system.displayStatus();

        System.out.println("--- 4. 測試搜尋演算法（Binary & Sequential Search） ---");
        system.searchById("R01");
        system.searchById("R09");
        system.searchByName("張小明");

        System.out.println("\n--- 5. 測試取消報名與自動遞補 ---");
        system.cancel("R01");
        system.displayStatus();

        System.out.println("--- 6. 測試復原取消（Stack 堆疊復原） ---");
        system.undoLastCancellation();
        system.displayStatus();

        System.out.println("--- 7. 測試取消不存在的資料 ---");
        system.cancel("NOT_EXIST");
    }
}