public class SpendingRecord {
    private String category;
    private double amount;

    public SpendingRecord(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}


