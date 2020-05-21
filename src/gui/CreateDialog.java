package gui;

import javax.swing.*;

public class CreateDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    public CreateDialog(GameWindow parent) {
        super(parent, "Create road system", true);

        setSize(600,300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(null);

        JLabel juncLabel = new JLabel("Number of junctions", SwingConstants.CENTER);
        juncLabel.setBounds(30, 0, 540, 50);
        add(juncLabel);

        JSlider junctionSlider = new JSlider(JSlider.HORIZONTAL, 3, 20, 11);
        junctionSlider.setMajorTickSpacing(1);
        junctionSlider.setBounds(30, 50, 540, 50);
        junctionSlider.setPaintTicks(true);
        junctionSlider.setPaintLabels(true);
        add(junctionSlider);

        JLabel vehiLabel = new JLabel("Number of vehicles", SwingConstants.CENTER);
        vehiLabel.setBounds(20, 100, 540, 50);
        add(vehiLabel);

        JSlider vehicleSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        vehicleSlider.setMajorTickSpacing(5);
        vehicleSlider.setBounds(30, 150, 540, 50);
        vehicleSlider.setPaintTicks(true);
        vehicleSlider.setPaintLabels(true);
        add(vehicleSlider);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            parent.setDriving(junctionSlider.getValue(), vehicleSlider.getValue());
            setVisible(false);
        });
        okButton.setBounds(0, 200, 300, 50);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        cancelButton.setBounds(300, 200, 300, 50);
        add(cancelButton);
    }
}
