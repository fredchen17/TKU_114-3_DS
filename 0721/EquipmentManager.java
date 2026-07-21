import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {

    private static ArrayList<Equipment> list = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== 設備借還管理系統 ===");
            System.out.println("1. 新增設備");
            System.out.println("2. 搜尋設備 (依代碼)");
            System.out.println("3. 借出設備");
            System.out.println("4. 歸還設備");
            System.out.println("5. 列出所有可借用設備");
            System.out.println("6. 離開系統");
            System.out.print("請選擇操作項: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addEquipment();
                    break;
                case "2":
                    searchEquipment();
                    break;
                case "3":
                    borrowEquipment();
                    break;
                case "4":
                    returnEquipment();
                    break;
                case "5":
                    listAvailableEquipment();
                    break;
                case "6":
                    System.out.println("感謝使用，系統已關閉。");
                    sc.close();
                    return;
                default:
                    System.out.println("【錯誤】無效選項，請輸入 1 至 6。");
            }
        }
    }

    private static Equipment findById(String id) {
        for (Equipment eq : list) {
            if (eq.getId().equalsIgnoreCase(id)) {
                return eq;
            }
        }
        return null;
    }

    private static void addEquipment() {
        System.out.print("請輸入設備代碼: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("【錯誤】代碼不能為空白！");
            return;
        }

        if (findById(id) != null) {
            System.out.println("【錯誤】設備代碼 [" + id + "] 已存在，不可重複！");
            return;
        }

        System.out.print("請輸入設備名稱: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("【錯誤】名稱不能為空白！");
            return;
        }

        list.add(new Equipment(id, name));
        System.out.println("【成功】已成功新增設備！");
    }

    private static void searchEquipment() {
        System.out.print("請輸入要搜尋的設備代碼: ");
        String id = sc.nextLine().trim();
        Equipment eq = findById(id);
        if (eq != null) {
            System.out.println("找到設備：");
            System.out.println(eq);
        } else {
            System.out.println("【結果】查無代碼為 [" + id + "] 的設備。");
        }
    }

    private static void borrowEquipment() {
        System.out.print("請輸入欲借出的設備代碼: ");
        String id = sc.nextLine().trim();
        Equipment eq = findById(id);

        if (eq == null) {
            System.out.println("【錯誤】找不到代碼為 [" + id + "] 的設備！");
        } else if (!eq.isAvailable()) {
            System.out.println("【提示】該設備 [" + eq.getName() + "] 目前已被借出，無法重複借用！");
        } else {
            eq.setAvailable(false);
            System.out.println("【成功】成功借出設備 [" + eq.getName() + "]！");
        }
    }

    private static void returnEquipment() {
        System.out.print("請輸入欲歸還的設備代碼: ");
        String id = sc.nextLine().trim();
        Equipment eq = findById(id);

        if (eq == null) {
            System.out.println("【錯誤】找不到代碼為 [" + id + "] 的設備！");
        } else if (eq.isAvailable()) {
            System.out.println("【提示】該設備 [" + eq.getName() + "] 目前為可借用狀態，並未借出！");
        } else {
            eq.setAvailable(true);
            System.out.println("【成功】成功歸還設備 [" + eq.getName() + "]！");
        }
    }

    private static void listAvailableEquipment() {
        System.out.println("--- 目前可借用設備清單 ---");
        boolean hasAvailable = false;
        for (Equipment eq : list) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                hasAvailable = true;
            }
        }

        if (!hasAvailable) {
            System.out.println("目前沒有可借用的設備。");
        }
    }
}