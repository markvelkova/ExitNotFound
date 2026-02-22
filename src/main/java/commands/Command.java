package commands;

import enums.CommandType;
import enums.Direction;

public class Command {
    private final CommandType type;
    private final String[] parameters;

    public Command(CommandType type, String[] parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public Command(CommandType type) {
        this.type = type;
        this.parameters = null;
    }

    public CommandType getType() {
        return type;
    }

    public String[] getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        if (parameters == null) {
            return "command type: " + type + "; parameters: none";
        }
        return "command type: " + type + "; parameters: " + String.join(", ", parameters);
    }

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
