public class TaskLinkedList {
    private TaskNode head;

    public void addFirst(String id, String description) {
        TaskNode newNode = new TaskNode(id, description);
        newNode.next = head;
        head = newNode;
        System.out.println("新增緊急工作: [" + id + "] " + description);
    }

    public void addLast(String id, String description) {
        TaskNode newNode = new TaskNode(id, description);
        if (head == null) {
            head = newNode;
            System.out.println("新增一般工作: [" + id + "] " + description);
            return;
        }

        TaskNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        System.out.println("新增一般工作: [" + id + "] " + description);
    }

    public boolean completeTask(String id) {
        TaskNode cur = head;
        while (cur != null) {
            if (cur.id.equalsIgnoreCase(id)) {
                cur.isCompleted = true;
                System.out.println("工作 [" + id + "] 已標記為完成！");
                return true;
            }
            cur = cur.next;
        }
        System.out.println("標記失敗：找不到工作代碼 [" + id + "]");
        return false;
    }

    public boolean removeTask(String id) {
        if (head == null) {
            System.out.println("刪除失敗：工作清單是空的！");
            return false;
        }

        if (head.id.equalsIgnoreCase(id)) {
            head = head.next;
            System.out.println("成功刪除工作: " + id);
            return true;
        }

        TaskNode cur = head;
        while (cur.next != null) {
            if (cur.next.id.equalsIgnoreCase(id)) {
                cur.next = cur.next.next;
                System.out.println("成功刪除工作: " + id);
                return true;
            }
            cur = cur.next;
        }

        System.out.println("刪除失敗：找不到工作代碼 [" + id + "]");
        return false;
    }

    public void printUncompleted() {
        if (head == null) {
            System.out.println("工作清單是空的。");
            return;
        }

        System.out.println("--- 未完成工作清單 ---");
        TaskNode cur = head;
        boolean hasUncompleted = false;
        while (cur != null) {
            if (!cur.isCompleted) {
                System.out.println("[" + cur.id + "] " + cur.description);
                hasUncompleted = true;
            }
            cur = cur.next;
        }
        if (!hasUncompleted) {
            System.out.println("所有工作皆已完成！");
        }
    }

    public void printCounts() {
        int total = 0;
        int uncompleted = 0;
        TaskNode cur = head;

        while (cur != null) {
            total++;
            if (!cur.isCompleted) {
                uncompleted++;
            }
            cur = cur.next;
        }

        System.out.println("工作總數: " + total + ", 未完成數量: " + uncompleted);
    }
}