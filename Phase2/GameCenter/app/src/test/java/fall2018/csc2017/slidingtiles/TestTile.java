package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.List;

public class TestTile {
    private Board board;

    public void setUp() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        board = new Board();
    }

    public void tearDown() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        board = new Board();
    }
}
