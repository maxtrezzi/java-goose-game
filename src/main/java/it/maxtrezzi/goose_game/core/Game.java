package it.maxtrezzi.goose_game.core;

import static it.maxtrezzi.goose_game.core.Consts.*;

/**
 * A single Goose Game
 * @author maxtrezzi
 */
public class Game {
    private final Board board = new Board(SPACE_COUNT, BRIDGES, GOOSES);
    private GamePlayers players;
    private GamePlayer winner;
    private final Dice dice1 = new Dice();
    private final Dice dice2 = new Dice();

    GamePlayer findPlayerOnSpace(int space, GamePlayer playerToExclude) {
        return players.findPlayerOnSpace(space, playerToExclude);
    }

    public Game(Players players) {
        assert (players!=null) : "players argument is required";

        if (players.isEmpty()) {
            throw new InvalidInputException(Messages.NO_PLAYERS);
        }

        this.players = new GamePlayers(this, players);
    }

    public String movePlayer(String name, int firstDice, int secondDice) {
        assert ( (name != null) && (!name.isEmpty()) ) : "Invalid name";
        assert ( (firstDice > 0) && (firstDice <= 6) ) : "Invalid firstDice value";
        assert ( (secondDice > 0) && (secondDice <= 6) ) : "Invalid secondDice value";

        GamePlayer player = players.getPlayerByName(name);

        if (player != null) {
            return player.moveBy(firstDice, secondDice);
        } else {
            return String.format(Messages.UNKNOWN_PLAYER, name);
        }
    }

    public String moveThrowingDice(String playerName) {
        return movePlayer(playerName, dice1.roll(), dice2.roll());
    }

    public Board getBoard() {
        return board;
    }

    public Game setWinner(GamePlayer player) {
        winner = player;
        return this;
    }

    public GamePlayer getGamePlayer(String name) {
        return this.players.getPlayerByName(name);
    }

    public GamePlayer getWinner() {
        return winner;
    }

    public boolean hasWinner() {
        return (winner != null);
    }
}
