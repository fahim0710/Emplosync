import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateIDDialog extends JDialog {
    private JTextField nameField;
    private JLabel idLabel;

    public GenerateIDDialog() {
        setTitle("Generate ID");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        JButton generateButton = new JButton("Generate");
        add(generateButton);

        idLabel = new JLabel("ID: ");
        add(idLabel);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = generateID(name);
                idLabel.setText("ID: " + id);
            }
        });

        setVisible(true);
    }

    private String generateID(String name) {
        int id = name.hashCode();
        return Integer.toString(id);
    }
}