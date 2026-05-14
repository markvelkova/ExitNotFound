package enums;

/**
 * Enumeration of all possible game phases in the game lifecycle.
 * Each phase represents a distinct stage in the game flow:
 * - welcome: initial greeting and game introduction
 * - loading: loading a previously saved game
 * - settingUp: game initialization
 * - nameAsking: requesting player's name
 * - tutorial: showing game instructions
 * - game: main gameplay loop
 * - save: game save process
 * - exited: player initiated game exit (requires confirmation)
 * - won: player found exit and won
 * - lost: player died and lost */
public enum GamePhase {
    welcome, loading, nameAsking, tutorial, game, save, exited, won, lost
}