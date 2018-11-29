package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * A TileFC in a sliding tiles puzzle.
 */
public class TileFC extends Tile implements Serializable {

    /**
     * Indicates whether the tile is up or down
     */
    private boolean isUp;

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
            background = (int) pics4x4[backgroundId];
        } else {
            background = (int) pics6x6[backgroundId];
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

}
