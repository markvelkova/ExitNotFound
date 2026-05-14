package game;

import actions.Answer;
import commands.Command;
import commands.CommandHandler;
import enums.CommandType;
import enums.GamePhase;
import enums.PlayerState;
import maputils.Map;
import maputils.MapConfig;
import toolkit.GameLoaderAndSaver;
import toolkit.GameState;
import toolkit.Player;
import ui.NarratorsEar;
import ui.NarratorsMouth;

import java.io.*;

public class Game {

    private Player p;
    private GamePhase phase;
    private Map map;
    private CommandHandler handler;
    private boolean gameEnded;
    private boolean nextGameShouldBePlayed;

    public Game(String mapFile, MapConfig mapConfig, PrintWriter w, BufferedReader r){
        nextGameShouldBePlayed = false;
        phase = GamePhase.welcome;
        p = new Player("playerDefaultName",100);

        try(BufferedReader mr = new BufferedReader(new FileReader(mapFile))){
            map = new Map(Integer.parseInt(mr.readLine()), Integer.parseInt(mr.readLine()), mapConfig);
            p.setCoord(map.initializeAndGetPlayer(mr));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        NarratorsMouth.speaker = w;
        NarratorsEar.listener = r;
        handler = new CommandHandler(p,map);
    }

    public boolean isGameEnded(){
        return gameEnded;
    }
    public boolean isNextGameShouldBePlayed() {
        return nextGameShouldBePlayed;
    }

    public void update(){
        switch(phase){
            case welcome -> {
                NarratorsMouth.welcome();
                NarratorsMouth.askAboutLoadingOldGame();

                if(handler.executeAction(new Answer(NarratorsEar.getCommand())))
                    phase = GamePhase.loading; //TODO: sem pridat nacteni mapy
                else
                    phase = GamePhase.nameAsking;
            }

            case loading -> {
                if(tryLoadGame()) {
                    NarratorsMouth.announceSuccessfulLoading();
                    requestTutorial();
                } else {
                    if (!handleFailedLoadingAndReturnIfShouldTryAgain())
                        phase = GamePhase.nameAsking;
                }
            }

            case nameAsking -> {
                askForName();
                requestTutorial();
            }

            case tutorial -> giveTutorial();

            case game -> playRound();

            case won -> {
                NarratorsMouth.announceWin(p);
                nextGameShouldBePlayed = askIfAnotherGameIsWanted();
                gameEnded = true;
            }

            case lost -> {
                NarratorsMouth.announceFailure(p);
                nextGameShouldBePlayed = askIfAnotherGameIsWanted();
                gameEnded = true;
            }

            case save -> {
                if(!trySaveGame())
                    handleFailedSaving();
            }

            case exited -> {
                if (shouldGameBeExited())
                    gameEnded = true;
                else
                    phase = GamePhase.game;
            }
        }
    }

    private static GamePhase getNewGamePhase(Player p){
        if(p.state == PlayerState.won)
            return GamePhase.won;

        if(p.state == PlayerState.dead)
            return GamePhase.lost;

        return GamePhase.game;
    }

    private void playRound(){
        Command c = NarratorsEar.getCommand();
        if(c.getType() == CommandType.exit){
            phase = GamePhase.exited;
            return;
        }
        if(c.getType() == CommandType.save){
            phase = GamePhase.save;
            return;
        }
        handler.executeAction(handler.createAction(c));
        p.updateByCurrentTile(map);
        if(p.foundNewObject()) {
            NarratorsMouth.announceFindingObject(p.objectFoundLastMove);
            p.useFoundObject();
        }
        if(p.shouldBeWorried())
            NarratorsMouth.printUnsettlingMessage(p);
        phase = getNewGamePhase(p);
    }

    private void welcome(){
        NarratorsMouth.welcome();
        NarratorsMouth.askAboutLoadingOldGame();

        if(handler.executeAction(new Answer(NarratorsEar.getCommand())))
            phase = GamePhase.loading;
        else
            phase = GamePhase.nameAsking;
    }

    private boolean tryLoadGame(){
        String file = NarratorsEar.getLine();
        if(file == null) return false;
        GameState state = GameLoaderAndSaver.load2(file);

        if (state != null){
            p = state.getPlayer();
            map = state.getMap();
            handler = new CommandHandler(p, map);
            return true;
        }
        return false;
    }

    private Boolean handleFailedLoadingAndReturnIfShouldTryAgain(){
        NarratorsMouth.announceFailedLoading();
        return (handler.executeAction(new Answer(NarratorsEar.getCommand())));
    }

    private boolean trySaveGame(){
        NarratorsMouth.saySavingInstruction();
        if (GameLoaderAndSaver.save2(NarratorsEar.getLine(),p,map)){
            NarratorsMouth.announceSuccessfulSaving();
            NarratorsMouth.backInTheGame();
            phase = GamePhase.game;
            return true;
        }
        return false;
    }

    private void handleFailedSaving(){
        NarratorsMouth.announceFailedSaving();
        if(!handler.executeAction(new Answer(NarratorsEar.getCommand()))){
            NarratorsMouth.backInTheGame();
            phase = GamePhase.game;
        }
    }

    private void askForName(){
        NarratorsMouth.askForName();
        p.setName(NarratorsEar.getLine());
    }

    private void requestTutorial(){
        NarratorsMouth.askAboutTutorial(p);
        if(handler.executeAction(new Answer(NarratorsEar.getCommand())))
            phase = GamePhase.tutorial;
        else
            phase = GamePhase.game;
    }

    private void giveTutorial(){
        NarratorsMouth.giveTutorial();
        phase = GamePhase.game;
    }

     private Boolean shouldGameBeExited(){
        NarratorsMouth.askForEndConfirmation();
        return handler.executeAction(new Answer(NarratorsEar.getCommand()));
    }

    private boolean askIfAnotherGameIsWanted(){
        NarratorsMouth.askForAnotherGame();
        return handler.executeAction(new Answer(NarratorsEar.getCommand()));
    }
}