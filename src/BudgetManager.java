import java.util.Scanner;
import javax.swing.JTextArea;

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

    // GUI
    public void setMonthlyBudgetGUI(double budget) {
        this.monthlyBudget = budget;
    }

    public void displayBudgetStatusGUI(JTextArea area) {
        if (monthlyBudget == 0) {
            area.append("❌ No budget set! Use 'Set Budget' button.\n");
            return;
        }
        
        double remaining = monthlyBudget - monthlySpent;
        double percentage = (monthlySpent / monthlyBudget) * 100;
        
        area.append("Monthly Budget: ₹" + String.format("%.2f", monthlyBudget) + "\n");
        area.append("Spent: ₹" + String.format("%.2f", monthlySpent) + " (" + 
                    String.format("%.1f", percentage) + "%)\n");
        area.append("Remaining: ₹" + String.format("%.2f", remaining) + "\n\n");
        
        if (percentage > 90) area.append("⚠️  CRITICAL - Budget almost gone!\n");
        else if (percentage > 75) area.append("🔶 HIGH usage - Be careful!\n");
        else area.append("✅ Good budget control!\n");
    }
}
