import java.util.List;

public interface Tracker {
    void addEntry();
    void deleteEntry();
    List<?> getEntries();
    double calculateTotal();
}