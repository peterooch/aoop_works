package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import components.*;
import utilities.Point;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private ThreadedDriving currDriving = null;
    private Thread drivingThread = null;
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
        exit.addActionListener(e -> {
            if (currDriving != null)
                currDriving.stop();
            dispose();
        });
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

    private static final int CREATE_BUTTON = 0;
    private static final int START_BUTTON  = 1;
    private static final int STOP_BUTTON   = 2;
    private static final int RESUME_BUTTON = 3;
    private static final int INFO_BUTTON   = 4;
    private static final int BUTTON_COUNT  = 5;

    private void setButtons() {
        buttons = new JButton[BUTTON_COUNT];
        buttons[CREATE_BUTTON] = new JButton("Create road sytem");
        buttons[START_BUTTON]  = new JButton("Start");
        buttons[STOP_BUTTON]   = new JButton("Stop");
        buttons[RESUME_BUTTON] = new JButton("Resume");
        buttons[INFO_BUTTON]   = new JButton("Info");

        for (int i = 0; i < BUTTON_COUNT; i++) {
            buttons[i].setBounds(10 + i * (Point.getMaxX() / BUTTON_COUNT), Point.getMaxY() + 10,
                    Point.getMaxX() / BUTTON_COUNT, 50);
            add(buttons[i]);
        }

        buttons[CREATE_BUTTON].addActionListener(e -> createDialog.setVisible(true));
        buttons[START_BUTTON].addActionListener(e -> {
            if (drivingThread != null)
                try {
                    drivingThread.start();
                } catch (IllegalThreadStateException exception) {
                    // Do nothing
                }
        });
        buttons[STOP_BUTTON].addActionListener(e -> {
            if (currDriving != null)
                currDriving.pause();
        });
        buttons[RESUME_BUTTON].addActionListener(e -> {
            if (currDriving != null)
                currDriving.resume();
        });
        buttons[INFO_BUTTON].addActionListener(e -> displayVehicleTable(tableShowing ? false : true));
    }

    public void Run() {
        setVisible(true);
    }

	public Driving getDriving() {
		return currDriving;
	}

	public void setDriving(int junc_count, int vehi_count) {
        if (currDriving != null)
            currDriving.stop();

        currDriving = new ThreadedDriving(roadPanel, junc_count, vehi_count);
        drivingThread = new Thread(currDriving, "Driving Thread");

        roadPanel.repaint();

        if (tableShowing)
        {
            /* Refresh in an odd way */
            displayVehicleTable(false);
            displayVehicleTable(true);
        }
    }
    
    private JPanel tablePanel = null;
    private boolean tableShowing = false;
    private void displayVehicleTable(boolean doShow) {
        tableShowing = doShow;

        if (!doShow) {
            roadPanel.setVisible(true);
            if (tablePanel != null) {
                tablePanel.setVisible(false);
                remove(tablePanel);
            }
            repaint();
            return;
        }

        if (currDriving == null)
            return;

        tablePanel = new JPanel();
        tablePanel.setBounds(10, 0, 800, 600);
        tablePanel.setLayout(null);

        ArrayList<Vehicle> vlist = currDriving.getVehicles();
        
        Vector<String> columns = new Vector<String>(5);
        columns.add("Vehicle #");
        columns.add("Type");
        columns.add("Location");
        columns.add("Time on loc");
        columns.add("Speed");
        
        Vector<Vector<String>> data = new Vector<Vector<String>>(vlist.size());
        
        for (Vehicle vehicle : vlist) {
            Vector<String> row = new Vector<String>(5);
            
            row.add(String.valueOf(vehicle.getId()));
            row.add(String.valueOf(vehicle.getVehicleType()));

            if (vehicle.getCurrentRoutePart() instanceof Junction) {
                Junction junction = (Junction)vehicle.getCurrentRoutePart();
                row.add("Junction " + junction.getJunctionName());
            } else {
                Road road = (Road)vehicle.getCurrentRoutePart();
                row.add("Road " + road.getStartJunction().getJunctionName() + "-" + road.getEndJunction().getJunctionName());
            }

            row.add(String.valueOf(vehicle.getTimeOnCurrentPart()));
            row.add(String.valueOf(Math.min(vehicle.getVehicleType().getAverageSpeed(), vehicle.getLastRoad().getMaxSpeed())));
            
            data.add(row);
        }
        JTable table = new JTable(new DefaultTableModel(data, columns));
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 800, 530);
        tablePanel.add(scrollPane);
        tablePanel.setVisible(true);
        roadPanel.setVisible(false);
        add(tablePanel);
        repaint();
    }
}
