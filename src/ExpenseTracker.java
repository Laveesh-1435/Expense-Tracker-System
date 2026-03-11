import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private final List<Expense> expenses;
    
    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
    }
    
    public void addExpense(java.util.Scanner scanner) {
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Amount (₹): ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        
        Expense expense = new Expense(desc, amount, category);
        expenses.add(expense);
        System.out.println("✅ Expense added: " + expense);
    }
    
    public void displayDailyExpenses() {
        LocalDate today = LocalDate.now();
        System.out.println("\n📊 Today's Expenses:");
        System.out.println("====================");
        
        double dailyTotal = 0;
        boolean hasExpenses = false;
        
        for (Expense expense : expenses) {
            if (expense.getDate().equals(today)) {
                System.out.println(expense);
                dailyTotal += expense.getAmount();
                hasExpenses = true;
            }
        }
        
        if (!hasExpenses) {
            System.out.println("No expenses recorded today.");
        } else {
            System.out.printf("💰 Daily Total: ₹%.2f%n", dailyTotal);
        }
    }
    
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }
}
