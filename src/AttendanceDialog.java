import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AttendanceDialog extends JDialog {
    private JTextField idField;
    private JTextField daysPresentField;
    private JTextField daysAbsentField;
    private JLabel attendanceLabel;

    public AttendanceDialog() {
        setTitle("Calculate Attendance");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Employee ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Days Present:"));
        daysPresentField = new JTextField();
        add(daysPresentField);

        add(new JLabel("Days Absent:"));
        daysAbsentField = new JTextField();
        add(daysAbsentField);

        JButton calculateButton = new JButton("Calculate");
        add(calculateButton);

        attendanceLabel = new JLabel("Attendance: ");
        add(attendanceLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                int daysPresent = Integer.parseInt(daysPresentField.getText());
                int daysAbsent = Integer.parseInt(daysAbsentField.getText());
                double attendance = calculateAttendance(daysPresent, daysAbsent);
                attendanceLabel.setText("Attendance: " + attendance + "%");
                updateEmployeeAttendance(id, daysPresent, daysAbsent);
            }
        });

        setVisible(true);
    }

    private double calculateAttendance(int daysPresent, int daysAbsent) {
        int totalDays = daysPresent + daysAbsent;
        return ((double) daysPresent / totalDays) * 100;
    }

    private void updateEmployeeAttendance(String id, int daysPresent, int daysAbsent) {
        File inputFile = new File("employeedetails.txt");
        File tempFile = new File("employeedetails_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[1].equals(id)) {
                    details[4] = String.valueOf(daysPresent);
                    details[5] = String.valueOf(daysAbsent);
                    line = String.join(",", details);
                }
                writer.write(line + "\n");
            }

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}