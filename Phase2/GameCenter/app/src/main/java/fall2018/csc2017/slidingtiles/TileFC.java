package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Observable;

/**
 * A TileFC in a sliding tiles puzzle.
 */
public class TileFC extends Observable implements Comparable<TileFC>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * Indicates whether the tile is up or down
     */
    private boolean isUp;

    /**
     * The unique id.
     */
    private int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    private Object[] pics6x6 = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
            R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
            R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
            R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_16,
            R.drawable.tile_17, R.drawable.tile_18, R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
            R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
            R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
            R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_16,
            R.drawable.tile_17, R.drawable.tile_18};

    private Object[] pics4x4 = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
            R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
            R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
            R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8};

    /**
     * A TileFC with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    TileFC(int id, int background) {
        this.id = id;
        this.background = background;

    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    TileFC(int backgroundId) {
        id = backgroundId + 1;
        if (BoardFC.NUM_COLS == 4) {
            tile4x4(backgroundId);
        } else {
            tile6x6(backgroundId);
        }
        background = R.drawable.block_blank;
    }

    /**
     * Show the blank side of the tile
     */

    public void showBlank() {
        background = R.drawable.block_blank;
        setChanged();
        notifyObservers();
        isUp = false;
    }

    /**
     * Show the picture
     */

    public void showPicture() {
        if (BoardFC.NUM_COLS == 4) {
            background = (int) pics4x4[id - 1];
        } else {
            background = (int) pics6x6[id - 1];
        }
        setChanged();
        notifyObservers();
        isUp = true;
    }

    public boolean isUp() {
        return isUp;
    }

    /**
     * Corresponding tile for a 4x4 board.
     *
     * @param backgroundId
     */
    private void tile4x4(int backgroundId) {
        background = (int) pics4x4[backgroundId];
    }

    /**
     * Corresponding tile for a 5x5 board.
     *
     * @param backgroundId
     */
    private void tile6x6(int backgroundId) {
        background = (int) pics6x6[backgroundId];
    }

    @Override
    public int compareTo(@NonNull TileFC o) {
        return o.id - this.id;
    }
}
