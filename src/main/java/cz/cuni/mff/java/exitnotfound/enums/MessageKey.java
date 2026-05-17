package cz.cuni.mff.java.exitnotfound.enums;

/**
 * Enumeration of all message keys used for localized text display.
 *
 * Each key corresponds to a message template stored in MessageRepository,
 * allowing for centralized management of all user-facing text in the game.
 */
public enum MessageKey {
    /** Generic error message when something goes wrong */
    ERROR,
    /** Prompt to ask player for their name */
    ASK_NAME,
    /** Message confirming leftward movement */
    MOVED_LEFT,
    /** Message confirming rightward movement */
    MOVED_RIGHT,
    /** Message confirming forward movement */
    MOVED_STRAIGHT,
    /** Message confirming backward movement (with humorous tone) */
    MOVED_BACK,
    /** Welcome message at game start */
    WELCOME,
    /** Question asking if player wants to see tutorial */
    ASK_TUTORIAL,
    /** Introduction text for the tutorial */
    TUTORIAL_INTRO,
    /** Header text for displaying list of available commands */
    LIST_COMMANDS_HEADER,
    /** Footer text for displaying list of available commands */
    LIST_COMMANDS_FOOTER,
    /** Confirmation prompt to end the current game */
    END_CONFIRMATION,
    /** Question asking if player wants to play another game */
    ANOTHER_GAME,
    /** Message displayed when player finds an object */
    FOUND_OBJECT,
    /** Victory message when player wins the game */
    WIN,
    /** Failure message when player dies */
    FAILURE,
    /** Message indicating the path ahead is blocked */
    PATH_BLOCKED,
    /** Message displayed when game save fails */
    FAILED_SAVING,
    /** Message displayed when game saves successfully */
    SUCCESSFUL_SAVING,
    /** Message confirming player is back in the main game */
    BACK_IN_GAME,
    /** Prompt about loading an old game */
    LOADING_NEW_GAME,
    /** Message displayed when game loads successfully */
    SUCCESSFUL_LOADING,
    /** Message displayed when game load fails */
    FAILED_LOADING,
    /** Instructions for saving the game */
    SAVE_INSTRUCTION,
    /** Random atmospheric message to create suspense */
    UNSETTLING_MESSAGE
}