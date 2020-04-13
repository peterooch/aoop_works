package components;

import java.util.ArrayList;
import java.util.Random;

import utilities.Point;

/**
 * Map class
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */

public class Map {
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;

    public Map() {
        this(20,20);
    }
    public Map(int junction_count, int road_count) {
        junctions = new ArrayList<Junction>(junction_count);
        
        Random randObj = new Random();

        for (int i = 0; i < junction_count; i++)
            junctions.add(new Junction(String.format("No%d", i + 1),
                                       new Point(randObj.nextDouble() * Point.MAXIMUM_X, randObj.nextDouble() * Point.MAXIMUM_Y)));

        generateRoads(road_count);
    }
    public Map(ArrayList<Junction> junctions) {
        this.junctions = junctions;
        generateRoads(junctions.size());
    }
    public Map(ArrayList<Junction> junctions, ArrayList<Road> roads) {
        this.junctions = junctions;
        this.roads = roads;
    }
    private void generateRoads(int count) {
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
        return true;
    }
    public boolean removeJunction(Junction junction) {
        return junctions.remove(junction);
    }
	public ArrayList<Junction> getJunctions() {
		return junctions;
	}
	public ArrayList<Road> getRoads() {
		return roads;
	}
}
