import java.util.ArrayList;
import java.util.List;

public class IncomeList {
    private List<TrackingIncome> incomeEntries = new ArrayList<>();

    public void addIncome(TrackingIncome income) {
        incomeEntries.add(income);
    }

    public void displayAllIncomes() {
        for (TrackingIncome i : incomeEntries) {
            i.displayIncome();
        }
    }

    public double getTotalIncome() {
        return incomeEntries.stream().mapToDouble(TrackingIncome::getIncomeAmount).sum();
    }
}
