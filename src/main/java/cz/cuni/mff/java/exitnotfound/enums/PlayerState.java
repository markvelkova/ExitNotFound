package cz.cuni.mff.java.exitnotfound.enums;

/**
 * Enumeration of possible states a player can be in during gameplay.
 * These states determine the outcome of the game and control game flow progression.
 */
public enum PlayerState {
    /** Player is actively playing and game continues */
    playing,
    /** Player successfully found and reached the exit door */
    won,
    /** Player's health reached zero */
    dead
}