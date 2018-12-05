package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestTileFC {

    private BoardFC board;
    private BoardFC board6x6;

    public void setUp4X4() {
        List<TileFC> tiles = new ArrayList<>();

        final int numTiles = board.NUM_ROWS * board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }

        this.board = new BoardFC(tiles);
    }

    public void setUp6X6() {
        List<TileFC> tiles6x6 = new ArrayList<>();
        this.board6x6.NUM_COLS = 6;
        this.board6x6.NUM_ROWS = 6;
        final int numTiles6x6 = board6x6.NUM_ROWS * board6x6.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles6x6; tileNum++) {
            tiles6x6.add(new TileFC(tileNum));
        }
        this.board6x6 = new BoardFC(tiles6x6);
    }

    public void tearDown4X4() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        this.board = new BoardFC(tiles);
        BoardFC.NUM_COLS = 4;
        BoardFC.NUM_ROWS = 4;
    }

    public void tearDown6X6() {
        this.board6x6.NUM_COLS = 6;
        this.board6x6.NUM_ROWS = 6;
        List<TileFC> tiles6x6 = new ArrayList<>();
        final int numTiles6x6 = board6x6.NUM_ROWS * board6x6.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles6x6; tileNum++) {
            tiles6x6.add(new TileFC(tileNum));
        }
        this.board6x6 = new BoardFC(tiles6x6);
        BoardFC.NUM_COLS = 4;
        BoardFC.NUM_ROWS = 4;
    }

    @Test
    public void testTileShowBlank() {
        setUp4X4();
        setUp6X6();
        int testBlank = R.drawable.block_blank;
        TileFC tile1 = (TileFC) board.tiles[0][0];
        TileFC tile2 = (TileFC) board6x6.tiles[0][1];
        tile2.showBlank();
        tile1.showBlank();
        assertEquals(testBlank, tile1.background);
        assertEquals(testBlank, tile2.background);
        tearDown4X4();
        tearDown6X6();
    }

    @Test
    public void testTileShowPicture() {
        setUp4X4();
        setUp6X6();
        int testPicture = R.drawable.fc_dh;
        int testPicture1 = R.drawable.fc_bg;
        board.tiles[0][0].background = R.drawable.block_blank;
        TileFC tile1 = (TileFC) board.tiles[0][0];
        TileFC tile2 = (TileFC) board6x6.tiles[0][1];
        tile1.showPicture();
        tile2.showPicture();
        assertEquals(testPicture, tile1.background);
        assertEquals(testPicture1, tile2.background);
        tearDown4X4();
        tearDown6X6();
    }

    @Test
    public void testIsUp() {
        setUp4X4();
        setUp6X6();
        TileFC tile1 = (TileFC) board.tiles[1][2];
        TileFC tile2 = (TileFC) board6x6.tiles[5][5];
        assertFalse(tile2.getisUp());
        assertFalse(tile1.getisUp());
        tile1.showPicture();
        tile2.showPicture();
        assertTrue(tile1.getisUp());
        assertTrue(tile2.getisUp());
        tearDown4X4();
        tearDown6X6();
    }
}
