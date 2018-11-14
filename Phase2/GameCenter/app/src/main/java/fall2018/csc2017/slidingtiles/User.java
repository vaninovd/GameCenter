package fall2018.csc2017.slidingtiles;
import java.io.Serializable;
import java.util.HashMap;

import android.support.annotation.NonNull;

public class User implements Comparable<User>, Serializable {
    private String name;
    private int score;
    private HashMap<String, Integer> scores = new HashMap<>();

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public HashMap getScores() {
        return scores;
    }

    public void addScore(String game, Integer score) {
        if (scores.containsKey(game)) {
            if (scores.get(game) < score) { scores.replace(game, score); }
        } else {
            scores.put(game, score);
        }
    }

    public int getGameScore(String game) {
        int score;
        if (scores.containsKey(game)){
            score = scores.get(game);
        } else {
            score = -1;
        }
        return score;
    }

    @Override
    public int compareTo(@NonNull User secondUser) {
        if (this.score < secondUser.score){
            return 1;
        } else if (this.score > secondUser.score){
            return -1;
        }
        return 0;
    }

}
