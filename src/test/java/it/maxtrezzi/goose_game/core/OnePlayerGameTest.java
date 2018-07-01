package it.maxtrezzi.goose_game.core;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OnePlayerGameTest {
    static private App app;
    private Game game;

    @BeforeAll
    static void beforeAll() {
        app = new App();
        app.addPlayer("Pippo");
    }

    @AfterAll
    static void afterAll() {
        app = null;
    }

    @BeforeEach
    void beforeEach() {
        game = app.createNewGame();
    }

    @AfterEach
    void afterEach() {
        game = null;
    }

    @Test
    @DisplayName("3. Win -> 1. Victory")
    void testScenario31()  {
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        assertEquals(60, game.getGamePlayer("Pippo").getPosition());
        String ret = game.movePlayer("Pippo",1,2);
        assertEquals("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!",ret);
        assertTrue(game.hasWinner());
        assertEquals(game.getWinner().getPlayer().getName(),"Pippo");
    }

    @Test
    @DisplayName("3. Win -> 2. Winning with the exact dice shooting")
    void testScenario32()  {
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        game.movePlayer("Pippo",6,6);
        String ret = game.movePlayer("Pippo",3,2);
        assertEquals("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61",ret);
        assertFalse(game.hasWinner());
    }

    @Test
    @DisplayName("4. The game throws the dice 1.")
    void testScenario41()  {
        game.moveThrowingDice("Pippo");
        assertTrue(game.getGamePlayer("Pippo").getPosition()>0);
    }

    @Test
    @DisplayName("5. Space 6 is 'The Bridge' 1.")
    void testScenario51()  {
        game.movePlayer("Pippo",2,2);
        assertEquals(4, game.getGamePlayer("Pippo").getPosition());
        String ret = game.movePlayer("Pippo",1,1);
        assertEquals("Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12",ret);
        assertEquals(12, game.getGamePlayer("Pippo").getPosition());
    }

    @Test
    @DisplayName("6. If you land on 'The Goose', move again. 1. Single Jump")
    void testScenario61()  {
        game.movePlayer("Pippo",1,2);
        assertEquals(3, game.getGamePlayer("Pippo").getPosition());
        String ret = game.movePlayer("Pippo",1,1);
        assertEquals("Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7",ret);
        assertEquals(7, game.getGamePlayer("Pippo").getPosition());
    }

    @Test
    @DisplayName("6. If you land on 'The Goose', move again. 1. Multiple Jump")
    void testScenario62()  {
        game.movePlayer("Pippo",5,5);
        assertEquals(10, game.getGamePlayer("Pippo").getPosition());
        String ret = game.movePlayer("Pippo",2,2);
        assertEquals(
                "Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22",
                ret);
        assertEquals(22, game.getGamePlayer("Pippo").getPosition());
    }

    @Test
    @DisplayName("unknown player")
    void testPlayerNotFound()  {
        String ret = game.movePlayer("pippo",5,5);
        assertEquals("Unknown player pippo",ret);
    }
}


