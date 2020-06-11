package gui;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

import components.*;
import components.builders.*;
import utilities.Point;

/**
 * Road system primary GUI window
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    /** Backend simulation primary object */
    private ThreadedDriving currDriving = null;
    /** Thread made from above object */
    private Thread drivingThread = null;
    /** Road system creation dialog box */
    private JDialog createDialog;
    /** Builder road system creation dialog box */
    private JDialog builderDialog;
    /** Car cloning dialog */
    private JDialog cloneCarDialog;
    /** Primary menubar */
    private JMenuBar menuBar;
    /** Road drawing panel */
    private RoadPanel roadPanel;
    /** GUI Buttons */
    private JButton[] buttons;

    /** Constructor, initializes and places all the gui components */
    public GameWindow() {
        /** Set up general window properties */
        super("Road system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(Point.getMaxX() + 40, Point.getMaxY() + 130);

        /** Set up internal gui components */
        initCreationDialog();
        initBuilderDialog();
        initCloneCarDialog();

        setMenu();
        setRoadPanel();
        setButtons();
    }

    /** Sets up the menu bar */
    private void setMenu() {
        menuBar = new JMenuBar();

        /** File menu */
        JMenu file = new JMenu("File (<- HW4 OPTIONS)");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            if (currDriving != null)
                currDriving.stop();
            dispose();
        });
        JMenuItem buildMap = new JMenuItem("Build map with Builder");
        buildMap.addActionListener(e -> {
            builderDialog.setVisible(true);
        });
        JMenuItem cloneCar = new JMenuItem("Clone a vehicle");
        cloneCar.addActionListener(e -> {
            if (currDriving != null)
                cloneCarDialog.setVisible(true);
        });
        file.add(buildMap);
        file.add(cloneCar);
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

    /** Creates and places the the road drawing panel */
    private void setRoadPanel() {
        roadPanel = new RoadPanel(this);
        roadPanel.setBounds(10, 0, 800, 600);
        roadPanel.setBackground(Color.WHITE);
        add(roadPanel);
    }

    private static final int CREATE_BUTTON = 0;
    private static final int START_BUTTON = 1;
    private static final int PAUSE_BUTTON = 2;
    private static final int RESUME_BUTTON = 3;
    private static final int INFO_BUTTON = 4;
    private static final int BUTTON_COUNT = 5;

    /** Creates and places the GUI buttons */
    private void setButtons() {
        buttons = new JButton[BUTTON_COUNT];
        buttons[CREATE_BUTTON] = new JButton("Create road sytem");
        buttons[START_BUTTON] = new JButton("Start");
        buttons[PAUSE_BUTTON] = new JButton("Pause");
        buttons[RESUME_BUTTON] = new JButton("Resume");
        buttons[INFO_BUTTON] = new JButton("Info");

        for (int i = 0; i < BUTTON_COUNT; i++) {
            buttons[i].setBounds(10 + i * (Point.getMaxX() / BUTTON_COUNT), Point.getMaxY() + 10,
                    Point.getMaxX() / BUTTON_COUNT, 50);
            add(buttons[i]);
        }

        /** Set actions on button clicks */
        buttons[CREATE_BUTTON].addActionListener(e -> createDialog.setVisible(true));
        buttons[START_BUTTON].addActionListener(e -> {
            /** Do we have a thread to work with? */
            if (drivingThread != null) {
                try {
                    drivingThread.start();
                } catch (IllegalThreadStateException exception) {
                    currDriving.resume();
                }
            }
        });
        buttons[PAUSE_BUTTON].addActionListener(e -> {
            if (currDriving != null)
                currDriving.pause();
        });
        buttons[RESUME_BUTTON].addActionListener(e -> {
            if (currDriving != null)
                currDriving.resume();
        });
        buttons[INFO_BUTTON].addActionListener(e -> displayVehicleTable(tableShowing ? false : true));
    }

    /** Show the window */
    public void Run() {
        setVisible(true);
    }

    /**
     * Fetch the internal driving object ref used by RoadPanel
     */
    public Driving getDriving() {
        return currDriving;
    }

    /** Prebuild the dialog box */
    private void initCreationDialog() {
        createDialog = new JDialog(this, "Create road system", true);

        createDialog.setSize(600, 300);
        createDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        createDialog.setLayout(null);

        /** Dialog component creation and placement */
        JLabel juncLabel = new JLabel("Number of junctions", SwingConstants.CENTER);
        juncLabel.setBounds(30, 0, 540, 50);
        createDialog.add(juncLabel);

        JSlider junctionSlider = new JSlider(JSlider.HORIZONTAL, 3, 20, 11);
        junctionSlider.setMajorTickSpacing(1);
        junctionSlider.setBounds(30, 50, 540, 50);
        junctionSlider.setPaintTicks(true);
        junctionSlider.setPaintLabels(true);
        createDialog.add(junctionSlider);

        JLabel vehiLabel = new JLabel("Number of vehicles", SwingConstants.CENTER);
        vehiLabel.setBounds(20, 100, 540, 50);
        createDialog.add(vehiLabel);

        JSlider vehicleSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        vehicleSlider.setMajorTickSpacing(5);
        vehicleSlider.setBounds(30, 150, 540, 50);
        vehicleSlider.setPaintTicks(true);
        vehicleSlider.setPaintLabels(true);
        createDialog.add(vehicleSlider);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            setDriving(junctionSlider.getValue(), vehicleSlider.getValue(), null);
            createDialog.setVisible(false);
        });
        okButton.setBounds(0, 200, 300, 50);
        createDialog.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> createDialog.setVisible(false));
        cancelButton.setBounds(300, 200, 300, 50);
        createDialog.add(cancelButton);
    }
    
    private void initBuilderDialog() {
        builderDialog = new JDialog(this, "Create map using builder", true);
        builderDialog.setSize(250, 150);
        builderDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        builderDialog.setLayout(null);

        JButton cityButton = new JButton("Build map with CityBuilder");
        cityButton.addActionListener(e -> {
            setDriving(0, 0, new CityBuilder());
            builderDialog.setVisible(false);
        });
        cityButton.setBounds(10, 10, 220, 45);
        builderDialog.add(cityButton);

        JButton countryButton = new JButton("Build map with CountryBuilder");
        countryButton.addActionListener(e -> {
            setDriving(0, 0, new CountryBuilder());
            builderDialog.setVisible(false);
        });
        countryButton.setBounds(10, 60, 220, 45);
        builderDialog.add(countryButton);
    }
    
    private void initCloneCarDialog() {
        cloneCarDialog = new JDialog(this, "Clone a vehicle", true);
        cloneCarDialog.setSize(310, 80);
        cloneCarDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        cloneCarDialog.setLayout(null);

        JLabel identLabel = new JLabel("Vehicle #");
        identLabel.setBounds(10, 10, 70, 20);
        cloneCarDialog.add(identLabel);

        JTextField idField = new JTextField();
        idField.setBounds(80, 10, 80, 20);
        cloneCarDialog.add(idField);

        JButton cloneButton = new JButton("Clone Vehicle");
        cloneButton.setBounds(160, 10, 120, 20);
        cloneButton.addActionListener(e -> {
            try {
                currDriving.addVehicle(Integer.parseInt(idField.getText()));
                cloneCarDialog.setVisible(false);
            } catch (NumberFormatException exception) {
                // Do nothing
            }
        });
        cloneCarDialog.add(cloneButton);
    }

    /**
     * Create/Update the current driving object according to the info from the road
     * system creation dialog
     * 
     * @param junc_count count of juncions
     * @param vehi_count count of vehicles
     * @param builder map builder, can be null
     */
    private void setDriving(int junc_count, int vehi_count, Builder builder) {
        /** Stop all current threads */
        if (currDriving != null)
            currDriving.stop();

        /** Create relevant objects */
        if (builder == null)
            currDriving = new ThreadedDriving(roadPanel, junc_count, vehi_count);
        else
            currDriving = new ThreadedDriving(roadPanel, builder);

        drivingThread = new Thread(currDriving, "Driving Thread");
        /** Redraw road panel to reflect changes */
        roadPanel.repaint();
        /** If the table is being shown, update it too */
        if (tableShowing) {
            /* Refresh in an odd way */
            displayVehicleTable(false);
            displayVehicleTable(true);
        }
    }

    private JPanel tablePanel = null;
    private boolean tableShowing = false;

    /**
     * Show a table detailing all the info on currently exisiting vehicles
     * 
     * @param doShow show/hide the table
     */
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
                Junction junction = (Junction) vehicle.getCurrentRoutePart();
                row.add("Junction " + junction.getJunctionName());
            } else {
                Road road = (Road) vehicle.getCurrentRoutePart();
                row.add("Road " + road.getStartJunction().getJunctionName() + "-"
                        + road.getEndJunction().getJunctionName());
            }

            row.add(String.valueOf(vehicle.getTimeOnCurrentPart()));
            row.add(String.valueOf(
                    Math.min(vehicle.getVehicleType().getAverageSpeed(), vehicle.getLastRoad().getMaxSpeed())));

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
