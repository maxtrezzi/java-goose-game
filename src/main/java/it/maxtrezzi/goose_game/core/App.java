package it.maxtrezzi.goose_game.core;

/**
 * Application logic of Java Goose Game
 * @author maxtrezzi
 */
public class App {
    private final Players players = new Players();

    public App() {

    }

    public String addPlayer(String name) {
        if ( (name == null) || (name.isEmpty()) ) {
            return Messages.PLAYER_NAME_IS_REQUIRED;
        }

        Player newPlayer = new Player(name);
        if (!players.hasPlayer(newPlayer)) {
            players.addPlayer(newPlayer);
            return (String.format(Messages.PLAYERS,players.getAllPlayersAsString()));
        } else {
            return (String.format(Messages.ALREADY_EXISTING_PLAYER,name));
        }
    }

    public Game createNewGame()  {
        return new Game(players);
    }

    public Players getPlayers() {
        return players;
    }
}
