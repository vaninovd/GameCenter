package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void testGetBoard() {
        setUp();
        Board2048 newBoard = new Board2048();
        this.boardManager.board = newBoard;
        assertEquals(this.boardManager.getBoard(), this.boardManager.getBoard());
        tearDown();
    }

    @Test
    public void testMakeMoveLeft() {
        setUp();
        Board2048 newBoard = new Board2048();
        Tile2048[] temp = {new Tile2048(1), new Tile2048(1), new Tile2048(0), new Tile2048(0)};
        newBoard.tiles[0] = temp;
        this.boardManager.board = newBoard;
        this.boardManager.makeMove(1);
        boolean exp = (this.boardManager.board.tiles[0][0].getId() == 2);
        assertTrue(exp);
        tearDown();
    }

    @Test
    public void testMakeMoveUp() {
        setUp();
        Board2048 board = new Board2048();
        board.tiles[0][0] = new Tile2048(1);
        board.tiles[1][0] = new Tile2048(1);
        board.tiles[2][0] = new Tile2048(1);
        board.tiles[3][0] = new Tile2048(1);
        this.boardManager.board = board;
        this.boardManager.makeMove(2);
        boolean exp = (board.tiles[0][0].getId() == 2) &
                (board.tiles[1][0].getId() == 2);
        assertTrue(exp);
        tearDown();
    }

    @Test
    public void testMakeMoveRight() {
        setUp();
        Board2048 newBoard = new Board2048();
        Tile2048[] temp = {new Tile2048(1), new Tile2048(1), new Tile2048(0), new Tile2048(0)};
        newBoard.tiles[0] = temp;
        this.boardManager.board = newBoard;
        this.boardManager.makeMove(3);
        boolean exp = (this.boardManager.board.tiles[0][3].getId() == 2);
        assertTrue(exp);
        tearDown();
    }

    @Test
    public void testMakeMoveDown() {
        setUp();
        Board2048 board = new Board2048();
        board.tiles[0][0] = new Tile2048(1);
        board.tiles[1][0] = new Tile2048(1);
        board.tiles[2][0] = new Tile2048(1);
        board.tiles[3][0] = new Tile2048(1);
        this.boardManager.board = board;
        this.boardManager.makeMove(4);
        boolean exp = (board.tiles[2][0].getId() == 2) &
                (board.tiles[3][0].getId() == 2);
        assertTrue(exp);
        tearDown();
    }

    @Test
    public void testGameOver() {
        setUp();
        assertFalse(this.boardManager.gameOver());
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
        this.boardManager.board.tiles = newTiles;
        assertTrue(this.boardManager.gameOver());
        tearDown();
    }
}
