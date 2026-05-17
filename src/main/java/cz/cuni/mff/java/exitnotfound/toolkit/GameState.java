package cz.cuni.mff.java.exitnotfound.toolkit;
import cz.cuni.mff.java.exitnotfound.maputils.Map;

import java.io.Serializable;

/** Represents a snapshot of the complete game state for saving/loading.
 * This class encapsulates the player and map information needed to completely restore
 * a game to its exact state when loaded. It implements Serializable to support
 * persisting game data to disk. */
public class GameState implements Serializable {
    /**
     * The player object associated with this game state.
     */
    private Player player;

    /**
     * The map object associated with this game state.
     */
    private Map map;

    /**
     * Constructs a GameState with the player and map.
     * @param player the player object to save
     * @param map the map object to save     */
    public GameState(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    /**
     * Retrieves the player object from this saved game state.
     * @return the Player instance associated with this game state     */
    public Player getPlayer() { return player; }
    /**
     * Retrieves the game map from this saved game state.
     * @return the Map instance associated with this game state     */
    public Map getMap() { return map; }
}

