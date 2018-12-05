package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

public abstract class BoardManager implements Serializable {

    /**
     * Size of the board
     */
    public int size = Board.NUM_COLS;

    /**
     * The board being managed.
     */
    public Board board;

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Method that detects if game is in a winning state
     * @return boolean
     */
    abstract boolean gameWon();
}
