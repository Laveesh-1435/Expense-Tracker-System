import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportGenerator {
    private final ExpenseTracker tracker;
    
    public ReportGenerator(ExpenseTracker tracker) {
        this.tracker = tracker;
    }
    
    public void generateMonthlyReport() {
        List<Expense> allExpenses = tracker.getAllExpenses();
        YearMonth currentMonth = YearMonth.now();
        
        List<Expense> monthlyExpenses = allExpenses.stream()
            .filter(e -> YearMonth.from(e.getDate()).equals(currentMonth))
            .collect(Collectors.toList());
        
        if (monthlyExpenses.isEmpty()) {
            System.out.println("No expenses for this month yet.");
            return;
        }
        
        double totalSpent = monthlyExpenses.stream()
            .mapToDouble(Expense::getAmount)
            .sum();
        
        Map<String, Double> categoryTotals = monthlyExpenses.stream()
            .collect(Collectors.groupingBy(
                Expense::getCategory,
                Collectors.summingDouble(Expense::getAmount)
            ));
        
        System.out.println("\n📈 Monthly Report (" + 
            currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")) + ")");
        System.out.println("=============================================");
        System.out.printf("Total Spent: ₹%.2f%n%n", totalSpent);
        
        System.out.println("🏷️  Category Breakdown:");
        categoryTotals.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(entry -> 
                System.out.printf("%s: ₹%.2f (%.1f%%)%n", 
                    entry.getKey(), entry.getValue(),
                    (entry.getValue() / totalSpent) * 100
                )
            );
    }
}
