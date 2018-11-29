package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardSlidingTilesAndTileSlidingTilesTest {

    /** The board manager for testing. */
    private BoardManagerSlidingTiles boardManager;
    private ArrayList arrayTiles = new ArrayList();

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<TileSlidingTiles> makeTiles(int width) {
        List<TileSlidingTiles> tiles = new ArrayList<>();
        final int numTiles = width * width;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileSlidingTiles(tileNum));
        }
        return tiles;
    }

    /**
     * Make a solved BoardSlidingTiles.
     */
    private void setUpCorrect(int width) {
        Board.NUM_ROWS = width;
        Board.NUM_COLS = width;
        List<TileSlidingTiles> tiles = makeTiles(width);
        BoardSlidingTiles board = new BoardSlidingTiles(tiles);
        boardManager = new BoardManagerSlidingTiles(board);
    }

    /**
     * Update the array of tiles corresponding to the current board
     */
    private void updateArray() {
        arrayTiles = new ArrayList();
        for (Tile tile : boardManager.getBoardST()) {
            arrayTiles.add(tile);
        }
    }

    /**
     * Test whether blankOnOddRow works.
     */
    @Test
    public void testBlankOnOddRow() {
        setUpCorrect(3);
        updateArray();
        assertFalse(boardManager.blankOnOddRow(arrayTiles));
        ((BoardSlidingTiles)boardManager.getBoardST()).swapTiles(1, 0, 2, 2);
        updateArray();
        assertTrue(boardManager.blankOnOddRow(arrayTiles));
        ((BoardSlidingTiles)boardManager.getBoardST()).swapTiles(0, 0, 1, 0);
        updateArray();
        assertFalse(boardManager.blankOnOddRow(arrayTiles));
        setUpCorrect(4);
        updateArray();
        assertTrue(boardManager.blankOnOddRow(arrayTiles));
        ((BoardSlidingTiles)boardManager.getBoardST()).swapTiles(2, 1, 3, 3);
        updateArray();
        assertFalse(boardManager.blankOnOddRow(arrayTiles));
        setUpCorrect(5);
        updateArray();
        assertFalse(boardManager.blankOnOddRow(arrayTiles));
        ((BoardSlidingTiles)boardManager.getBoardST()).swapTiles(1, 1, 4, 4);
        updateArray();
        assertTrue(boardManager.blankOnOddRow(arrayTiles));
    }

    /**
     * Test whether evenNumOfInversions works.
     */
    @Test
    public void testEvenNumOfInversions() {
        // starting position test for 3x3 board
        setUpCorrect(3);
        updateArray();
        assertEquals(true, boardManager.evenNumOfInversions(arrayTiles));
        ((BoardSlidingTiles)boardManager.getBoardST()).swapTiles(0, 0, 0, 1);
        updateArray();
        assertEquals(false, boardManager.evenNumOfInversions(arrayTiles));

        // 4 8 6
        // 1 2
        // 3 7 5
        List<TileSlidingTiles> testTiles = new ArrayList<>();
        testTiles.add(new TileSlidingTiles(3));
        testTiles.add(new TileSlidingTiles(7));
        testTiles.add(new TileSlidingTiles(5));
        testTiles.add(new TileSlidingTiles(0));
        testTiles.add(new TileSlidingTiles(1));
        testTiles.add(new TileSlidingTiles(8));
        testTiles.add(new TileSlidingTiles(2));
        testTiles.add(new TileSlidingTiles(6));
        testTiles.add(new TileSlidingTiles(4));
        assertEquals(true, boardManager.evenNumOfInversions(testTiles));

        // 8 1 2
        //   4 3
        // 7 6 5
        BoardSlidingTiles.NUM_COLS = 3;
        List<TileSlidingTiles> testTiles2 = new ArrayList<>();
        testTiles2.add(new TileSlidingTiles(7));
        testTiles2.add(new TileSlidingTiles(0));
        testTiles2.add(new TileSlidingTiles(1));
        testTiles2.add(new TileSlidingTiles(8));
        testTiles2.add(new TileSlidingTiles(3));
        testTiles2.add(new TileSlidingTiles(2));
        testTiles2.add(new TileSlidingTiles(6));
        testTiles2.add(new TileSlidingTiles(5));
        testTiles2.add(new TileSlidingTiles(4));
        assertEquals(false, boardManager.evenNumOfInversions(testTiles2));

        // 2   7
        // 4 6 8
        // 1 5 3
        List<TileSlidingTiles> testTiles3 = new ArrayList<>();
        testTiles3.add(new TileSlidingTiles(1));
        testTiles3.add(new TileSlidingTiles(8));
        testTiles3.add(new TileSlidingTiles(6));
        testTiles3.add(new TileSlidingTiles(3));
        testTiles3.add(new TileSlidingTiles(5));
        testTiles3.add(new TileSlidingTiles(7));
        testTiles3.add(new TileSlidingTiles(0));
        testTiles3.add(new TileSlidingTiles(4));
        testTiles3.add(new TileSlidingTiles(2));
        assertEquals(false, boardManager.evenNumOfInversions(testTiles3));

        // starting position test for 5x5 board
        setUpCorrect(5);
        updateArray();
        assertEquals(true, boardManager.evenNumOfInversions(arrayTiles));
        ((BoardSlidingTiles)boardManager.getBoardST()).swapTiles(0, 0, 0, 1);
        updateArray();
        assertEquals(false, boardManager.evenNumOfInversions(arrayTiles));

        // 16 22 17 8  23
        // 7  13 20 10 1
        // 9  11 24 2  14
        // 5  19 4  15 21
        // 12    3  6  18
        BoardSlidingTiles.NUM_COLS = 5;
        List<TileSlidingTiles> testTiles4 = new ArrayList<>();
        testTiles4.add(new TileSlidingTiles(15));
        testTiles4.add(new TileSlidingTiles(21));
        testTiles4.add(new TileSlidingTiles(16));
        testTiles4.add(new TileSlidingTiles(7));
        testTiles4.add(new TileSlidingTiles(22));
        testTiles4.add(new TileSlidingTiles(6));
        testTiles4.add(new TileSlidingTiles(12));
        testTiles4.add(new TileSlidingTiles(19));
        testTiles4.add(new TileSlidingTiles(9));
        testTiles4.add(new TileSlidingTiles(0));
        testTiles4.add(new TileSlidingTiles(8));
        testTiles4.add(new TileSlidingTiles(10));
        testTiles4.add(new TileSlidingTiles(23));
        testTiles4.add(new TileSlidingTiles(1));
        testTiles4.add(new TileSlidingTiles(13));
        testTiles4.add(new TileSlidingTiles(4));
        testTiles4.add(new TileSlidingTiles(18));
        testTiles4.add(new TileSlidingTiles(3));
        testTiles4.add(new TileSlidingTiles(14));
        testTiles4.add(new TileSlidingTiles(20));
        testTiles4.add(new TileSlidingTiles(11));
        testTiles4.add(new TileSlidingTiles(24));
        testTiles4.add(new TileSlidingTiles(2));
        testTiles4.add(new TileSlidingTiles(5));
        testTiles4.add(new TileSlidingTiles(17));
        assertEquals(true, boardManager.evenNumOfInversions(testTiles4));

        // 22 16 17 8  23
        // 7  13 20 10 1
        // 9  11 24 2  14
        // 5  19 4  15 21
        // 12    3  6  18
        BoardSlidingTiles.NUM_COLS = 5;
        List<TileSlidingTiles> testTiles5 = new ArrayList<>();
        testTiles5.add(new TileSlidingTiles(21));
        testTiles5.add(new TileSlidingTiles(15));
        testTiles5.add(new TileSlidingTiles(16));
        testTiles5.add(new TileSlidingTiles(7));
        testTiles5.add(new TileSlidingTiles(22));
        testTiles5.add(new TileSlidingTiles(6));
        testTiles5.add(new TileSlidingTiles(12));
        testTiles5.add(new TileSlidingTiles(19));
        testTiles5.add(new TileSlidingTiles(9));
        testTiles5.add(new TileSlidingTiles(0));
        testTiles5.add(new TileSlidingTiles(8));
        testTiles5.add(new TileSlidingTiles(10));
        testTiles5.add(new TileSlidingTiles(23));
        testTiles5.add(new TileSlidingTiles(1));
        testTiles5.add(new TileSlidingTiles(13));
        testTiles5.add(new TileSlidingTiles(4));
        testTiles5.add(new TileSlidingTiles(18));
        testTiles5.add(new TileSlidingTiles(3));
        testTiles5.add(new TileSlidingTiles(14));
        testTiles5.add(new TileSlidingTiles(20));
        testTiles5.add(new TileSlidingTiles(11));
        testTiles5.add(new TileSlidingTiles(24));
        testTiles5.add(new TileSlidingTiles(2));
        testTiles5.add(new TileSlidingTiles(5));
        testTiles5.add(new TileSlidingTiles(17));
        assertEquals(false, boardManager.evenNumOfInversions(testTiles5));
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        ((BoardSlidingTiles)boardManager.getBoard()).swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect(4);
        assertEquals(true, boardManager.gameWon());
        swapFirstTwoTiles();
        assertEquals(false, boardManager.gameWon());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect(4);
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager.getBoard().getTile(0, 1).getId());
        ((BoardSlidingTiles)boardManager.getBoard()).swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect(4);
        assertEquals(15, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        ((BoardSlidingTiles)boardManager.getBoard()).swapTiles(3, 3, 3, 2);
        assertEquals(16, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpCorrect(4);
        assertEquals(true, boardManager.isValidTap(11));
        assertEquals(false, boardManager.isValidTap(15));
        assertEquals(false, boardManager.isValidTap(10));
    }
}

