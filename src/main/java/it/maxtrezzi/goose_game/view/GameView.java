package it.maxtrezzi.goose_game.view;

import it.maxtrezzi.goose_game.core.Consts;
import it.maxtrezzi.goose_game.core.Game;
import it.maxtrezzi.goose_game.core.Messages;

import java.util.Scanner;

import static it.maxtrezzi.goose_game.core.Messages.GAME_MENU;

/**
 * Implements the View of the gaming phase with menu output and command input handling
 * @author maxtrezzi
 */
public class GameView extends View {
    private final Game game;

    public GameView(Game game) {
        this.game = game;
    }

    public GameView show() {
        Scanner scanner = new Scanner(System.in);
        while (!game.hasWinner()) {
            System.out.println(GAME_MENU);
            String command = scanner.nextLine();
            if (command.startsWith(Consts.MOVE_PLAYER_COMMAND)) {
                String args = command.substring(Consts.MOVE_PLAYER_COMMAND.length());
                try {
                    MoveArgs moveArgs = MoveArgs.parseMoveArgs(args);
                    if (moveArgs.isComplete()) {
                        println(game.movePlayer(moveArgs.getPlayerName(),moveArgs.getFirstDice(),moveArgs.getSecondDice()));
                    } else {
                        println(game.moveThrowingDice(moveArgs.getPlayerName()));
                    }
                } catch (Exception e) {
                    println(e.getMessage());
                }
            } else if (command.equals(Consts.EXIT_COMMAND)) {
                println(Messages.GAME_QUITTED);
                break;
            } else {
                println(Messages.UNKNOWN_COMMAND);
            }
        }
        return this;
    }
}
