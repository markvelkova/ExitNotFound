package actions;

import maputils.Map;
import toolkit.Player;
import enums.Direction;
import ui.NarratorsMouth;

public class Move extends Action {

    private final Map map;
    private final Player player;
    private final Direction dir;

    public Move(Direction dir, Map map, Player player) {
        this.map = map;
        this.player = player;
        this.dir = dir;
    }

    @Override
    public boolean execute() {
        if (player.move(dir, map)) {
            NarratorsMouth.moved(dir);
        }
        else {
            NarratorsMouth.pathBlocked();
        }
        return true;
    }
}
