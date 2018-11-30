package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestBoardManagerFC {
    private BoardManagerFC boardManager;
    private BoardFC board;

    public void setUp() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        board = new BoardFC(tiles);
        boardManager.board = board;

    }

    public void tearDown() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        board = new BoardFC(tiles);
        boardManager.board = board;
    }

    @Test
    public void testGetNumMoves(){

    }

    @Test
    public void testResetNumMoves(){

    }
    @Test
    public void testSetNumMoves(){

    }
    @Test
    public void testGetBoardFC(){

    }
    @Test
    public void testGameWon(){

    }
    @Test
    public void testIsValidTap(){

    }
    @Test
    public void testTouchMove(){

    }
    @Test
    public void testProcessCLick(){

    }
    @Test
    public void testUndoMove(){

    }
    @Test
    public void testGetSizeMoves(){

    }
}
