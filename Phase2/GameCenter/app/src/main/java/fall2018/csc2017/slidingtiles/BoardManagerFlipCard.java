package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManagerFlipCard implements Serializable {

    public int size = Board.NUM_COLS;

    /**
     * The board being managed.
     */
    private BoardFlipCard board;

    /**
     * The id of the first tile facing up temporarily
     */
    private TileFlipCard tempTile1;

    /**
     * The id of the second tile facing up temporarily
     */
    private TileFlipCard tempTile2;

    /**
     * Manage a default (3 undos allowed) board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManagerFlipCard(BoardFlipCard board) {
        this.board = board;
    }

    /**
     * Manage a new shuffled board with options to select number of undos.
     */
    BoardManagerFlipCard() {
        List<TileFlipCard> tiles = new ArrayList<>();
        final int numTiles = BoardFlipCard.NUM_ROWS * BoardFlipCard.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileFlipCard(tileNum));
            tiles.add(new TileFlipCard(tileNum));
        }
        Collections.shuffle(tiles);
        this.board = new BoardFlipCard(tiles);
    }

    /**
     * Return the current board.
     */
    BoardFlipCard getBoard() {
        return board;
    }

    /**
     * Return whether all tiles are facing up.
     *
     * @return whether the tiles are facing up.
     */
    boolean puzzleSolved() {
        int index = 1;
        for (TileFlipCard t : this.board) {
            if (!t.isUp()) {
                return false;
            }
            index++;
        }
        return true;
    }

    /**
     * Return whether tap at position is valid.
     *
     * @param position the position of the tap
     *
     * @return boolean whether the tap is valid
     */
    public boolean isValidTap(int position) {
        int row = position / Board.NUM_ROWS;
        int col = position % Board.NUM_COLS;
        TileFlipCard clickedTile = this.board.getTileFlipCard(row, col);
        return !clickedTile.isUp();
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {
        if (isValidTap(position)) {
            int row = position / Board.NUM_ROWS;
            int col = position % Board.NUM_COLS;
            TileFlipCard clickedTile = this.board.getTileFlipCard(row, col);
            //if temp tile 1 is not up, i.e. if no tile was clicked, flip the tile clicked
            if (tempTile1 == null) {
                //flip clicked tile and assign tile that was just clicked as tempTile1
                clickedTile.flipUp();
                tempTile1 = clickedTile;
                //if tempTile2 is not up, i.e. if one tile is clicked and is up
            } else if (tempTile2 == null) {
                //flip the clicked tile and assign it to tempTile2
                clickedTile.flipUp();
                tempTile2 = clickedTile;
                analyzeMove();
            }
        }
    }

    /**
     * Analyze the move made. If tempTile1 matches tempTile2, leave the tiles up, make both temp
     * tiles null. If temporary tiles do not match, flip both tiles back, make both temp tiles null.
     */
    public void analyzeMove() {
        if (tempTile1.getPicture() == tempTile2.getPicture()) {
        } else {
            tempTile1.flipDown();
            tempTile2.flipDown();
        }
        tempTile2 = tempTile1= null;
    }

}