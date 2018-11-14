package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

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

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public Tile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    public Tile(int backgroundId) {
        id = backgroundId + 1;
        if (Board.NUM_COLS == 3) {
            tile3x3(backgroundId);
        }
        else if (Board.NUM_COLS == 4) {
            tile4x4(backgroundId);
        }
        else {
            tile5x5(backgroundId);
        }
    }

    /**
     * Corresponding tile for a 3x3 board.
     *
     * @param backgroundId
     */
    private void tile3x3(int backgroundId) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
                R.drawable.tile_def};
        background = (int) pics[backgroundId];
    }

    /**
     * Corresponding tile for a 4x4 board.
     *
     * @param backgroundId
     */
    private void tile4x4(int backgroundId) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
                R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
                R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_def};
        background = (int) pics[backgroundId];
    }

    /**
     * Corresponding tile for a 5x5 board.
     *
     * @param backgroundId
     */
    private void tile5x5(int backgroundId) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
                R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
                R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_16,
                R.drawable.tile_17, R.drawable.tile_18, R.drawable.tile_19, R.drawable.tile_20,
                R.drawable.tile_21, R.drawable.tile_22, R.drawable.tile_23, R.drawable.tile_24,
                R.drawable.tile_def};
        background = (int) pics[backgroundId];
    }

    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }
}
