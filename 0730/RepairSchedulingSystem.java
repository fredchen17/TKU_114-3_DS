import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class RepairSchedulingSystem {

    private List<RepairTask> allTasks = new ArrayList<>();
    private Queue<RepairTask> waitingQueue = new LinkedList<>();
    private Stack<RepairTask> completedStack = new Stack<>();
    private Set<String> existingIds = new HashSet<>();

    public boolean addTask(RepairTask task) {
        if (task == null) {
            return false;
        }
        if (existingIds.contains(task.getId())) {
            System.out.println("Error: Task ID '" + task.getId() + "' already exists!");
            return false;
        }
        existingIds.add(task.getId());
        allTasks.add(task);
        waitingQueue.offer(task);
        System.out.println("Added Task: " + task);
        return true;
    }

    public void completeNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Cannot complete: Waiting Queue is empty.");
            return;
        }
        RepairTask task = waitingQueue.poll();
        completedStack.push(task);
        System.out.println("Completed Task: " + task);
    }

    public void undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("Cannot undo: Completed Stack is empty.");
            return;
        }
        RepairTask task = completedStack.pop();

        Queue<RepairTask> newQueue = new LinkedList<>();
        newQueue.offer(task);
        newQueue.addAll(waitingQueue);
        waitingQueue = newQueue;

        System.out.println("Undo completed. Task restored to front of Queue: " + task);
    }

    public void sortByPriorityDesc() {
        if (allTasks.isEmpty()) {
            System.out.println("All tasks list is empty. Nothing to sort.");
            return;
        }
        RepairTask[] arr = allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.mergeSortByPriorityDesc(arr, 0, arr.length - 1);
        allTasks.clear();
        for (RepairTask t : arr) {
            allTasks.add(t);
        }
        System.out.println("All tasks sorted by priority (Descending, stable).");
    }

    public void searchById(String id) {
        RepairTask[] arr = allTasks.toArray(new RepairTask[0]);
        RepairTask result = RepairAlgorithms.searchById(arr, id);
        System.out.println("Search by ID '" + id + "':");
        if (result != null) {
            System.out.println("  " + result);
        } else {
            System.out.println("  Task not found.");
        }
    }

    public void searchByDevice(String deviceName) {
        RepairTask[] arr = allTasks.toArray(new RepairTask[0]);
        List<RepairTask> result = RepairAlgorithms.searchByDeviceName(arr, deviceName);
        System.out.println("Search by Device '" + deviceName + "':");
        if (result.isEmpty()) {
            System.out.println("  No tasks found for this device.");
        } else {
            for (RepairTask t : result) {
                System.out.println("  " + t);
            }
        }
    }

    public void displayStatistics() {
        System.out.println("=== Statistics ===");
        System.out.println("Total Tasks:     " + allTasks.size());
        System.out.println("Waiting Tasks:   " + waitingQueue.size());
        System.out.println("Completed Tasks: " + completedStack.size());
        System.out.println();
    }

    public void displayAllTasks() {
        System.out.println("--- All Tasks ---");
        if (allTasks.isEmpty()) {
            System.out.println("(Empty)");
        } else {
            for (RepairTask t : allTasks) {
                System.out.println(t);
            }
        }
    }

    public static void main(String[] args) {
        RepairSchedulingSystem system = new RepairSchedulingSystem();

        System.out.println("=== 1. Test Empty System & Edge Cases ===");
        system.displayStatistics();
        system.completeNextTask();
        system.undoLastCompletion();
        system.searchById("TASK01");
        System.out.println();

        System.out.println("=== 2. Add Tasks & Test Stable Sorting (Same Priority) ===");
        system.addTask(new RepairTask("T01", "Laptop", 2));
        system.addTask(new RepairTask("T02", "Printer", 5));
        system.addTask(new RepairTask("T03", "Laptop", 5));
        system.addTask(new RepairTask("T04", "Monitor", 1));
        system.addTask(new RepairTask("T05", "Printer", 5));
        system.addTask(new RepairTask("T01", "Duplicate Test", 3));
        System.out.println();

        System.out.println("=== 3. Complete Tasks & Undo ===");
        system.completeNextTask();
        system.completeNextTask();
        system.displayStatistics();

        system.undoLastCompletion();
        system.displayStatistics();
        System.out.println();

        System.out.println("=== 4. Merge Sort (Priority Descending) ===");
        System.out.println("Before Sort:");
        system.displayAllTasks();

        system.sortByPriorityDesc();

        System.out.println("After Sort:");
        system.displayAllTasks();
        System.out.println();

        System.out.println("=== 5. Search Tests ===");
        system.searchById("T03");
        system.searchById("T99");
        system.searchByDevice("Printer");
        system.searchByDevice("Tablet");
    }
}