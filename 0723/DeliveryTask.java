public class DeliveryTask {
    String taskId;
    String address;

    public DeliveryTask(String taskId, String address) {
        this.taskId = taskId;
        this.address = address;
    }

    @Override
    public String toString() {
        return "任務代碼: " + taskId + " | 配送地址: " + address;
    }
}