import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        setTitle("Stockwell");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);

        LoginPanel loginPanel = new LoginPanel();

        setContentPane(loginPanel.getPanel());
    }
}
