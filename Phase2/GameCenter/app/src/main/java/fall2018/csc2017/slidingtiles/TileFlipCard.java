package fall2018.csc2017.slidingtiles;

public class TileFlipCard {

    /**
     * The unique id of every tile.
     */
    private int id;

    /**
     * The id of the front side of the Tile. When the tile is first created the front side of the tile
     * is blank.
     */
    private int front = R.drawable.tile_def;;

    /**
     * The id of the tile's picture, i.e. the image side of the tile. When the tile is first
     * created the front side of the tile is blank.
     */
    private int back;

    /**
     *
     * @return
     */
    public boolean isUp() {
        return isUp;
    }

    private boolean isUp = false;

    TileFlipCard(int tileId) {
        this.back = tileId;
        if (Board.NUM_COLS == 4) {
            flipCard4x4(id);
        }
        else {
            flipCard6x6(id);
        }
    }


    private void flipCard4x4(int tileId) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8};
        id = (int) pics[tileId];
    }

    private void flipCard6x6(int tileId) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
                R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
                R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_16,
                R.drawable.tile_17, R.drawable.tile_18};
        id = (int) pics[tileId];
    }

    public void flipUp() {
        int tempId = this.front;
        this.front = this.back;
        this.back = tempId;
        this.isUp = true;
    }

    public void flipDown() {
        int temp = this.front;
        this.front = this.back;
        this.back = temp;
        this.isUp = false;
    }

    public int getId() {
        return this.id;
    }
}
