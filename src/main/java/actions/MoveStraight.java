package actions;

import maputils.Map;
import toolkit.Player;
import enums.Direction;
import ui.NarratorsMouth;

public class MoveStraight extends Action {

    private final Map map;
    private final Player player;

    public MoveStraight(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    @Override
    public boolean execute() {

        if (player.move(Direction.straight, map)) {
            NarratorsMouth.movedStraight();
        }
        else {
            NarratorsMouth.pathBlocked();
        }

        return true;
    }
}
