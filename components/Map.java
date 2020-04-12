package components;

import java.util.ArrayList;

public class Map {
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;

    public Map() {

    }
    public Map(int junction_count, int road_count) {

    }
    public Map(ArrayList<Junction> junctions) {
        this.junctions = junctions;
    }
    public Map(ArrayList<Junction> junctions, ArrayList<Road> roads) {
        this.junctions = junctions;
        this.roads = roads;
    }
    public boolean addRoad(Road road) {
        return false;
    }
    public boolean removeRoad(Road road) {
        return false;
    }
    public boolean addJunction(Junction junction) {
        return false;
    }
    public boolean removeJunction(Junction junction) {
        return false;
    }
}
