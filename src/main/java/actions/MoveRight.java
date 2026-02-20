package actions;

import maputils.Map;
import toolkit.Player;
import enums.Direction;
import ui.NarratorsMouth;

public class MoveRight extends Action {

    private final Map map;
    private final Player player;

    public MoveRight(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    @Override
    public boolean execute() {

        if (player.move(Direction.right, map)) {
            NarratorsMouth.movedRight();
        }
        else {
            NarratorsMouth.pathBlocked();
        }

        return true;
    }
}
