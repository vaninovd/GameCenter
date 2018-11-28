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
public class BoardFC extends Observable implements Serializable, Iterable<TileFC> {

    /**
     * The number of rows in BoardFC.
     */
    static int NUM_ROWS;

    /**
     * The number of rows.
     */
    static int NUM_COLS;

    /**
     * The tiles on the board in row-major order.
     */
    public TileFC[][] tiles = new TileFC[NUM_ROWS][NUM_COLS];

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
    BoardFC(List<TileFC> tiles) {
        System.out.println("BOARD CREATED");
        Iterator<TileFC> tIterator = tiles.iterator();

        for (int row = 0; row != BoardFC.NUM_ROWS; row++) {
            for (int col = 0; col != BoardFC.NUM_COLS; col++) {
                TileFC tile = tIterator.next();
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
    TileFC getTile(int row, int col) {
        return tiles[row][col];
    }

    TileFC getTile(int position) {
        if (position < 0 || position > 36) {
            position = 0;
        }
        int row = position / BoardFC.NUM_ROWS;
        int col = position % BoardFC.NUM_COLS;
        return tiles[row][col];

    }

    @Override
    public String toString() {
        return "BoardFC{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    public void showBlank(int row, int col) {
        tiles[row][col].showBlank();
    }

    public void showBlank(int position) {
        if (position < 0 || position > 36) {
            position = 0;
        }

        int row = position / BoardFC.NUM_ROWS;
        int col = position % BoardFC.NUM_COLS;
        tiles[row][col].showBlank();
    }

    public void showPicture(int row, int col) {
        tiles[row][col].showPicture();
    }

    public void showPicture(int position) {
        if (position < 0 || position > 36) {
            position = 0;
        }

        int row = position / BoardFC.NUM_ROWS;
        int col = position % BoardFC.NUM_COLS;
        tiles[row][col].showPicture();

    }

    /**
     * Return an iterator for this BoardFC.
     *
     * @return iterator for this BoardFC.
     */
    @NonNull
    public Iterator<TileFC> iterator() {
        return new IteratorTile(this.tiles);
    }

    int getBlankCol() {
        return blankCol;
    }

    int getBlankRow() {
        return blankRow;
    }


    /**
     * Iterator for BoardFC. Iterates over the tiles on the board.
     */
    public class IteratorTile implements Iterator<TileFC> {


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
        private TileFC[][] tiles;

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
         * @param tileGrid a TileFC grid over which to iterate.
         */
        IteratorTile(TileFC[][] tileGrid) {
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
        public TileFC next() {
            TileFC toReturn = tiles[rowIndex][colIndex];
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
