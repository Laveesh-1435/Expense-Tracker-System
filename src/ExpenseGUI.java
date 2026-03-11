import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.time.LocalDate;
// import java.util.List;

public class ExpenseGUI extends JFrame {
    private final ExpenseTracker tracker;
    private final BudgetManager budgetManager;
    private final ReportGenerator reportGenerator;
    private JTextArea displayArea;
    private JTextField descField, amountField, categoryField;
    
    public ExpenseGUI() {
        tracker = new ExpenseTracker();
        budgetManager = new BudgetManager();
        reportGenerator = new ReportGenerator(tracker);
        initUI();
        loadData();
    }
    
    private void initUI() {
        setTitle("💰 Expense Tracker - Delhi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Input Panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        
        panel.add(new JLabel("Description:"));
        descField = new JTextField(15);
        panel.add(descField);
        
        panel.add(new JLabel("Amount (₹):"));
        amountField = new JTextField(8);
        panel.add(amountField);
        
        panel.add(new JLabel("Category:"));
        categoryField = new JTextField(12);
        panel.add(categoryField);
        
        JButton addBtn = new JButton("➕ Add Expense");
        addBtn.addActionListener(e -> addExpense());
        panel.add(addBtn);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton todayBtn = new JButton("📊 Today");
        todayBtn.addActionListener(e -> showToday());
        panel.add(todayBtn);
        
        JButton budgetBtn = new JButton("💳 Budget");
        budgetBtn.addActionListener(e -> showBudget());
        panel.add(budgetBtn);
        
        JButton reportBtn = new JButton("📈 Report");
        reportBtn.addActionListener(e -> showReport());
        panel.add(reportBtn);
        
        JButton clearBtn = new JButton("🧹 Clear");
        clearBtn.addActionListener(e -> displayArea.setText(""));
        panel.add(clearBtn);
        
        return panel;
    }
    
    private void addExpense() {
        try {
            String desc = descField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            
            if (desc.isEmpty() || amount <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid details!");
                return;
            }
            
            tracker.addExpenseGUI(desc, amount, category);
            JOptionPane.showMessageDialog(this, "✅ Expense Added!");
            clearFields();
            showToday();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
        }
    }
    
    private void showToday() {
        displayArea.setText("📊 TODAY'S EXPENSES (" + LocalDate.now() + ")\n" +
                          "============================================\n");
        tracker.displayDailyExpensesGUI(displayArea);
    }
    
    private void showBudget() {
        displayArea.setText("💳 BUDGET STATUS\n================\n");
        budgetManager.displayBudgetStatusGUI(displayArea);
        
        JButton setBudgetBtn = new JButton("Set Budget");
        setBudgetBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Monthly Budget (₹):");
            if (input != null) {
                try {
                    budgetManager.setMonthlyBudgetGUI(Double.parseDouble(input));
                    showBudget();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid budget!");
                }
            }
        });
    }
    
    private void showReport() {
        displayArea.setText("📈 MONTHLY REPORT\n==================\n");
        reportGenerator.generateMonthlyReportGUI(displayArea);
    }
    
    private void clearFields() {
        descField.setText("");
        amountField.setText("");
        categoryField.setText("");
    }
    
    @SuppressWarnings("unused")
    private void loadData() {
        DataStorage storage = new DataStorage();
        // Data loads automatically through tracker
    }
}
