package gui;

import java.awt.*;
import javax.swing.*;

import components.*;
import utilities.Utilities;

public class RoadPanel extends JPanel implements Utilities {
    private static final long serialVersionUID = 1L;
    private static final Color[] colors = { Color.BLUE, Color.MAGENTA, Color.ORANGE };
    Color vehicleColor = null;
    GameWindow parent;

    public RoadPanel(GameWindow parent) {
        this.parent = parent;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Driving currDriving = parent.getDriving();

        if (currDriving == null)
            return;

        g.setColor(Color.BLACK);

        /** Draw road lines */
        for (Road road : currDriving.getMap().getRoads()) {
            g.drawLine((int) road.getStartJunction().getX(),
                       (int) road.getStartJunction().getY(),
                       (int) road.getEndJunction().getX(),
                       (int) road.getEndJunction().getY());
        }

        /** Draw junction circles */
        final double length = 20, angle_offset = 0.15;
        for (Junction junction : currDriving.getMap().getJunctions()) {
            if (junction instanceof LightedJunction) {
                LightedJunction lJunction = (LightedJunction) junction;
                if (lJunction.getLights().getTrafficLightsOn()) {
                    Junction dest = lJunction.getExitingRoads().get(lJunction.getLights().getGreenLightIndex()).getEndJunction();

                    int x1 = (int) lJunction.getX();
                    int y1 = (int) lJunction.getY();
                    double tetha = Math.atan2(y1 - dest.getY(), x1 - dest.getX());
                    int x2 = (int) (x1 - length * Math.cos(tetha + angle_offset));
                    int y2 = (int) (y1 - length * Math.sin(tetha + angle_offset));
                    int x3 = (int) (x1 - length * Math.cos(tetha - angle_offset));
                    int y3 = (int) (y1 - length * Math.sin(tetha - angle_offset));
                    // Debug
                    //g.drawLine(x1, y1, (int)dest.getX(), (int)dest.getY());
                    
                    /** Draw green triangle pointing at destination */
                    g.setColor(Color.GREEN);
                    g.fillPolygon(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GREEN);
                }
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillOval((int) junction.getX() - 5, (int) junction.getY() - 5, 10, 10);
        }
        /** Draw vehicle sprites */
        for (Vehicle vehicle : currDriving.getVehicles()) {
            int x1, x2, y1, y2;
            RouteParts currPart = vehicle.getCurrentRoutePart();
            
            if (currPart instanceof Junction) {
                Junction junction = (Junction) currPart;
                
                x1 = x2 = (int) junction.getX();
                y1 = y2 = (int) junction.getY();
            } else {
                Road road = (Road) currPart;
                Junction start = road.getStartJunction();
                Junction end = road.getEndJunction();
                x2 = (int) end.getX();
                y2 = (int) end.getY();
                
                double dx = start.getX() - end.getX();
                double dy = start.getY() - end.getY();
                double ratio = (vehicle.getTimeFromRouteStart() * Math.min(vehicle.getVehicleType().getAverageSpeed(), road.getMaxSpeed())) / road.getLength();
                x1 = (int) (start.getX() + dx * ratio);
                y1 = (int) (start.getY() + dy * ratio);
            }
            g.setColor((vehicleColor != null) ? vehicleColor : colors[getRandomInt(0, colors.length)]);
            drawRotatedVehicle(g, x1, y1, x2, y2, 10, 4);
        }
    }

    private void drawRotatedVehicle(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1, delta = 10;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = delta, xn = xm, ym = h, yn = -h, x;
        double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
        double sin = dy / D, cos = dx / D;
        x = xm * cos - ym * sin + x1;
        xx = xm1 * cos - ym1 * sin + x1;
        ym = xm * sin + ym * cos + y1;
        ym1 = xm1 * sin + ym1 * cos + y1;
        xm = x;
        xm1 = xx;
        x = xn * cos - yn * sin + x1;
        xx = xn1 * cos - yn1 * sin + x1;
        yn = xn * sin + yn * cos + y1;
        yn1 = xn1 * sin + yn1 * cos + y1;
        xn = x;
        xn1 = xx;
        int[] xpoints = { (int) xm1, (int) xn1, (int) xn, (int) xm };
        int[] ypoints = { (int) ym1, (int) yn1, (int) yn, (int) ym };
        g.fillPolygon(xpoints, ypoints, 4);
        g.setColor(Color.BLACK);
        g.fillOval((int) xm1 - 2, (int) ym1 - 2, 4, 4);
        g.fillOval((int) xn1 - 2, (int) yn1 - 2, 4, 4);
        g.fillOval((int) xm - 2, (int) ym - 2, 4, 4);
        g.fillOval((int) xn - 2, (int) yn - 2, 4, 4);
    }

    public void setVehicleColor(Color color) {
        vehicleColor = color;
        repaint();
    }
}
