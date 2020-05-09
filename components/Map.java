package components;

import java.util.ArrayList;
import utilities.Utilities;

/**
 * Map class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Map implements Utilities {
    /**
     * map junctions
     */
    private ArrayList<Junction> junctions;
    /**
     * map roads
     */
    private ArrayList<Road> roads;

    /**
     * map traffic lights
     */
    private ArrayList<TrafficLights> lights;

    /**
     * default constructor
     */
    public Map() {
        this(20);
    }

    /**
     * constructor with one integer for junction amount
     * @param junction_count
     */
    public Map(int junction_count) {
        System.out.println("-------- Creating Junctions ------");
        generateJunctions(junction_count);
        System.out.println("---------- Creating Roads --------");
        generateSeqRoads();
        System.out.println("----- Traffic lights turn on -----");
        turnLightsOn();
    }

    /**
     * function that generates junctions number
     * @param count
     */
    private void generateJunctions(int count) {
        junctions = new ArrayList<Junction>(count);
        lights =  new ArrayList<TrafficLights>();
        for (int i = 0; i < count; i++) {
            Junction newJunc = getRandomBoolean() ? new Junction() : new LightedJunction();

            if (newJunc instanceof LightedJunction)
                lights.add(((LightedJunction)newJunc).getLights());

            junctions.add(newJunc);
        }
    }

    /**
     * function that generates sequential roads
     */
    private void generateSeqRoads() {
        int junc_count = junctions.size();
        roads = new ArrayList<Road>(junc_count * (junc_count - 1));

        for (int i = 0; i < junc_count; i++) {
            for (int j = 0; j < junc_count; j++) {
                if (j == i)
                    continue;

                Junction to = junctions.get(i);
                Junction from = junctions.get(j);

                Road road = new Road(from, to);

                roads.add(road);
            }
        }
    }

    /**
     * junctions getter
     * @return list of junctions
     */
    public ArrayList<Junction> getJunctions() {
        return junctions;
    }

    /**
     * roads getter
     * @return list of roads
     */
    public ArrayList<Road> getRoads() {
        return roads;
    }

	public ArrayList<TrafficLights> getTrafficLights() {
		return lights;
    }
    
    public void SetAllRoads() {
        // Method that does something for constructor... it doesn't make any sense
        generateSeqRoads();
    }

    public void turnLightsOn() {
        // Method that does something for constructor... it doesn't make any sense
        for (TrafficLights light : lights)
            light.setTrafficLightsOn(true);
    }
}
