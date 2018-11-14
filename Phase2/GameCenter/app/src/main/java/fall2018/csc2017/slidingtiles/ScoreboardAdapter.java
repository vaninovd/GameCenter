package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// this is our custom adapter for a scoreboard
public class ScoreboardAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<User> itemsItems;

    public ScoreboardAdapter(Context context, List<User> itemsItems) {
        this.mContext = context;
        this.itemsItems = itemsItems;
    }

    @Override
    public int getCount() {
        return itemsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return itemsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

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
            holder.name = (TextView) scoreView.findViewById(R.id.name);
            holder.score = (TextView) scoreView.findViewById(R.id.score);
//            holder.undo = (TextView) scoreView.findViewById(R.id.undo);

            scoreView.setTag(holder);

        } else {
            holder = (ViewHolder) scoreView.getTag();
        }

        final User m = itemsItems.get(position);
        holder.name.setText(m.getName());
        holder.score.setText(m.getScores().get(StartingActivity.name).toString());

        return scoreView;
    }

    static class ViewHolder {
        TextView name;
        TextView score;
    }
}
