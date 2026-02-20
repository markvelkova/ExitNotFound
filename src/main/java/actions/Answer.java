package actions;

import commands.Command;
import enums.CommandType;

import java.util.EnumSet;

public class Answer extends Action {
    private final Command command;
    public Answer(Command command) {
        this.supportedCommands = EnumSet.of(CommandType.ok);
        this.command = command;
    }

    @Override
    public boolean execute() {
        return supportedCommands.contains(command.getType());
    }
}