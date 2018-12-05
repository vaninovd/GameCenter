package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManagerSlidingTiles extends BoardManager implements Serializable {

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
        if (BoardSlidingTiles.NUM_COLS % 2 == 1) {
            checkSolvableForOddWidth(tiles);
        } else {
            checkSolvableForEvenWidth(tiles);
        }
        this.board = new BoardSlidingTiles(tiles);
        this.moves = new ArrayList<>();
        this.maxUndos = maxUndos;
        this.unlimitedMoves = unlimited;
    }

    /**
     * Return the solvable order of the tiles for a grid of odd width.
     * Source: http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     *
     * @param tiles the tiles
     */
    private void checkSolvableForOddWidth(List<TileSlidingTiles> tiles) {
        while (!evenNumOfInversions(tiles)) {
            Collections.shuffle(tiles);
        }
    }

    /**
     * Return the solvable order of the tiles for a grid of even width.
     * Source: http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     *
     * @param tiles the tiles
     */
    private void checkSolvableForEvenWidth(List<TileSlidingTiles> tiles) {
        while (!checkSolvableForEvenWidthHelper(tiles)) {
            Collections.shuffle(tiles);
        }
    }

    /**
     * Return true if the number of inversions is even, return false otherwise.
     *
     * @param tiles the tiles
     * @return boolean
     */
    public boolean evenNumOfInversions(List<TileSlidingTiles> tiles) {
        int inversions = 0;
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                if (tiles.get(i).getBackground() != R.drawable.tile_def &&
                        tiles.get(j).getBackground() != R.drawable.tile_def) {
                    if (tiles.get(i).getId() > tiles.get(j).getId())
                        inversions++;
                }
            }
        }
        return inversions % 2 != 1;
    }

    /**
     * Helper function for the checkSolvableForEvenWidth method to check if the
     * invariant (#inversions even) == (blank on odd row) holds for the game to be solved.
     * #taken from the http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     *
     * @param tiles the tiles
     * @return boolean
     */
    public boolean checkSolvableForEvenWidthHelper(List<TileSlidingTiles> tiles) {
        boolean evenInversion = evenNumOfInversions(tiles);
        boolean blankOddRow = blankOnOddRow(tiles);
        return evenInversion == blankOddRow;
    }

    /**
     * Return whether the blank tile is on odd row.
     *
     * @param tiles the tiles
     * @return boolean
     */
    public boolean blankOnOddRow(List<TileSlidingTiles> tiles) {
        int i = 0;
        int blankId = Board.NUM_ROWS * Board.NUM_ROWS;
        for (TileSlidingTiles tile : tiles) {
            if (tile.getId() == blankId)
                break;
            i++;
        }
        return ((i / Board.NUM_ROWS) % 2 == 1);
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
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean gameWon() {
        int index = 1;
        for (fall2018.csc2017.slidingtiles.Tile t : this.board) {
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
        TileSlidingTiles above = row == 0 ? null : (TileSlidingTiles) board.getTile(row - 1, col);
        TileSlidingTiles below = row == BoardSlidingTiles.NUM_ROWS - 1 ? null : (TileSlidingTiles) board.getTile(row + 1, col);
        TileSlidingTiles left = col == 0 ? null : (TileSlidingTiles) board.getTile(row, col - 1);
        TileSlidingTiles right = col == BoardSlidingTiles.NUM_COLS - 1 ? null : (TileSlidingTiles) board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Return the current board.
     */
    Board getBoardST() {
        return (BoardSlidingTiles) this.board;
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
            this.moves.add(((BoardSlidingTiles) this.board).getBlankRow());
            this.moves.add(((BoardSlidingTiles) this.board).getBlankCol());
            ((BoardSlidingTiles) this.board).swapTiles(((BoardSlidingTiles) this.board).getBlankRow(),
                    ((BoardSlidingTiles) this.board).getBlankCol(), row, col);
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
            ((BoardSlidingTiles) this.board).swapTiles(((BoardSlidingTiles) this.board).getBlankRow(),
                    ((BoardSlidingTiles) this.board).getBlankCol(),
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
    public int getSizeMoves() {
        return this.moves.size();
    }
}