package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Activity for selecting sliding tiles settings (complexity, number of undos)
 * Extends starting activity.
 */
public class SlidingTilesSettingsActivity extends StartingActivitySlidingTiles {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_tiles_settings);
        addStartGameButtonListener();
    }

    /**
     * Activates the Start Game button listener.
     */
    private void addStartGameButtonListener() {
        Button startGame = findViewById(R.id.startGameButton);
        startGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RadioGroup complexityGroup = findViewById(R.id.complexity);
                CheckBox unlimitedBox = findViewById(R.id.unlimited);
                EditText numUndo = findViewById(R.id.numUndo);
                int maxUndo = numUndo.getText().toString().equals("") ? 3 : Integer.parseInt(numUndo.getText().toString());
                boolean unlimited = unlimitedBox.isChecked();
                if (complexityGroup.getCheckedRadioButtonId() == R.id.complexity5b5) {
                    switchToGame(unlimited, maxUndo, 5);
                } else if (complexityGroup.getCheckedRadioButtonId() == R.id.complexity4b4) {
                    switchToGame(unlimited, maxUndo, 4);
                } else {
                    switchToGame(unlimited, maxUndo, 3);
                }
            }
        });
    }

    /**
     * Switch to the GameActivitySlidingTiles view to play the game.
     */
    private void switchToGame(boolean unlimited, int numUndo, int size) {
        BoardSlidingTiles.NUM_COLS = size;
        BoardSlidingTiles.NUM_ROWS = size;
        BoardManagerSlidingTiles.resetNumMoves();
        boardManager = new BoardManagerSlidingTiles(unlimited, numUndo);
        Intent tmp = new Intent(this, GameActivitySlidingTiles.class);
        saveToFile(StartingActivitySlidingTiles.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }
}
