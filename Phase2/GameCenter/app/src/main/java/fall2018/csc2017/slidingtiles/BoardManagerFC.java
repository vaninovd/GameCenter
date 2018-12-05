package fall2018.csc2017.slidingtiles;

import android.os.Handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.StrictMath.abs;

/**
 * Manage a boardFC, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManagerFC extends BoardManager implements Serializable {

    /**
     * The number of clicks made by player.
     */
    private int clickNumber = 0;

    /**
     * Total count of moves, including undos.
     */
    private static int numMoves = 0;

    /**
     * The position of first click made.
     */
    private int firstClicked;

    /**
     * The position of the second click made.
     */
    private int secondClicked;

    /**
     * Enable clicks.
     */
    private boolean clickEnabled = true;

    /**
     * Manage a default (3 undos allowed) boardFC that has been pre-populated.
     * @param boardFC the boardFC
     */
    BoardManagerFC(Board boardFC) {
        this.board = boardFC;
    }

    /**
     * Manage a new shuffled boardFC with options to select number of undos.
     */
    BoardManagerFC() {
        List<TileFC> tiles = new ArrayList<>();
        final int numTiles = BoardFC.NUM_ROWS * BoardFC.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFC(tileNum));
        }
        Collections.shuffle(tiles);
        this.board = new BoardFC(tiles);
    }

    /**
     * The number of total moves made including undos.
     */
    public static int getNumMoves() {
        return numMoves;
    }

    /**
     * Reset the number of moves.
     */
    public static void resetNumMoves() {
        numMoves = 0;
    }

    /**
     * Set the number of moves.
     * @param numMoves
     */
    public static void setNumMoves(int numMoves) {
        BoardManagerFC.numMoves = numMoves;
    }

    /**
     * Return the current boardFC.
     */
    Board getBoardFC() {
        return board;
    }

    /**
     * Return whether the tiles are all facing up, i.e. game is over.
     * @return whether the tiles are in row-major order
     */
    boolean gameWon() {
        for (Tile t : this.board) {
            if (!((TileFC)t).getisUp()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return whether the tile is facing up or down.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {
        return !((TileFC)board.getTile(position)).getisUp();
    }

    /**
     * Process a touch at position in the boardFC, flipping tile up.
     *
     * @param position the position
     */
    public void touchMove(int position) {
        if (isValidTap(position) && clickEnabled) {
            clickNumber ++;
            if (clickNumber % 2 == 1) {
                firstClicked = position;
                ((BoardFC)board).showPicture(firstClicked);
            } else {
                secondClicked = position;
                ((BoardFC)board).showPicture(secondClicked);
            }
            clickEnabled = false;
            processCLick();

        }
    }

    /**
     * Process the result of this click. Either flip back last two tiles clicked (if they are different)
     * or leave them up if they are the same.
     */
    public void processCLick() {
        if (clickNumber % 2 == 0) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //get tiles at positions
                    Tile tile1 = board.getTile(firstClicked);
                    Tile tile2 = board.getTile(secondClicked);
                    if (abs(tile1.getId() - tile2.getId()) != (board.numTiles() / 2)) {
                        ((BoardFC)board).showBlank(firstClicked);
                        ((BoardFC)board).showBlank(secondClicked);
                    }
                    clickEnabled = true;
                }

            }, 1000);
        } else {
            clickEnabled = true;
        }
    }

    /**
     * Process an undo command. Can undo only when first card is up. If second card was flipped,
     * no undo is possible until the move is processed.
     */
    void undoMove() {
        if (clickNumber % 2 == 1) {
            clickNumber++;
            ((TileFC)board.getTile(firstClicked)).showBlank();
        }
    }

    /**
     * Return the number of Moves made
     */
    public int getSizeMoves(){
        return this.clickNumber;
    }
}