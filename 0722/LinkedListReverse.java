public class LinkedListReverse {

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

    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        Node prev = null;
        Node cur = head;
        Node next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        head = prev;
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
        System.out.println("--- 測試空串列 ---");
        LinkedListReverse list1 = new LinkedListReverse();
        list1.reverse();
        list1.printList();

        System.out.println("\n--- 測試單一節點 ---");
        LinkedListReverse list2 = new LinkedListReverse();
        list2.add(10);
        System.out.print("反轉前: ");
        list2.printList();
        list2.reverse();
        System.out.print("反轉後: ");
        list2.printList();

        System.out.println("\n--- 測試多節點 ---");
        LinkedListReverse list3 = new LinkedListReverse();
        list3.add(10);
        list3.add(20);
        list3.add(30);
        list3.add(40);
        System.out.print("反轉前: ");
        list3.printList();
        list3.reverse();
        System.out.print("反轉後: ");
        list3.printList();
    }
}