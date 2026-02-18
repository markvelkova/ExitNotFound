package ui;

import java.util.ArrayList;
import java.util.Random;

public class UnsettlingMessenger
{
    private static ArrayList<String> generateListOfUnsettlingMessages()
    {
        var list = new ArrayList<String> //TODO grrrrrr
        {
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
         """
        };
        return list;

    }
    public static String GetUnsettlingMesssage()
    {
        ArrayList <String> unsettlingMessages = generateListOfUnsettlingMessages();
        Random random = new Random();
        return unsettlingMessages[random.nextInt(unsettlingMessages.size()];
    }

}