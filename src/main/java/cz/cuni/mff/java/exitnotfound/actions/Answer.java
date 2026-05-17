package cz.cuni.mff.java.exitnotfound.actions;

import cz.cuni.mff.java.exitnotfound.commands.Command;
import cz.cuni.mff.java.exitnotfound.enums.CommandType;

import java.util.EnumSet;

/**
 * Action that validates if a player answered "ok" to a yes/no question.
 *
 * This action is used to process player confirmation responses, particularly for
 * questions such as tutorial choices, game load attempts, and end confirmations.
 * It checks whether the received command is of type CommandType.ok.
 */
public class Answer extends Action {
    private final Command command;

    /**
     * Constructs an Answer action for the given command.
     *
     * @param command the command to validate as an "ok" response
     */
    public Answer(Command command) {
        this.supportedCommands = EnumSet.of(CommandType.ok);
        this.command = command;
    }

    /**
     * Executes the action by checking if the command is "ok".
     *
     * @return true if the command is "ok", false otherwise
     */
    @Override
    public boolean execute() {
        return supportedCommands.contains(command.getType());
    }
}