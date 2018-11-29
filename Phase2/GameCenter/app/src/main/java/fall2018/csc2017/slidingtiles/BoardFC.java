package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * The Flip Card board.
 */
public class BoardFC extends Board implements Serializable, Iterable<Tile> {

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    BoardFC(List<TileFC> tiles) {
        System.out.println(this.tiles.length);
        Iterator<TileFC> tIterator = tiles.iterator();
        for (int row = 0; row != BoardFC.NUM_ROWS; row++) {
            for (int col = 0; col != BoardFC.NUM_COLS; col++) {
                TileFC tile = tIterator.next();
                this.tiles[row][col] = tile;
            }
        }
    }

    /**
     * Show the blank side of the tile to the user.
     * @param position of the tile
     */
    public void showBlank(int position) {
        if (position < 0 || position > 36) {
            position = 0;
        }
        int row = position / BoardFC.NUM_ROWS;
        int col = position % BoardFC.NUM_COLS;
        ((TileFC)tiles[row][col]).showBlank();
    }

    /**
     * Show the picture side of the tile to the user.
     * @param position of the tile
     */

    public void showPicture(int position) {
        if (position < 0 || position > 36) {
            position = 0;
        }
        int row = position / BoardFC.NUM_ROWS;
        int col = position % BoardFC.NUM_COLS;
        ((TileFC)tiles[row][col]).showPicture();
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
