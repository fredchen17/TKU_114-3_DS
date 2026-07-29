import java.util.ArrayList;
import java.util.List;

public class RepairAlgorithms {

    public static void mergeSortByPriorityDesc(RepairTask[] tasks, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortByPriorityDesc(tasks, left, mid);
        mergeSortByPriorityDesc(tasks, mid + 1, right);

        merge(tasks, left, mid, right);
    }

    private static void merge(RepairTask[] tasks, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        RepairTask[] leftArr = new RepairTask[n1];
        RepairTask[] rightArr = new RepairTask[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = tasks[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = tasks[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i].getPriority() >= rightArr[j].getPriority()) {
                tasks[k] = leftArr[i];
                i++;
            } else {
                tasks[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            tasks[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            tasks[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static RepairTask searchById(RepairTask[] tasks, String id) {
        if (tasks == null || id == null) {
            return null;
        }
        for (RepairTask task : tasks) {
            if (task != null && task.getId().equalsIgnoreCase(id)) {
                return task;
            }
        }
        return null;
    }

    public static List<RepairTask> searchByDeviceName(RepairTask[] tasks, String deviceName) {
        List<RepairTask> result = new ArrayList<>();
        if (tasks == null || deviceName == null) {
            return result;
        }
        for (RepairTask task : tasks) {
            if (task != null && task.getDeviceName().equalsIgnoreCase(deviceName)) {
                result.add(task);
            }
        }
        return result;
    }
}