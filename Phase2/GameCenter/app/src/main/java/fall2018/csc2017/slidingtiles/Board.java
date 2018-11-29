package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;


/**
 * The Board superclass.
 */
public class Board  extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows in this Board.
     */
    static int NUM_ROWS = 4;

    /**
     * The number of cols in this Board.
     */
    static int NUM_COLS = 4;

    /**
     * The tiles on the board.
     */
    public Tile[][] tiles= new Tile[NUM_COLS][NUM_ROWS];

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
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Return the tile at position.
     * @param position int position of the tile
     * @return tile at position.
     */
    Tile getTile(int position) {
        if (position < 0 || position > 36) {
            position = 0;
        }
        int row = position / BoardFC.NUM_ROWS;
        int col = position % BoardFC.NUM_COLS;
        return tiles[row][col];
    }

    /**
     * String representation of the board.
     * @return string representing the board.
     */
    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Return an iterator for this Board.
     * @return iterator for this Board.
     */
    @NonNull
    public Iterator<Tile> iterator() {
        return new IteratorTile(this.tiles);
    }

    /**
     * Iterator for BoardFC. Iterates over the tiles on the board.
     */
    public class IteratorTile implements Iterator<Tile> {

        /**
         * Row index from which to start iteration.
         */
        private int rowIndex;

        /**
         * Column index from which ot start iteration.
         */
        private int colIndex;

        /**
         * BoardFC of tiles over which to iterate.
         */
        private Tile[][] tiles;

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
         * @param tileGrid a Tile grid over which to iterate.
         */
        IteratorTile(Tile[][] tileGrid) {
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
        public Tile next() {
            Tile toReturn = tiles[rowIndex][colIndex];
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
