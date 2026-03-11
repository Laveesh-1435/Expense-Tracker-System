import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense {
    private final String description;
    private final double amount;
    private final String category;
    private final LocalDate date;
    
    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
    }
    
    // Getters
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    
    @Override
    public String toString() {
        return String.format("%s | ₹%.2f | %s | %s", 
            date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            amount, category, description);
    }
}
