package it.maxtrezzi.goose_game.core;

import java.util.*;

/**
 * Handles a Map of {@link GamePlayer} keyed by player's names.
 * @author maxtrezzi
 */
class GamePlayers {
    private Game game;
    private final Map<String, GamePlayer> gamePlayers = new LinkedHashMap<>();

    public GamePlayers(Game game, Players players) {
        assert(game != null) : "GamePlayers: game must be assigned";
        assert(players != null) : "GamePlayers: players must be assigned";
        this.game = game;

        for (Player player : players.getCollection()) {
            gamePlayers.put(player.getName(), new GamePlayer(game, player));
        }
    }

    public Game getGame() {
        return game;
    }

    public GamePlayer getPlayerByName(String name) {
        return gamePlayers.get(name);
    }

    public GamePlayer findPlayerOnSpace(int space, GamePlayer playerToExclude) {
        for (GamePlayer gp: gamePlayers.values()) {
            if ( (gp != playerToExclude) && (gp.getPosition()==space) ) {
                return gp;
            }
        }
        return null;
    }
}
