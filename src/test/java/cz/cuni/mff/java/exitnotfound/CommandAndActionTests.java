package cz.cuni.mff.java.exitnotfound;

import cz.cuni.mff.java.exitnotfound.commands.Command;
import cz.cuni.mff.java.exitnotfound.commands.CommandHandler;
import cz.cuni.mff.java.exitnotfound.commands.CommandScholar;
import cz.cuni.mff.java.exitnotfound.actions.*;
import cz.cuni.mff.java.exitnotfound.enums.CommandType;
import cz.cuni.mff.java.exitnotfound.enums.Direction;
import cz.cuni.mff.java.exitnotfound.maputils.Map;
import cz.cuni.mff.java.exitnotfound.maputils.MapConfig;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class CommandAndActionTests {

    @Test
    public void testCommandValidation() {
        //valid go command
        Command goLeft = new Command(CommandType.go, new String[]{"left"});
        assertTrue(goLeft.isValid());

        //invalid go (wrong parameter)
        Command goWrong = new Command(CommandType.go, new String[]{"blabla"});
        assertFalse(goWrong.isValid());

        //commands with no param
        Command commandsNoParam = new Command(CommandType.commands);
        assertTrue(commandsNoParam.isValid());

        //commands with valid param
        Command commandsMap = new Command(CommandType.commands, new String[]{"map"});
        assertTrue(commandsMap.isValid());

        //commands with invalid param
        Command commandsBad = new Command(CommandType.commands, new String[]{"grrrrrr"});
        assertFalse(commandsBad.isValid());
    }

    @Test
    public void testCommandHandlerCreatesCorrectActions() throws Exception {

        // following initializations are beacuse of the commmandhandler
        MapConfig cfg = new MapConfig(100, 1, 1);
        Map map = new Map(1, 1, cfg);

        BufferedReader br = new BufferedReader(new StringReader("P\n"));
        Map.Coord start = map.initializeAndGetPlayer(br);

        Player player = new Player("hero", 100);
        player.setCoord(new Map.Coord(start.x, start.y));

        CommandHandler handler = new CommandHandler(player, map);

        Action aMap = handler.createAction(new Command(CommandType.map));
        assertInstanceOf(DisplayMap.class, aMap, "map command should create DisplayMap");

        Action aStats = handler.createAction(new Command(CommandType.stats));
        assertInstanceOf(DisplayStats.class, aStats, "stats command should create DisplayStats");

        Action aCommands = handler.createAction(new Command(CommandType.commands));
        assertInstanceOf(DisplayCommands.class, aCommands, "commands command should create DisplayCommands");

        Action aGo = handler.createAction(new Command(CommandType.go, new String[]{"left"}));
        assertInstanceOf(Move.class, aGo, "go command should create Move");

        Action aInvalid = handler.createAction(new Command(CommandType.invalid));
        assertInstanceOf(InvalidAction.class, aInvalid, "invalid should create InvalidAction");
    }

    @Test
    public void testDisplayMapWritesOutput() throws Exception {
        MapConfig cfg = new MapConfig(100, 1, 1);
        Map map = new Map(3, 2, cfg);

        BufferedReader br = new BufferedReader(new StringReader(
                """
                  GPG
                  BBB
                  """
        ));
        Map.Coord start = map.initializeAndGetPlayer(br);

        Player p = new Player("hero", 100);
        p.setCoord(new Map.Coord(start.x, start.y));

        StringWriter sw = new StringWriter();
        cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth.speaker = new PrintWriter(sw);

        DisplayMap dm = new DisplayMap(map, p, cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth.speaker);
        boolean ok = dm.execute();
        assertTrue(ok, "DisplayMap.execute should return true");
        String out = sw.toString();

        String expectedMap =
                "%%%%%%%%%%% M A P %%%%%%%%%%%\n" +
                "% T % \n" +
                "% % % \n";
        assertEquals(expectedMap, out);
    }
    @Test
    public void testDisplayMapWritesOutputDiscover() throws Exception {
        MapConfig cfg = new MapConfig(100, 1, 1);
        Map map = new Map(3, 2, cfg);

        BufferedReader br = new BufferedReader(new StringReader(
                """
                  GGG
                  BPB
                  """
        ));
        Map.Coord start = map.initializeAndGetPlayer(br);

        Player p = new Player("hero", 100);
        p.setCoord(new Map.Coord(start.x, start.y));

        StringWriter sw = new StringWriter();
        cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth.speaker = new PrintWriter(sw);
        p.move(Direction.straight, map); // move north into the tile above, the initial should be seen as discovered

        DisplayMap dm = new DisplayMap(map, p, cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth.speaker);
        boolean ok = dm.execute();
        assertTrue(ok, "DisplayMap.execute should return true");
        String out = sw.toString();

        String expectedMap =
                "%%%%%%%%%%% M A P %%%%%%%%%%%\n" +
                        "% T % \n" +
                        "% . % \n";
        assertEquals(expectedMap, out);
    }
}