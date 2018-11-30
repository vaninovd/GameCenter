package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestBoard2048 {
    private Board2048 board;

    public void setUp() {
        this.board = new Board2048();
        Board2048.resetScore();
        Board2048.resetNumMoves();
        Board2048.resetScoreAdded();
    }

    @Test
    public void testMergeLeft() {
        setUp();
        Tile2048[] temp = {new Tile2048(1),
                new Tile2048(1),
                new Tile2048(0),
                new Tile2048(0)};
        this.board.tiles[0] = temp;
        this.board.mergeLeft();
        boolean exp = (this.board.tiles[0][0].getId() == 2);
        assertTrue(exp);
    }

    @Test
    public void testMergeUp() {
        setUp();
        this.board.tiles[0][0] = new Tile2048(1);
        this.board.tiles[1][0] = new Tile2048(1);
        this.board.tiles[2][0] = new Tile2048(1);
        this.board.tiles[3][0] = new Tile2048(1);
        this.board.mergeUp();
        boolean exp = (this.board.tiles[0][0].getId() == 2) &
                      (this.board.tiles[1][0].getId() == 2);
        assertTrue(exp);
    }

    @Test
    public void testMergeRight() {
        setUp();
        Tile2048[] temp = {new Tile2048(1),
                new Tile2048(1),
                new Tile2048(0),
                new Tile2048(0)};
        this.board.tiles[0] = temp;
        this.board.mergeRight();
        boolean exp = (this.board.tiles[0][3].getId() == 2);
        assertTrue(exp);
    }

    @Test
    public void testMergeDown() {
        setUp();
        this.board.tiles[0][0] = new Tile2048(1);
        this.board.tiles[1][0] = new Tile2048(1);
        this.board.tiles[2][0] = new Tile2048(1);
        this.board.tiles[3][0] = new Tile2048(1);
        this.board.mergeDown();
        boolean exp = (this.board.tiles[2][0].getId() == 2) &
                      (this.board.tiles[3][0].getId() == 2);
        assertTrue(exp);
    }

    @Test
    public void testGetTile() {
        setUp();
        Tile2048 exp = new Tile2048(0);
        board.tiles[0][0] = exp;
        assertEquals(exp, board.getTile(0, 0));
    }

    @Test
    public void testMakeTempCopy() {
        setUp();
        Tile[][] copy = board.makeTempCopy(board.tiles);
        assertNotEquals(copy, this.board);
    }

    @Test
    public void testIsStuck() {
        setUp();
        assertFalse(this.board.isStuck());
        Tile2048[][] newTiles = new Tile2048[Board2048.NUM_ROWS][Board2048.NUM_COLS];
        int count=1;
        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
            for (int col = 0; col != Board2048.NUM_COLS; col++) {
                if (count == 12) {
                    count = 1;
                } else {
                    count++;
                }
                newTiles[row][col] = new Tile2048(count);
            }
        }
        this.board.tiles = newTiles;
        assertTrue(this.board.isStuck());
    }

    @Test
    public void testIterator() {
        setUp();
        int count = 0;
        for (Tile i : this.board) {
            count++;
        }
        assertEquals(16, count);
    }

    @Test
    public void testGetScore() {
        setUp();
        Tile2048[] temp = {new Tile2048(1),
                new Tile2048(1),
                new Tile2048(0),
                new Tile2048(0)};
        Tile2048[] blank = {new Tile2048(0),
                new Tile2048(0),
                new Tile2048(0),
                new Tile2048(0)};
        this.board.tiles[0] = temp;
        this.board.tiles[1] = blank;
        this.board.tiles[2] = blank;
        this.board.tiles[3] = blank;
        this.board.mergeRight();
        assertEquals(4, Board2048.getScore());
    }

    @Test
    public void testResetScore() {
        setUp();
        Tile2048[] temp = {new Tile2048(1),
                new Tile2048(1),
                new Tile2048(0),
                new Tile2048(0)};
        this.board.tiles[0] = temp;
        this.board.mergeRight();
        Board2048.resetScore();
        assertEquals(0, Board2048.getScore());
    }

    @Test
    public void testGetScoreAdded() {
        setUp();
        Tile2048[] temp = {new Tile2048(1),
                new Tile2048(1),
                new Tile2048(0),
                new Tile2048(0)};
        Tile2048[] blank = {new Tile2048(0),
                new Tile2048(0),
                new Tile2048(0),
                new Tile2048(0)};
        this.board.tiles[0] = temp;
        this.board.tiles[1] = blank;
        this.board.tiles[2] = blank;
        this.board.tiles[3] = blank;
        this.board.mergeRight();
        assertEquals(4, Board2048.getScoreAdded());
    }

    @Test
    public void testGetNumMoves() {
        setUp();
        Tile2048[] temp = {new Tile2048(1),
                new Tile2048(1),
                new Tile2048(0),
                new Tile2048(0)};
        this.board.tiles[0] = temp;
        this.board.mergeRight();
        assertEquals(1, Board2048.getNumMoves());
    }

    @Test
    public void testResetNumMoves() {
        setUp();
        Tile2048[] temp = {new Tile2048(1),
                new Tile2048(1),
                new Tile2048(0),
                new Tile2048(0)};
        this.board.tiles[0] = temp;
        this.board.mergeRight();
        Board2048.resetNumMoves();
        assertEquals(0, Board2048.getNumMoves());
        setUp();
    }
}
