import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {

        setLayout(new GridLayout(3, 3, 10, 10));



        Font buttonFont = new Font("Arial", Font.PLAIN, 12);

        JButton addEmployeeButton = new JButton("Add Employee");

        addEmployeeButton.setFont(buttonFont);

        JButton removeEmployeeButton = new JButton("Remove Employee");
        removeEmployeeButton.setSize(1000,2000);
        removeEmployeeButton.setFont(buttonFont);

        JButton viewEmployeeButton = new JButton("View Employee Details");
        viewEmployeeButton.setFont(buttonFont);

        JButton calculateSalaryButton = new JButton("Calculate Salary");
        calculateSalaryButton.setFont(buttonFont);

        JButton calculateBonusButton = new JButton("Calculate Bonus and Fines");
        calculateBonusButton.setFont(buttonFont);

        JButton calculateAttendanceButton = new JButton("Calculate Attendance");
        calculateAttendanceButton.setFont(buttonFont);

        JButton reviewFeedbackButton = new JButton("Review & Feedback");
        reviewFeedbackButton.setFont(buttonFont);

        addEmployeeButton.addActionListener(e -> new AddEmployeeDialog());
        removeEmployeeButton.addActionListener(e -> new RemoveEmployeeDialog());
        viewEmployeeButton.addActionListener(e -> new ViewEmployeeDialog());
        calculateSalaryButton.addActionListener(e -> new CalculateSalaryDialog());
        calculateBonusButton.addActionListener(e -> new CalculateBonusDialog());
        calculateAttendanceButton.addActionListener(e -> new AttendanceDialog());
        reviewFeedbackButton.addActionListener(e -> new ReviewFeedbackDialog());

        add(addEmployeeButton);
        add(removeEmployeeButton);
        add(viewEmployeeButton);
        add(calculateSalaryButton);
        add(calculateBonusButton);
        add(calculateAttendanceButton);
        add(reviewFeedbackButton);
    }
}