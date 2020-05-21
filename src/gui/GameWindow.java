package gui;

import java.awt.Color;
import javax.swing.*;
import components.Driving;
import utilities.Point;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private Driving currDriving = null;
    private CreateDialog createDialog;
    private JMenuBar menuBar;
    private RoadPanel roadPanel;
    private JButton[] buttons;

    public GameWindow() {
        super("Road system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(Point.getMaxX() + 40, Point.getMaxY() + 130);

        createDialog = new CreateDialog(this);

        setMenu();
        setRoadPanel();
        setButtons();
    }

    private void setMenu() {
        menuBar = new JMenuBar();

        /** File menu */
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        file.add(exit);
        menuBar.add(file);

        /** Background menu */
        JMenu background = new JMenu("Background");
        JMenuItem blue = new JMenuItem("Blue");
        blue.addActionListener(e -> {
            roadPanel.setBackground(Color.CYAN);
            roadPanel.repaint();
        });
        JMenuItem none = new JMenuItem("None");
        none.addActionListener(e -> {
            roadPanel.setBackground(Color.WHITE);
            roadPanel.repaint();
        });
        background.add(blue);
        background.add(none);
        menuBar.add(background);

        /** Vehicle color menu */
        JMenu vehicleColor = new JMenu("Vehicle color");
        blue = new JMenuItem("Blue");
        blue.addActionListener(e -> roadPanel.setVehicleColor(Color.BLUE));
        JMenuItem magenta = new JMenuItem("Magenta");
        magenta.addActionListener(e -> roadPanel.setVehicleColor(Color.MAGENTA));
        JMenuItem orange = new JMenuItem("Orange");
        orange.addActionListener(e -> roadPanel.setVehicleColor(Color.ORANGE));
        JMenuItem random = new JMenuItem("Random");
        random.addActionListener(e -> roadPanel.setVehicleColor(null));

        vehicleColor.add(blue);
        vehicleColor.add(magenta);
        vehicleColor.add(orange);
        vehicleColor.add(random);
        menuBar.add(vehicleColor);

        /** Help menu */
        JMenu help = new JMenu("Help");
        JMenuItem help2 = new JMenuItem("Help");
        help2.addActionListener(e -> JOptionPane.showMessageDialog(this, "Home Work 3\nGUI & Threads"));
        help.add(help2);
        menuBar.add(help);

        setJMenuBar(menuBar);
    }

    private void setRoadPanel() {
        roadPanel = new RoadPanel(this);
        roadPanel.setBounds(10, 0, 800, 600);
        roadPanel.setBackground(Color.WHITE);
        add(roadPanel);
    }

    private final int CREATE_BUTTON = 0;
    private final int START_BUTTON  = 1;
    private final int STOP_BUTTON   = 2;
    private final int RESUME_BUTTON = 3;
    private final int INFO_BUTTON   = 4;
    private final int BUTTON_COUNT  = 5;

    private void setButtons() {
        buttons = new JButton[BUTTON_COUNT];
        buttons[CREATE_BUTTON] = new JButton("Create road sytem");
        buttons[START_BUTTON]  = new JButton("Start");
        buttons[STOP_BUTTON]   = new JButton("Stop");
        buttons[RESUME_BUTTON] = new JButton("Resume");
        buttons[INFO_BUTTON]   = new JButton("Info");

        for (int i = 0; i < BUTTON_COUNT; i++) {
            buttons[i].setBounds(i * Point.getMaxX() / BUTTON_COUNT, Point.getMaxY() + 10, Point.getMaxX() / BUTTON_COUNT, 50);
            add(buttons[i]);
        }

        buttons[CREATE_BUTTON].addActionListener(e -> createDialog.setVisible(true));
    }

    public void Run() {
        setVisible(true);
    }

	public Driving getDriving() {
		return currDriving;
	}

	public void setDriving(int junc_count, int vehi_count) {
        currDriving = new Driving(junc_count, vehi_count);
        roadPanel.repaint();
	}
}
