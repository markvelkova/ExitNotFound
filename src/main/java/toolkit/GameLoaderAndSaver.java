package toolkit;

import maputils.Map;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameLoaderAndSaver {

    public static boolean load(String filename, Player player) {
        Path path = Path.of(filename);
        if (!Files.exists(path)) {
            return false;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            if (line == null) return false;

            while (line != null) {
                String[] splitted = line.split("\\$");
                if (splitted.length < 2) return false;

                switch (splitted[0]) {
                    case "NAME" -> player.setName(splitted[1]);
                    case "HEALTH" -> {
                        try {
                            player.setHealth(Integer.parseInt(splitted[1]));
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }
                    case "UNSMESS" -> {
                        try {
                            player.setNumberOfUnsettlingMessagesHeard(Integer.parseInt(splitted[1]));
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }
                    default -> {
                        return false;
                    }
                }

                line = reader.readLine();
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public static boolean save(String filename, Player player) {
        if (filename == null || filename.isBlank()) {
            filename = "coconut.mgtg";
        }
        Path path = Path.of(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("NAME$" + player.getName() + "$");
            writer.newLine();
            writer.write("HEALTH$" + player.getHealth() + "$");
            writer.newLine();
            writer.write("UNSMESS$" + player.getNumberOfUnsettlingMessagesHeard() + "$");
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean save2(String filename, Player player, Map map) {
        if (filename == null || filename.isBlank()) {
            filename = "coconut.mgtg";
        }

        GameState state = new GameState(player, map);

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filename))) {

            out.writeObject(state);
            return true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static GameState load2(String filename) { //TODO: fails
        File file = new File(filename);
        if (!file.exists()) return null;

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            return (GameState) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
