public class Transaction {
    private String transactionId;
    private String accountNo;
    private int amount;
    private long timestampSequence;

    public Transaction(String transactionId, String accountNo, int amount, long timestampSequence) {
        this.transactionId = transactionId;
        this.accountNo = accountNo;
        this.amount = amount;
        this.timestampSequence = timestampSequence;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public int getAmount() {
        return amount;
    }

    public long getTimestampSequence() {
        return timestampSequence;
    }

    @Override
    public String toString() {
        return String.format("交易編號: %-8s | 帳號: %-10s | 金額: $%6d | 時間序號: %d",
                transactionId, accountNo, amount, timestampSequence);
    }
}