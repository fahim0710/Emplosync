import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("EmploSync - Employee Management and Payroll System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);




        JPanel loginPanel = new LoginPanel(this);


        setContentPane(loginPanel);
    }

    public void showMainPanel() {
        JPanel mainPanel = new MainPanel();
        setContentPane(mainPanel);
        revalidate();
    }
}