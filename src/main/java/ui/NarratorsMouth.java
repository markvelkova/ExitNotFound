package ui;

import commands.CommandScholar;
import enums.CommandType;
import enums.Direction;
import enums.MessageKey;
import maputils.interfaces.FoundableObject;
import toolkit.Player;
import ui.MessageRepository;

import java.io.PrintWriter;

public class NarratorsMouth {

    public static PrintWriter speaker;

    private static void printAndFlush(String text) {
        speaker.print(text);
        speaker.flush();
    }

    public static void write(String text) {
        printAndFlush(text);
    }

    public static void errorMessage() {
        printAndFlush(MessageRepository.get(MessageKey.ERROR));
    }

    public static void askForName() {
        printAndFlush(MessageRepository.get(MessageKey.ASK_NAME));
    }

    public static void moved(Direction dir) {
        switch(dir) {
            case straight -> movedStraight();
            case left -> movedLeft();
            case right -> movedRight();
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

    public static void welcome() {
        printAndFlush(MessageRepository.get(MessageKey.WELCOME));
    }

    public static void askAboutTutorial(Player p) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.ASK_TUTORIAL,
                        p.getName()
                )
        );
    }

    public static void giveTutorial() {
        giveTutorialIntro();
        displayListOfCommands();
    }

    public static void giveTutorialIntro() {
        printAndFlush(MessageRepository.get(MessageKey.TUTORIAL_INTRO));
    }

    public static void displayListOfCommands() {

        printAndFlush(MessageRepository.get(MessageKey.LIST_COMMANDS_HEADER));

        for (CommandType type : CommandType.values()) {
            if (CommandScholar.userCanUseThisCommand(type)) {
                printAndFlush(CommandScholar.getCommandInfo(type));
            }
        }
        printAndFlush(MessageRepository.get(MessageKey.LIST_COMMANDS_FOOTER));
    }

    public static void displayCommand(CommandType type) {
        speaker.println("-----------------");
        speaker.println("YOU HAVE ASKED FOR THIS!!!");
        printAndFlush(CommandScholar.getCommandInfo(type));
        speaker.println("enjoy:)\n");
    }

    public static void askForEndConfirmation() {
        printAndFlush(MessageRepository.get(MessageKey.END_CONFIRMATION));
    }

    public static void askForAnotherGame() {
        printAndFlush(MessageRepository.get(MessageKey.ANOTHER_GAME));
    }

    public static void announceFindingObject(FoundableObject o) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.FOUND_OBJECT,
                        o.toString()
                )
        );
    }

    public static void announceWin(Player p) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.WIN,
                        p.getName()
                )
        );
    }

    public static void announceFailure(Player p) {
        printAndFlush(
                MessageRepository.get(
                        MessageKey.FAILURE,
                        p.getName()
                )
        );
    }

    public static void printStats(String statsString) {
        printAndFlush(statsString);
    }

    public static void pathBlocked() {
        printAndFlush(MessageRepository.get(MessageKey.PATH_BLOCKED));
    }

    public static void announceFailedSaving() {
        printAndFlush(MessageRepository.get(MessageKey.FAILED_SAVING));
    }

    public static void announceSuccessfulSaving() {
        printAndFlush(MessageRepository.get(MessageKey.SUCCESSFUL_SAVING));
    }

    public static void backInTheGame() {
        printAndFlush(MessageRepository.get(MessageKey.BACK_IN_GAME));
    }

    public static void askAboutLoadingOldGame() {
        printAndFlush(MessageRepository.get(MessageKey.LOADING_NEW_GAME));
    }

    public static void announceSuccessfulLoading() {
        printAndFlush(MessageRepository.get(MessageKey.SUCCESSFUL_LOADING));
    }

    public static void announceFailedLoading() {
        printAndFlush(MessageRepository.get(MessageKey.FAILED_LOADING));
    }

    public static void saySavingInstruction() {
        printAndFlush(MessageRepository.get(MessageKey.SAVE_INSTRUCTION));
    }

    public static void printMap(String mapString) {
        printAndFlush(mapString);
    }

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