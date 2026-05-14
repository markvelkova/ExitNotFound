package maputils;

import maputils.factories.BadTileFactory;
import maputils.factories.GoodTileFactory;
import maputils.interfaces.*;
import maputils.tiles.Wall;
import maputils.topobjects.Apple;
import maputils.topobjects.Door;
import toolkit.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * Represents the game world map with tiles, obstacles, and interactive objects.
 * This class manages a 2D grid of map tiles, with discovery tracking, interactive
 * top-level objects (items, doors), and player discovery mechanics. The map supports
 * serialization for save/load functionality and provides various query methods for
 * game logic and display. */
public class Map implements Serializable {
    private MapConfig config;
    private GoodTileFactory goodFact;
    private BadTileFactory badFact;
    private MapTile[][] mapWithoutTopObjects;
    private TopObject[][] mapOfTopObjects;
    public final int height;
    public final int width;
    /**
     * Constructs a new map with the specified dimensions and configuration.
     * @param width the width of the map in tiles
     * @param height the height of the map in tiles
     * @param config the configuration for tile generation     */
    public Map(int width, int height, MapConfig config) {
        this.width = width;
        this.height = height;

        mapWithoutTopObjects = new MapTile[height][width];
        mapOfTopObjects = new TopObject[height][width];

        this.config = config;
        goodFact = new GoodTileFactory(config.goodTileMaxProb);
        badFact = new BadTileFactory(config.badTileMaxImpact);
    }
    /**
     *  Reads map data from a file and initializes the map structure.
     *  This method parses a map file format where each character represents a tile or object:
     *  - G for good tile, B for bad tile, X for wall, D for door, A for apple, P for player start
     *  Discovers the starting tile and returns the player's starting position.
     *  @param reader a BufferedReader positioned at the start of map data
     *  @return the Coord position of the player's starting location
     *  @throws IOException if reading the map data fails   */
    public Coord initializeAndGetPlayer(BufferedReader reader) throws IOException {
        Coord result = new Coord(0,0);
        for (int i = 0; i < height; i++) {
            String line = reader.readLine();
            if (line == null) continue;
            for (int j = 0; j < width; j++) {
                int newChar = line.charAt(j);
                if (readTileAndSayIfPlayerFound(newChar, i, j)) {
                    result = new Coord(j, i);
                    mapWithoutTopObjects[i][j].discover();
                }
            }
        }
        return result;
    }

    private boolean readTileAndSayIfPlayerFound(int newChar, int i, int j) {
        if (GameSymbols.TILES.contains(newChar)) {
            mapWithoutTopObjects[i][j] = readMapTile(newChar);
        }
        else if (GameSymbols.TOPS.contains(newChar)) {
            mapWithoutTopObjects[i][j] = readMapTile(GameSymbols.GOOD);
            mapOfTopObjects[i][j] = readTopObject(newChar);
        } else if (newChar == GameSymbols.PLAYER) {
            mapWithoutTopObjects[i][j] = readMapTile(GameSymbols.GOOD);
            return true;
        } else
            throw new RuntimeException("MAP INVALID"); //TODO
        return false;
    }
    /**
     * Retrieves and removes a findable object at the specified coordinates.
     * If a FoundableObject (apple, door) exists at this position, it is removed from,
     * the map and returned. Returns null if there is no object or if the position is invalid.
     * @param i the y coordinate
     * @param j the x coordinate
     * @return the FoundableObject at this position, or null if none exists     */
    public FoundableObject getFoundableObject(int i, int j) {
        if (isWallOrOutside(i, j))
            return null;
        if (mapOfTopObjects[i][j] instanceof FoundableObject foundable) {
            mapOfTopObjects[i][j] = null;
            return foundable;
        }
        return null;
    }

