package ui;

import commands.CommandScholar;
import enums.CommandType;
import enums.Direction;
import enums.MessageKey;
import maputils.interfaces.FoundableObject;
import toolkit.Player;
import ui.MessageRepository;

import java.io.PrintWriter;

/**
 * Utility class for sending text output and game information to the player.
 * This class handles all console output to the player, including game messages,
 * command help, map display, and statistics. It retrieves message templates from
 * MessageRepository and writes them to the game's output stream. */
public class NarratorsMouth {

    public static PrintWriter speaker;
    /**
     * Prints and flushes raw text to the output.
     * @param text the text to display     */
    private static void printAndFlush(String text) {
        speaker.print(text);
        speaker.flush();
    }

    /**
     * Writes raw text directly to the player without message lookup.
     * @param text the text string to display to the player     */
    public static void write(String text) {
        printAndFlush(text);
    }
    /**
     * Displays an error message to the player.     */
    public static void errorMessage() {
        printAndFlush(MessageRepository.get(MessageKey.ERROR));
    }

    /**
     *  Prompts the player to enter their name.     */
    public static void askForName() {
        printAndFlush(MessageRepository.get(MessageKey.ASK_NAME));
    }

    /**
     * Announces to the player that they moved in a specific direction.
     * @param dir the direction the player moved     */
    public static void moved(Direction dir) {
        switch(dir) {
            case straight -> movedStraight();
            case left -> movedLeft();
            case right -> movedRight();
            case back -> movedBack();
        }
    }

    private static void movedLeft() {
        printAndFlush(MessageRepository.get(MessageKey.MOVED_LEFT));
    }

    private static void movedRight() {
        printAndFlush(MessageRepository.get(MessageKey.MOVED_RIGHT));
    }

    private static void movedStraight() {
        printAndFlush(MessageRepository.get(MessageKey.MOVED_STRAIGHT));
    }

    private static void movedBack() {
        printAndFlush(MessageRepository.get(MessageKey.MOVED_BACK));
    }
    /**
     * Displays the welcome message.     */
    public static void welcome() {
        printAndFlush(MessageRepository.get(MessageKey.WELCOME));
    }

    /**
     * Asks the player if they want to see the tutorial.
     * @param p the player to address by name     */
    public static void askAboutTutorial(Player p) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.ASK_TUTORIAL,
                        p.getName()
                )
        );
    }

    /**    Shows the tutorial introduction.     */
    public static void giveTutorial() {
        giveTutorialIntro();
        displayListOfCommands();
    }

    /**     Shows the tutorial introduction text.     */
    public static void giveTutorialIntro() {
        printAndFlush(MessageRepository.get(MessageKey.TUTORIAL_INTRO));
    }

    /**
     * Displays the complete list of available commands with descriptions.
     * This shows all user-accessible commands with their parameters and effects,
     * typically shown during the tutorial or when the player requests help.     */
    public static void displayListOfCommands() {

        printAndFlush(MessageRepository.get(MessageKey.LIST_COMMANDS_HEADER));

        for (CommandType type : CommandType.values()) {
            if (CommandScholar.userCanUseThisCommand(type)) {
                printAndFlush(CommandScholar.getCommandInfo(type));
            }
        }
        printAndFlush(MessageRepository.get(MessageKey.LIST_COMMANDS_FOOTER));
    }

    /**
     * Displays detailed information about a specific command.
     * @param type the command type to display information for     */
    public static void displayCommand(CommandType type) {
        speaker.println("-----------------");
        speaker.println("YOU HAVE ASKED FOR THIS!!!");
        printAndFlush(CommandScholar.getCommandInfo(type));
        speaker.println("enjoy:)\n");
    }
    /**     Asks for confirmation to end the game.     */
    public static void askForEndConfirmation() {
        printAndFlush(MessageRepository.get(MessageKey.END_CONFIRMATION));
    }

    /**     Asks if the player wants to play another game.     */
    public static void askForAnotherGame() {
        printAndFlush(MessageRepository.get(MessageKey.ANOTHER_GAME));
    }

    /**
     * Announces that the player found an object.
     * @param o the found object     */
    public static void announceFindingObject(FoundableObject o) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.FOUND_OBJECT,
                        o.getEndingOfFindingMessage()
                )
        );
    }
    /**
     * Announces that the player has won the game.
     * @param p the player who won     */
    public static void announceWin(Player p) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.WIN,
                        p.getName()
                )
        );
    }
    /**
     * Announces that the player has died and lost the game.
     * @param p the player who died     */
    public static void announceFailure(Player p) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.FAILURE,
                        p.getName()
                )
        );
    }
    /**
     * Displays the player's statistics.
     * @param statsString the formatted statistics string     */
    public static void printStats(String statsString) {
        printAndFlush(statsString);
    }

    /**     Announces that the path is blocked.     */
    public static void pathBlocked() {
        printAndFlush(MessageRepository.get(MessageKey.PATH_BLOCKED));
    }

    /**     Announces that saving failed.     */
    public static void announceFailedSaving() {
        printAndFlush(MessageRepository.get(MessageKey.FAILED_SAVING));
    }

    /**     Announces that saving was successful.     */
    public static void announceSuccessfulSaving() {
        printAndFlush(MessageRepository.get(MessageKey.SUCCESSFUL_SAVING));
    }

    /**     Announces that the player is back in the game.     */
    public static void backInTheGame() {
        printAndFlush(MessageRepository.get(MessageKey.BACK_IN_GAME));
    }

    /**     Asks about loading an old game.     */
    public static void askAboutLoadingOldGame() {
        printAndFlush(MessageRepository.get(MessageKey.LOADING_NEW_GAME));
    }

    /**     Announces that loading was successful.     */
    public static void announceSuccessfulLoading() {
        printAndFlush(MessageRepository.get(MessageKey.SUCCESSFUL_LOADING));
    }
    /**     Announces that loading failed.     */
    public static void announceFailedLoading() {
        printAndFlush(MessageRepository.get(MessageKey.FAILED_LOADING));
    }

    /**     Shows the saving instruction.     */
    public static void saySavingInstruction() {
        printAndFlush(MessageRepository.get(MessageKey.SAVE_INSTRUCTION));
    }

    /**
     * Displays the current game map.
     * @param mapString the formatted map string to display     */
    public static void printMap(String mapString) {
        printAndFlush(mapString);
    }
    /**
     * Displays an unsettling or atmospheric message to increase tension.
     * This randomly selects a message from UnsettlingMessenger and displays it,
     * also incrementing the player's unsettling message counter.
     * @param p the player to update message count for     */
    public static void printUnsettlingMessage(Player p) {

        printAndFlush(
                MessageRepository.get(
                        MessageKey.UNSETTLING_MESSAGE,
                        UnsettlingMessenger.getUnsettlingMesssage()
                )
        );

        p.setNumberOfUnsettlingMessagesHeard(
                p.getNumberOfUnsettlingMessagesHeard() + 1
        );
    }
}