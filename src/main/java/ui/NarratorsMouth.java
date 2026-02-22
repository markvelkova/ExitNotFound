package ui;

import commands.CommandScholar;
import enums.CommandType;
import maputils.interfaces.FoundableObject;
import toolkit.Player;

import java.io.PrintWriter;

public class NarratorsMouth {

    public static PrintWriter speaker;
    private static void printAndFlush(String text) {
        speaker.print(text);
        speaker.print("\n");
        speaker.flush();
    }

    public static void write(String text) {
        printAndFlush(text);
    }

    public static void errorMessage() {
        printAndFlush("Unfortunately, this went kinda wrong, try again");
    }

    public static void askForName() {
        printAndFlush("""
                -----------------
                Time to hear your name, how would you like to be called?
                Write it here and press [enter]:
                """);
    }

    public static void movedLeft() {
        printAndFlush("You went left.");
    }

    public static void movedRight() {
        printAndFlush("You went right.");
    }

    public static void movedStraight() {
        printAndFlush("You went straight.");
    }

    public static void welcome() {
        printAndFlush("""
                Hello, adventurer!
                This is the most bananas program ever!
                Do no let yourself get confused by anything weird you come across in this, let us say, game. After all, a weirdo has programmed it...
                
                """);
    }

    public static void askAboutTutorial(Player p) {
        printAndFlush("""
                -----------------
                So, """ + p.getName() + """
                ...
                You seem to be new to this game, my memory is terrible though, so who knows.
                Anyway, would you like me to recall the basics of the gameplay for you?

                If yes, just write
                ok[enter]
                if no, write anything else and enter it!
                
                (and if you typed something by mistake and now are lost, type commands[enter])
                """);
    }

    public static void giveTutorial() {
        giveTutorialIntro();
        displayListOfCommands();
    }

    public static void giveTutorialIntro() {
        printAndFlush("""
                -----------------
                Alright.

                Whole game is based on your text interaction here in the console, do not exit it without saving, your progress would be lost forever...
                
                Valid input consist of a command and optional additional parameters depending on a command.
                """);
    }

    public static void displayListOfCommands() {

        printAndFlush("""
                _____________________________________
                list of valid commands
                _____________________________________
                """);

        for (CommandType type : CommandType.values()) {
            if (CommandScholar.userCanUseThisCommand(type)) {
                printAndFlush(CommandScholar.getCommandInfo(type));
                speaker.println();
            }
        }

        printAndFlush("""
                _____________________________________
                """);
    }

    public static void displayCommand(CommandType type) {
        speaker.println("-----------------");
        speaker.println("YOU HAVE ASKED FOR THIS!!!");
        printAndFlush(CommandScholar.getCommandInfo(type));
        speaker.println("enjoy:)\n");
    }

    public static void askForEndConfirmation() {
        printAndFlush("""
                -----------------
                Do you really want to end this misery?

                I mean game, ehm...

                but do you want to end anyway?
                are you sure you saved it?
                """);
    }
    public static void askForAnotherGame() {
        printAndFlush("""
                Do you want to send another hero in this dark hell?
                """);

    }

    public static void announceFindingObject(FoundableObject o) {
        printAndFlush("You found" + o.toString() + "!\n");
    }
    public static void announceWin(Player p) {
        printAndFlush("Congratulations " + p.getName() + "! You won!\n");
    }
    public static void announceFailure(Player p) {
        printAndFlush(p.getName() + " died in the darkness... \n\n requiescat in pace");
    }

    public static void pathBlocked() {
        printAndFlush("""
                -----------------
                the path is blocked this way...
                """);
    }

    public static void announceFailedSaving() {
        printAndFlush("""
                -----------------
                saving failed

                do you want to try again?
                """);
    }

    public static void announceSuccessfulSaving() {
        printAndFlush("""
                -----------------
                saving was successful!
                """);
    }

    public static void backInTheGame() {
        printAndFlush("""
                -----------------
                you are now back in the game
                """);
    }
    public static void askAboutLoadingOldGame()
    {
        printAndFlush("""

        -----------------
        Currently you are on the way to start a completely new spectacular game. 
        If you wish so, press [enter].

        But there is a chance to load an old game from a file, how?
        1) the file must be valid file made by this game previously
        2) the extension must be .mgtg
        3) the file must be in the same directory as the .exe of the game
            maybe 4) if you are brave and have your file somewhere else, it is possible, but instead of a name of the file, you will have to use the path to it
        
        conclusion:
        for loading:
            type ok[enter]
            type the name (path) of the file including the extension and press [enter]

        for starting a new game:
            press [enter]

        """);
    }
    public static void announceSuccessfulLoading()
    {
        printAndFlush("""

        -----------------
        loading was successful!
        

        """);
    }
    public static void announceFailedLoading()
    {
        printAndFlush("""

        -----------------
        Loading attempt failed.
        Do you want to try again?
        for loading:
            type ok[enter]
            type the name (path) of the file including the extension and press [enter]
        
        for starting a new game:
            press [enter] 

        """);
    }
    public static void saySavingInstruction()
    {
        printAndFlush("""

        -----------------
        You have chosen to save your file, wise indeed

        type the path of a .mgtg file in which you want to save and press [enter]
        
        if you only press [enter] default file called coconut.mgtg will be used

        """);
    }
    public static void printUnsettlingMessage(Player p) {
        printAndFlush("""
            ¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.
            """ +
                UnsettlingMessenger.getUnsettlingMesssage() +
                """
                ¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.
                """);
        p.setNumberOfUnsettlingMessagesHeard(p.getNumberOfUnsettlingMessagesHeard()+1);
    }
}
