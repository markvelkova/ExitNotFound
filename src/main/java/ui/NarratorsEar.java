package ui;

import commands.Command;
import enums.CommandType;

import java.io.BufferedReader;
import java.io.IOException;

/** * Utility class for reading user input and parsing player commands.
 * This class handles all player text input, parsing raw input into Command objects
 * that the game can understand. It maintains a static BufferedReader reference for
 * console input and provides methods to read lines and parse them into commands. */
public class NarratorsEar {

    public static BufferedReader listener;

    /**
     * Reads a single line of raw input from the user.
     * @return the line of text entered by the user, or null if an error occurs     */
    public static String getLine() {
        try {
            return listener.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Reads a line of input from the user and parses it into a Command object.
     * The input is parsed as whitespace-separated tokens where the first token is the
     * command type and subsequent tokens are parameters. The resulting Command is
     * validated using Command.isValid(). Invalid commands are returned as CommandType.invalid.
     * @return a parsed Command object, or a Command with type invalid if parsing/validation failed
     * */
    public static Command getCommand() {

        NarratorsMouth.write(">> ");

        String input = "invalid";
        try{
            input = listener.readLine();
        } catch(IOException e){
            // input will stay invalid
        }

        if (input == null || input.isEmpty()) {
            return new Command(CommandType.empty);
        }

        String[] splittedInput = input.split("\\s+");

        CommandType type;

        try {
            type = CommandType.valueOf(splittedInput[0]);

            if (type == CommandType.invalid || type == CommandType.empty)
                return new Command(CommandType.invalid);

        } catch (IllegalArgumentException e) {
            return new Command(CommandType.invalid);
        }

        String[] parameters = new String[splittedInput.length - 1];

        System.arraycopy(splittedInput, 1, parameters, 0, parameters.length);

        Command command =
                (parameters.length == 0)
                        ? new Command(type)
                        : new Command(type, parameters);

        if (command.isValid())
            return command;

        return new Command(CommandType.invalid);
    }
}
