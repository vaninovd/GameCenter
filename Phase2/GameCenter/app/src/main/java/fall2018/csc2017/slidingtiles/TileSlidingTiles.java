package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * A TileSlidingTiles in a sliding tiles puzzle.
 */
public class TileSlidingTiles extends Tile implements Serializable {

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    TileSlidingTiles(int backgroundId) {
        id = backgroundId + 1;
        if (BoardSlidingTiles.NUM_COLS == 3) {
            tile3x3(backgroundId);
        }
        else if (BoardSlidingTiles.NUM_COLS == 4) {
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

}
