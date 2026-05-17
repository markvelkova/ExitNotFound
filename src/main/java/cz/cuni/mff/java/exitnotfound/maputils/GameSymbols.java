package cz.cuni.mff.java.exitnotfound.maputils;

import java.util.Set;

/**
 * Constants defining character symbols used in map files and display.
 *
 * This class centralizes all symbol definitions for map parsing and rendering,
 * including tiles, obstacles, and interactive objects. Symbols are defined as integer
 * character codes (ASCII values).
 */
public class GameSymbols {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * All constants are static and can be accessed directly on the class.
     */
    private GameSymbols() {
    }
    /** Character symbol representing the player on the map */
    public static final int PLAYER = 'P';
    /** Character symbol representing a wall or impassable obstacle */
    public static final int WALL = 'X';
    /** Character symbol representing a beneficial (good) tile */
    public static final int GOOD = 'G';
    /** Character symbol representing a harmful (bad) tile */
    public static final int BAD = 'B';
    /** Character symbol representing the exit door */
    public static final int DOOR = 'D';
    /** Character symbol representing a collectible apple */
    public static final int APPLE = 'A';

    /** Set containing all valid map tile symbols (ground-level tiles) */
    public static final Set<Integer> TILES = Set.of(GOOD, BAD, WALL);
    /** Set containing all valid top object symbols (items and doors) */
    public static final Set<Integer> TOPS = Set.of(DOOR, APPLE);
}