package cz.cuni.mff.java.exitnotfound.actions;

import cz.cuni.mff.java.exitnotfound.maputils.Map;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;
import cz.cuni.mff.java.exitnotfound.enums.Direction;
import cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth;

/**
 * Action that handles player movement in the game world.
 *
 * This action attempts to move the player in a specified direction (left, right, straight, or back).
 * If the movement is successful, it displays a confirmation message; if blocked,
 * it displays a message indicating the path is blocked.
 */
public class Move extends Action {

    private final Map map;
    private final Player player;
    private final Direction dir;

    /**
     * Constructs a Move action for the specified direction.
     *
     * @param dir the direction in which the player wants to move
     * @param map the game map containing tile information and obstacles
     * @param player the player attempting to move
     */
    public Move(Direction dir, Map map, Player player) {
        this.map = map;
        this.player = player;
        this.dir = dir;
    }

    /**
     * Executes the movement action and provides feedback.
     *
     * @return true always, as the action always completes (either moving or reporting blockage)
     */
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