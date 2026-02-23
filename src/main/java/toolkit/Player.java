package toolkit;
import enums.Direction;
import enums.PlayerState;
import maputils.interfaces.MovableMapObject;
import maputils.interfaces.TopObject;
import maputils.Map.Coord;
import maputils.Map;

public class Player implements MovableMapObject, TopObject {

    public enum FacingDirection {west, north, east, south}

    private FacingDirection facing;
    private Coord coord;
    private String name;
    private int health;
    private int numberOfUnsettlingMessagesHeard;
    private boolean foundTheDoorAndLeftTheGame;
    public PlayerState state;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.numberOfUnsettlingMessagesHeard = 0;
        this.facing = FacingDirection.north;
        this.coord = new Coord(0, 0);
        this.foundTheDoorAndLeftTheGame = false;
        this.state = PlayerState.playing;
    }
    public boolean isFoundTheDoorAndLeftTheGame() { return this.foundTheDoorAndLeftTheGame; }

    public String getPrintableStats() {
        return toString();
    }

    public FacingDirection getFacing() { return facing; }
    public void setFacing(FacingDirection facing) { this.facing = facing; }

    public Coord getCoord() { return coord; }
    public void setCoord(Coord coord) { this.coord = coord; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getNumberOfUnsettlingMessagesHeard() { return numberOfUnsettlingMessagesHeard; }
    public void setNumberOfUnsettlingMessagesHeard(int number) { this.numberOfUnsettlingMessagesHeard = number; }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
                "| PLAYER STATS\n" +
                "| NAME: " + name + "\n" +
                "| HEALTH: " + health + "\n" +
                "| SURVIVED UNSETTLING MESSAGES: " + numberOfUnsettlingMessagesHeard + "\n" +
                "----------------------------------------\n";
    }

    public boolean move(Direction d, Map map) {
        int dx = 0, dy = 0;
        FacingDirection newFacing = facing;

        switch (d) {
            case Direction.straight -> {
                switch (facing) {
                    case north -> dy = -1;
                    case south -> dy = 1;
                    case east -> dx = 1;
                    case west -> dx = -1;
                }
            }
            case Direction.left -> {
                switch (facing) {
                    case north -> { dx = -1; newFacing = FacingDirection.west; }
                    case south -> { dx = 1;  newFacing = FacingDirection.east; }
                    case east -> { dy = -1; newFacing = FacingDirection.north; }
                    case west -> { dy = 1;  newFacing = FacingDirection.south; }
                }
            }
            case Direction.right -> {
                switch (facing) {
                    case north -> { dx = 1;  newFacing = FacingDirection.east; }
                    case south -> { dx = -1; newFacing = FacingDirection.west; }
                    case east -> { dy = 1;  newFacing = FacingDirection.south; }
                    case west -> { dy = -1; newFacing = FacingDirection.north; }
                }
            }
        }

        int newX = coord.x + dx;
        int newY = coord.y + dy;

        if (!map.isWallOrOutside(newY, newX)) { // pozor, map indexuje opačně
            coord = new Coord(newX, newY);
            facing = newFacing;
            return true;
        } else {
            return false;
        }
    }


}
