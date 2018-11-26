package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.NoSuchElementException;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class BoardSlidingTiles extends Observable implements Serializable, Iterable<TileSlidingTiles> {

    /**
     * The number of rows in BoardSlidingTiles.
     */
    static int NUM_ROWS;

    /**
     * The number of rows.
     */
    static int NUM_COLS;

    /**
     * The tiles on the board in row-major order.
     */
    public TileSlidingTiles[][] tiles = new TileSlidingTiles[NUM_ROWS][NUM_COLS];

    /**
     * Row index of blank tile.
     */
    private int blankRow;

    /**
     * Column index of blank tile.
     */
    private int blankCol;

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
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_COLS * NUM_ROWS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    TileSlidingTiles getTile(int row, int col) {
        return tiles[row][col];
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
        TileSlidingTiles tempTile = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tempTile;
        //update blank tile coordinates
        blankRow = row2;
        blankCol = col2;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "BoardSlidingTiles{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Return an iterator for this BoardSlidingTiles.
     *
     * @return iterator for this BoardSlidingTiles.
     */
    @NonNull
    public Iterator<TileSlidingTiles> iterator() {
        return new IteratorTile(this.tiles);
    }

    int getBlankCol() {
        return blankCol;
    }

    int getBlankRow() {
        return blankRow;
    }


    /**
     * Iterator for BoardSlidingTiles. Iterates over the tiles on the board.
     */
    public class IteratorTile implements Iterator<TileSlidingTiles> {


        /**
         * Row index from which to start iteration.
         */
        private int rowIndex;

        /**
         * Column index from which ot start iteration.
         */
        private int colIndex;

        /**
         * BoardSlidingTiles of tiles over which to iterate.
         */
        private TileSlidingTiles[][] tiles;

        /**
         * Total number of tiles returned so far.
         */
        private int totalSoFar;

        /**
         * The size of the board.
         */
        private int size;

        /**
         * A new iterator.
         *
         * @param tileGrid a TileSlidingTiles grid over which to iterate.
         */
        IteratorTile(TileSlidingTiles[][] tileGrid) {
            this.tiles = tileGrid;
            this.rowIndex = 0;
            this.colIndex = 0;
            this.totalSoFar = 0;
            this.size = tileGrid.length * tileGrid[0].length;
        }

        @Override
        public boolean hasNext() {
            return totalSoFar < size;
        }

        @Override
        public TileSlidingTiles next() {
            TileSlidingTiles toReturn = tiles[rowIndex][colIndex];
            if (totalSoFar < size) {
                if (colIndex == NUM_COLS - 1) {
                    colIndex = 0;
                    rowIndex++;
                } else {
                    colIndex++;
                }
                totalSoFar ++;
            } else if (totalSoFar == size) {
                totalSoFar ++;
            } else {
                throw new NoSuchElementException();
            }
            return toReturn;
        }
    }
}
