import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class AddEmployeeDialog extends JDialog {
    private JTextField nameField;
    private JTextField positionField;
    private JTextField salaryField;
    private JTextField daysPresentField;
    private JTextField daysAbsentField;

    public AddEmployeeDialog() {
        setTitle("Add Employee");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        add(new JLabel("Days Present:"));
        daysPresentField = new JTextField();
        add(daysPresentField);

        add(new JLabel("Days Absent:"));
        daysAbsentField = new JTextField();
        add(daysAbsentField);

        JButton addButton = new JButton("Add");
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String position = positionField.getText();
                String salary = salaryField.getText();
                String daysPresent = daysPresentField.getText();
                String daysAbsent = daysAbsentField.getText();
                String id = generateRandomID();
                addEmployeeToFile(name, id, position, salary, daysPresent, daysAbsent);
                dispose();
            }
        });

        setVisible(true);
    }

    private String generateRandomID() {
        Random rand = new Random();
        int id = rand.nextInt(9000) + 1000; // Generates a random 4-digit number
        return String.valueOf(id);
    }

    private void addEmployeeToFile(String name, String id, String position, String salary, String daysPresent, String daysAbsent) {
        try (FileWriter writer = new FileWriter("employeedetails.txt", true)) {
            writer.write(name + "," + id + "," + position + "," + salary + "," + daysPresent + "," + daysAbsent + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}