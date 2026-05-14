package maputils.topobjects;

import enums.PlayerState;
import maputils.interfaces.FoundableObject;
import maputils.interfaces.TopObject;
import toolkit.Player;

import java.io.Serializable;

/**
 * Represents the exit door, the goal object in the game.
 * When the player finds the door, they win the game. This object implements
 * the FoundableObject interface to trigger the win condition upon discovery. */
public class Door implements FoundableObject, TopObject, Serializable
{
    /**
     *  Provides the message text when the player finds the door.
     *  @return "a door" - completes the phrase "You found a door"     */
    @Override
    public String getEndingOfFindingMessage() {
        return "a door";
    }

    /**
     * Triggers when the player discovers the door, setting them to win state.
     * @param p the player who found the door     */
    @Override
    public void find(Player p)
    {
        p.state = PlayerState.won;
    }
}