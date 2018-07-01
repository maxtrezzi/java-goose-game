package it.maxtrezzi.goose_game.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest  {
    private App app;

    @BeforeEach
    void beforeEach() {
        app = new App();
    }

    @AfterEach
    void afterEach() {
        app = null;
    }

    @Test
    @DisplayName("1. AddPlayers -> 1. AddPlayer")
    void testScenario11()  {
        String res1 = app.addPlayer("Pippo");
        assertEquals("players: Pippo", res1);
        assertEquals(app.getPlayers().getCollection().size(),1);
        assertTrue(app.getPlayers().hasPlayer(new Player("Pippo")));

        String res2 = app.addPlayer("Pluto");
        assertEquals("players: Pippo, Pluto", res2);
        assertEquals(app.getPlayers().getCollection().size(),2);
        assertTrue(app.getPlayers().hasPlayer(new Player("Pluto")));
        assertTrue(app.getPlayers().hasPlayer(new Player("Pippo")));
    }

    @Test
    @DisplayName("1. AddPlayers -> 2. Duplicated")
    void testScenario12()  {
        app.addPlayer("Pippo");
        String res1 = app.addPlayer("Pippo");
        assertEquals("Pippo: already existing player", res1);
        assertEquals(app.getPlayers().getCollection().size(),1);
        assertTrue(app.getPlayers().hasPlayer(new Player("Pippo")));
    }

    @Test
    @DisplayName("Player Name is Required")
    void testPlayerIsRequired()  {
        String res1 = app.addPlayer(null);
        assertEquals("Player's name is required", res1);
        String res2 = app.addPlayer("");
        assertEquals("Player's name is required", res2);
    }
}
