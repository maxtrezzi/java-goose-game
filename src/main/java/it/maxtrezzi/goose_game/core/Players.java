package it.maxtrezzi.goose_game.core;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Handles a set of {@link Player}
 * @author maxtrezzi
 */
public class Players {
    private final Set<Player> players= new LinkedHashSet<>();

    public Players() {
    }

    public Players addPlayer(Player player) {
        if (!hasPlayer(player)) {
            players.add(player);
        } else {
            throw new IllegalArgumentException(String.format(Messages.ALREADY_EXISTING_PLAYER, player.getName()));
        }

        return this;
    }

    public boolean isEmpty () {
        return (players.isEmpty());
    }

    public boolean hasPlayer (Player player) {
        return (players.contains(player));
    }

    public Collection<Player> getCollection() {
        return Collections.unmodifiableCollection(players);
    }

    public String getAllPlayersAsString() {
        String comma = "";
        StringBuilder result = new StringBuilder();

        for (Player p : players) {
            result
                .append(comma)
                .append(p.getName());
            comma = ", ";
        }

        return result.toString();
    }
}
