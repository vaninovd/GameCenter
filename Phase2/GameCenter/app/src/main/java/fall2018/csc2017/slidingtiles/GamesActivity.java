package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        addStartSlidingTilesListener();
        addStart2048Listener();
        addStartFlipCardListener();
    }

    /**
     * Activates the StartSlidingTiles button.
     */
    private void addStartSlidingTilesListener() {
        Button StartSlidingTiles = findViewById(R.id.StartSlidingTiles);
        StartSlidingTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSlidingTiles();
            }
        });
    }

    /**
     * Activates the Start2048 button.
     */
    private void addStart2048Listener() {
        Button Start2048 = findViewById(R.id.Start2048);
        Start2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTo2048();
            }
        });
    }

    /**
     * Activates the StartFlipCard button.
     */
    private void addStartFlipCardListener() {
        Button StartFlipCard = findViewById(R.id.StartFlipCard);
        StartFlipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFlipCard();
            }
        });
    }


    /**
     * Switches to Sliding Tiles Starting Activity
     */
    private void switchToSlidingTiles() {
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Switches to 2048 Activity
     */
    private void switchTo2048() {
        // Temporary Main2048.class or not?
        Intent tmp = new Intent(this, Main2048.class);
        startActivity(tmp);
    }

    /**
     * Switches to Flip Card Activity
     */
    private void switchToFlipCard() {
        // Temporary BoardManagerFlipCard.class -- change to a main one
        Intent tmp = new Intent(this, BoardManagerFlipCard.class);
        startActivity(tmp);
    }
}