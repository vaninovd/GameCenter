package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import static fall2018.csc2017.slidingtiles.BoardManager.getNumMoves;


public class MovementController {

    private BoardManager boardManager = null;

    public MovementController() {
    }

    /**
     * Set the BoardManager to the current board manager of the game.
     */
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Process a swipe and updates the scoreboard if the swipe led to victory.
     */
    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            BoardManager.setNumMoves(getNumMoves() + 1);
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN! Check out the LEADERBOARD!", Toast.LENGTH_SHORT).show();
                String username = UserManager.currentUser;
                User curruser = LoginActivity.users.getUser(username);
                int score = BoardManager.getNumMoves();
                curruser.addScore(StartingActivity.name, score);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
