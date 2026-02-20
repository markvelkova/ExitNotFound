package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class UnsettlingMessenger
{
    private static ArrayList<String> generateListOfUnsettlingMessages()
    {

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
    public static String getUnsettlingMesssage()
    {
        ArrayList <String> unsettlingMessages = generateListOfUnsettlingMessages();
        Random random = new Random();
        return unsettlingMessages.get(random.nextInt(unsettlingMessages.size()));
    }

}