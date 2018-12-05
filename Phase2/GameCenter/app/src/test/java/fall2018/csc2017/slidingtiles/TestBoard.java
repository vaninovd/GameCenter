package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestBoard {
    private Board board;

    private void setUp(int size) {
        this.board = new Board();
        Board.NUM_COLS = size;
        Board.NUM_ROWS = size;
    }

    @Test
    public void testGetTile() {
        setUp(4);
        this.board.tiles = new Tile[Board.NUM_ROWS][Board.NUM_COLS];
        Tile newTile = new Tile();
        this.board.tiles[0][0] = newTile;
        assertEquals(this.board.getTile(0), newTile);
        assertEquals(this.board.getTile(0, 0), newTile);
        assertEquals(newTile, this.board.getTile(-1));
    }

    @Test
    public void testToString() {
        setUp(4);
        String expected = "Board{" +
                "tiles=" + Arrays.toString(this.board.tiles) +
                '}';
        assertEquals(expected, this.board.toString());
    }

    @Test
    public void testIterator() {
        setUp(4);
        this.board.tiles = new Tile[Board.NUM_ROWS][Board.NUM_COLS];
        int count = 0;
        for (Tile i : this.board) {
            count++;
        }
        assertEquals(16, count);
    }
}
