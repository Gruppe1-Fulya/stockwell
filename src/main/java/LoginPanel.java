import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel{

    private JButton loginButton;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(){

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // passwordlar char[] olarak aliniyor

                if(username.equals("admin") && password.equals("admin")){
                    System.out.println("open Home Screen");
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }
}
