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
     * constructor with two integers for junctions amount and roads amount
     * @param junction_count
     * @param road_count
     */
    public Map(int junction_count, int road_count) {
        generateJunctions(junction_count);
        generateRandomRoads(road_count);
    }

    /**
     * constructor with junctions list
     * @param junctions
     */
    public Map(ArrayList<Junction> junctions) {
        this.junctions = junctions;
        generateSeqRoads();
    }

    /**
     * constructor with junctions list and roads list
     * @param junctions
     * @param roads
     */
    public Map(ArrayList<Junction> junctions, ArrayList<Road> roads) {
        this.junctions = junctions;
        this.roads = roads;
    }

    /**
     * function that generates junctions number
     * @param count
     */
    private void generateJunctions(int count) {
        junctions = new ArrayList<Junction>(count);

        Random randObj = new Random();

        for (int i = 0; i < count; i++) {
            junctions.add(new Junction(String.format("No%d", i + 1),
                    new Point(randObj.nextDouble() * Point.MAXIMUM_X, randObj.nextDouble() * Point.MAXIMUM_Y)));
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

                if (to.getHasLights() && to.getEnteringRoads().size() == 1) {
                    to.setLightsOn();
                }
                roads.add(road);
            }
        }
    }

    /**
     * function that generates random roads
     * @param count that stands for number of roads
     */
    private void generateRandomRoads(int count) {
        roads = new ArrayList<Road>(count);

        int created = 0;
        int junc_count = junctions.size();
        Random randObj = new Random();

        while (created < count) {
            int idx_a = randObj.nextInt(junc_count);
            int idx_b = randObj.nextInt(junc_count);

            if (idx_a == idx_b)
                continue;

            Road road = new Road(junctions.get(idx_a), junctions.get(idx_b));

            if (roads.contains(road))
                continue;

            roads.add(road);
            created++;
        }
    }

    /**
     * function that adds a road to the map
     * @param road
     * @return boolean value that says if the map was not added due that its already in the map
     */
    public boolean addRoad(Road road) {
        if (roads.contains(road))
            return false;
        roads.add(road);
        return true;
    }

    /**
     * function that removes roads
     * @param road
     * @return a boolean value that says if the road was added
     */
    public boolean removeRoad(Road road) {
        return roads.remove(road);
    }

    /**
     * function that adds a junction
     * @param junction
     * @return a boolean value that says if the junction was added
     */
    public boolean addJunction(Junction junction) {
        if (junctions.contains(junction))
            return false;
        junctions.add(junction);
        System.out.printf("%s has been added to the map.\n", junction.toString());
        return true;
    }

    /**
     * function that removes a junction
     * @param junction
     * @return a boolean value that says if the junction was removed
     */
    public boolean removeJunction(Junction junction) {
        boolean result = junctions.remove(junction);

        if (result)
            System.out.printf("%s has been removed from the map.\n", junction.toString());

        return result;
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
