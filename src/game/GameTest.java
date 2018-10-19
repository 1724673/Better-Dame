package game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        this.game = new Game();
    }

    @Test
    public void initialPlayerListIsEmpty() {
        assertTrue(this.game.getPlayerList().isEmpty());
    }

    @Test
    public void canAddWhitePlayer() {
        this.game.addPlayer("White Player", Colors.WHITE.toString());
        assertTrue(!this.game.getPlayerList().isEmpty());
        assertEquals("White Player", this.game.getPlayerList().get(0).getName());
    }

    @Test
    public void canAddBlackPlayer() {
        this.game.addPlayer("Black Player", Colors.BLACK.toString());
        assertTrue(!this.game.getPlayerList().isEmpty());
        assertEquals("Black Player", this.game.getPlayerList().get(0).getName());
    }

    @Test
    public void canAddBothPlayers() {
        this.game.addPlayer("White Player", Colors.WHITE.toString());
        this.game.addPlayer("Black Player", Colors.BLACK.toString());

        assertEquals(2, this.game.getPlayerList().size());
    }

    @Test
    public void startGameWorksAndSetsCorrectStartingPlayer() {
        this.game.addPlayer("White Player", Colors.WHITE.toString());
        this.game.addPlayer("Black Player", Colors.BLACK.toString());
        this.game.start();

        assertEquals("White Player", this.game.getActivePlayer());
    }

}