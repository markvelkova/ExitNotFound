package commands;

import enums.CommandType;
import enums.Direction;

/**
 * Represents a single player command with its type and optional parameters.
 *
 * A command consists of a CommandType and zero or more parameters.
 * This class is responsible for storing and providing access to command information,
 * validating command syntax, and checking if commands are appropriate for current game context.
 */
public class Command {
    private final CommandType type;
    private final String[] parameters;

    /**
     * Constructs a command with the specified type and parameters.
     *
     * @param type the type of the command
     * @param parameters the parameters for the command, or null if none
     */
    public Command(CommandType type, String[] parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    /**
     * Constructs a command with the specified type and no parameters.
     *
     * @param type the type of the command
     */
    public Command(CommandType type) {
        this.type = type;
        this.parameters = null;
    }

    /**
     * Retrieves the type of this command.
     *
     * @return the CommandType of this command
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Retrieves the parameters associated with this command.
     *
     * @return an array of String parameters, or null if the command has no parameters
     */
    public String[] getParameters() {
        return parameters;
    }

    /**
     * Validates whether this command has correct syntax and parameters.
     *
     * Each command type has specific validation rules:
     * - ok, stats, exit, save, map: must have no parameters
     * - commands: may have zero or one parameter (specific command name to describe)
     * - go: must have exactly one parameter (left, right, straight, or back)
     *
     * @return true if the command is properly formatted and valid, false otherwise
     */
    public boolean isValid() {
        switch (type) {

            case ok:
            case stats:
            case exit:
            case save:
            case map:
                return parameters == null;

            case commands:
                if (parameters == null) return true;
                if (parameters.length == 1) {
                    try {
                        CommandType t = CommandType.valueOf(parameters[0]);
                        return CommandScholar.userCanUseThisCommand(t);
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                }
                return false;

            case go:
                if (parameters == null) return false;
                if (parameters.length == 1) {
                    try {
                        Direction dir = Direction.valueOf(parameters[0]);
                        return CommandScholar.userCanUseThisCommand(type);
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                }
                return false;

            default:
                return false;
        }
    }
}