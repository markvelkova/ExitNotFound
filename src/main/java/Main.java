import actions.Answer;
import commands.Command;
import commands.CommandHandler;
import enums.CommandType;
import enums.GamePhase;
import maputils.Map;
import toolkit.GameLoaderAndSaver;
import toolkit.Player;
import ui.NarratorsEar;
import ui.NarratorsMouth;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

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

        boolean shouldKeepPlaying = true;

        while (shouldKeepPlaying) {

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

                    if (c.getType() == CommandType.end)
                        phase = GamePhase.end;

                    else if (c.getType() == CommandType.save)
                        phase = GamePhase.save;

                    else {
                        handler.handleAction(
                                handler.createAction(c));

                        NarratorsMouth.printUnsettlingMessage();
                        p.setNumberOfUnsettlingMessagesHeard(
                                p.getNumberOfUnsettlingMessagesHeard()+1
                        );
                    }
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

                case end:

                    NarratorsMouth.askForEndConfirmation();

                    if (handler.handleAction(
                            new Answer(NarratorsEar.getCommand())))
                        shouldKeepPlaying = false;
                    else
                        phase = GamePhase.game;

                    break;
            }
        }
    }
}
