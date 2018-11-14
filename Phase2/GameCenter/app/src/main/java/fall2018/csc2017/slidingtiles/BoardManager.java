package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    public int size = Board.NUM_COLS;

    /**
     * The board being managed.
     */
    private Board board;

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
     * The number of undos made.
     */
    private int undosNum = 0;

    private static int numMoves = 0;

    /**
     * Manage a default (3 undos allowed) board that has been pre-populated.
     *
     * @param board the board
     */

    BoardManager(Board board) {
        this.board = board;
        this.maxUndos = 3;
        this.unlimitedMoves = false;
    }

    /**
     * Manage a new shuffled default (3 undos allowed) board.
     */
    BoardManager() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        Collections.shuffle(tiles);
        this.board = new Board(tiles);
        this.moves = new ArrayList<>();
        this.maxUndos = 3;
        this.unlimitedMoves = false;
    }

    /**
     * Manage a board that has been pre-populated with options to select number of undos.
     *
     * @param board the board
     */

    BoardManager(Board board, boolean unlimited, int maxUndos) {
        this.board = board;
        this.maxUndos = maxUndos;
        this.unlimitedMoves = unlimited;
    }

    /**
     * Manage a new shuffled board with options to select number of undos.
     */
    BoardManager(boolean unlimited, int maxUndos) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        Collections.shuffle(tiles);
        this.board = new Board(tiles);
        this.moves = new ArrayList<>();
        this.maxUndos = maxUndos;
        this.unlimitedMoves = unlimited;
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
        BoardManager.numMoves = numMoves;
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
    boolean puzzleSolved() {
        int index = 1;
        for (Tile t : this.board) {
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

        int row = position / Board.NUM_COLS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
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

        int row = position / Board.NUM_ROWS;
        int col = position % Board.NUM_COLS;
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
        undosNum++;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Return the number of Moves made
     */
    public int getSizeMoves(){
        return this.moves.size();
    }
}