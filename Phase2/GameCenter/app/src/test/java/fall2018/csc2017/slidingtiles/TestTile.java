package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TestTile {
    private BoardFC board;

    public void setUp() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        board = new BoardFC(tiles);
    }

    public void tearDown() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        board = new BoardFC(tiles);
    }

    @Test
    public void testGetBackground(){
        setUp();
        Tile tile = board.tiles[0][0];
        assertEquals(2131099745, tile.getBackground());
        tearDown();
    }

    @Test
    public void testGetId(){
        setUp();
        Tile tile = board.tiles[0][0];
        assertEquals(1, tile.getId());
        tearDown();
    }

    @Test
    public void testCompareTo(){
        setUp();
        Tile tile1 = board.tiles[0][0];
        Tile tile2 = board.tiles[0][1];
        int test = tile1.compareTo(tile2);
        assertEquals(1, test);
        tearDown();
    }
}
