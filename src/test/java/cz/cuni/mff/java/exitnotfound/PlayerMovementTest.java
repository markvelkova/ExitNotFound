package cz.cuni.mff.java.exitnotfound;

import cz.cuni.mff.java.exitnotfound.maputils.Map;
import cz.cuni.mff.java.exitnotfound.maputils.MapConfig;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;
import cz.cuni.mff.java.exitnotfound.enums.Direction;
import cz.cuni.mff.java.exitnotfound.ui.NarratorsMouth;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerMovementTest {

    @Test
    public void testPlayerCannotMoveIntoWall() throws Exception {
        //map with walls everywhere around
        MapConfig cfg = new MapConfig(1, 1, 1);
        Map map = new Map(3, 3, cfg);

        BufferedReader br = new BufferedReader(new StringReader(
                """
                   XXX
                   XPX
                   XXX
                   """
        ));
        Map.Coord start = map.initializeAndGetPlayer(br);

        Player p = new Player("hero", 100);
        p.setCoord(new Map.Coord(start.x, start.y));

        //position must remain unchanged
        boolean movedLeft = p.move(Direction.left, map);
        boolean movedRight = p.move(Direction.right, map);
        boolean movedStraight = p.move(Direction.straight, map);
        boolean movedBack = p.move(Direction.back, map);

        assertFalse(movedLeft, "Expected blocked by wall when moving left");
        assertFalse(movedRight, "Expected blocked by wall when moving right");
        assertFalse(movedStraight, "Expected blocked by wall when moving straight");
        assertFalse(movedBack, "Expected blocked by wall when moving back");
        assertEquals(start.x, p.getCoord().x);
        assertEquals(start.y, p.getCoord().y);
    }

    @Test
    public void testPlayerMovesAndFacingChanges() throws Exception {
        //player can move left and right
        MapConfig cfg = new MapConfig(100, 1, 1);
        Map map = new Map(3, 1, cfg);

        BufferedReader br = new BufferedReader(new StringReader(
                "GPG\n"
        ));
        Map.Coord start = map.initializeAndGetPlayer(br);

        Player p = new Player("hero", 100);
        p.setCoord(new Map.Coord(start.x, start.y));
        //initial facing is north, moving left should go to x-1 and set facing to west
        boolean moved = p.move(Direction.left, map);

        assertTrue(moved, "Player should be able to move left into a good tile");
        assertEquals(start.x - 1, p.getCoord().x, "X coordinate should have decreased by 1");
        assertEquals(start.y, p.getCoord().y, "Y coordinate should be unchanged");
        assertEquals(Player.FacingDirection.west, p.getFacing(), "Facing should become west after left move");
    }

    @Test
    public void testMoveActionOutputs() throws Exception {
        MapConfig cfg = new MapConfig(100, 1, 1);
        Map map = new Map(3, 1, cfg);

        BufferedReader br = new BufferedReader(new StringReader("GPG\n"));
        Map.Coord start = map.initializeAndGetPlayer(br);

        Player p = new Player("hero", 100);
        p.setCoord(new Map.Coord(start.x, start.y));

        StringWriter sw = new StringWriter();
        NarratorsMouth.speaker = new PrintWriter(sw);

        cz.cuni.mff.java.exitnotfound.actions.Move moveAction =
                new cz.cuni.mff.java.exitnotfound.actions.Move(Direction.right, map, p);

        moveAction.execute();

        String out = sw.toString();
        assertTrue(out.contains("You went right"));
    }
}