    private MapTile readMapTile(int c) {
        if (c == GameSymbols.GOOD) return goodFact.getNewMapTile();
        else if (c == GameSymbols.BAD) return badFact.getNewMapTile();
        else return new Wall();
    }
    private TopObject readTopObject(int c) {
        TopObject result = null;
        switch (c){
            case GameSymbols.DOOR -> result = new Door();
            case GameSymbols.APPLE -> result = new Apple(config.appleBonus);
        }
        return result;
    }
    /**
     * Retrieves the health impact of the tile at specified coordinates.
     * Different tiles affect player health differently (good tiles may heal,
     * bad tiles may damage). Returns 0 for non-empty tiles like walls.
     * @param x the y coordinate
     * @param y the x coordinate
     * @return the health impact value (positive for healing, negative for damage, 0 for none)     */
    public int getPlayerHealthTileEffect(int x, int y) {
        if (mapWithoutTopObjects[x][y] instanceof EmptyMapTile tile) {
            return tile.getPlayerHealthImpact();
        }
        return 0;
    }
    /**
     * Checks whether coordinates represent a wall or an out-of-bounds area.
     * Used for collision detection and path validation during movement.
     * @param i the y coordinate
     * @param j the x coordinate
     * @return true if this is a wall or outside the map bounds, false if passable     */
    public boolean isWallOrOutside(int i, int j) {
        return i < 0 || i >= height || j < 0 || j >= width
                || mapWithoutTopObjects[i][j] instanceof Wall;
    }
    /**
     * Checks if the tile at coordinates is of an exact specific type.
     * This is a strict equality check using object Class comparison.
     * @param i the y coordinate
     * @param j the x coordinate
     * @param t the tile class to check for
     * @return true if the tile is exactly this class type, false otherwise     */
    public boolean isTileExactType(int i, int j, Class<?> t) {
        return mapWithoutTopObjects[i][j].getClass() == t;
    }
    /**
     * Checks if the tile at coordinates is assignable to a specific type.
     * This uses instanceof-like logic to check if a tile implements or extends a given class.
     * Allows for polymorphic type checking.
     * @param i the y coordinate
     * @param j the x coordinate
     * @param t the tile class/interface to check compatibility with
     * @return true if the tile is assignable to this type, false otherwise     */
    public boolean isTypeAssignable(int i, int j, Class<?> t) {
        MapTile obj = mapWithoutTopObjects[i][j];
        return obj != null && t.isAssignableFrom(obj.getClass());
    }
    /**
     * Generates a formatted string representation of the entire map for display.
     * The display shows:
     * - Player position with facing direction (^, v, <, >)
     * - Discovered tiles as periods
     * - Undiscovered tiles as percent signs
     * - Walls as X characters
     * @param p the player, used to show position and facing direction
     * @return a formatted string ready to be printed to console     */
    public String getForDisplayWholeMap(Player p) {
        StringBuilder sb = new StringBuilder();
        sb.append("%%%%%%%%%%% M A P %%%%%%%%%%%\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == p.getCoord().y && j == p.getCoord().x) {
                    switch (p.getFacing()) {
                        case south -> sb.append('V');
                        case west  -> sb.append('<');
                        case north -> sb.append('T');
                        case east  -> sb.append('>');
                    }
                } else if (!mapWithoutTopObjects[i][j].wasDiscovered()) {
                    sb.append('%');
                } else if (mapWithoutTopObjects[i][j] instanceof NonEmptyMapTile) {
                    sb.append((char) GameSymbols.WALL);
                } else {
                    sb.append(".");
                }
                sb.append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    /**
     * Marks the tile at the specified coordinates as discovered.
     * Discovered tiles are revealed on the map display. This is typically called
     * when a player attempts to move to or look at a tile location.
     * @param x the y coordinate
     * @param y the x coordinate     */
    public void discover(int x, int y) {
        if (mapWithoutTopObjects[x][y] instanceof Discoverable discoverable) {
            discoverable.discover();
        }
    }
    /**
     * Represents a coordinate position on the game map.
     * This simple class holds x and y coordinates and is immutable once created.
     * Used for player position tracking and other location-based references.     */
    public static class Coord implements Serializable{
        public final int x;
        public final int y;
        /**
         * Constructs a coordinate with the specified x and y values.
         * @param x the x coordinate
         * @param y the y coordinate         */
        public Coord(int x, int y) { this.x = x; this.y = y; }
    }
}
