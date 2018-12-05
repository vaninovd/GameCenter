package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Observable;

public class Tile extends Observable implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    int background;

    /**
     * The unique id.
     */
    int id;

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
     * Comparator for two tile objects by Id (value)
     * @param o Tile object
     * @return int
     */
    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }

}
