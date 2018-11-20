package fall2018.csc2017.slidingtiles;

public class TileFlipCard {

    private int id;

    private int picture;

    private int blank;

    private int front;

    private boolean isUp = false;

    public static final int bLaNk_pIc = R.drawable.activity_main_pic;

    TileFlipCard(int id) {
        this.id = id + 1;
        if (Board.NUM_COLS == 4) {
            flipCard4x4(id);
        }
        else {
            flipCard6x6(id);
        }
    }

    private void flipCard4x4(int id) {
        Object[] pics = {R.drawable.dh, R.drawable.dh, R.drawable.dh, R.drawable.dh,
                R.drawable.dh, R.drawable.dh, R.drawable.dh, R.drawable.dh,
                R.drawable.dh, R.drawable.dh, R.drawable.dh, R.drawable.dh,
                R.drawable.dh, R.drawable.dh, R.drawable.dh, R.drawable.dh};
        picture = (int) pics[id];
        blank = bLaNk_pIc;
    }

    private void flipCard6x6(int id) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
                R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
                R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_16,
                R.drawable.tile_17, R.drawable.tile_18, R.drawable.tile_19, R.drawable.tile_20,
                R.drawable.tile_21, R.drawable.tile_22, R.drawable.tile_23, R.drawable.tile_24,
                R.drawable.dh, R.drawable.dh, R.drawable.dh, R.drawable.dh,
                R.drawable.dh, R.drawable.dh, R.drawable.dh, R.drawable.dh,
                R.drawable.dh, R.drawable.dh, R.drawable.dh, R.drawable.dh};
        picture = (int) pics[id];
        blank = bLaNk_pIc;
    }

    private void flipUp() {
        this.front = this.picture;
        this.isUp = true;
    }

    private void flipDown() {
        this.front = this.blank;
        this.isUp = false;
    }


}
