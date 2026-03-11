import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        BudgetManager budgetManager = new BudgetManager();
        ReportGenerator reportGenerator = new ReportGenerator(tracker);
        DataStorage storage = new DataStorage();
        
        System.out.println("=== Expense Tracker ===");
        
        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View Today's Expenses");
            System.out.println("3. Set Monthly Budget");
            System.out.println("4. View Budget Status");
            System.out.println("5. Generate Monthly Report");
            System.out.println("6. Exit");
            
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1 -> {
                    tracker.addExpense(scanner);
                    storage.saveExpenses(tracker.getAllExpenses());
                }
                case 2 -> tracker.displayDailyExpenses();
                case 3 -> budgetManager.setMonthlyBudget(scanner);
                case 4 -> budgetManager.displayBudgetStatus();
                case 5 -> reportGenerator.generateMonthlyReport();
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
