package it.maxtrezzi.goose_game.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import it.maxtrezzi.goose_game.core.*;

class SeveralPlayersGameTest {
    private static App app;
    private Game game;

    @BeforeAll
    static void beforeAll() {
        app = new App();
        app.addPlayer("Pippo");
        app.addPlayer("Pluto");
        app.addPlayer("Paperino");
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
    @DisplayName("2. Move a Player -> 1. Start")
    void testScenario21()  {
        String ret1 = game.movePlayer("Pippo",4,3);
        assertEquals("Pippo rolls 4, 3. Pippo moves from Start to 7", ret1);
        String ret2 = game.movePlayer("Pluto",2,2);
        assertEquals("Pluto rolls 2, 2. Pluto moves from Start to 4", ret2);
        String ret3 = game.movePlayer("Pippo",2,3);
        assertEquals("Pippo rolls 2, 3. Pippo moves from 7 to 12", ret3);
    }

    @Test
    @DisplayName("7. Prank (Optional Step). 1. Prank")
    void testScenario71()  {
        game.movePlayer("Pippo",5,5);
        game.movePlayer("Pluto",6,6);
        game.movePlayer("Pippo",3,2);
        game.movePlayer("Pluto",2,3);
        assertEquals(15, game.getGamePlayer("Pippo").getPosition());
        assertEquals(17, game.getGamePlayer("Pluto").getPosition());
        String ret = game.movePlayer("Pippo",1,1);
        assertEquals("Pippo rolls 1, 1. Pippo moves from 15 to 17. On 17 there is Pluto, who returns to 15", ret);
        assertEquals(17, game.getGamePlayer("Pippo").getPosition());
        assertEquals(15, game.getGamePlayer("Pluto").getPosition());
    }

    @Test
    @DisplayName("Big Game")
    void testBigGame()  {
        game.movePlayer("Pippo",1,2);
        String ret1 = game.movePlayer("Pluto",1,2);
        assertEquals("Pluto rolls 1, 2. Pluto moves from Start to 3. On 3 there is Pippo, who returns to Start", ret1);
        assertEquals(0, game.getGamePlayer("Pippo").getPosition());
        assertEquals(3, game.getGamePlayer("Pluto").getPosition());
        game.movePlayer("Paperino",5,4);
        game.movePlayer("Pippo",4,5);
        assertEquals(0, game.getGamePlayer("Paperino").getPosition());
        assertEquals(36, game.getGamePlayer("Pippo").getPosition());
        game.movePlayer("Pluto",3,3);
        assertEquals(15, game.getGamePlayer("Pluto").getPosition());
        game.movePlayer("Paperino",3,3);
        assertEquals(12, game.getGamePlayer("Paperino").getPosition());
        game.movePlayer("Pippo",6,6);
        assertEquals(48, game.getGamePlayer("Pippo").getPosition());
        game.movePlayer("Pluto",3,5);
        assertEquals(31, game.getGamePlayer("Pluto").getPosition());
        game.movePlayer("Paperino",6,5);
        assertEquals(34, game.getGamePlayer("Paperino").getPosition());
        game.movePlayer("Pippo",1,4);
        assertEquals(53, game.getGamePlayer("Pippo").getPosition());
        game.movePlayer("Pluto",6,5);
        assertEquals(42, game.getGamePlayer("Pluto").getPosition());
        game.movePlayer("Paperino",3,4);
        assertEquals(41, game.getGamePlayer("Paperino").getPosition());
        game.movePlayer("Pippo",1,1);
        assertEquals(55, game.getGamePlayer("Pippo").getPosition());
        game.movePlayer("Pluto",3,5);
        assertEquals(50, game.getGamePlayer("Pluto").getPosition());
        game.movePlayer("Paperino",6,4);
        assertEquals(51, game.getGamePlayer("Paperino").getPosition());
        game.movePlayer("Pippo",4,6);
        assertEquals(61, game.getGamePlayer("Pippo").getPosition());
        game.movePlayer("Pluto",6,6);
        assertEquals(62, game.getGamePlayer("Pluto").getPosition());
        game.movePlayer("Paperino",5,4);
        assertEquals(60, game.getGamePlayer("Paperino").getPosition());
        game.movePlayer("Pippo",2,1);
        assertEquals(62, game.getGamePlayer("Pippo").getPosition());
        assertEquals(61, game.getGamePlayer("Pluto").getPosition());
        game.movePlayer("Pluto",2,3);
        assertEquals(60, game.getGamePlayer("Pluto").getPosition());
        assertEquals(61, game.getGamePlayer("Paperino").getPosition());
        String ret2 = game.movePlayer("Paperino",1,1);
        assertEquals("Paperino rolls 1, 1. Paperino moves from 61 to 63. Paperino Wins!", ret2);
        assertTrue(game.hasWinner());
        assertEquals("Paperino",game.getWinner().getPlayer().getName());
    }

}
