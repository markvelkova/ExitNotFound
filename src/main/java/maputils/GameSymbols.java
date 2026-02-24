package maputils;

import java.util.Set;

public class GameSymbols {
    public static final int PLAYER = 'P';
    public static final int WALL = 'X';
    public static final int GOOD = 'G';
    public static final int BAD = 'B';
    public static final int DOOR = 'D';
    public static final int APPLE = 'A';

    public static final Set<Integer> TILES = Set.of(GOOD, BAD, WALL);
    public static final Set<Integer> TOPS = Set.of(DOOR, APPLE);
}
