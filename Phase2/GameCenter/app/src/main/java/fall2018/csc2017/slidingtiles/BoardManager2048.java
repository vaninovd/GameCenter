package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager2048 extends BoardManager implements Serializable {

    /**
     * Constructor for BoardManager class
     */
    BoardManager2048() {
        this.board = new Board2048();
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean gameWon() {
        Board board = this.getBoard();
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
        return ((Board2048)this.board).isStuck();
    }

    /**
     * Executes the appropriate move to swiping motion detected
     * @param direction direction of swipe
     */
    public void makeMove(int direction){
        switch(direction) {
            case 1:
                ((Board2048)this.board).mergeLeft();
                break;
            case 2:
                ((Board2048)this.board).mergeUp();
                break;
            case 3:
                ((Board2048)this.board).mergeRight();
                break;
            case 4:
                ((Board2048)this.board).mergeDown();
                break;
        }
    }
}
