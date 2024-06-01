import javax.swing.SwingUtilities;

public class EmploSync {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}