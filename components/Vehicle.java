package components;

public class Vehicle {
    private int id;
    private String type;
    private int speed;
    private Route currentRoute;
    private Junction lastJunction;
    private Road lastRoad;
    private boolean movesNow;
    private double spentTime;

    public Vehicle(int id, String type, Junction lastJunction) {
        this.id = id;
        this.type = type;
        this.lastJunction = lastJunction;
    }

    public void move() {

    }
    public void status() {

    }
    public void checkIn() {

    }
    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }
    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }
    public Route getCurrentRoute() {
        return currentRoute;
    }
    public void setLastJunction(Junction lastJunction) {
        this.lastJunction = lastJunction;
    }
    public Junction getLastJunction() {
        return lastJunction;
    }
    public void setLastRoad(Road lastRoad) {
        this.lastRoad = lastRoad;
    }
    public Road getLastRoad() {
        return lastRoad;
    }
    public void setMovesNow(boolean movesNow) {
        this.movesNow = movesNow;
    }
    public boolean getMovesNow() {
        return movesNow;
    }
    public void setSpentTime(double spentTime) {
        this.spentTime = spentTime;
    }
    public double getSpentTime() {
        return spentTime;
    }

}
