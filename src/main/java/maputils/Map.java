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

public class Map implements Serializable {
    private MapConfig config;
    private GoodTileFactory goodFact;
    private BadTileFactory badFact;
    private MapTile[][] mapWithoutTopObjects;
    private TopObject[][] mapOfTopObjects;
    public final int height;
    public final int width;

    public Map(int width, int height, MapConfig config) {
        this.width = width;
        this.height = height;

        mapWithoutTopObjects = new MapTile[height][width];
        mapOfTopObjects = new TopObject[height][width];

        this.config = config;
        goodFact = new GoodTileFactory(config.goodTileMaxProb);
        badFact = new BadTileFactory(config.badTileMaxImpact);
    }

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

    public int getPlayerHealthTileEffect(int x, int y) {
        if (mapWithoutTopObjects[x][y] instanceof EmptyMapTile tile) {
            return tile.getPlayerHealthImpact();
        }
        return 0;
    }

    public boolean isWallOrOutside(int i, int j) {
        return i < 0 || i >= height || j < 0 || j >= width
                || mapWithoutTopObjects[i][j] instanceof Wall;
    }

    public boolean isTileExactType(int i, int j, Class<?> t) {
        return mapWithoutTopObjects[i][j].getClass() == t;
    }

    public boolean isTypeAssignable(int i, int j, Class<?> t) {
        MapTile obj = mapWithoutTopObjects[i][j];
        return obj != null && t.isAssignableFrom(obj.getClass());
    }

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

    public void discover(int x, int y) {
        if (mapWithoutTopObjects[x][y] instanceof Discoverable discoverable) {
            discoverable.discover();
        }
    }

    public static class Coord implements Serializable{
        public final int x;
        public final int y;
        public Coord(int x, int y) { this.x = x; this.y = y; }
    }
}
