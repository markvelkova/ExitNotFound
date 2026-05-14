package toolkit;
import enums.Direction;
import enums.PlayerState;
import maputils.interfaces.FoundableObject;
import maputils.interfaces.MovableMapObject;
import maputils.interfaces.TopObject;
import maputils.Map.Coord;
import maputils.Map;

import java.io.Serializable;

public class Player implements MovableMapObject, TopObject, Serializable {

    public enum FacingDirection {west, north, east, south}

    private FacingDirection facing;
    private Coord coord;
    private String name;
    private int health;
    private int numberOfUnsettlingMessagesHeard;
    private boolean foundTheDoorAndLeftTheGame;
    private boolean currentTileBad;
    public PlayerState state;
    public FoundableObject objectFoundLastMove;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.numberOfUnsettlingMessagesHeard = 0;
        this.facing = FacingDirection.north;
        this.coord = new Coord(0, 0);
        this.foundTheDoorAndLeftTheGame = false;
        this.state = PlayerState.playing;
    }
    public boolean shouldLive() {
        return health > 0;
    }
    public boolean isFoundTheDoorAndLeftTheGame() { return this.foundTheDoorAndLeftTheGame; }
    public void findDoor(){
        foundTheDoorAndLeftTheGame = true;
    }
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

    private Coord calculateNewDesiredPlayerPosition(Direction d) {
        int dx = 0, dy = 0;
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
                    case north -> dx = -1;
                    case south -> dx = 1;
                    case east -> dy = -1;
                    case west -> dy = 1;
                }
            }
            case Direction.right -> {
                switch (facing) {
                    case north -> dx = 1;
                    case south -> dx = -1;
                    case east -> dy = 1;
                    case west -> dy = -1;
                }
            }
        }
        return new Coord(coord.x + dx, coord.y + dy);
    }
    private FacingDirection calculateNewFacingDirection(Direction d) {
        FacingDirection newFacing = facing;
        switch (d) {
            case Direction.straight -> {}
            case Direction.left -> {
                switch (facing) {
                    case north -> newFacing = FacingDirection.west;
                    case south -> newFacing = FacingDirection.east;
                    case east -> newFacing = FacingDirection.north;
                    case west -> newFacing = FacingDirection.south;
                }
            }
            case Direction.right -> {
                switch (facing) {
                    case north -> newFacing = FacingDirection.east;
                    case south -> newFacing = FacingDirection.west;
                    case east -> newFacing = FacingDirection.south;
                    case west -> newFacing = FacingDirection.north;
                }
            }
        }
        return newFacing;
    }
    private void inspectTileForTopObjects(int x,int y, Map map) {
        objectFoundLastMove = map.getFoundableObject(y, x);
        if (objectFoundLastMove != null)
            objectFoundLastMove.find(this);
    }

    public boolean move(Direction d, Map map) {
        Coord desired = calculateNewDesiredPlayerPosition(d);
        map.discover(desired.y, desired.x);
        if (!map.isWallOrOutside(desired.y, desired.x)) { // pozor, map indexuje opačně
            inspectTileForTopObjects(desired.x,desired.y,map);
            coord = desired;
            facing = calculateNewFacingDirection(d);
            return true;
        } else {
            return false;
        }
    }

    public boolean foundNewObject(){
        return objectFoundLastMove != null;
    }
    public void useFoundObject() {
        objectFoundLastMove = null;
    }

    public boolean shouldBeWorried() {
        return (currentTileBad || health < 15);
    }

    public void updateByCurrentTile(Map map) {
        int healthImpact = map.getPlayerHealthTileEffect(coord.y, coord.x);
        currentTileBad = healthImpact < 0;
        updateHealth(healthImpact);
        if (health <= 0)
            state = PlayerState.dead;
    }
    public void updateHealth(int update) {
        health += update;
    }
}
