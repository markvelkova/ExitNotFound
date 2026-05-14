package actions;

import enums.CommandType;
import java.util.Set;

/**
 * Abstract base class for all game actions.
 *
 * This class serves as a blueprint for implementing various player actions in the game,
 * such as movement, displaying maps, or handling invalid commands.
 * Each concrete action defines which command types it supports.
 */
public abstract class Action {
    protected Set<CommandType> supportedCommands;

    /**
     * Executes the action and returns whether it was successful.
     *
     * @return true if the action completed successfully, false otherwise
     */
    public abstract boolean execute();
}