package actions;

import enums.CommandType;
import java.util.Set;

public abstract class Action {
    protected Set<CommandType> supportedCommands;
    public abstract boolean execute();
}
