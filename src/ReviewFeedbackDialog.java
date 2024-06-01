import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ReviewFeedbackDialog extends JDialog {
    private JTextField idField;
    private JTextArea commentArea;

    public ReviewFeedbackDialog() {
        setTitle("Review & Feedback");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        JPanel idPanel = new JPanel(new BorderLayout());
        idPanel.add(new JLabel("Employee ID:"), BorderLayout.WEST);
        idField = new JTextField();
        idPanel.add(idField, BorderLayout.CENTER);
        topPanel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit Comment");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String comments = commentArea.getText();
                addCommentsToFile(id, comments);
                commentArea.setText("");
            }
        });
        buttonPanel.add(submitButton);

        JButton viewCommentsButton = new JButton("View Comments");
        viewCommentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                viewComments(id);
            }
        });
        buttonPanel.add(viewCommentsButton);

        topPanel.add(buttonPanel);
        add(topPanel, BorderLayout.NORTH);

        commentArea = new JTextArea();
        commentArea.setBorder(BorderFactory.createTitledBorder("Comments"));
        add(new JScrollPane(commentArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void addCommentsToFile(String id, String comments) {
        File file = new File("feedback.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("ID: " + id + "\nComments: " + comments + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewComments(String id) {
        File file = new File("feedback.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Feedback file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder comments = new StringBuilder();
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID: " + id)) {
                    found = true;
                    comments.append(line).append("\n");
                    while ((line = reader.readLine()) != null && !line.startsWith("ID: ")) {
                        comments.append(line).append("\n");
                    }
                }
            }

            if (found) {
                JOptionPane.showMessageDialog(this, comments.toString(), "Comments for ID: " + id, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No comments found for ID: " + id, "No Comments", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}