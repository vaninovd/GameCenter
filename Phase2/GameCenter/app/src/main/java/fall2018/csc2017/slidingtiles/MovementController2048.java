package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;

public class MovementController2048 {

    private BoardManager2048 boardManager = null;

    public MovementController2048() {
    }

    /**
     * Set the BoardManager to the current board manager of the game.
     */
    public void setBoardManager(BoardManager2048 boardManager) {
        this.boardManager = boardManager;
    }

//    /**
//     * Process a swipe and updates the scoreboard if the swipe led to victory.
//     */
//    public void processTapMovement(Context context, int position, boolean display) {
//        if (boardManager.isValidTap(position)) {
//            BoardManager.setNumMoves(getNumMoves() + 1);
//            boardManager.touchMove(position);
//            if (boardManager.puzzleSolved()) {
//                Toast.makeText(context, "YOU WIN! Check out the LEADERBOARD!", Toast.LENGTH_SHORT).show();
//                String username = UserManager.currentUser;
//                User curruser = LoginActivity.users.getUser(username);
//                int score = BoardManager.getNumMoves();
//                curruser.addScore(StartingActivity.name, score);
//            }
//        } else {
//            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
//        }
//    }

    /**
     * process a swipe
     * @param context
     * @param direction an integer denoting the direction of the swipe (1:left, 2:up, 3:right, 4:down)
     */
    public void processSwipeMovement(Context context, int direction) {
        if (!boardManager.getBoard().isStuck()) {
            switch (direction) {
                case 1: boardManager.getBoard().mergeLeft();
                    break;
                case 2: boardManager.getBoard().mergeUp();
                    break;
                case 3: boardManager.getBoard().mergeRight();
                    break;
                case 4: boardManager.getBoard().mergeDown();
                    break;
            }
            if (boardManager.gameWon()) {
                Toast.makeText(context, "YOU WIN! Check out the LEADERBOARD!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "GAME OVER!", Toast.LENGTH_SHORT).show();
        }
    }
}
