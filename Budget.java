public class Budget {
    private String category;
    private double amount;

    public Budget(String category, double amount) {
        if (!isValidCategory(category)) {
            throw new IllegalArgumentException("Invalid category name.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Budget amount must be positive.");
        }
        this.category = category;
        this.amount = amount;
    }

    private boolean isValidCategory(String category) {
        return category != null && category.matches("[a-zA-Z0-9 ]{3,50}");
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Category: " + category + ", Budget: $" + String.format("%.2f", amount);
    }
}