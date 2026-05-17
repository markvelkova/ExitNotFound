package cz.cuni.mff.java.exitnotfound.enums;

/**
 * Enumeration of movement directions available to the player.
 *
 * These directions are relative to the player's current facing direction
 * and represent the four possible movement options.
 */
public enum Direction {
    /** Movement to the left relative to player's facing direction */
    left,
    /** Movement straight ahead in the player's facing direction */
    straight,
    /** Movement to the right relative to player's facing direction */
    right,
    /** Movement backward opposite to player's facing direction */
    back
}