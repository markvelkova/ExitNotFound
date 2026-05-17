package cz.cuni.mff.java.exitnotfound.enums;

/**
 * Enumeration of all possible game phases in the game lifecycle.
 * Each phase represents a distinct stage in the game flow.
 */
public enum GamePhase {
    /** Initial greeting and game introduction phase */
    welcome,
    /** Loading a previously saved game */
    loading,
    /** Requesting player's name during setup */
    nameAsking,
    /** Showing game instructions and tutorial */
    tutorial,
    /** Main gameplay loop phase */
    game,
    /** Game save process phase */
    save,
    /** Player initiated exit (requires confirmation) */
    exited,
    /** Player found exit and won the game */
    won,
    /** Player died and lost the game */
    lost
}