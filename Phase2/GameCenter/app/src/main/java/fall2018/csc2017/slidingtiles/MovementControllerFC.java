package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

import static fall2018.csc2017.slidingtiles.BoardManagerFC.getNumMoves;


public class MovementControllerFC {

    private BoardManagerFC boardManagerFC = null;

    public MovementControllerFC() {
    }

    /**
     * Set the BoardManagerFC to the current board manager of the game.
     */
    public void setBoardManagerFC(BoardManagerFC boardManager) {
        this.boardManagerFC = boardManager;
    }

    /**
     * Process a swipe and updates the scoreboard if the swipe led to victory.
     */
    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManagerFC.isValidTap(position)) {
            BoardManagerFC.setNumMoves(getNumMoves() + 1);
            boardManagerFC.touchMove(position);
            if (boardManagerFC.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN! Check out the LEADERBOARD!", Toast.LENGTH_SHORT).show();
                String username = UserManager.currentUser;
                User curruser = LoginActivity.users.getUser(username);
                int score = BoardManagerFC.getNumMoves();
                curruser.addScore(StartingActivityFC.name, score);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
