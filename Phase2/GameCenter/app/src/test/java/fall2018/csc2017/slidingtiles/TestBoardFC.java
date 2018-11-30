package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBoardFC {
    private BoardFC board;

    public void setUp() {
        board.NUM_COLS = 4;
        board.NUM_ROWS = 4;
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = board.NUM_ROWS * board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        this.board = new BoardFC(tiles);
    }

    public void tearDown() {
        board.NUM_COLS = 4;
        board.NUM_ROWS = 4;
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = board.NUM_ROWS * board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        this.board = new BoardFC(tiles);
    }

    @Test
    public void testBoardShowBlank(){
        setUp();
        int testBlank = R.drawable.block_blank;
        board.showBlank(0);
        assertEquals(testBlank, board.tiles[0][0].background);
        setUp();
    }

    @Test
    public void testBoardShowPicture(){
        setUp();
        int testPicture = R.drawable.fc_dh;
        board.tiles[0][0].background = R.drawable.block_blank;
        board.showPicture(0);
        assertEquals(testPicture, board.tiles[0][0].background);
        setUp();
    }

    @Test
    public void testBoardIterator(){
        setUp();
        int count = 0;
        for (Tile i : this.board) {
            count++;
        }
        assertEquals(16, count);
        setUp();
    }
}