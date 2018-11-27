package fall2018.csc2017.slidingtiles;

import android.service.quicksettings.Tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManagerSlidingTiles implements Serializable {

    public int size = BoardSlidingTiles.NUM_COLS;

    /**
     * The board being managed.
     */
    private BoardSlidingTiles board;

    /**
     * The sequence of moves made.
     */
    private ArrayList<Integer> moves;

    /**
     * Indicates whether unlimited moves are allowed.
     */
    private boolean unlimitedMoves;

    /**
     * The number of undo moves allowed
     */
    private int maxUndos;

    /**
     * The number of undos used.
     */
    private int undosLeft = 0;

    /**
     * Total count of moves, including undos.
     */
    private static int numMoves = 0;

    /**
     * Manage a default (3 undos allowed) board that has been pre-populated.
     *
     * @param board the board
     */

    BoardManagerSlidingTiles(BoardSlidingTiles board) {
        this.board = board;
        this.maxUndos = 3;
        this.unlimitedMoves = false;
    }

    /**
     * Manage a new shuffled board with options to select number of undos.
     */
    BoardManagerSlidingTiles(boolean unlimited, int maxUndos) {
        List<TileSlidingTiles> tiles = new ArrayList<>();
        final int numTiles = BoardSlidingTiles.NUM_ROWS * BoardSlidingTiles.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileSlidingTiles(tileNum));
        }
        Collections.shuffle(tiles);
        this.board = new BoardSlidingTiles(tiles);
        this.moves = new ArrayList<>();
        this.maxUndos = maxUndos;
        this.unlimitedMoves = unlimited;
    }

    /**
     * Return whether the blank tile is on odd row.
     *
     * @param tiles the tiles
     * @return boolean
     */
    private boolean blankOnOddRow(List<TileSlidingTiles> tiles) {
        int i = 0;
        for (TileSlidingTiles tile : tiles) {
            if (tile.getId() == 16)
                break;
            i++;
        }
        return ((i / 4) % 2 == 1);
    }

    /**
     * The number of total moves made including undos.
     */
    public static int getNumMoves() {
        return numMoves;
    }

    public static void resetNumMoves() {
        numMoves = 0;
    }

    public static void setNumMoves(int numMoves) {
        BoardManagerSlidingTiles.numMoves = numMoves;
    }


    /**
     * Return the current board.
     */
    BoardSlidingTiles getBoard() {
        return board;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        int index = 1;
        for (TileSlidingTiles t : this.board) {
            if (t.getId() != index) {
                return false;
            }
            index++;
        }
        return true;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / BoardSlidingTiles.NUM_COLS;
        int col = position % BoardSlidingTiles.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        TileSlidingTiles above = row == 0 ? null : board.getTile(row - 1, col);
        TileSlidingTiles below = row == BoardSlidingTiles.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        TileSlidingTiles left = col == 0 ? null : board.getTile(row, col - 1);
        TileSlidingTiles right = col == BoardSlidingTiles.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / BoardSlidingTiles.NUM_ROWS;
        int col = position % BoardSlidingTiles.NUM_COLS;
        if (this.isValidTap(position)) {
            //add the position of the blank tile for undo move
            this.moves.add(this.board.getBlankRow());
            this.moves.add(this.board.getBlankCol());
            this.board.swapTiles(this.board.getBlankRow(), this.board.getBlankCol(), row, col);
            if (this.undosLeft < maxUndos) {
                this.undosLeft++;
            }
        }
    }


    /**
     * Process an undo command.
     */
    void undoMove() {
        setNumMoves(getNumMoves() + 1);
        if (unlimitedMoves || undosLeft > 0) {
            int colUndo = this.moves.remove(this.moves.size() - 1);
            int rowUndo = this.moves.remove(this.moves.size() - 1);
            this.board.swapTiles(this.board.getBlankRow(), this.board.getBlankCol(),
                    rowUndo, colUndo);
            if (!unlimitedMoves) {
                undosLeft--;
            }
        } else {
            //throw out of bounds exception as if we were trying to remove element from empty list
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Return the number of Moves made
     */
    public int getSizeMoves(){
        return this.moves.size();
    }
}