import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class OrderManagementPractice {

    private List<Order> mainOrders = new ArrayList<>();
    private Queue<Order> pendingQueue = new LinkedList<>();
    private Stack<Order> completedStack = new Stack<>();
    private Set<String> existingIds = new HashSet<>();

    public boolean addOrder(Order order) {
        if (order == null) {
            return false;
        }
        if (existingIds.contains(order.getId())) {
            System.out.println("Error: Order ID '" + order.getId() + "' already exists!");
            return false;
        }
        existingIds.add(order.getId());
        mainOrders.add(order);
        pendingQueue.offer(order);
        System.out.println("Added: " + order);
        return true;
    }

    public void peekNextPending() {
        if (pendingQueue.isEmpty()) {
            System.out.println("Next Pending Order: None (Queue is empty)");
        } else {
            System.out.println("Next Pending Order: " + pendingQueue.peek());
        }
    }

    public void processNextOrder() {
        if (pendingQueue.isEmpty()) {
            System.out.println("Processing failed: Pending Queue is empty.");
            return;
        }
        Order processed = pendingQueue.poll();
        completedStack.push(processed);
        System.out.println("Processed order: " + processed);
    }

    public void peekLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("Last Completed Order: None (Stack is empty)");
        } else {
            System.out.println("Last Completed Order: " + completedStack.peek());
        }
    }

    public void sortMainOrdersByAmountDesc() {
        if (mainOrders.isEmpty()) {
            System.out.println("Main orders list is empty. Nothing to sort.");
            return;
        }
        Order[] arr = mainOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortByAmountDesc(arr, 0, arr.length - 1);
        mainOrders.clear();
        for (Order o : arr) {
            mainOrders.add(o);
        }
        System.out.println("Main orders sorted by amount descending.");
    }

    public void searchCustomer(String name) {
        Order[] arr = mainOrders.toArray(new Order[0]);
        List<Order> result = OrderAlgorithms.searchByCustomerName(arr, name);
        System.out.println("Search results for '" + name + "':");
        if (result.isEmpty()) {
            System.out.println("  No orders found.");
        } else {
            for (Order o : result) {
                System.out.println("  " + o);
            }
        }
    }

    public void displayMainOrders() {
        System.out.println("--- Current Main Orders ---");
        if (mainOrders.isEmpty()) {
            System.out.println("(Empty)");
        } else {
            for (Order o : mainOrders) {
                System.out.println(o);
            }
        }
    }

    public static void main(String[] args) {
        OrderManagementPractice system = new OrderManagementPractice();

        System.out.println("=== 1. Test Empty Queue & Stack ===");
        system.peekNextPending();
        system.processNextOrder();
        system.peekLastCompleted();
        system.searchCustomer("Alice");
        System.out.println();

        System.out.println("=== 2. Add Orders & Test Duplicate ID ===");
        system.addOrder(new Order("ORD01", "Alice", 1500.0));
        system.addOrder(new Order("ORD02", "Bob", 3200.5));
        system.addOrder(new Order("ORD03", "Alice", 800.0));
        system.addOrder(new Order("ORD01", "Charlie", 2000.0));
        system.addOrder(new Order("ORD04", "David", 4500.0));
        System.out.println();

        System.out.println("=== 3. Peek & Process Queue ===");
        system.peekNextPending();
        system.processNextOrder();
        system.peekNextPending();
        system.peekLastCompleted();
        System.out.println();

        System.out.println("=== 4. Merge Sort (Amount Descending) ===");
        System.out.println("Before Sort:");
        system.displayMainOrders();
        system.sortMainOrdersByAmountDesc();
        System.out.println("After Sort:");
        system.displayMainOrders();
        System.out.println();

        System.out.println("=== 5. Search Tests ===");
        system.searchCustomer("Alice");
        system.searchCustomer("Eve");
    }
}