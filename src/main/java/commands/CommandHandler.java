package commands;

import actions.*;
import enums.CommandType;
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

    public boolean handleAction(Action act) {

        // C#:
        // act.GetType() == typeof(Answer)

        // Java:
        if (act instanceof Answer q) {
            return handleQuestion(q);
        }

        return act.execute();
    }

    private boolean handleQuestion(Answer q) {
        return q.execute();
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

                switch (dir) {
                    case left:
                        return new MoveLeft(map, player);
                    case right:
                        return new MoveRight(map, player);
                    default:
                        return new MoveStraight(map, player);
                }

            default:
                return new InvalidAction(command);
        }
    }
}
