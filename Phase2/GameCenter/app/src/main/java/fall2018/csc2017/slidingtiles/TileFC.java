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

    private Object[] pics6x6 = {R.drawable.fc_dh, R.drawable.fc_bg, R.drawable.fc_pg,
            R.drawable.fc_tf, R.drawable.fc_jc, R.drawable.fc_dl, R.drawable.fc_dj,
            R.drawable.fc_js, R.drawable.fc_jt, R.drawable.fc_mz, R.drawable.fc_fp,
            R.drawable.fc_jc_, R.drawable.fc_kr, R.drawable.fc_sj, R.drawable.fc_dh2,
            R.drawable.fc_se, R.drawable.fc_at, R.drawable.fc_mg, R.drawable.fc_dh,
            R.drawable.fc_bg, R.drawable.fc_pg, R.drawable.fc_tf, R.drawable.fc_jc,
            R.drawable.fc_dl, R.drawable.fc_dj, R.drawable.fc_js, R.drawable.fc_jt,
            R.drawable.fc_mz, R.drawable.fc_fp, R.drawable.fc_jc_, R.drawable.fc_kr,
            R.drawable.fc_sj, R.drawable.fc_dh2, R.drawable.fc_se, R.drawable.fc_at,
            R.drawable.fc_mg};

    private Object[] pics4x4 = {R.drawable.fc_dh, R.drawable.fc_bg, R.drawable.fc_pg,
            R.drawable.fc_tf, R.drawable.fc_jc, R.drawable.fc_dl, R.drawable.fc_dj,
            R.drawable.fc_js, R.drawable.fc_dh, R.drawable.fc_bg, R.drawable.fc_pg,
            R.drawable.fc_tf, R.drawable.fc_jc, R.drawable.fc_dl, R.drawable.fc_dj,
            R.drawable.fc_js};

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

    /**
     * Getter for field.
     * @return boolean
     */
    public boolean getisUp() {
        return isUp;
    }

}
