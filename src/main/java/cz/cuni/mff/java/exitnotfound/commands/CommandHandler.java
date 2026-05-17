package cz.cuni.mff.java.exitnotfound.commands;

import cz.cuni.mff.java.exitnotfound.actions.*;
import cz.cuni.mff.java.exitnotfound.enums.Direction;
import cz.cuni.mff.java.exitnotfound.maputils.Map;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;
import cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth;

/**
 * Handles the creation and execution of game actions based on player commands.
 *
 * This class acts as a factory and executor for actions. It takes a Command object,
 * determines what action it represents, creates the appropriate Action instance,
 * and can execute it. It maintains references to the player and game map for context.
 */
public class CommandHandler {

    private Player player;
    private Map map;

    /**
     * Constructs a CommandHandler with the player and map context.
     *
     * @param p the player object
     * @param m the game map
     */
    public CommandHandler(Player p, Map m) {
        this.player = p;
        this.map = m;
    }

    /**
     * Executes the provided action and returns whether execution was successful.
     *
     * @param act the action to execute
     * @return true if the action completed successfully, false otherwise
     */
    public boolean executeAction(Action act) {
        return act.execute();
    }

    /**
     * Creates an appropriate Action instance for the given Command.
     *
     * This method examines the command type and constructs the corresponding action:
     * - map: creates DisplayMap action
     * - stats: creates DisplayStats action
     * - commands: creates DisplayCommands action
     * - go: creates Move action (with direction parsing)
     * - invalid/unknown: creates InvalidAction
     *
     * @param command the command to convert into an action
     * @return a new Action instance appropriate for this command
     */
    public Action createAction(Command command) {

        switch (command.getType()) {

            case map:
                return new DisplayMap(
                        map,
                        player,
                        NarratorsMouth.speaker
                );

            case stats:
                return new DisplayStats(command, player);

            case commands:
                return new DisplayCommands(command);

            case go:

                Direction dir;

                try {
                    dir = Direction.valueOf(
                            command.getParameters()[0]);
                }
                catch (Exception e) {
                    return new InvalidAction(command);
                }
                return new Move(dir,map,player);

            default:
                return new InvalidAction(command);
        }
    }
}