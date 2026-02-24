package commands;

import actions.*;
import enums.Direction;
import maputils.Map;
import toolkit.Player;
import ui.NarratorsMouth;

public class CommandHandler {

    private Player player;
    private Map map;

    public CommandHandler(Player p, Map m) {
        this.player = p;
        this.map = m;
    }

    public boolean executeAction(Action act) {
        return act.execute();
    }

    public Action createAction(Command command) {

        switch (command.getType()) {

            case map:
                return new DisplayMap(
                        map,
                        player,
                        NarratorsMouth.speaker
                );

            case stats:
                return new DisplayStats(command, player);

            case commands:
                return new DisplayCommands(command);

            case go:

                Direction dir;

                try {
                    dir = Direction.valueOf(
                            command.getParameters()[0]);
                }
                catch (Exception e) {
                    return new InvalidAction(command);
                }
                return new Move(dir,map,player);

            default:
                return new InvalidAction(command);
        }
    }
}
