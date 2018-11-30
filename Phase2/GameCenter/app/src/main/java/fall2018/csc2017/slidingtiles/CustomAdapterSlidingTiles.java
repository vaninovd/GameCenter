package fall2018.csc2017.slidingtiles;

/*
Taken from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java

This Class is an overwrite of the Base Adapter class
It is designed to aid setting the button sizes and positions in the GridView
 */


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class CustomAdapterSlidingTiles extends BaseAdapter {
    private ArrayList<Button> mButtons = null;
    private int mColumnWidth, mColumnHeight;

    public CustomAdapterSlidingTiles(ArrayList<Button> buttons, int columnWidth, int columnHeight) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }

    /**
     * Gets the number of tiles in the current board.
     * @return int
     */
    @Override
    public int getCount() {
        return mButtons.size();
    }

    /**
     * Returns tile at given index
     * @param position int
     * @return Object
     */
    @Override
    public Object getItem(int position) {
        return mButtons.get(position);
    }

    /**
     * Get the value of the tile
     * @param position int
     * @return long
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get the current view of the context
     * @param position int
     * @param convertView View
     * @param parent ViewGroup
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mButtons.get(position);
        } else {
            button = (Button) convertView;
        }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        button.setLayoutParams(params);

        return button;
    }
}
