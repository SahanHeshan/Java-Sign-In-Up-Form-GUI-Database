import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class SignUpForm extends JPanel implements ActionListener {
    private final JLabel title, name, email, gender, dob, pass;
    private final JTextField nameField, emailField;
    private final JPasswordField passField;
    private final JRadioButton male, female;
    private final ButtonGroup gengp;
    private final JComboBox<String> date, month, year;
    private final JCheckBox term;
    private final JButton signUpButton;

    private final String[] dates = {"1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31"};
    private final String[] months = {"Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private final String[] years = {"1990", "1991", "1992", "1993", "1994",
            "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
            "2019"};

    public SignUpForm() {
        setLayout(null);

        // Component for a 500x500 JPanel
        title = new JLabel("Sign Up");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(195, 18, 200, 35);
        title.setForeground(Color.BLUE);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setBounds(45, 70, 100, 30);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setBounds(195, 70, 250, 30);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.BOLD, 20));
        email.setBounds(45, 120, 100, 30);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField.setBounds(195, 120, 250, 30);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.BOLD, 20));
        gender.setBounds(45, 170, 100, 30);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.BOLD, 18));
        male.setBounds(192, 170, 100, 30);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.BOLD, 18));
        female.setBounds(310, 170, 100, 30);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("Birthday");
        dob.setFont(new Font("Arial", Font.BOLD, 20));
        dob.setBounds(45, 220, 100, 30);

        date = new JComboBox<>(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 18));
        date.setBounds(195, 220, 60, 30);

        month = new JComboBox<>(months);
        month.setFont(new Font("Arial", Font.PLAIN, 18));
        month.setBounds(260, 220, 90, 30);

        year = new JComboBox<>(years);
        year.setFont(new Font("Arial", Font.PLAIN, 18));
        year.setBounds(355, 220, 90, 30);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.BOLD, 20));
        pass.setBounds(45, 270, 100, 30);

        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 18));
        passField.setBounds(195, 270, 250, 30);

        term = new JCheckBox("I am not a robot.");
        term.setFont(new Font("Arial", Font.PLAIN, 18));
        term.setBounds(43, 320, 200, 30);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.setBackground(Color.BLUE);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBounds(45, 370, 400, 40);

        // Adding components to the panel
        add(title);
        add(name);
        add(nameField);
        add(email);
        add(emailField);
        add(gender);
        add(male);
        add(female);
        add(dob);
        add(date);
        add(month);
        add(year);
        add(pass);
        add(passField);
        add(term);
        add(signUpButton);

        signUpButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            if (term.isSelected()) {
                String userName = nameField.getText();
                String userEmail = emailField.getText();
                String userGender = male.isSelected() ? "Male" : "Female";
                String userDOB = year.getSelectedItem() + "-" + (month.getSelectedIndex() + 1) + "-" + date.getSelectedItem();
                String userPass = new String(passField.getPassword());

                String insertQuery = "INSERT INTO `user` (`Name`, `Email`, `Gender`, `Birthday`, `Password`) VALUES (?, ?, ?, ?, ?)";
                // Database connection details on DbConnection.java

                if (!userName.isEmpty() && !userEmail.isEmpty()  && !userPass.isEmpty()){ // proceed only if not null
                    try (Connection connection = DbConnection.getConnection();
                         PreparedStatement preparedStmt = connection.prepareStatement(insertQuery)) {

                        preparedStmt.setString(1, userName);
                        preparedStmt.setString(2, userEmail);
                        preparedStmt.setString(3, userGender);
                        preparedStmt.setString(4, userDOB);
                        preparedStmt.setString(5, userPass);

                        preparedStmt.execute();
                        JOptionPane.showMessageDialog(null, "Registration Success!!");

                    } catch (SQLException exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Registration Failed: " + exception.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Fill ALL fields");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please accept the terms & conditions!!");
            }
        }
    }
}
