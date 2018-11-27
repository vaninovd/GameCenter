package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestBoardManager2048 {
    private BoardManager2048 boardManager;

    public void setUp() {
        this.boardManager = new BoardManager2048();
    }

    public void tearDown() {
        this.boardManager = new BoardManager2048();
    }

    @Test
    public void testGameWon() {
        setUp();
        boardManager.getBoard().tiles[3][1] = new Tile2048(11);
        assertTrue(boardManager.gameWon());
        tearDown();
    }
}
