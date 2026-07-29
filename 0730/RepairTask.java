public class RepairTask {
    private String id;
    private String deviceName;
    private int priority;

    public RepairTask(String id, String deviceName, int priority) {
        this.id = id;
        this.deviceName = deviceName;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "RepairTask{id='" + id + "', device='" + deviceName + "', priority=" + priority + "}";
    }
}