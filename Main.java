public class Main {
    public static void main(String[] args) {
        BudgetManager manager = new BudgetManager();
        manager.setBudget("Groceries", 500.00);
        manager.setBudget("Rent", 1000.00);

        manager.addSpending("Groceries", 200.00);
        manager.addSpending("Rent", 950.00);
        manager.addSpending("Entertainment", 150.00);

        manager.displayBudgets();
        manager.generateSpendingAnalysis();
    }
}