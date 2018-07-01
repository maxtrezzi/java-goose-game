package it.maxtrezzi.goose_game.view;

import it.maxtrezzi.goose_game.core.Consts;
import it.maxtrezzi.goose_game.core.Messages;

import java.util.Scanner;

import static it.maxtrezzi.goose_game.core.Messages.APP_MENU;

/**
 * Implements the main view with main menu output and command input handling
 * @author maxtrezzi
 */
public class AppView extends View {
    private final it.maxtrezzi.goose_game.core.App appModel = new it.maxtrezzi.goose_game.core.App();

    public AppView() {
    }

    @Override
    public View show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(APP_MENU);
            String command = scanner.nextLine();
            try {
                if (command.startsWith(Consts.ADD_PLAYER_COMMAND)) {
                    String newPlayerName = command.substring(Consts.ADD_PLAYER_COMMAND.length()).trim();
                    println(appModel.addPlayer(newPlayerName));
                } else if (command.equals(Consts.PLAY_COMMAND)) {
                    startNewGame();
                } else if (command.equals(Consts.EXIT_COMMAND)) {
                    println(Messages.BYE);
                    break;
                } else {
                    println(Messages.UNKNOWN_COMMAND);
                }
            } catch (Exception e) {
                println(e.getMessage());
            }
        }
        return this;
    }

    private AppView startNewGame()  {
        new GameView(appModel.createNewGame()).show();
        return this;
    }



}
