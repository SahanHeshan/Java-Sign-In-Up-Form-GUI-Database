import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignInForm extends JPanel implements ActionListener {
    private final JLabel title, email, pass;
    private final JTextField emailField;
    private final JPasswordField passField;
    private final JButton signInButton;


    public SignInForm() {
        setLayout(null);

        title = new JLabel("Sign In");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(195, 18, 200, 35);
        title.setForeground(Color.BLUE);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.BOLD, 20));
        email.setBounds(45, 70, 100, 30);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));
        emailField.setBounds(195, 70, 250, 30);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.BOLD, 20));
        pass.setBounds(45, 120, 100, 30);

        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 15));
        passField.setBounds(195, 120, 250, 30);

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 20));
        signInButton.setBackground(Color.BLUE);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBounds(45, 180, 400, 40);

        // Adding components to the panel
        add(title);
        add(email);
        add(emailField);
        add(pass);
        add(passField);
        add(signInButton);

        signInButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
                String email = emailField.getText();
                String pass = new String(passField.getPassword());

                String insertQuery = "SELECT * FROM `user` WHERE Email = ? AND Password = ?;";

                try (Connection connection = DbConnection.getConnection();
                     PreparedStatement preparedStmt = connection.prepareStatement(insertQuery)) {
                    preparedStmt.setString(1, email);
                    preparedStmt.setString(2, pass);

                    preparedStmt.execute();
                    JOptionPane.showMessageDialog(null, "Login Success!!");

                } catch (SQLException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Login Failed " + exception.getMessage());
                }
        }
    }
}
