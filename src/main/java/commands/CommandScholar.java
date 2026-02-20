package commands;

import enums.CommandType;

public class CommandScholar {

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
                        parameters: left/right/straight
                        effect: moves you in the desired direction
                        
                        """;

            case end:
                return """
                        end
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

    public static boolean userCanUseThisCommand(CommandType t) {
        switch (t) {
            case ok:
            case stats:
            case commands:
            case end:
            case save:
            case go:
            case map:
                return true;
            default:
                return false;
        }
    }
}

