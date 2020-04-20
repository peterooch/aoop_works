package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

/**
 * Map class
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

public class Map {
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;

    public Map() {
        this(20);
    }

    public Map(int junction_count) {
        generateJunctions(junction_count);
        generateSeqRoads();
    }

    public Map(int junction_count, int road_count) {
        generateJunctions(junction_count);
        generateRandomRoads(road_count);
    }

    public Map(ArrayList<Junction> junctions) {
        this.junctions = junctions;
        generateSeqRoads();
    }

    public Map(ArrayList<Junction> junctions, ArrayList<Road> roads) {
        this.junctions = junctions;
        this.roads = roads;
    }

    private void generateJunctions(int count) {
        junctions = new ArrayList<Junction>(count);

        Random randObj = new Random();

        for (int i = 0; i < count; i++) {
            junctions.add(new Junction(String.format("No%d", i + 1),
                    new Point(randObj.nextDouble() * Point.MAXIMUM_X, randObj.nextDouble() * Point.MAXIMUM_Y)));
        }
    }

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

    public boolean addRoad(Road road) {
        if (roads.contains(road))
            return false;
        roads.add(road);
        return true;
    }

    public boolean removeRoad(Road road) {
        return roads.remove(road);
    }

    public boolean addJunction(Junction junction) {
        if (junctions.contains(junction))
            return false;
        junctions.add(junction);
        System.out.printf("%s has been added to the map.\n", junction.toString());
        return true;
    }

    public boolean removeJunction(Junction junction) {
        boolean result = junctions.remove(junction);

        if (result)
            System.out.printf("%s has been removed from the map.\n", junction.toString());

        return result;
    }

    public ArrayList<Junction> getJunctions() {
        return junctions;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }
}
