package it.maxtrezzi.goose_game.core;

/**
 * Represents a single player in the application
 * @author maxtrezzi
 */
class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            return name.equals(((Player)obj).getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
