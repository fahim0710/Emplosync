import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewEmployeeDialog extends JDialog {
    private JTextArea textArea;

    public ViewEmployeeDialog() {
        setTitle("View Employee Details");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        loadEmployeeDetails();
        setVisible(true);
    }

    private void loadEmployeeDetails() {
        File file = new File("employeedetails.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Employee details file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}