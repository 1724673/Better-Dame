package game;

import game.player.Player;
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

    @Test
    public void evaluateBoardStatusAndPossibleMoves() {

        this.game.getBoard().initializeStartFigures();

        this.game.setActivePlayer(new Player("White", Colors.WHITE));

        this.game.updateCurrentPossibleMoves();

        for (String s : this.game.getPossibleMoves()) {
            System.out.println(s);
        }
    }

    @Test
    public void possibleMovesFindsStrikePossibilitiesAndGivesCorrectFieldIDs() {
        this.game.getBoard().setSingleFigureOnBoard('F', 3, Colors.WHITE);
        this.game.getBoard().setSingleFigureOnBoard('E', 4, Colors.BLACK);
        this.game.getBoard().setSingleFigureOnBoard('E', 2, Colors.BLACK);
        this.game.getBoard().setSingleFigureOnBoard('E', 4, Colors.BLACK);

        this.game.addPlayer("White Player", Colors.WHITE.toString());
        this.game.setActivePlayer(this.game.getPlayerList().get(0));

        this.game.updateCurrentPossibleMoves();

        for (String s : this.game.getPossibleMoves()) {
            System.out.println(s);
        }

    }

    @Test
    public void possibleMovesFindsLongJump() {
        this.game.getBoard().setSingleFigureOnBoard('H', 1, Colors.WHITE);
        this.game.getBoard().setSingleFigureOnBoard('G', 2, Colors.BLACK);
        this.game.getBoard().setSingleFigureOnBoard('E', 4, Colors.BLACK);
        this.game.getBoard().setSingleFigureOnBoard('C', 6, Colors.BLACK);

        this.game.addPlayer("White Player", Colors.WHITE.toString());
        this.game.setActivePlayer(this.game.getPlayerList().get(0));

        this.game.updateCurrentPossibleMoves();

        for (String s : this.game.getPossibleMoves()) {
            System.out.println(s);
        }

    }

}