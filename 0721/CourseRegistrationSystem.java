import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private static ArrayList<Course> courses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== 選課管理系統 ===");
            System.out.println("1. 新增課程");
            System.out.println("2. 學生選課");
            System.out.println("3. 學生退選");
            System.out.println("4. 刪除課程");
            System.out.println("5. 搜尋課程 (依代碼或名稱)");
            System.out.println("6. 輸出課程統計與額滿名單");
            System.out.println("7. 離開系統");
            System.out.print("請選擇操作選項: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    enrollCourse();
                    break;
                case "3":
                    dropCourse();
                    break;
                case "4":
                    deleteCourse();
                    break;
                case "5":
                    searchCourse();
                    break;
                case "6":
                    printStatistics();
                    break;
                case "7":
                    System.out.println("感謝使用選課管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效選項，請重新輸入。");
            }
        }
    }

    private static Course findById(String id) {
        for (Course c : courses) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    private static void addCourse() {
        System.out.print("請輸入課程代碼: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("【錯誤】課程代碼不可空白！");
            return;
        }

        if (findById(id) != null) {
            System.out.println("【錯誤】課程代碼 [" + id + "] 已存在，不可重複！");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("【錯誤】課程名稱不可空白！");
            return;
        }

        System.out.print("請輸入課程容量: ");
        int capacity;
        try {
            capacity = Integer.parseInt(scanner.nextLine().trim());
            if (capacity <= 0) {
                System.out.println("【錯誤】課程容量必須大於 0！");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("【錯誤】請輸入有效的整數數字！");
            return;
        }

        courses.add(new Course(id, name, capacity));
        System.out.println("【成功】課程 [" + name + "] 新增成功！");
    }

    private static void enrollCourse() {
        System.out.print("請輸入欲選課的課程代碼: ");
        String id = scanner.nextLine().trim();
        Course c = findById(id);

        if (c == null) {
            System.out.println("【錯誤】找不到代碼為 [" + id + "] 的課程！");
            return;
        }

        if (c.isFull()) {
            System.out.println("【錯誤】課程 [" + c.getName() + "] 已額滿，無法再增加人數！");
        } else {
            c.enroll();
            System.out.println("【成功】選課成功！目前選課人數: " + c.getEnrolled() + "/" + c.getCapacity());
        }
    }

    private static void dropCourse() {
        System.out.print("請輸入欲退選的課程代碼: ");
        String id = scanner.nextLine().trim();
        Course c = findById(id);

        if (c == null) {
            System.out.println("【錯誤】找不到代碼為 [" + id + "] 的課程！");
            return;
        }

        if (c.getEnrolled() == 0) {
            System.out.println("【錯誤】課程 [" + c.getName() + "] 目前選課人數為 0，無法進行退選！");
        } else {
            c.drop();
            System.out.println("【成功】退選成功！目前選課人數: " + c.getEnrolled() + "/" + c.getCapacity());
        }
    }

    private static void deleteCourse() {
        System.out.print("請輸入要刪除的課程代碼: ");
        String id = scanner.nextLine().trim();
        Course c = findById(id);

        if (c == null) {
            System.out.println("【錯誤】找不到代碼為 [" + id + "] 的課程，刪除失敗！");
            return;
        }

        courses.remove(c);
        System.out.println("【成功】已成功刪除課程 [" + c.getName() + "]！");
    }

    private static void searchCourse() {
        System.out.print("請輸入搜尋關鍵字 (代碼或名稱): ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("【錯誤】搜尋關鍵字不可空白！");
            return;
        }

        boolean found = false;
        for (Course c : courses) {
            if (c.getId().equalsIgnoreCase(keyword) || c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                if (!found) {
                    System.out.println("--- 搜尋結果 ---");
                }
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("【提示】查無符合 [" + keyword + "] 的課程。");
        }
    }

    private static void printStatistics() {
        int totalCourses = courses.size();
        int totalEnrolled = 0;
        ArrayList<Course> fullCourses = new ArrayList<>();

        for (Course c : courses) {
            totalEnrolled += c.getEnrolled();
            if (c.isFull()) {
                fullCourses.add(c);
            }
        }

        System.out.println("\n=== 課程統計資訊 ===");
        System.out.println("總課程數     : " + totalCourses + " 門");
        System.out.println("總選課人次   : " + totalEnrolled + " 人次");
        System.out.println("額滿課程數量 : " + fullCourses.size() + " 門");
        
        System.out.println("\n--- 額滿課程清單 ---");
        if (fullCourses.isEmpty()) {
            System.out.println("目前無任何額滿課程。");
        } else {
            for (Course c : fullCourses) {
                System.out.println(c);
            }
        }
    }
}