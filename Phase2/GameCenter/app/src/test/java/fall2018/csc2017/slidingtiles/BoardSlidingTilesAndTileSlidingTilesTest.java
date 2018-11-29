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
    BoardManagerSlidingTiles boardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<TileSlidingTiles> makeTiles() {
        List<TileSlidingTiles> tiles = new ArrayList<>();
        final int numTiles = BoardSlidingTiles.NUM_ROWS * BoardSlidingTiles.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileSlidingTiles(tileNum + 1, tileNum));
        }

        return tiles;
    }

    /**
     * Make a solved BoardSlidingTiles.
     */
    private void setUpCorrect() {
        List<TileSlidingTiles> tiles = makeTiles();
        BoardSlidingTiles board = new BoardSlidingTiles(tiles);
        boardManager = new BoardManagerSlidingTiles(board);
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
        setUpCorrect();
        assertEquals(true, boardManager.gameWon());
        swapFirstTwoTiles();
        assertEquals(false, boardManager.gameWon());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
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
        setUpCorrect();
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
        setUpCorrect();
        assertEquals(true, boardManager.isValidTap(11));
        assertEquals(true, boardManager.isValidTap(15));
        assertEquals(false, boardManager.isValidTap(10));
    }
}

