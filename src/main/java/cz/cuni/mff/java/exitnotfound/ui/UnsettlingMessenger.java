package cz.cuni.mff.java.exitnotfound.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Provides random atmospheric messages to create suspense during gameplay.
 * This utility class maintains a collection of unsettling or atmospheric messages
 * that are displayed to the player at strategic moments (such as when on dangerous
 * tiles or with low health) to create an immersive game atmosphere.
 */
public class UnsettlingMessenger {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * All methods are static and can be called directly on the class.
     */
    private UnsettlingMessenger() {
    }
    /**
     * Generates and returns a list of unsettling messages.
     *
     * @return an ArrayList containing all available unsettling messages
     */
    private static ArrayList<String> generateListOfUnsettlingMessages() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(
                """
                Ugh, was that a spider?
                """,
                """
                My sock is kinda slimy, have I stepped on a slug or what?
                """,
                """ 
                sshhhhhuuuuum
         
                What was that?
                """,
                """
                Brr, it's getting colder and colder
                """,
                """
                Am I ever gonna get out?
                """,
                """
                Who's there? I heard you!
                """,
                """
                ...
                """,
                """
                *creepy sounds coming from behind*
                """,
                """
                wrong decision, buddy.... very wrong
                """,
                """
                pssst, do you hear it?
                """,
                """
                uh oh...
                """,
                """
                AAAAAAARGH! Let me go!
                """,
                """
                YOU WILL DIE, muhahahahaha
                """,
                """
                ha ha ha  ha
                """,
                """
                AAAAAAARGH! Let me go!
                """,
                "\u0007",
                """
                It's not you, who's playing now ;-)
                """
        ));
        return list;

    }

    /**
     * Returns a randomly selected unsettling atmospheric message.
     * This method generates the message pool and returns one random message each time
     * it's called, providing variety in atmospheric feedback during gameplay.
     *
     * @return a random unsettling message string
     */
    public static String getUnsettlingMesssage() {
        ArrayList<String> unsettlingMessages = generateListOfUnsettlingMessages();
        Random random = new Random();
        return unsettlingMessages.get(random.nextInt(unsettlingMessages.size()));
    }
}