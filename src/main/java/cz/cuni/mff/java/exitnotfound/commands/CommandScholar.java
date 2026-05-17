package cz.cuni.mff.java.exitnotfound.commands;

import cz.cuni.mff.java.exitnotfound.enums.CommandType;

/**
 * Provides information about available game commands and their usage.
 *
 * This utility class contains static methods to retrieve detailed descriptions
 * of each command type, including their parameters and effects. It also determines
 * which commands players are allowed to use.
 */
public class CommandScholar {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * All methods are static and can be called directly on the class.
     */
    private CommandScholar() {
    }

    /**
     * Retrieves detailed documentation and usage information for a specific command.
     *
     * This method returns a formatted string containing the command name, parameters,
     * and effect description that helps the player understand how to use the command.
     *
     * @param type the command type to get information for
     * @return a formatted string describing the command, its parameters, and effects
     */
    public static String getCommandInfo(CommandType type) {
        switch (type) {
            case ok:
                return """
                        ok
                        parameters: none
                        effect: means yes, should be typed any time you want to answer yes
                        
                        """;

            case stats:
                return """
                        stats
                        parameters: none
                        effect: displays your current game stats, can be called anytime, does not affect the game
                        
                        """;

            case commands:
                return """
                        commands
                        parameters: none or name of a specific command
                        effect: if no parameter is given, than this list is exactly, what you will see; if a specific command name is given, then you will only see information about the given command
                        
                        """;

            case go:
                return """
                        go
                        parameters: left/right/straight/back
                        effect: moves you in the desired direction
                        
                        """;

            case exit:
                return """
                        exit
                        parameters: none
                        effect: by using this command, you are suggesting ending the game, do not worry, you will be asked if you really want it anyway
                        
                        """;

            case save:
                return """
                        save
                        parameters: none
                        effect: displays the saving options
                        
                        """;

            case map:
                return """
                        map
                        parameters: none
                        effect: displays map (or currently known part of it)
                        
                        """;

            default:
                return "unsupported command\n";
        }
    }

    /**
     * Determines whether a command is available for player use.
     *
     * Not all command types are available to players; some are reserved for internal use.
     * This method validates whether a given command type can be used in normal gameplay.
     *
     * @param t the command type to check
     * @return true if the player can use this command, false if it's restricted
     */
    public static boolean userCanUseThisCommand(CommandType t) {
        switch (t) {
            case ok:
            case stats:
            case commands:
            case exit:
            case save:
            case go:
            case map:
                return true;
            default:
                return false;
        }
    }
}