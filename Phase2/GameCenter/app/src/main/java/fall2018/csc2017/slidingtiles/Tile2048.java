package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * A TileSlidingTiles in a sliding tiles puzzle.
 */
public class Tile2048 extends Tile implements Serializable {

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param exponent
     */
    Tile2048(int exponent) {
        this.id = exponent;
        tile(exponent);
    }

    /**
     * Corresponding tile for a 4x4 board.
     *
     * @param backgroundId
     */
    private void tile(int backgroundId) {
        Object[] pics = {R.drawable.block_blank, R.drawable.block_2, R.drawable.block_4, R.drawable.block_8,
                R.drawable.block_16, R.drawable.block_32, R.drawable.block_64, R.drawable.block_128, R.drawable.block_256,
                R.drawable.block_512, R.drawable.block_1024, R.drawable.block_2048, R.drawable.block_4096};
        this.background = (int) pics[backgroundId];
    }


}

