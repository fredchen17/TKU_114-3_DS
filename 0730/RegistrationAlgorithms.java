import java.util.ArrayList;
import java.util.List;

public class RegistrationAlgorithms {

    public static void mergeSortById(Registration[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortById(array, left, mid);
        mergeSortById(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(Registration[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Registration[] leftArr = new Registration[n1];
        Registration[] rightArr = new Registration[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].getId().compareTo(rightArr[j].getId()) <= 0) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static int binarySearchById(Registration[] array, String targetId) {
        if (array == null || targetId == null) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = array[mid].getId().compareTo(targetId);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static List<Registration> sequentialSearchByName(List<Registration> list, String name) {
        List<Registration> result = new ArrayList<>();
        if (list == null || name == null) {
            return result;
        }
        for (Registration reg : list) {
            if (reg != null && reg.getName().equalsIgnoreCase(name)) {
                result.add(reg);
            }
        }
        return result;
    }
}