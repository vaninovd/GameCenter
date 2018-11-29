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
class BoardManagerFC implements Serializable {

    public int size = BoardFC.NUM_COLS;

    /**
     * The boardFC being managed.
     */
    private BoardFC boardFC;


    private int clickNumber = 0;

    /**
     * Total count of moves, including undos.
     */
    private static int numMoves = 0;

    /**
     * The position of first click made
     */

    private int firstClicked;

    private int secondClicked;

    /**
     * Enable clicks.
     */
    private boolean clickEnabled = true;

    /**
     * Manage a default (3 undos allowed) boardFC that has been pre-populated.
     *
     * @param boardFC the boardFC
     */

    BoardManagerFC(BoardFC boardFC) {
        this.boardFC = boardFC;
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
        this.boardFC = new BoardFC(tiles);
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
        BoardManagerFC.numMoves = numMoves;
    }

    /**
     * Return the current boardFC.
     */
    Board getBoardFC() {
        return boardFC;
    }

    /**
     * Return whether the tiles are all facing up, i.e. game is over.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        for (Tile t : this.boardFC) {
            TileFC m = (TileFC) t;
            if (!m.isUp()) {
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
        return !boardFC.getTile(position).isUp();
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
                boardFC.showPicture(firstClicked);
            } else {
                secondClicked = position;
                boardFC.showPicture(secondClicked);
            }
            clickEnabled = false;
            processCLick();

        }
    }

    /**
     * Process the result of this click.
     */
    public void processCLick() {

        if (clickNumber % 2 == 0) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //get tiles at positions
                    TileFC tile1 = boardFC.getTile(firstClicked);
                    TileFC tile2 = boardFC.getTile(secondClicked);
                    if (abs(tile1.getId() - tile2.getId()) != (boardFC.numTiles() / 2)) {
                        boardFC.showBlank(firstClicked);
                        boardFC.showBlank(secondClicked);
                    }
                    clickEnabled = true;
                }

            }, 1000);
        } else {
            clickEnabled = true;
        }
    }

    /**
     * Process an undo command.
     */
    void undoMove() {
        if (clickNumber % 2 == 1) {
            clickNumber++;
            boardFC.getTile(firstClicked).showBlank();
        }
    }

    /**
     * Return the number of Moves made
     */
    public int getSizeMoves(){
        return this.clickNumber;
    }
}