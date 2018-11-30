package fall2018.csc2017.slidingtiles;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestTileFC {

    private BoardFC board;

    public void setUp() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        this.board = new BoardFC(tiles);
    }

    public void tearDown() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        this.board = new BoardFC(tiles);
    }

    @Test
    public void testTileShowBlank(){
        setUp();
        int testBlank = R.drawable.block_blank;
        TileFC tile1 = (TileFC) board.tiles[0][0];
        tile1.showBlank();
        assertEquals(testBlank, tile1.background);
        tearDown();
    }

    @Test
    public void testTileShowPicture(){
        setUp();
        int testPicture = R.drawable.fc_dh;
        board.tiles[0][0].background = R.drawable.block_blank;
        TileFC tile1 = (TileFC) board.tiles[0][0];
        tile1.showPicture();
        assertEquals(testPicture, tile1.background);
        tearDown();
    }

    @Test
    public void testIsUp(){
        setUp();
        TileFC tile1 = (TileFC)board.tiles[0][0];
        assertFalse(tile1.isUp());
        tile1.showPicture();
        assertTrue(tile1.isUp());
        tearDown();
    }
}
