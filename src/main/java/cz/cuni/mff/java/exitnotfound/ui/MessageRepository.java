package cz.cuni.mff.java.exitnotfound.ui;

import java.util.Map;
import cz.cuni.mff.java.exitnotfound.enums.MessageKey;

/**
 * Central repository for all user-facing messages and text in the game.
 * This class stores localized message templates indexed by MessageKey. All text
 * displayed to the player is retrieved from this repository, allowing for easy
 * maintenance and potential future localization. Messages can include format placeholders
 * for dynamic content like player names.
 */
public class MessageRepository {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * All methods are static and can be called directly on the class.
     */
    private MessageRepository() {
    }

    /**
     * Static initializer containing all message templates indexed by MessageKey.
     * Each entry maps a unique MessageKey to a localized message string.
     */
    private static final Map<MessageKey, String> messages = Map.ofEntries(

            Map.entry(MessageKey.ERROR,
                    "Unfortunately, this went kinda wrong, try again\n"),

            Map.entry(MessageKey.ASK_NAME,
                    """
                    -----------------
                    Time to hear your name, how would you like to be called?
                    Write it here and press [enter]:
                    """),

            Map.entry(MessageKey.MOVED_LEFT,
                    "You went left.\n"),

            Map.entry(MessageKey.MOVED_RIGHT,
                    "You went right.\n"),

            Map.entry(MessageKey.MOVED_STRAIGHT,
                    "You went straight.\n"),
            Map.entry(MessageKey.MOVED_BACK,
                    "You went back... Cowardish\n"),

            Map.entry(MessageKey.WELCOME,
                    """
                    Hello, adventurer!
                    This is the most bananas program ever!
                    Do not let yourself get confused by anything weird you come across in this game.
                    After all, a weirdo has programmed it...
                    """),

            Map.entry(MessageKey.ASK_TUTORIAL,
                    """
                    -----------------
                    So, %s
                    You seem to be new to this game, my memory is terrible though, so who knows.
                    Anyway, would you like me to recall the basics of the gameplay for you?

                    If yes, just write
                    ok[enter]
                    If no, write anything else and enter it!

                    (and if you typed something by mistake and now are lost, type commands[enter])
                    """),

            Map.entry(MessageKey.TUTORIAL_INTRO,
                    """
                    -----------------
                    Alright.

                    Whole game is based on your text interaction here in the console.
                    Do not exit it without saving, your progress would be lost forever...

                    Valid input consists of a command and optional parameters depending on command.
                    """),

            Map.entry(MessageKey.LIST_COMMANDS_HEADER,
                    """
                    _____________________________________
                    list of valid commands
                    _____________________________________
                    """),

            Map.entry(MessageKey.LIST_COMMANDS_FOOTER,
                    """
                    _____________________________________
                    """),

            Map.entry(MessageKey.END_CONFIRMATION,
                    """
                    -----------------
                    Do you really want to end this misery?

                    I mean game, ehm...

                    But do you want to end anyway?
                    Are you sure you saved it? If yes, then confirm it by an ok :)
                    """),

            Map.entry(MessageKey.ANOTHER_GAME,
                    "Do you want to send another hero in this dark hell?\n"),

            Map.entry(MessageKey.WIN,
                    "Congratulations %s! You won!\n"),

            Map.entry(MessageKey.FOUND_OBJECT,
                    "You found %s.\n"),

            Map.entry(MessageKey.FAILURE,
                    "%s died in the darkness...\n\nrequiescat in pace\n\n"),

            Map.entry(MessageKey.PATH_BLOCKED,
                    """
                    -----------------
                    The path is blocked this way...
                    """),

            Map.entry(MessageKey.FAILED_SAVING,
                    """
                    -----------------
                    Saving failed.

                    Do you want to try again?
                    """),

            Map.entry(MessageKey.SUCCESSFUL_SAVING,
                    """
                    -----------------
                    Saving was successful!
                    """),

            Map.entry(MessageKey.BACK_IN_GAME,
                    """
                    -----------------
                    You are now back in the game
                    """),

            Map.entry(MessageKey.LOADING_NEW_GAME,
                    """
                    -----------------
                    Currently you are on the way to start a completely new spectacular game.

                    But there is a chance to load an old game from a file.

                    Requirements:
                    1) The file must be valid file made by this game previously
                    2) Extension must be .mgtg
                    3) File must be in same directory as game executable (or, if you are brave and your file is elsewhere, use full path)

                    For loading:
                    type ok[enter]
                    type file name/path including extension and press [enter]

                    For new game:
                    Press [enter]
                    """
            ),

            Map.entry(MessageKey.SUCCESSFUL_LOADING,
                    """
                    -----------------
                    Loading was successful!
                    """),

            Map.entry(MessageKey.FAILED_LOADING,
                    """
                    -----------------
                    Loading attempt failed.
                    Do you want to try again?
                    """),

            Map.entry(MessageKey.SAVE_INSTRUCTION,
                    """
                    -----------------
                    You have chosen to save your file, wise indeed.

                    Type the path of a .mgtg file where you want to save and press [enter].

                    If you only press [enter], default file called coconut.mgtg will be used.
                    """),

            Map.entry(MessageKey.UNSETTLING_MESSAGE,
                    """
                    ¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.
                    %s
                    ¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.¨.
                    """)
    );

    /**
     * Retrieves a message template by key and optionally formats it with arguments.
     * This method looks up a message definition by its key. If additional arguments are
     * provided, they are used to format the message using String.format(), allowing
     * for dynamic content insertion (e.g., player names).
     *
     * @param key the MessageKey identifying which message to retrieve
     * @param args optional format arguments for the message template
     * @return the formatted message string, or "Message not found" if the key is missing
     */
    public static String get(MessageKey key, Object... args) {

        String msg = messages.getOrDefault(key, "Message not found");

        if (args.length > 0) {
            return String.format(msg, args);
        }

        return msg;
    }
}