package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

public class MovementController2048 {

    private BoardManager2048 boardManager = null;

    public MovementController2048() {
    }

    /**
     * Set the BoardManagerSlidingTiles to the current board manager of the game.
     */
    public void setBoardManager(BoardManager2048 boardManager) {
        this.boardManager = boardManager;
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
                Toast.makeText(context, "YOU WIN! Check out the LEADERBOARD!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "GAME OVER!", Toast.LENGTH_SHORT).show();
        }
    }
}
