package cz.cuni.mff.java.exitnotfound.enums;

/**
 * Enumeration of all command types available in the game.
 *
 * Each value represents a unique action the player can request:
 * - ok: confirms a yes/no question
 * - stats: displays player statistics
 * - commands: shows available commands
 * - go: moves the player in a direction
 * - save: initiates game save process
 * - exit: requests to end the current game
 * - map: displays the game map
 * - empty/invalid: used for error handling
 */
public enum CommandType {
    /** Confirms a yes/no question or positive response */
    ok,
    /** Displays the player's current game statistics */
    stats,
    /** Shows a list of available commands with descriptions */
    commands,
    /** Moves the player in a specified direction */
    go,
    /** Initiates the game save process */
    save,
    /** Requests to exit and end the current game */
    exit,
    /** Used for empty or no-command input */
    empty,
    /** Used for unrecognized or invalid commands */
    invalid,
    /** Displays the current game map */
    map
}