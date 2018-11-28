package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Activity for selecting sliding tiles settings (complexity, number of undos)
 * Extends starting activity.
 */
public class SettingsActivityFC extends StartingActivityFC {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_flip_card);
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
                if (complexityGroup.getCheckedRadioButtonId() == R.id.complexity6b6) {
                    switchToGame( 6);
                } else if (complexityGroup.getCheckedRadioButtonId() == R.id.complexity4b4) {
                    switchToGame(4);
                }
            }
        });
    }

    /**
     * Switch to the GameActivityFC view to play the game.
     */
    private void switchToGame(int size) {
        BoardFC.NUM_COLS = size;
        BoardFC.NUM_ROWS = size;
        BoardManagerFC.resetNumMoves();
        boardManager = new BoardManagerFC();
        Intent tmp = new Intent(this, GameActivityFC.class);
        saveToFile(StartingActivityFC.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }
}
