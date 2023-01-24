package loginModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private static JLabel usernameLabel, passwordLabel, messageLabel;
    private static JTextField usernameText;
    private static JPasswordField passwordText;
    private static JButton loginButton;
    private static JButton resetButton;
    private static JPanel loginPanel;
    private static String username, password;

    private static Login instance = null;


    //SINGLETON DESIGN PATTERN
    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }

        return instance;
    }

    public Login() {
        usernameLabel = new JLabel("Username");
        usernameText = new JTextField(26);  // Setting length of user to be 26 character

        passwordLabel = new JLabel("Password");
        passwordText = new JPasswordField();

        messageLabel = new JLabel();
        messageLabel.setFont(new Font(null, Font.BOLD, 12));


        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");


        //Creating Panel
        loginPanel = new JPanel(new GridLayout(4, 1));

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameText);

        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);

        loginPanel.add(loginButton);
        loginPanel.add(resetButton);

        loginPanel.add(messageLabel);


        add(loginPanel, BorderLayout.CENTER);
        setTitle("Login Page");

        //Action when click login  button
        loginButton.addActionListener(this);

        //Action when click   button
        resetButton.addActionListener(this);


    }


    /**
     * This is Action Listener for login and reset button
     * @param e Login or Reset button.
     *          login button will convert username and password to string and pass to proxy class.
     *          reset button will clear the username and password field.
     *          If login is successful, it will display a message and close the login page.
     *          If login is unsuccessful, it will display a message.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resetButton) {
            usernameText.setText("");
            passwordText.setText("");
        }

        if (e.getSource() == loginButton) {
            username = usernameText.getText().toUpperCase();
            password = String.valueOf(passwordText.getPassword());
            login(username, password);

            if(ProxyLogin.loginStatus == true){
                messageLabel.setText(ProxyLogin.loginMessage);
                messageLabel.setForeground(Color.GREEN);
                dispose();
              //  new MainGUI();
            }
            else{
                messageLabel.setText(ProxyLogin.loginMessage);
                messageLabel.setForeground(Color.RED);
            }

        }

    }

    


    /**
     * This method will pass username and password to proxy class
     * @param incomingUsername- username from user
     * @param incomingPassword- password from user
     */
    public void login(String incomingUsername, String incomingPassword) {
        Login.username = incomingUsername;
        Login.password = incomingPassword;

        //PROXY DESIGN PATTERN
        ProxyLogin checkLogin = new ProxyLogin(username, password);
        // boolean flag = checkLogin.doValidate("JOHN", "mypassword1", "");
        checkLogin.doValidate();
    }

    public void closeLogin(){
        dispose();
    }
}
