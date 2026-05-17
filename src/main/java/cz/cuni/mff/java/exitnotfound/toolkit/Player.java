package cz.cuni.mff.java.exitnotfound.toolkit;
import cz.cuni.mff.java.exitnotfound.enums.Direction;
import cz.cuni.mff.java.exitnotfound.enums.PlayerState;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.FoundableObject;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.MovableMapObject;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.TopObject;
import cz.cuni.mff.java.exitnotfound.maputils.Map.Coord;
import cz.cuni.mff.java.exitnotfound.maputils.Map;

import java.io.Serializable;

/**
 * Represents the player character in the game world.
 * The Player class manages all player-related state including position, health,
 * facing direction, and game status. It handles movement calculations relative to
 * the player's current facing direction, tracks discovered objects, and manages
 * health updates from various game events.
 */
public class Player implements MovableMapObject, TopObject, Serializable {

    /**
     * Nested enum representing the cardinal directions the player can face.
     * The player's facing direction affects how relative directions (left/right/straight/back)
     * are interpreted when executing movement commands.
     */
    public enum FacingDirection {
        /** Player facing west (left) */
        west,
        /** Player facing north (up) */
        north,
        /** Player facing east (right) */
        east,
        /** Player facing south (down) */
        south
    }

    /**
     * The direction the player is currently facing.
     */
    private FacingDirection facing;

    /**
     * The player's current position on the map.
     */
    private Coord coord;

    /**
     * The player's character name.
     */
    private String name;

    /**
     * The player's current health points.
     */
    private int health;

    /**
     * Counter tracking how many unsettling/atmospheric messages the player has received.
     */
    private int numberOfUnsettlingMessagesHeard;

    /**
     * Flag indicating whether the player has found and exited through the door.
     */
    private boolean foundTheDoorAndLeftTheGame;

    /**
     * Flag indicating whether the player is currently standing on a harmful (bad) tile.
     */
    private boolean currentTileBad;
    /**
     * The current game state of the player (playing, won, or dead).
     * This determines whether the game should continue, end in victory, or end in defeat.
     */
    public PlayerState state;
    /**
     * The last object found by the player during their most recent move.
     * This is used to handle object discovery interactions (apples, doors).
     */
    public FoundableObject objectFoundLastMove;

    /**
     * Constructs a new player with the specified name and health.
     *
     * @param name the player's name
     * @param health the player's starting health points
     */
    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.numberOfUnsettlingMessagesHeard = 0;
        this.facing = FacingDirection.north;
        this.coord = new Coord(0, 0);
        this.foundTheDoorAndLeftTheGame = false;
        this.state = PlayerState.playing;
    }

    /**
     * Determines whether the player is still alive.
     *
     * @return true if the player's health is greater than zero, false if they are dead
     */
    public boolean shouldLive() {
        return health > 0;
    }

    /**
     * Checks whether the player has found and activated the exit door.
     *
     * @return true if the player has found the door, false otherwise
     */
    public boolean isFoundTheDoorAndLeftTheGame() { return this.foundTheDoorAndLeftTheGame; }

    /**
     * Marks that the player has found the exit door and set state to won.
     */
    public void findDoor(){
        foundTheDoorAndLeftTheGame = true;
    }

    /**
     * Retrieves a formatted string of the player's current statistics.
     *
     * @return a formatted string containing player name, health, and unsettling messages heard
     */
    public String getPrintableStats() {
        return toString();
    }

    /**
     * Gets the player's current facing direction.
     *
     * @return the facing direction
     */
    public FacingDirection getFacing() { return facing; }

    /**
     * Sets the player's facing direction.
     *
     * @param facing the new facing direction
     */
    public void setFacing(FacingDirection facing) { this.facing = facing; }

    /**
     * Gets the player's current coordinates.
     *
     * @return the player's position
     */
    public Coord getCoord() { return coord; }

    /**
     * Sets the player's coordinates.
     *
     * @param coord the new position
     */
    public void setCoord(Coord coord) { this.coord = coord; }

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() { return name; }

    /**
     * Sets the player's name.
     *
     * @param name the new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the player's current health.
     *
     * @return the health points
     */
    public int getHealth() { return health; }

    /**
     * Sets the player's health.
     *
     * @param health the new health value
     */
    public void setHealth(int health) { this.health = health; }

    /**
     * Gets the number of unsettling messages the player has heard.
     *
     * @return the count of unsettling messages
     */
    public int getNumberOfUnsettlingMessagesHeard() { return numberOfUnsettlingMessagesHeard; }

    /**
     * Sets the number of unsettling messages the player has heard.
     *
     * @param number the new count
     */
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

    /**
     * Calculates the new desired position based on the movement direction.
     *
     * @param d the relative direction for movement
     * @return the new coordinates
     */
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
            case Direction.back -> {
                switch (facing) {
                    case north -> dy = 1;
                    case south -> dy = -1;
                    case east -> dx = -1;
                    case west -> dx = 1;
                }
            }
        }
        return new Coord(coord.x + dx, coord.y + dy);
    }

    /**
     * Calculates the new facing direction after movement.
     *
     * @param d the relative direction moved
     * @return the new facing direction
     */
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
            case Direction.back -> {
                switch (facing) {
                    case north -> newFacing = FacingDirection.south;
                    case south -> newFacing = FacingDirection.north;
                    case east -> newFacing = FacingDirection.west;
                    case west -> newFacing = FacingDirection.east;
                }
            }
        }
        return newFacing;
    }

    /**
     * Inspects the tile for top objects and collects any found objects.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param map the game map
     */
    private void inspectTileForTopObjects(int x,int y, Map map) {
        objectFoundLastMove = map.getFoundableObject(y, x);
        if (objectFoundLastMove != null)
            objectFoundLastMove.find(this);
    }

    /**
     * Attempts to move the player in the specified direction.
     * This method calculates the new position based on the player's current facing direction
     * and the relative direction requested. It checks if the destination is passable,
     * inspects for objects to find, updates position and facing if successful.
     *
     * @param d the relative direction for movement
     * @param map the game map for path validation and object discovery
     * @return true if movement was successful, false if path was blocked
     */
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

    /**
     * Checks whether the player found an object on their last move.
     *
     * @return true if an object was discovered during the last move, false otherwise
     */
    public boolean foundNewObject(){
        return objectFoundLastMove != null;
    }

    /**
     * Clears the last found object after the player has interacted with it.
     */
    public void useFoundObject() {
        objectFoundLastMove = null;
    }

    /**
     * Determines whether the player should see an unsettling message.
     * Unsettling messages are triggered when the player is on a bad tile or
     * when their health drops below a critical threshold.
     *
     * @return true if an unsettling message should be displayed, false otherwise
     */
    public boolean shouldBeWorried() {
        return (currentTileBad || health < 15);
    }

    /**
     * Updates the player's state based on the properties of their current tile.
     * This method retrieves the health impact from the current tile, updates the player's
     * health accordingly, and checks if the player has died as a result.
     *
     * @param map the game map to query for tile properties
     */
    public void updateByCurrentTile(Map map) {
        int healthImpact = map.getPlayerHealthTileEffect(coord.y, coord.x);
        currentTileBad = healthImpact < 0;
        updateHealth(healthImpact);
        if (health <= 0)
            state = PlayerState.dead;
    }

    /**
     * Modifies the player's health by the specified amount.
     * A positive value heals the player, while a negative value damages them.
     *
     * @param update the health change amount (positive for healing, negative for damage)
     */
    public void updateHealth(int update) {
        health += update;
    }
}