public class Order {
    private String id;
    private String customerName;
    private double amount;

    public Order(String id, String customerName, double amount) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', customer='" + customerName + "', amount=" + amount + "}";
    }
}