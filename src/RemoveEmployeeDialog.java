import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveEmployeeDialog extends JDialog {
    private JTextField idField;

    public RemoveEmployeeDialog() {
        setTitle("Remove Employee");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Employee ID:"));
        idField = new JTextField();
        add(idField);

        JButton removeButton = new JButton("Remove");
        add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                boolean removed = removeEmployeeFromFile(id);
                if (removed) {
                    JOptionPane.showMessageDialog(RemoveEmployeeDialog.this, "Employee removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(RemoveEmployeeDialog.this, "Employee ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        setVisible(true);
    }

    private boolean removeEmployeeFromFile(String id) {
        File inputFile = new File("E:\\Emplo\\src\\employeedetails.txt");
        File tempFile = new File("E:\\Emplo\\src\\employeedetails_temp.txt");

        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (!details[1].equals(id)) {
                    writer.write(line + System.lineSeparator());
                } else {
                    removed = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (removed) {
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } else {
            tempFile.delete();
        }

        return removed;
    }
}
