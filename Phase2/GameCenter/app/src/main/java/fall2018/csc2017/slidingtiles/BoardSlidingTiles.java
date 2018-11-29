package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class BoardSlidingTiles extends Board implements Serializable, Iterable<Tile> {

    /**
     * Row index of blank tile.
     */
    private int blankRow;

    /**
     * Column index of blank tile.
     */
    private int blankCol;

    /**
     * Return the row position of the blank column.
     * @return int row position of blank column
     */
    public int getBlankRow() {
        return blankRow;
    }

    /**
     * Return the col position of the blank column.
     * @return int col position of blank column
     */

    public int getBlankCol() {
        return blankCol;
    }

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    BoardSlidingTiles(List<TileSlidingTiles> tiles) {
        Iterator<TileSlidingTiles> tIterator = tiles.iterator();
        for (int row = 0; row != BoardSlidingTiles.NUM_ROWS; row++) {
            for (int col = 0; col != BoardSlidingTiles.NUM_COLS; col++) {
                TileSlidingTiles tile = tIterator.next();
                //if detected blank tile, set blank tile coordinates
                if (tile.getId() == this.numTiles()) {
                    blankRow = row;
                    blankCol = col;
                }
                this.tiles[row][col] = tile;
            }
        }
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2).
     * Precondition: TileSlidingTiles at (row1, col1) is the blank tile.
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        TileSlidingTiles tempTile = (TileSlidingTiles) tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tempTile;
        blankRow = row2;
        blankCol = col2;
        setChanged();
        notifyObservers();
    }

    /**
     * Return an iterator for this BoardFC.
     *
     * @return iterator for this BoardFC.
     */
    @NonNull
    public Iterator<Tile> iterator() {
        return new IteratorTile(this.tiles);
    }

}
