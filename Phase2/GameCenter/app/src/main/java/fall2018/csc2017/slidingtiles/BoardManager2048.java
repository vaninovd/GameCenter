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
     * Constructor for BoardManager class
     */
    BoardManager2048() {
        this.board = new Board2048();
    }

    /**
     * Return the current board.
     */
    Board2048 getBoard() {
        return board;
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
                if (board.tiles[row][col].getId() >= 11) {
                    won = true;
                    break outer;
                }
            }
        }
        return won;
    }

    /**
     * Indicates whether the current state of the game is over
     * @return boolean
     */
    public boolean gameOver() {
        return this.board.isStuck();
    }

    /**
     * Executes the appropriate move to swiping motion detected
     * @param direction direction of swipe
     */
    public void makeMove(int direction){
        switch(direction) {
            case 1:
                this.board.mergeLeft();
                break;
            case 2:
                this.board.mergeUp();
                break;
            case 3:
                this.board.mergeRight();
                break;
            case 4:
                this.board.mergeDown();
                break;
        }
    }
}
