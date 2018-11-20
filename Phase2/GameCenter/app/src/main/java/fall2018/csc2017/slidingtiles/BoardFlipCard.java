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
public class BoardFlipCard extends Observable implements Serializable, Iterable<TileFlipCard> {

    /**
     * The number of rows in Board.
     */
    static int NUM_ROWS;

    /**
     * The number of rows.
     */
    static int NUM_COLS;

    /**
     * The tiles on the board in row-major order.
     */
    public TileFlipCard[][] tiles = new TileFlipCard[NUM_ROWS][NUM_COLS];

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    BoardFlipCard(List<TileFlipCard> tiles) {
        Iterator<TileFlipCard> tIterator = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                TileFlipCard tile = tIterator.next();
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
    TileFlipCard getTileFlipCard(int row, int col) {
        return tiles[row][col];
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Return an iterator for this Board.
     *
     * @return iterator for this Board.
     */
    @NonNull
    public Iterator<TileFlipCard> iterator() {
        return new IteratorTile(this.tiles);
    }

    /**
     * Iterator for Board. Iterates over the tiles on the board.
     */
    public class IteratorTile implements Iterator<TileFlipCard> {


        /**
         * Row index from which to start iteration.
         */
        private int rowIndex;

        /**
         * Column index from which ot start iteration.
         */
        private int colIndex;

        /**
         * Board of tiles over which to iterate.
         */
        private TileFlipCard[][] tiles;

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
        IteratorTile(TileFlipCard[][] tileGrid) {
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
        public TileFlipCard next() {
            TileFlipCard toReturn = tiles[rowIndex][colIndex];
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