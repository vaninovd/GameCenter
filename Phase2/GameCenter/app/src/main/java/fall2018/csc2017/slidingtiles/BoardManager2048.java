package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager2048 implements Serializable {

    public int size = Board2048.NUM_COLS;

    /**
     * The board being managed.
     */
    private Board2048 board;

    /**
     * Score count
     */
    private static int score = 0;

    /**
     * Manage a default (3 undos allowed) board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManager2048(Board2048 board) {
        this.board = board;
    }

    BoardManager2048() {
        this.board = new Board2048();
    }

    /**
     * Return the current board.
     */
    Board2048 getBoard() {
        return board;
    }

    // TODO: implement this method
    public void resetScoreAdded() {

    }

    // TODO: implement this method
    public void resetScore() {
    }

    /**
     * Getter for the score
     * @return int
     */
    public static int getScore() {
        return score;
    }

    private void addScore(double pow) {
        score += pow;
    }

    public static void setNumMoves(int numMoves) {
        BoardManager2048.score = numMoves;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean gameWon() {
        Board2048 board = this.getBoard();
        boolean won = false;
        outer:
        for (int row=0; row < Board2048.NUM_ROWS; row++) {
            for (int col=0; col < Board2048.NUM_COLS; col++) {
                if (board.tiles[row][col].getExponent() >= 11) {
                    won = true;
                    break outer;
                }
            }
        }
        return won;
    }

}
