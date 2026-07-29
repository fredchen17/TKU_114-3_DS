public class TransactionSortingSystem {

    public static void sortTransactions(Transaction[] transactions) {
        if (transactions == null || transactions.length <= 1) {
            return;
        }

        int n = transactions.length;
        for (int i = 1; i < n; i++) {
            Transaction key = transactions[i];
            int j = i - 1;

            while (j >= 0 && shouldSwap(transactions[j], key)) {
                transactions[j + 1] = transactions[j];
                j--;
            }
            transactions[j + 1] = key;
        }
    }

    private static boolean shouldSwap(Transaction current, Transaction key) {
        if (current.getAmount() < key.getAmount()) {
            return true;
        }
        if (current.getAmount() == key.getAmount() && current.getTimestampSequence() > key.getTimestampSequence()) {
            return true;
        }
        return false;
    }

    public static void displayTransactions(Transaction[] transactions, String title) {
        System.out.println("==========================================================================");
        System.out.println("                        " + title);
        System.out.println("==========================================================================");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("TX1001", "ACC-001", 5000, 10003L),
            new Transaction("TX1002", "ACC-002", 12000, 10001L),
            new Transaction("TX1003", "ACC-003", 5000, 10001L),  
            new Transaction("TX1004", "ACC-004", 25000, 10005L),
            new Transaction("TX1005", "ACC-005", 5000, 10002L),  
            new Transaction("TX1006", "ACC-006", 12000, 10004L), 
            new Transaction("TX1007", "ACC-007", 800, 10000L)
        };

        displayTransactions(transactions, "排序前交易紀錄");

        sortTransactions(transactions);

        displayTransactions(transactions, "排序後交易紀錄 (金額降冪，相同金額依時間序號升冪)");
    }
}