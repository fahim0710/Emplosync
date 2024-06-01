import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.SwingUtilities;


public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        setLayout(null);




        /*ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("profile.png"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 100, 1050, 500);
        add(image); */

        mainFrame.getContentPane().setBackground(Color.WHITE);
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(5, 5));
        setBackground(Color.white);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10,20,30,40);

        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                    mainFrame.showMainPanel();
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean authenticate(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\user\\Downloads\\Emplo\\Emplo\\src\\employee.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}