import java.util.ArrayList;
import java.util.List;

public class OrderAlgorithms {

    public static void mergeSortByAmountDesc(Order[] orders, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortByAmountDesc(orders, left, mid);
        mergeSortByAmountDesc(orders, mid + 1, right);

        merge(orders, left, mid, right);
    }

    private static void merge(Order[] orders, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Order[] leftArr = new Order[n1];
        Order[] rightArr = new Order[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = orders[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = orders[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i].getAmount() >= rightArr[j].getAmount()) {
                orders[k] = leftArr[i];
                i++;
            } else {
                orders[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            orders[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            orders[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static List<Order> searchByCustomerName(Order[] orders, String name) {
        List<Order> result = new ArrayList<>();
        if (orders == null || name == null) {
            return result;
        }

        for (Order order : orders) {
            if (order != null && order.getCustomerName().equalsIgnoreCase(name)) {
                result.add(order);
            }
        }
        return result;
    }
}