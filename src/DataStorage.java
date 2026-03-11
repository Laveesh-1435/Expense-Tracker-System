import java.io.*;
import java.util.List;

public class DataStorage {
    private static final String FILENAME = "expenses.txt";
    
    public void saveExpenses(List<Expense> expenses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Expense expense : expenses) {
                writer.println(expense.getDate() + "|" + 
                             expense.getDescription() + "|" + 
                             expense.getAmount() + "|" + 
                             expense.getCategory());
            }
            System.out.println("💾 Data saved to " + FILENAME);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}
