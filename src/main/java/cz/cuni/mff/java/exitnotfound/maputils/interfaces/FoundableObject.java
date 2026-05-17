package cz.cuni.mff.java.exitnotfound.maputils.interfaces;

import cz.cuni.mff.java.exitnotfound.toolkit.Player;

/**
 * Interface for objects that can be found and interacted with by the player.
 * Foundable objects (items, exits) trigger special effects when the player discovers them.
 * Each object can provide a message describing the discovery and execute custom logic. */
public interface FoundableObject
{
    /**
     * Retrieves the message suffix that completes "You found..." when this object is discovered.
     * @return a string completing the phrase "You found X" (e.g., "a door" or "a little apple")     */
    String getEndingOfFindingMessage();

    /**
     * Executes the special effect when the player discovers this object.
     * Different objects have different effects: apples heal the player, doors trigger win condition.
     * @param p the player who found this object     */
    void find(Player p);
}

