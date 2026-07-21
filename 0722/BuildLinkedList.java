public class BuildLinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Node head = n1;

        printList(head);
        printCountAndSum(head);

        Node emptyHead = null;
        printList(emptyHead);
        printCountAndSum(emptyHead);
    }

    public static void printList(Node head) {
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

    public static void printCountAndSum(Node head) {
        if (head == null) {
            System.out.println("節點數: 0, 總和: 0");
            return;
        }
        int count = 0;
        int sum = 0;
        Node cur = head;
        while (cur != null) {
            count++;
            sum += cur.data;
            cur = cur.next;
        }
        System.out.println("節點數: " + count + ", 總和: " + sum);
    }
}