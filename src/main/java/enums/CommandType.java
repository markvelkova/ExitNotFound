package enums;

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
    ok, stats, commands, go, save, exit, empty, invalid, map
}