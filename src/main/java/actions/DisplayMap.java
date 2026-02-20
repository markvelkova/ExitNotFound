package actions;

import maputils.Map;
import toolkit.Player;

import java.io.Writer;

public class DisplayMap extends Action {

    private final Map map;
    private final Player player;
    private final Writer writer;

    public DisplayMap(Map map, Player player, Writer writer) {
        this.map = map;
        this.player = player;
        this.writer = writer;
    }

    @Override
    public boolean execute() {
        try {
            writer.write(map.getForDisplayWholeMap(player));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
