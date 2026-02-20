package actions;

import commands.Command;
import enums.CommandType;
import toolkit.Player;
import ui.NarratorsMouth;

import java.util.EnumSet;

public class DisplayStats extends Action {

    private Command command;
    private Player p;

    public DisplayStats(Command command, Player p) {
        this.supportedCommands = EnumSet.of(CommandType.stats);
        this.command = command;
        this.p = p;
    }

    @Override
    public boolean execute() {
        if (supportedCommands.contains(command.getType())) {
            NarratorsMouth.write(p.toString());
            return true;
        }
        return false;
    }
}
