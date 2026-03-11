import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

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

    // GUI
    public void addExpenseGUI(String desc, double amount, String category) {
        Expense expense = new Expense(desc, amount, category);
        expenses.add(expense);
    }

    public void displayDailyExpensesGUI(JTextArea area) {
        LocalDate today = LocalDate.now();
        double dailyTotal = 0;
        boolean hasExpenses = false;

        for (Expense expense : expenses) {
            if (expense.getDate().equals(today)) {
                area.append(expense.toString() + "\n");
                dailyTotal += expense.getAmount();
                hasExpenses = true;
            }
        }

        if (!hasExpenses) {
            area.append("No expenses today! 🎉\n");
        } else {
            area.append("\n💰 DAILY TOTAL: ₹" + String.format("%.2f", dailyTotal) + "\n");
        }
    }

}
