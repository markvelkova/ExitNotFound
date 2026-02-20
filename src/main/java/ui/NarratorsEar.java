package ui;

import commands.Command;
import enums.CommandType;

import java.io.BufferedReader;
import java.io.IOException;

public class NarratorsEar {

    public static BufferedReader listener;

    public static String getLine() throws IOException {
        return listener.readLine();
    }

    public static Command getCommand() throws IOException {

        NarratorsMouth.write(">> ");

        String input = listener.readLine();

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
