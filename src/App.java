import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExpenseGUI gui = new ExpenseGUI();  // Launches the GUI
        });
    }
}
