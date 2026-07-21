import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {

    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== 通訊錄管理系統 ===");
            System.out.println("1. 新增聯絡人");
            System.out.println("2. 搜尋聯絡人 (依代碼或姓名)");
            System.out.println("3. 修改電話");
            System.out.println("4. 刪除聯絡人");
            System.out.println("5. 列出完整清單");
            System.out.println("6. 結束系統");
            System.out.print("請選擇操作項目: ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    updatePhone();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    listAllContacts();
                    break;
                case "6":
                    System.out.println("系統已結束，謝謝使用！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效的操作選項，請重新輸入。");
            }
        }
    }

    public static Contact findById(String id) {
        for (Contact c : contacts) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public static void addContact() {
        System.out.print("請輸入聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("【錯誤】代碼不可空白！");
            return;
        }

        if (findById(id) != null) {
            System.out.println("【錯誤】代碼 [" + id + "] 已存在，無法重複新增！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("【錯誤】姓名不可空白！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入電子郵件: ");
        String email = scanner.nextLine().trim();

        contacts.add(new Contact(id, name, phone, email));
        System.out.println("【成功】成功新增聯絡人！");
    }

    public static void searchContact() {
        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("【錯誤】搜尋關鍵字不可空白！");
            return;
        }

        boolean found = false;
        for (Contact c : contacts) {
            if (c.getId().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                if (!found) {
                    System.out.println("--- 搜尋結果 ---");
                }
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("【提示】查無符合 [" + keyword + "] 的聯絡人。");
        }
    }

    public static void updatePhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        Contact contact = findById(id);

        if (contact == null) {
            System.out.println("【錯誤】找不到代碼為 [" + id + "] 的聯絡人！");
            return;
        }

        System.out.println("目前聯絡人資訊: " + contact);
        System.out.print("請輸入新的電話號碼: ");
        String newPhone = scanner.nextLine().trim();
        if (newPhone.isEmpty()) {
            System.out.println("【錯誤】電話號碼不可空白！");
            return;
        }

        contact.setPhone(newPhone);
        System.out.println("【成功】電話號碼已更新！");
    }

    public static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        Contact contact = findById(id);

        if (contact == null) {
            System.out.println("【錯誤】找不到代碼為 [" + id + "] 的聯絡人，刪除失敗！");
            return;
        }

        contacts.remove(contact);
        System.out.println("【成功】已成功刪除代碼為 [" + id + "] 的聯絡人！");
    }

    public static void listAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("【提示】目前通訊錄沒有任何聯絡人。");
            return;
        }

        System.out.println("=== 完整通訊錄清單 (" + contacts.size() + " 筆) ===");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }
}