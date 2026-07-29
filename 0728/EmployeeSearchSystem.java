import java.util.Arrays;
import java.util.Comparator;

public class EmployeeSearchSystem {

    public static Employee searchById(Employee[] employees, String targetId) {
        if (employees == null || employees.length == 0 || targetId == null) {
            return null;
        }

        int low = 0;
        int high = employees.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = employees[mid].getId().compareTo(targetId);

            if (cmp == 0) {
                return employees[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }

    public static boolean checkDuplicateIds(Employee[] employees) {
        if (employees == null || employees.length <= 1) {
            return false;
        }
        
        boolean hasDuplicate = false;
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i].getId().equals(employees[i + 1].getId())) {
                System.out.println("警告: 發現重複的員工編號 [" + employees[i].getId() + "]");
                hasDuplicate = true;
            }
        }
        return hasDuplicate;
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E001", "張小明", "資訊部", "101"),
            new Employee("E003", "李美麗", "人事部", "102"),
            new Employee("E005", "王大衛", "業務部", "103"),
            new Employee("E008", "陳志強", "研發部", "104"),
            new Employee("E012", "林怡君", "財務部", "105")
        };

        Arrays.sort(employees, Comparator.comparing(Employee::getId));

        System.out.println("====== 員工查詢系統 ======");

        // 1. 正常搜尋
        System.out.println("--- 測試 1: 查詢存在編號 (E005) ---");
        Employee result1 = searchById(employees, "E005");
        if (result1 != null) {
            System.out.println("查詢成功:\n" + result1);
        } else {
            System.out.println("找不到該員工編號！");
        }

        // 2. 找不到情況
        System.out.println("\n--- 測試 2: 查詢不存在編號 (E999) ---");
        Employee result2 = searchById(employees, "E999");
        if (result2 != null) {
            System.out.println("查詢成功:\n" + result2);
        } else {
            System.out.println("找不到該員工編號: E999");
        }

        // 3. 空陣列處理
        System.out.println("\n--- 測試 3: 空陣列查詢 ---");
        Employee[] emptyEmployees = {};
        Employee result3 = searchById(emptyEmployees, "E001");
        if (result3 != null) {
            System.out.println("查詢成功:\n" + result3);
        } else {
            System.out.println("查詢失敗: 員工資料庫為空！");
        }

        // 4. 重複編號檢查與處理
        System.out.println("\n--- 測試 4: 處理重複編號資料集 ---");
        Employee[] duplicateEmployees = {
            new Employee("E001", "張小明", "資訊部", "101"),
            new Employee("E002", "李美麗", "人事部", "102"),
            new Employee("E002", "王大衛", "業務部", "103"),
            new Employee("E004", "陳志強", "研發部", "104")
        };
        Arrays.sort(duplicateEmployees, Comparator.comparing(Employee::getId));
        if (checkDuplicateIds(duplicateEmployees)) {
            System.out.println("提示: 建議先進行編號去重或修正後再執行檢索。");
        }
        Employee result4 = searchById(duplicateEmployees, "E002");
        if (result4 != null) {
            System.out.println("查詢成功（返回第一個比對到的資料）:\n" + result4);
        }
    }
}