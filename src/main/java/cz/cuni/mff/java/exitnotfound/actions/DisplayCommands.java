package cz.cuni.mff.java.exitnotfound.actions;

import cz.cuni.mff.java.exitnotfound.commands.Command;
import cz.cuni.mff.java.exitnotfound.enums.CommandType;
import cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth;

import java.util.EnumSet;

/**
 * Action responsible for displaying available commands to the player.
 *
 * This action handles the "commands" command with optional parameters.
 * If no parameters are provided, it displays a list of all available commands.
 * If a command name parameter is provided, it displays detailed information about that specific command.
 */
public class DisplayCommands extends Action {

    private final Command command;

    /**
     * Constructs a DisplayCommands action for the given command.
     *
     * @param command the command to process, which may include a specific command name as parameter
     */
    public DisplayCommands(Command command) {
        this.supportedCommands = EnumSet.of(CommandType.commands);
        this.command = command;
    }

    /**
     * Executes the action by displaying commands or command details.
     *
     * @return true if the action executed successfully, false otherwise
     */
    @Override
    public boolean execute() {

        if (!supportedCommands.contains(command.getType()))
            return false;

        if (command.getParameters() == null) {
            NarratorsMouth.displayListOfCommands();
        }
        else {
            CommandType displayable =
                    CommandType.valueOf(command.getParameters()[0]);

            NarratorsMouth.displayCommand(displayable);
        }

        return true;
    }
}