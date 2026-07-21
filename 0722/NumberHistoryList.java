public class NumberHistoryList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        System.out.println("前端新增: " + data);
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            System.out.println("尾端新增: " + data);
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        System.out.println("尾端新增: " + data);
    }

    public boolean search(int target) {
        Node cur = head;
        while (cur != null) {
            if (cur.data == target) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean remove(int target) {
        if (head == null) {
            return false;
        }

        if (head.data == target) {
            head = head.next;
            return true;
        }

        Node cur = head;
        while (cur.next != null) {
            if (cur.next.data == target) {
                cur.next = cur.next.next;
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    public void printList() {
        if (head == null) {
            System.out.println("串列內容: [空串列]");
            return;
        }
        System.out.print("串列內容: ");
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data);
            if (cur.next != null) {
                System.out.print(" -> ");
            }
            cur = cur.next;
        }
        System.out.println();
    }

    public void printStats() {
        if (head == null) {
            System.out.println("Size: 0, 總和: 0, 最大值: 無, 最小值: 無");
            return;
        }

        int size = 0;
        int sum = 0;
        int max = head.data;
        int min = head.data;

        Node cur = head;
        while (cur != null) {
            size++;
            sum += cur.data;
            if (cur.data > max) {
                max = cur.data;
            }
            if (cur.data < min) {
                min = cur.data;
            }
            cur = cur.next;
        }

        System.out.println("Size: " + size + ", 總和: " + sum + ", 最大值: " + max + ", 最小值: " + min);
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("=== 測試 1: 空串列統計 ===");
        list.printList();
        list.printStats();

        System.out.println("\n=== 開始測試操作 (至少8次操作) ===");
        list.addFirst(20);
        list.addFirst(10);
        list.addLast(30);
        list.addLast(40);

        System.out.println("\n--- 目前狀態 ---");
        list.printList();
        list.printStats();

        System.out.println("\n搜尋 30: " + (list.search(30) ? "找到了" : "找不到"));
        System.out.println("搜尋 99: " + (list.search(99) ? "找到了" : "找不到"));

        System.out.println("\n刪除 10 (頭節點): " + list.remove(10));
        list.printList();

        System.out.println("\n刪除 40 (尾節點): " + list.remove(40));
        list.printList();

        System.out.println("\n刪除 100 (不存在): " + list.remove(100));
        list.printList();

        System.out.println("\n--- 最終統計 ---");
        list.printStats();
    }
}