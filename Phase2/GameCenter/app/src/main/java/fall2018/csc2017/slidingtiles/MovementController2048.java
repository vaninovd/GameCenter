package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

public class MovementController2048 {

    private BoardManager2048 boardManager;

    public MovementController2048() {
    }

    /**
     * Set the BoardManagerSlidingTiles to the current board manager of the game.
     */
    public void setBoardManager(BoardManager2048 boardManager) {
        this.boardManager = boardManager;
    }

    public void recordScore() {
        String username = LoginActivity.usersManager.getCurrentUser();
        User curruser = LoginActivity.usersManager.getUser(username);
        int score = Board2048.getScore();
        curruser.addScore(StartingActivity2048.name, score);
    }

    /**
     * process a swipe
     * @param context the current context
     * @param direction an integer denoting the direction of the swipe (1:left, 2:up, 3:right, 4:down)
     */
    public void processSwipeMovement(Context context, int direction) {
        if (!boardManager.gameOver()) {
            boardManager.makeMove(direction);
            if (boardManager.gameWon()) {
                Toast.makeText(context, "YOU GOT 2048! Check the LEADERBOARD!", Toast.LENGTH_SHORT).show();
                recordScore();
            }
        } else {
            Toast.makeText(context, "NO MORE MOVES! Check the LEADERBOARD!", Toast.LENGTH_SHORT).show();
            recordScore();
        }
    }
}
