package fall2018.csc2017.slidingtiles;

import android.hardware.usb.UsbRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainListView extends AppCompatActivity {
//    private ArrayList<User> usersList;
//    private List<User> usersList = new ArrayList<User>();
    private ListView listView;
    private ScoreboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);
        ArrayList<User> usersList;
        usersList = LoginActivity.users.getUserList();

        listView = (ListView) findViewById(R.id.list);
        adapter = new ScoreboardAdapter(this, usersList);

        listView.setAdapter(adapter);

        //populate our Users array
        //sort the list by high scores
        Collections.sort(usersList);

        //All done, so notify the adapter to populate the list using the Items Array
        adapter.notifyDataSetChanged();
    }
}
