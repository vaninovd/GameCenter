package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestBoard2048 {
    private Board2048 board;

    public void setUp() {
        this.board = new Board2048();
    }

    public void tearDown() {
        this.board = new Board2048();
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
        boolean exp = (this.board.tiles[0][0].getId() == 2) &
                (this.board.tiles[0][1].getId() == 0) &
                (this.board.tiles[0][2].getId() == 0) &
                (this.board.tiles[0][3].getId() == 0);
        assertTrue(exp);
        tearDown();
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
                (this.board.tiles[1][0].getId() == 2) &
                (this.board.tiles[2][0].getId() == 0) &
                (this.board.tiles[3][0].getId() == 0);
        assertTrue(exp);
        tearDown();
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
        boolean exp = (this.board.tiles[0][0].getId() == 0) &
                (this.board.tiles[0][1].getId() == 0) &
                (this.board.tiles[0][2].getId() == 0) &
                (this.board.tiles[0][3].getId() == 2);
        assertTrue(exp);
        tearDown();
    }

    @Test
    public void testMergeDown() {
        setUp();
        this.board.tiles[0][0] = new Tile2048(1);
        this.board.tiles[1][0] = new Tile2048(1);
        this.board.tiles[2][0] = new Tile2048(1);
        this.board.tiles[3][0] = new Tile2048(1);
        this.board.mergeUp();
        boolean exp = (this.board.tiles[0][0].getId() == 0) &
                (this.board.tiles[1][0].getId() == 0) &
                (this.board.tiles[2][0].getId() == 2) &
                (this.board.tiles[3][0].getId() == 2);
        assertTrue(exp);
        tearDown();
    }

    @Test
    public void testGetTile() {
        setUp();
        Tile2048 exp = new Tile2048(0);
        board.tiles[0][0] = exp;
        assertEquals(exp, board.getTile(0, 0));
        tearDown();
    }

    @Test
    public void testMakeTempCopy() {
        setUp();
        Tile2048[][] copy = board.makeTempCopy(board.tiles);
        assertNotEquals(copy, this.board);
        tearDown();
    }

    @Test
    public void testPushLeft() {}

    @Test
    public void testPushUp() {}

    @Test
    public void testPushRight() {}

    @Test
    public void testPushDown() {}

    @Test
    public void testSpawnTile() {
        setUp();
        boolean exp = false;
        int count = 0;
        outer:
        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
            for (int col = 0; col != Board2048.NUM_COLS; col++) {
                if ((board.tiles[row][col].getId() != 0) & count == 2) {
                    exp = true;
                    break outer;
                } else {
                    count++;
                }
            }
        }
        assertTrue(exp);
        tearDown();
    }

    @Test
    public void testIsSpawnable() {}

//    @Test
//    public void testGetEmptySpots() {
//        setUp();
//        int[] expSpot = {2, 2};
//        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
//            for (int col = 0; col != Board2048.NUM_COLS; col++) {
//                if (row != 2 & col != 2) {
//                    board.tiles[row][col] = new Tile2048(1);
//                }
//            }
//        }
//        int[] act = board.getEmptySpots()[0];
//        assertArrayEquals(expSpot, act);
//        tearDown();
//    }

    @Test
    public void testHasHoles() {}

    @Test
    public void testIsStuck() {}

    @Test
    public void testIsStuckHoriontal() {}

    @Test
    public void testIsStuckVertical() {}

    @Test
    public void testIterator() {}
}
