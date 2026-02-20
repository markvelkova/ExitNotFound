package actions;

import commands.Command;
import enums.CommandType;
import ui.NarratorsMouth;

import java.util.EnumSet;

public class InvalidAction extends Action {

    private Command command;

    public InvalidAction(Command command) {
        this.supportedCommands =
                EnumSet.of(CommandType.invalid, CommandType.empty);
        this.command = command;
    }

    @Override
    public boolean execute() {
        if (supportedCommands.contains(command.getType())) {
            NarratorsMouth.errorMessage();
            return true;
        }
        return false;
    }
}
