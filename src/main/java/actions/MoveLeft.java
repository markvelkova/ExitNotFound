package actions;

import maputils.Map;
import toolkit.Player;
import enums.Direction;
import ui.NarratorsMouth;

public class MoveLeft extends Action {

    private final Map map;
    private final Player player;

    public MoveLeft(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    @Override
    public boolean execute() {

        if (player.move(Direction.left, map)) {
            NarratorsMouth.movedLeft();
        }
        else {
            NarratorsMouth.pathBlocked();
        }

        return true;
    }
}
