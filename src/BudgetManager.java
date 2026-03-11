import java.util.Scanner;

public class BudgetManager {
    private double monthlyBudget = 0;
    private final double monthlySpent = 0;
    
    public void setMonthlyBudget(Scanner scanner) {
        System.out.print("Enter monthly budget (₹): ");
        this.monthlyBudget = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("✅ Budget set: ₹" + monthlyBudget);
    }
    
    public void displayBudgetStatus() {
        if (monthlyBudget == 0) {
            System.out.println("❌ No budget set yet!");
            return;
        }
        
        double remaining = monthlyBudget - monthlySpent;
        double percentageUsed = (monthlySpent / monthlyBudget) * 100;
        
        System.out.println("\n💳 Budget Status:");
        System.out.println("=================");
        System.out.printf("Monthly Budget: ₹%.2f%n", monthlyBudget);
        System.out.printf("Spent: ₹%.2f (%.1f%%)%n", monthlySpent, percentageUsed);
        System.out.printf("Remaining: ₹%.2f%n", remaining);
        
        if (percentageUsed > 90) {
            System.out.println("⚠️  Budget almost exhausted!");
        } else if (percentageUsed > 75) {
            System.out.println("🔶 Budget usage is high.");
        }
    }
}
