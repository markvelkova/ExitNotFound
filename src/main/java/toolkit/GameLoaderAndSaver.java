package toolkit;

import maputils.Map;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/** * Utility class for saving and loading complete game states to/from disk. * * This class provides methods for persisting game progress using Java serialization, * allowing players to save their game and resume later. The modern save/load methods * (save2/load2) serialize the entire GameState object, while legacy methods handle * simpler text-based formats. */
public class GameLoaderAndSaver {
    /**
     * Saves the complete game state (player and map) to a file using serialization.
     * If no filename or a blank filename is provided, defaults to "coconut.mgtg".
     * The entire GameState object is serialized and written to the specified file.
     * @param filename the path/name of the file to save to (or null/blank for default)
     * @param player the player object to save
     * @param map the map object to save
     * @return true if save was successful, false if an error occurred     */
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
    /**
     * Loads a complete game state from a serialized file.
     * This method reads a previously saved game file and reconstructs the GameState object,
     * which includes both the Player and Map. If the file doesn't exist or deserialization fails,
     * null is returned.
     * @param filename the path/name of the file to load from
     * @return the loaded GameState object, or null if loading failed     */
    public static GameState load2(String filename) {
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
