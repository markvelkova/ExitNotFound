package game;

import maputils.MapConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class GameEngine {

    private final Game game;

    public GameEngine(Game game){
        this.game = game;
    }

    public boolean run(){
        while(!game.isGameEnded()){
            game.update();
        }
        return game.isNextGameShouldBePlayed();
    }

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
