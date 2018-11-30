package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Custom activity view for displaying user and score in a List View
 */
public class MainListView extends AppCompatActivity {
    private ListView listView;
    private ScoreboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);
        ArrayList<User> usersList;
        usersList = LoginActivity.usersManager.getUserList();

        listView = (ListView) findViewById(R.id.list);
        adapter = new ScoreboardAdapter(this, usersList);

        listView.setAdapter(adapter);

        //sort the list by high scores
        Collections.sort(usersList);

        //notify the adapter to populate the list using the usersList Array
        adapter.notifyDataSetChanged();
    }
}
