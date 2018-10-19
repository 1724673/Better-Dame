package game.board;

import game.Colors;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BoardTest {

    private Board board;

    @org.junit.Before
    public void setUp() {
        this.board = new Board();
    }

    @Test
    public void fieldIsNotNullAfterConstructor() {
        assertNotEquals(null, this.board.getFields());
    }

    @Test
    public void fieldIs8by8() {
        assertEquals(64, this.board.getFields().size());
    }

    @Test
    public void fieldBuilderWorks() {
        // Start of the rows
        assertEquals("A1", this.board.getFields().get("A1").getId());
        assertEquals("B1", this.board.getFields().get("B1").getId());
        assertEquals("C1", this.board.getFields().get("C1").getId());
        assertEquals("D1", this.board.getFields().get("D1").getId());
        assertEquals("E1", this.board.getFields().get("E1").getId());
        assertEquals("F1", this.board.getFields().get("F1").getId());
        assertEquals("G1", this.board.getFields().get("G1").getId());
        assertEquals("H1", this.board.getFields().get("H1").getId());

        // End of the rows
        assertEquals("A8", this.board.getFields().get("A8").getId());
        assertEquals("B8", this.board.getFields().get("B8").getId());
        assertEquals("C8", this.board.getFields().get("C8").getId());
        assertEquals("D8", this.board.getFields().get("D8").getId());
        assertEquals("E8", this.board.getFields().get("E8").getId());
        assertEquals("F8", this.board.getFields().get("F8").getId());
        assertEquals("G8", this.board.getFields().get("G8").getId());
        assertEquals("H8", this.board.getFields().get("H8").getId());
    }

    @Test
    public void fieldBuilderSetsWhiteCorrectly() {
        // White rows - start
        assertEquals(Colors.WHITE, this.board.getFields().get("A1").getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("C1").getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("E1").getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("G1").getColor());

        // White rows - end
        assertEquals(Colors.WHITE, this.board.getFields().get("A7").getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("C7").getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("E7").getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("G7").getColor());
    }

    @Test
    public void fieldBuilderSetsBlackCorrectly() {
        // Black rows - start
        assertEquals(Colors.BLACK, this.board.getFields().get("B1").getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("D1").getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("F1").getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("H1").getColor());

        // White rows - end
        assertEquals(Colors.BLACK, this.board.getFields().get("B7").getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("D7").getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("F7").getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("H7").getColor());
    }

    @Test
    public void initializeFiguresWorksForBlackFigures() {
        this.board.initializeStartFigures();
        // Row A
        assertEquals(Colors.BLACK, this.board.getFields().get("A2").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("A4").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("A6").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("A8").getCurrentFigure().getColor());

        // Row B
        assertEquals(Colors.BLACK, this.board.getFields().get("B1").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("B3").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("B5").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("B7").getCurrentFigure().getColor());

        // Row C
        assertEquals(Colors.BLACK, this.board.getFields().get("C2").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("C4").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("C6").getCurrentFigure().getColor());
        assertEquals(Colors.BLACK, this.board.getFields().get("C8").getCurrentFigure().getColor());
    }

    @Test
    public void initializeFiguresWorksForWhiteFigures() {
        this.board.initializeStartFigures();
        // Row F
        assertEquals(Colors.WHITE, this.board.getFields().get("F1").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("F3").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("F5").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("F7").getCurrentFigure().getColor());

        // Row G
        assertEquals(Colors.WHITE, this.board.getFields().get("G2").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("G4").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("G6").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("G8").getCurrentFigure().getColor());

        // Row H
        assertEquals(Colors.WHITE, this.board.getFields().get("H1").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("H3").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("H5").getCurrentFigure().getColor());
        assertEquals(Colors.WHITE, this.board.getFields().get("H7").getCurrentFigure().getColor());
    }

    @Ignore
    @Test
    public void printBoardWithoutFigures() {
        System.out.println(this.board.printBoard());
    }

    @Ignore
    @Test
    public void printBoardWithFigures() {
        this.board.initializeStartFigures();
        System.out.println(this.board.printBoard());
    }

    @Test
    public void settingASingleFigureWorks() {
        this.board.setSingleFigureOnBoard('A', 2, Colors.BLACK);
        assertEquals(Colors.BLACK, this.board.getFields().get("A2").getCurrentFigure().getColor());
    }

}