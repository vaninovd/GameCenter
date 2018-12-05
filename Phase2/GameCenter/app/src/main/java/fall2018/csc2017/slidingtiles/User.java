package fall2018.csc2017.slidingtiles;
import java.io.Serializable;
import java.util.HashMap;

import android.support.annotation.NonNull;

public class User implements Comparable<User>, Serializable {

    /**
     * Name of this user.
     */
    private String name;


    /**
     * Hashmap storing the scores (values) the user got in different games (keys).
     */
    private HashMap<String, Integer> scores = new HashMap<>();

    public User(String name) {
        this.name = name;
    }


    /**
     * Return the name of this user.
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for this user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return user's score.
     * @return int user's score
     */
    public int getScore(String name) {
        if (scores.containsKey(name)) {
            return scores.get(name);
        }
            return 0;
    }

    /**
     * Add a score that user scored in game to hashmap that stores the scores.
     * @param game name of the game for which to record the score
     * @param score score to record
     */
    public void addScore(String game, Integer score) {
        if (scores.containsKey(game)) {
            if (scores.get(game) < score) { scores.replace(game, score); }
        } else {
            scores.put(game, score);
        }
    }

    /**
     * Compare users by their scores.
     * @param secondUser a user to compare this user to
     * @return int -1, 0, or 1 depending on whose score is higher.
     */
    @Override
    public int compareTo(@NonNull User secondUser) {
        if (this.getScore(GamesActivity.currGame) < secondUser.getScore(GamesActivity.currGame)){
            return 1;
        } else if (this.getScore(GamesActivity.currGame) > secondUser.getScore(GamesActivity.currGame)){
            return -1;
        }
        return 0;
    }

}
