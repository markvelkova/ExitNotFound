package maputils;

import maputils.interfaces.MapTile;
import maputils.interfaces.NonEmptyMapTile;
import maputils.interfaces.TopObject;
import toolkit.Player;
import java.io.BufferedReader;
import java.io.IOException;

public class Map {

    private MapTile[][] mapWithoutTopObjects;
    private TopObject[][] mapOfTopObjects;
    public final int height;
    public final int width;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        mapWithoutTopObjects = new MapTile[height][width];
        mapOfTopObjects = new TopObject[height][width];
    }

    public Coord initializeAndGetPlayer(BufferedReader reader) throws IOException {
        Coord result = new Coord(0,0);

        for (int i = 0; i < height; i++) {
            String line = reader.readLine();
            if (line == null) continue;

            for (int j = 0; j < width; j++) {
                int newChar = line.charAt(j);

                if (newChar == GameSymbols.PLAYER) {
                    mapWithoutTopObjects[i][j] = readMapTile(GameSymbols.GOOD);
                    result = new Coord(j, i);
                } else if (newChar == GameSymbols.DOOR) {
                    mapWithoutTopObjects[i][j] = readMapTile(GameSymbols.GOOD);
                    mapOfTopObjects[i][j] = new Door();
                } else {
                    mapWithoutTopObjects[i][j] = readMapTile(newChar);
                }
            }
        }
        return result;
    }

    private MapTile readMapTile(int c) {
        if (c == GameSymbols.GOOD) return new Good();
        else if (c == GameSymbols.BAD) return new Bad();
        else return new Wall();
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
                    sb.append(' ');
                } else if (mapWithoutTopObjects[i][j] instanceof NonEmptyMapTile) {
                    sb.append((char) GameSymbols.WALL);
                    sb.append(' ');
                } else {
                    sb.append(". ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static class Coord {
        public final int x;
        public final int y;
        public Coord(int x, int y) { this.x = x; this.y = y; }
    }
}
