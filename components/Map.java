package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

/**
 * Map class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Map {
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
        generateJunctions(junction_count);
        generateSeqRoads();
    }

    /**
     * function that generates junctions number
     * @param count
     */
    private void generateJunctions(int count) {
        junctions = new ArrayList<Junction>(count);

        for (int i = 0; i < count; i++) {
            junctions.add(new Junction());
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
                
                /*
                if (to.getHasLights() && to.getEnteringRoads().size() == 1) {
                    to.setLightsOn();
                }
                */
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
}
