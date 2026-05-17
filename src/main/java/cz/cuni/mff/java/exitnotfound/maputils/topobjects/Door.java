package cz.cuni.mff.java.exitnotfound.maputils.topobjects;

import cz.cuni.mff.java.exitnotfound.enums.PlayerState;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.FoundableObject;
import cz.cuni.mff.java.exitnotfound.maputils.interfaces.TopObject;
import cz.cuni.mff.java.exitnotfound.toolkit.Player;

import java.io.Serializable;

/**
 * Represents the exit door, the goal object in the game.
 * When the player finds the door, they win the game. This object implements
 * the FoundableObject interface to trigger the win condition upon discovery.
 */
public class Door implements FoundableObject, TopObject, Serializable {
    /**
     * Initializes a new Door object with default settings.
     */
    public Door() {
    }
    /**
     * Provides the message text when the player finds the door.
     *
     * @return "a door" - completes the phrase "You found a door"
     */
    @Override
    public String getEndingOfFindingMessage() {
        return "a door";
    }

    /**
     * Triggers when the player discovers the door, setting them to win state.
     *
     * @param p the player who found the door
     */
    @Override
    public void find(Player p) {
        p.state = PlayerState.won;
    }
}