package toolkit;
import maputils.Map;

import java.io.Serializable;

public class GameState implements Serializable {

    private Player player;
    private Map map;

    public GameState(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    public Player getPlayer() { return player; }
    public Map getMap() { return map; }
}

