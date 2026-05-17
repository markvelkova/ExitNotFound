package cz.cuni.mff.java.exitnotfound.actions;

import cz.cuni.mff.java.exitnotfound.commands.Command;
import cz.cuni.mff.java.exitnotfound.enums.CommandType;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;
import cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth;

import java.util.EnumSet;

/**
 * Action that displays the player's current statistics to the console.
 *
 * This action executes when the player uses the "stats" command and shows
 * the player's name, health points, and the number of unsettling messages heard.
 */
public class DisplayStats extends Action {

    private Command command;
    private Player p;

    /**
     * Constructs a DisplayStats action for the given command and player.
     *
     * @param command the command that triggered this action
     * @param p the player whose statistics to display
     */
    public DisplayStats(Command command, Player p) {
        this.supportedCommands = EnumSet.of(CommandType.stats);
        this.command = command;
        this.p = p;
    }

    /**
     * Executes the action by printing the player's statistics.
     *
     * @return true if the command is valid and stats were displayed, false otherwise
     */
    @Override
    public boolean execute() {
        if (supportedCommands.contains(command.getType())) {
            NarratorsMouth.printStats(p.getPrintableStats());
            return true;
        }
        return false;
    }
}