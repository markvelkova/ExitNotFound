package enums;

/** Enumeration of possible states a player can be in during gameplay.
 * These states determine the outcome of the game and control game flow progression:
 * - playing: player is actively playing (game continues)
 * - won: player successfully found and reached the exit (game ends)
 * - dead: player's health reached zero (game ends) */
public enum PlayerState {
    playing, won, dead
}
