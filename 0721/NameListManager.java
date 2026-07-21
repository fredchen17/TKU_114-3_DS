import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();

        while (true) {
            System.out.println("=== 姓名管理系統 ===");
            System.out.println("1. 新增");
            System.out.println("2. 搜尋");
            System.out.println("3. 修改");
            System.out.println("4. 刪除");
            System.out.println("5. 列出全部");
            System.out.println("6. 離開");
            System.out.print("請選擇功能: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("輸入要新增的姓名: ");
                String name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("錯誤：姓名不能空白！");
                } else {
                    names.add(name);
                    System.out.println("新增成功！");
                }

            } else if (choice.equals("2")) {
                System.out.print("輸入要搜尋的姓名: ");
                String target = sc.nextLine().trim();
                boolean found = false;
                for (String n : names) {
                    if (n.equalsIgnoreCase(target)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println("找到了！名單中有 " + target);
                } else {
                    System.out.println("找不到該姓名！");
                }

            } else if (choice.equals("3")) {
                System.out.print("輸入要修改的舊姓名: ");
                String oldName = sc.nextLine().trim();
                int index = -1;
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).equalsIgnoreCase(oldName)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    System.out.print("輸入新姓名: ");
                    String newName = sc.nextLine().trim();
                    if (newName.isEmpty()) {
                        System.out.println("錯誤：新姓名不能空白！");
                    } else {
                        names.set(index, newName);
                        System.out.println("修改成功！");
                    }
                } else {
                    System.out.println("找不到要修改的姓名！");
                }

            } else if (choice.equals("4")) {
                System.out.print("輸入要刪除的姓名: ");
                String delName = sc.nextLine().trim();
                int removeIndex = -1;
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).equalsIgnoreCase(delName)) {
                        removeIndex = i;
                        break;
                    }
                }

                if (removeIndex != -1) {
                    names.remove(removeIndex);
                    System.out.println("刪除成功！");
                } else {
                    System.out.println("找不到該姓名，刪除失敗！");
                }

            } else if (choice.equals("5")) {
                if (names.isEmpty()) {
                    System.out.println("目前沒有任何姓名。");
                } else {
                    System.out.println("--- 姓名清單 ---");
                    for (int i = 0; i < names.size(); i++) {
                        System.out.println((i + 1) + ". " + names.get(i));
                    }
                }

            } else if (choice.equals("6")) {
                System.out.println("謝謝使用，再見！");
                break;

            } else {
                System.out.println("無效選項，請重新輸入！");
            }

            System.out.println();
        }

        sc.close();
    }
}