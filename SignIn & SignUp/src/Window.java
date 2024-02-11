import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        //Full Window
        setTitle("Sign In/Up Form");
        setBounds(500, 10, 500, 750);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //SignUp Section
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setBounds(0, 0, 500, 420);
        add(signUpForm);

        //SignIn Section
        SignInForm signInForm = new SignInForm();
        signInForm.setBounds(0, 430, 500, 300);
        add(signInForm);
    }
}
