package cz.cuni.mff.java.exitnotfound.game;

import cz.cuni.mff.java.exitnotfound.maputils.MapConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Main game loop engine that repeatedly updates the game until it ends.
 * This class serves as the core execution loop for the game. It contains the main
 * method entry point and manages the continuous game update cycle until the player
 * decides to stop playing entirely. */
public class GameEngine {

    private final Game game;

    /**
     * Constructs a GameEngine with the specified game instance.
     * @param game the game to run     */
    public GameEngine(Game game){
        this.game = game;
    }

    /**
     * Executes the main game loop until the current game ends.
     * This method repeatedly calls game.update() until the game signals it has ended,
     * then returns whether the player wants to start another game.
     * @return true if the player wants to play another game, false if they want to exit completely     */
    public boolean run(){
        while(!game.isGameEnded()){
            game.update();
        }
        return game.isNextGameShouldBePlayed();
    }

    /**
     * Main entry point for the application.
     * @param args command line arguments (not used)     */
    public static void main(String[] args){
        boolean playAgain = true;
        MapConfig gameConfig = new MapConfig(80,20,15);
        while(playAgain){
            Game game = new Game(
                    "mapa.txt",
                    gameConfig,
                    new PrintWriter(System.out),
                    new BufferedReader(new InputStreamReader(System.in))
            );
            GameEngine engine = new GameEngine(game);
            playAgain = engine.run();
        }
    }
}
