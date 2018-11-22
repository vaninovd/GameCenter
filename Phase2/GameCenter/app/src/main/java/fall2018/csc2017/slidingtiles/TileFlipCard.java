package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A double sided tile in the Flip Card Game.
 */
public class TileFlipCard implements Comparable<TileFlipCard>, Serializable{

    /**
     * The unique id of every tile.
     */
    private int id;

    /**
     * The id of the front side of the tile. When the tile is first created the front side of the
     * tile is blank. This is the side of the tile that is shown to the user.
     */
    private int front = R.drawable.tile_def;;

    /**
     * The id of the back side of the tile. When the tile is first created the back side of the
     * tile is a picture. This is the side of the tile is hidden from the user.
     */
    private int back;

    /**
     * The function is made to know whether the tile is facing up i.e. the picture side of the tile.
     * @return true if tile is showing picture side or false if the tile is showing the blank side.
     */
    public boolean isUp() {
        return tileUp;
    }

    /**
     * A variable indicating wether the tile is up or down.
     */
    private boolean tileUp = false;

    /**
     * A tile with a tileId. Look up and set the id.
     * @param tileId
     */
    TileFlipCard(int tileId) {
        this.back = tileId;
        if (Board.NUM_COLS == 4) {
            flipCard4x4(id);
        }
        else {
            flipCard6x6(id);
        }
    }

    /**
     * Corresponding tiles for 4x4 board size
     * @param tileId
     */
    private void flipCard4x4(int tileId) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8};
        id = (int) pics[tileId];
    }

    /**
     * Corresponding tiles for 6x6 board size.
     * @param tileId
     */
    private void flipCard6x6(int tileId) {
        Object[] pics = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
                R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
                R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_16,
                R.drawable.tile_17, R.drawable.tile_18};
        id = (int) pics[tileId];
    }

    /**
     * Flips the tile up i.e. the user now sees the picture side of the tile.
     */
    public void flipUp() {
        int tempId = this.front;
        this.front = this.back;
        this.back = tempId;
        this.tileUp = true;
    }

    /**
     * Flips down the tile i.e. the user sees the blank side of the tile.
     */
    public void flipDown() {
        int temp = this.front;
        this.front = this.back;
        this.back = temp;
        this.tileUp = false;
    }

    /**
     * Refers to the unique id of the tile.
     * @return the id of the tile.
     */
    public int getId() {
        return this.id;
    }

    /**
     * refers to the front of the tile i.e. whatever the user sees at the moment.
     * @return front of the tile
     */
    public int getFront(){
        return this.front;
    }

    /**
     * Refers to the back of the tile i.e whatever is hidden from the user at the moment.
     * @return the back of the tile
     */
    public int getBack(){
        return this.back;
    }

    @Override
    public int compareTo(@NonNull TileFlipCard o) {
        return o.id - this.id;
    }
}
