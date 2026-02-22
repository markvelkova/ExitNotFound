import actions.Answer;
import commands.Command;
import commands.CommandHandler;
import enums.CommandType;
import enums.GamePhase;
import enums.PlayerState;
import maputils.Map;
import toolkit.GameLoaderAndSaver;
import toolkit.Player;
import ui.NarratorsEar;
import ui.NarratorsMouth;

import java.io.*;
import java.nio.Buffer;

public class Game {
    private Player p;
    private GamePhase phase;
    private Map map;
    private PrintWriter speakerOut;
    private BufferedReader listenerIn;
    private CommandHandler handler;
    private boolean gameEnded;

    public Game(String mapFile, PrintWriter w, BufferedReader r) {
        phase = GamePhase.welcome;
        p = new Player("playerDefaultName", 100);

        try(BufferedReader mr = new BufferedReader(new FileReader(mapFile))) {
            map = new Map(
                    Integer.parseInt(mr.readLine()),
                    Integer.parseInt(mr.readLine())
            );
            p.setCoord(map.initializeAndGetPlayer(mr));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        NarratorsMouth.speaker = w;
        NarratorsEar.listener = r;
        handler = new CommandHandler(p, map);
        gameEnded = false;
    }

    private static GamePhase getNewGamePhaseAccordingToPlayer(Player p) {
        if (p.state == PlayerState.won) {
            return GamePhase.won;
        }
        if (p.state == PlayerState.dead) {
            return GamePhase.lost;
        }
        return GamePhase.game;
    }

    public void playRound() {
        Command c = NarratorsEar.getCommand();

        // exit the game
        if (c.getType() == CommandType.exit) {
            phase = GamePhase.exited;
            return;
        }
        // save the game
        if (c.getType() == CommandType.save) {
            phase = GamePhase.save;
            return;
        }
        // play the round
        handler.handleAction(handler.createAction(c));
        NarratorsMouth.printUnsettlingMessage(p);

        // win, lose or continue
        phase = getNewGamePhaseAccordingToPlayer(p);
    }

    public void welcome() {
        NarratorsMouth.welcome();
        NarratorsMouth.askAboutLoadingOldGame();

        if (handler.handleAction(new Answer(NarratorsEar.getCommand()))) // if player wrote ok
            phase = GamePhase.loading;
        else
            phase = GamePhase.nameAsking;
    }
    public boolean tryLoadGame() {
        String gameFileName = NarratorsEar.getLine();
        if (gameFileName == null)
            return false;

        if (GameLoaderAndSaver.load(gameFileName, p)) {
            NarratorsMouth.announceSuccessfulLoading();
            phase = GamePhase.tutorial;
            return true;
        }
        return false;
    }
    public void handleFailedLoading() {
            NarratorsMouth.announceFailedLoading();
            if (!handler.handleAction(new Answer(NarratorsEar.getCommand())))
                phase = GamePhase.nameAsking;
    }
    public boolean trySaveGame() {
        NarratorsMouth.saySavingInstruction();
        if (GameLoaderAndSaver.save(NarratorsEar.getLine(), p)) {
            NarratorsMouth.announceSuccessfulSaving();
            phase = GamePhase.game;
            NarratorsMouth.backInTheGame();
            return true;
        }
        return false;
    }
    public void handleFailedSaving() {
        NarratorsMouth.announceFailedSaving();
        if (!handler.handleAction(new Answer(NarratorsEar.getCommand()))) {
            NarratorsMouth.backInTheGame();
            phase = GamePhase.game;
        }
    }

    public void askForNameAndInitialize() {
        NarratorsMouth.askForName();
        p.setName(NarratorsEar.getLine());
        NarratorsMouth.askAboutTutorial(p);

        if (handler.handleAction(new Answer(NarratorsEar.getCommand())))
            phase = GamePhase.tutorial;
        else
            phase = GamePhase.game;
    }
    public void giveTutorial(){
        NarratorsMouth.giveTutorial();
        phase = GamePhase.game;
    }
    public void exitGame() {
        NarratorsMouth.askForEndConfirmation();
        if (handler.handleAction(new Answer(NarratorsEar.getCommand())))
            gameEnded = true;
        else
            phase = GamePhase.game;
    }
    public void win() {
        NarratorsMouth.announceWin(p);
        gameEnded = true;
    }
    public void lose() {
        NarratorsMouth.announceFailure(p);
        gameEnded = true;
    }
    public boolean askIfAnotherGameIsWanted() {
        NarratorsMouth.askForAnotherGame();
        return handler.handleAction(new Answer(NarratorsEar.getCommand()));
    }

    public static void main(String[] args) {
        boolean shouldPlayAnotherGame = true;

        while (shouldPlayAnotherGame) {
            Game game = new Game("mapa.txt",new PrintWriter(System.out), new BufferedReader(new InputStreamReader(System.in)));
            while (!game.gameEnded) {
                switch (game.phase) {
                    case welcome:
                        game.welcome();
                        break;
                    case loading:
                        if(!game.tryLoadGame()) {
                            game.handleFailedLoading();
                        }
                        break;
                    case nameAsking:
                        game.askForNameAndInitialize();
                        break;
                    case tutorial:
                        game.giveTutorial();
                        break;
                    case game:
                        game.playRound();
                        break;
                    case won:
                        game.win();
                        if(!game.askIfAnotherGameIsWanted())
                            shouldPlayAnotherGame = false;
                        break;
                    case lost:
                        game.lose();
                        if(!game.askIfAnotherGameIsWanted())
                            shouldPlayAnotherGame = false;
                        break;
                    case save:
                        if (!game.trySaveGame())
                            game.handleFailedSaving();
                        break;
                    case exited:
                        game.exitGame();
                        shouldPlayAnotherGame = false;
                        break;
                }
            }
        }
    }

    public static void main2(String[] args) throws Exception {

        GamePhase phase = GamePhase.welcome;

        Player p = new Player("playerDefaultName", 100);

        BufferedReader r = new BufferedReader(new FileReader("mapa.txt"));

        Map map = new Map(
                Integer.parseInt(r.readLine()),
                Integer.parseInt(r.readLine())
        );

        p.setCoord(map.initializeAndGetPlayer(r));

        NarratorsMouth.speaker = new PrintWriter(System.out);
        NarratorsEar.listener =
                new BufferedReader(new InputStreamReader(System.in));

        CommandHandler handler = new CommandHandler(p, map);
        boolean gameEnded = false;

        while (p.state == PlayerState.playing && !gameEnded) {

            switch (phase) {

                case welcome:
                    NarratorsMouth.welcome();
                    NarratorsMouth.askAboutLoadingOldGame();

                    if (handler.handleAction(
                            new Answer(NarratorsEar.getCommand())))
                        phase = GamePhase.loading;
                    else
                        phase = GamePhase.nameAsking;
                    break;

                case loading:

                    if (GameLoaderAndSaver.load(
                            NarratorsEar.getLine(), p)) {

                        NarratorsMouth.announceSuccessfulLoading();
                        phase = GamePhase.tutorial;
                    }
                    else {
                        NarratorsMouth.announceFailedLoading();

                        if (!handler.handleAction(
                                new Answer(NarratorsEar.getCommand())))
                            phase = GamePhase.nameAsking;
                    }
                    break;

                case nameAsking:

                    NarratorsMouth.askForName();
                    p.setName(NarratorsEar.getLine());
                    NarratorsMouth.askAboutTutorial(p);

                    if (handler.handleAction(
                            new Answer(NarratorsEar.getCommand())))
                        phase = GamePhase.tutorial;
                    else
                        phase = GamePhase.game;
                    break;

                case tutorial:
                    NarratorsMouth.giveTutorial();
                    phase = GamePhase.game;
                    break;

                case game:

                    Command c = NarratorsEar.getCommand();

                    // exit the game
                    if (c.getType() == CommandType.exit) {
                        phase = GamePhase.exited;
                        break;
                    }
                    // save the game
                    if (c.getType() == CommandType.save) {
                        phase = GamePhase.save;
                        break;
                    }
                    // play the round
                    handler.handleAction(handler.createAction(c));
                    NarratorsMouth.printUnsettlingMessage(p);

                    // win, lose or continue
                    phase = getNewGamePhaseAccordingToPlayer(p);

                    break;
                case won:
                    break;
                case lost:
                    break;

                case save:
                    NarratorsMouth.saySavingInstruction();

                    if (GameLoaderAndSaver.save(
                            NarratorsEar.getLine(), p)) {

                        NarratorsMouth.announceSuccessfulSaving();
                        phase = GamePhase.game;
                        NarratorsMouth.backInTheGame();
                    }
                    else {
                        NarratorsMouth.announceFailedSaving();

                        if (!handler.handleAction(
                                new Answer(NarratorsEar.getCommand()))) {

                            NarratorsMouth.backInTheGame();
                            phase = GamePhase.game;
                        }
                    }
                    break;
                case exited:
                    NarratorsMouth.askForEndConfirmation();
                    if (handler.handleAction(
                            new Answer(NarratorsEar.getCommand())))
                        gameEnded = true;
                    else
                        phase = GamePhase.game;
                    break;

            }
        }
    }
}
