/*
 * Program: Symulacja busów przejeżdżających przez most
 *    Plik: NarrowBridgeWindow.java
 *          - odpowiada za wyświetlenie okna głównego, w którym użytkownik wybiera
 *            parametry symulacji, oraz w którym wyświetlane są logi
 *
 *   Autor: Zofia Zub
 *  indeks: 259114
 *    Data: 14.01.2022 r.
 */


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NarrowBridgeWindow extends JFrame implements ActionListener, ChangeListener {
    private static final String APP_TITLE = "Symulacja busów na moście";
    private static final String APP_AUTHOR = "Zofia Zub \nnr indeksu 259114 \n13.01.2022";

    private static int TRAFFIC = 0;

    public JMenuBar menuBar = new JMenuBar();
    public JMenu menu = new JMenu("Menu");
    public JMenu limitationOptions = new JMenu("Limitation options");
    public JMenuItem limitToThree = new JMenuItem("max 3 busy na moście");
    public JMenuItem limitToOne = new JMenuItem("max 1 bus na moście");
    public JMenuItem maxThreeOneWay = new JMenuItem("max 3 busy w jednym kierunku");
    public JMenuItem noLimit = new JMenuItem("brak ograniczeń");
    public JMenuItem authorMenu = new JMenuItem("Autor");
    public JLabel waiting = new JLabel("Kolejka:");
    public JLabel onTheBridge = new JLabel("Na moście:");

    public JSlider slider = new JSlider(0, 5000, 0);
    public JTextArea logInfo = new JTextArea(15, 40);
    public JTextField waitText = new JTextField("", 20);
    public JTextField onTheBridgeText = new JTextField("", 20);
    public JPanel panel = new JPanel();

    public NarrowBridgeWindow() {
        super(APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        logInfo.setSize(900, 500);
        JScrollPane scrollPane = new JScrollPane(logInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);
        setResizable(false);
        setContentPane(panel);
        displayMenu();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayMenu() {
        menu.addActionListener(this);
        limitationOptions.addActionListener(this);
        limitToThree.addActionListener(this);
        limitToOne.addActionListener(this);
        maxThreeOneWay.addActionListener(this);
        noLimit.addActionListener(this);
        authorMenu.addActionListener(this);
        slider.addChangeListener(this);

        panel.add(menuBar);

        menuBar.add(menu);
        menuBar.add(limitationOptions);

        limitationOptions.add(limitToOne);
        limitationOptions.add(limitToThree);
        limitationOptions.add(maxThreeOneWay);
        limitationOptions.add(noLimit);

        menu.add(authorMenu);
        panel.add(slider);
        panel.add(waiting);
        panel.add(waitText);
        panel.add(onTheBridge);
        panel.add(onTheBridgeText);
        panel.add(logInfo);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        NarrowBridge bridge = new NarrowBridge();

        while (true) {
            Bus bus = new Bus(bridge);
            new Thread(bus).start();
            try {
                Thread.sleep(5500 - TRAFFIC);
            } catch (InterruptedException e) {
            }
        }
    }

    public void logBusInfo(StringBuilder sb) {
        logInfo.append(String.valueOf(sb));
    }

    public void logBridgeInfo(StringBuilder sb) {
        onTheBridgeText.setText(String.valueOf(sb));
    }

    public void logQueueInfo(StringBuilder sb) {
        waitText.setText(String.valueOf(sb));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == authorMenu) {
            JOptionPane.showMessageDialog(this, APP_AUTHOR, APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
        }

        if (source == limitToOne) {
            Bus.limitation = 1;
        }

        if (source == limitToThree) {
            Bus.limitation = 3;
        }

        if (source == maxThreeOneWay) {
            Bus.limitation = 30;
            int randomDirection = (Math.random() <= 0.5) ? 1 : 2;
            if (randomDirection == 1) {
                Bus.directionLimitation = BusDirection.EAST;
            } else {
                Bus.directionLimitation = BusDirection.WEST;
            }
        }

        if (source == noLimit) {
            Bus.limitation = 0;
        }

        if (source == slider) {
            slider.getValue();
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        TRAFFIC = slider.getValue();
    }
}
