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
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField.setBounds(195, 70, 250, 30);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.BOLD, 20));
        pass.setBounds(45, 120, 100, 30);

        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 18));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            String userEmail = emailField.getText();
            String userPass = new String(passField.getPassword());
            // database table name: user
            String selectQuery = "SELECT * FROM `user` WHERE Email = ? AND Password = ?;";
            // Database connection details on DbConnection.java
            if (!userEmail.isEmpty()  && !userPass.isEmpty()){
                try (Connection connection = DbConnection.getConnection();
                     PreparedStatement preparedStmt = connection.prepareStatement(selectQuery)) {

                    preparedStmt.setString(1, userEmail);
                    preparedStmt.setString(2, userPass);

                    ResultSet resultSet = preparedStmt.executeQuery();
                    if (resultSet.next()) {
                        // at least 1 matching data in table (email is set as unique. so, at most 1 match)
                        JOptionPane.showMessageDialog(null, "Login Success!!");
                    } else {
                        // 0 matching data in table
                        JOptionPane.showMessageDialog(null, "Login Failed: Incorrect email or password");
                    }

                } catch (SQLException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Login Failed: " + exception.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Fill ALL fields");
            }

        }
    }

}
