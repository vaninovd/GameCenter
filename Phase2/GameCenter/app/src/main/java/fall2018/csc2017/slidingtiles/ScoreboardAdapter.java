package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * The custom adapter for Scoreboard display.
 * Extends BaseAdapter.
 */
public class ScoreboardAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<User> usersList;

    public ScoreboardAdapter(Context context, List<User> usersList) {
        this.mContext = context;
        this.usersList = usersList;
    }

    /**
     * Getter for the score count.
     */
    @Override
    public int getCount() {
        return usersList.size();
    }

    /**
     * Returns the user at the specified position of our usersList.
     */
    @Override
    public Object getItem(int location) {
        return usersList.get(location);
    }

    /**
     * Adapter interface method to return position in the list.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Implementation of interface method to generate an item's view in a List View
     */
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View scoreView, ViewGroup parent) {
        ViewHolder holder;
        if (inflater == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (scoreView == null) {

            scoreView = inflater.inflate(R.layout.activity_scoreboard, parent, false);
            holder = new ViewHolder();

            holder.name = scoreView.findViewById(R.id.name);
            holder.score = scoreView.findViewById(R.id.score);

            scoreView.setTag(holder);

        } else {
            holder = (ViewHolder) scoreView.getTag();
        }

        final User m = usersList.get(position);
        holder.name.setText(m.getName());
        holder.score.setText(Integer.toString(m.getScore(GamesActivity.currGame)));

        return scoreView;
    }

    /**
     * This class ensures smooth scrolling of our list
     */
    static class ViewHolder {
        TextView name;
        TextView score;
    }
}
