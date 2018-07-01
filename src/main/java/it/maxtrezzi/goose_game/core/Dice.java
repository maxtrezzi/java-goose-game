package it.maxtrezzi.goose_game.core;

import java.util.Random;

/**
 * A dice which can roll
 * @author maxtrezzi
 */
public class Dice {
    private final Random random = new Random();

    public int roll() {
        return random.nextInt(6)+1;
    }
}
