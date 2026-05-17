package cz.cuni.mff.java.exitnotfound.actions;

import cz.cuni.mff.java.exitnotfound.maputils.Map;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;
import cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth;

import java.io.Writer;

/**
 * Action that renders and displays the current game map to the player.
 *
 * This action shows the player's current position, orientation, discovered tiles,
 * and unexplored areas on the map. The display includes the player's facing direction
 * and shows walls, discovered tiles, and undiscovered areas using specific symbols.
 */
public class DisplayMap extends Action {

    private final Map map;
    private final Player player;
    private final Writer writer;

    /**
     * Constructs a DisplayMap action with the game map and player.
     *
     * @param map the game map to display
     * @param player the player whose position and discoveries to show
     * @param writer the output writer for displaying the map
     */
    public DisplayMap(Map map, Player player, Writer writer) {
        this.map = map;
        this.player = player;
        this.writer = writer;
    }

    /**
     * Executes the action by printing the map to the output.
     *
     * @return true if the map was displayed successfully, false if an error occurred
     */
    @Override
    public boolean execute() {
        try {
            NarratorsMouth.printMap(map.getForDisplayWholeMap(player));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}