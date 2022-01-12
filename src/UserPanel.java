import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public class UserPanel extends JFrame{
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        new UserPanel();
    }

    private JLabel label1       = new JLabel();
    private JComboBox comboBox1 = new JComboBox();
    private JLabel label2       = new JLabel();
    private JOptionPane busID   = new JOptionPane();
    private JLabel label3       = new JLabel();
    private JOptionPane queue   = new JOptionPane();

    public UserPanel panel = new UserPanel();

    public UserPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 2000);
        setLocationRelativeTo(null);
        setContentPane(panel);
        createComponents();
        setVisible(true);
    }

    private void createComponents(){

        label1.setText("Ograniczenie ruchu:");
        label2.setText("Na moscie:");
        label3.setText("W kolejce:");
    }


}
