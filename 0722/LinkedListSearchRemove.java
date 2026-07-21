public class LinkedListSearchRemove {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
    }

    public boolean contains(int target) {
        Node cur = head;
        while (cur != null) {
            if (cur.data == target) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
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
            System.out.println("串列是空的");
            return;
        }
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

    public static void main(String[] args) {
        LinkedListSearchRemove list = new LinkedListSearchRemove();

        System.out.println("--- 測試空串列 ---");
        list.printList();
        System.out.println("是否有 10: " + list.contains(10));
        System.out.println("刪除 10: " + list.removeValue(10));
        list.printList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        System.out.println("\n--- 初始資料 ---");
        list.printList();

        System.out.println("\n--- 刪除 head (10) ---");
        System.out.println("刪除結果: " + list.removeValue(10));
        list.printList();

        System.out.println("\n--- 刪除中間 (30) ---");
        System.out.println("刪除結果: " + list.removeValue(30));
        list.printList();

        System.out.println("\n--- 刪除最後 (40) ---");
        System.out.println("刪除結果: " + list.removeValue(40));
        list.printList();

        System.out.println("\n--- 刪除找不到的 (99) ---");
        System.out.println("刪除結果: " + list.removeValue(99));
        list.printList();
    }
}   