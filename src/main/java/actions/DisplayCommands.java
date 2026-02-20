package actions;

import commands.Command;
import enums.CommandType;
import ui.NarratorsMouth;

import java.util.EnumSet;

public class DisplayCommands extends Action {

    private final Command command;

    public DisplayCommands(Command command) {
        this.supportedCommands = EnumSet.of(CommandType.commands);
        this.command = command;
    }

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
