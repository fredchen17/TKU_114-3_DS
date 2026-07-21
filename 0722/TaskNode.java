public class TaskNode {
    String id;
    String description;
    boolean isCompleted;
    TaskNode next;

    public TaskNode(String id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
        this.next = null;
    }
}