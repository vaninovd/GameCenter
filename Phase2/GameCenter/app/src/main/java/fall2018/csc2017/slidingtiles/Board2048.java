package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Random;

/**
 * The 2048 board.
 */
public class Board2048 extends Board implements Serializable, Iterable<Tile> {

    /**
     * Score count
     */
    private static int score;

    /**
     * Number of moves count
     */
    private static int numMoves;

    /**
     * Value added after a successful move with merges (sum of all tiles merged)
     */
    private static int scoreAdded;

    /**
     * A new board of tiles in row-major order.
     */
    Board2048() {
        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
            for (int col = 0; col != Board2048.NUM_COLS; col++) {
                Tile2048 tile = new Tile2048(0);
                this.tiles[row][col] = tile;
            }
        }
        spawnTile();
        spawnTile();
    }

    /**
     * Getter for the scoreADDED
     * @return int
     */
    public static int getScoreAdded() {
        return Board2048.scoreAdded;
    }

    /**
     * Getter for the score
     * @return int
     */
    public static int getScore() {
        return Board2048.score;
    }

    /**
     * Getter for the numMoves
     * @return number of moves made
     */
    public static int  getNumMoves(){
        return Board2048.numMoves;
    }

    /**
     * Resets the score at the beginning of a game
     */
    public static void resetScore() {
        score = 0;
    }

    public static void resetNumMoves() {numMoves = 0;}

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    @Override
    Tile2048 getTile(int row, int col) {
        return (Tile2048) tiles[row][col];
    }

    public Tile[][] makeTempCopy(Tile[][] tiles) {
        Tile[][] temp = new Tile[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < Board2048.NUM_ROWS; row++) {
            for (int col = 0; col < Board2048.NUM_COLS; col++) {
                temp[row][col] = tiles[row][col];
            }
        }
        return temp;
    }

    /**
     * Merges tiles together when a left swipe is initiated
     */
    public void mergeLeft() {
        Tile[][] temp1 = makeTempCopy(tiles);
        pushLeft();
        int tempAdded = score;
        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
            for (int col = 1; col != Board2048.NUM_COLS; col++) {
                Tile2048 prevTile = getTile(row, col-1);
                Tile2048 currTile = getTile(row, col);
                if (prevTile.getId() == currTile.getId() & prevTile.getId() != 0) {
                    tiles[row][col - 1] = new Tile2048(prevTile.getId() + 1);
                    tiles[row][col] = new Tile2048(0);
                    addScore(Math.pow(2, prevTile.getId()+1));
                }
            }
        }
        scoreAdded = getScore() - tempAdded;
        pushLeft();
        if (isSpawnable(temp1, tiles)) {
            numMoves += 1;
            spawnTile();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Helper function for mergeLeft() that pushes all tile to as far left as possible
     */
    private void pushLeft() {
        for (int row = 0; row < Board2048.NUM_ROWS; row++) {
            int farthest = 0;
            for (int col = 0; col < Board2048.NUM_COLS; col++) {
                // if it's not a blank tile
                if (tiles[row][col].getId() != 0) {
                    Tile2048 tempTile = getTile(row, farthest);
                    // swap the tiles
                    tiles[row][farthest] = tiles[row][col];
                    tiles[row][col] = tempTile;
                    farthest++;
                }
            }
        }
    }

    /**
     * Merges tiles together when a right swipe is intitiated
     */
    public void mergeRight() {
        Tile[][] temp1 = makeTempCopy(tiles);
        pushRight();
        int tempAdded = score;
        for (int row = 0; row < Board2048.NUM_ROWS; row++) {
            for (int col = tiles[row].length - 2; col >= 0; col--) {
                Tile2048 prevTile = getTile(row, col+1);
                Tile2048 currTile = getTile(row, col);
                if (prevTile.getId() == currTile.getId() & prevTile.getId() != 0) {
                    tiles[row][col + 1] = new Tile2048(prevTile.getId()+1);
                    tiles[row][col] = new Tile2048(0);
                    addScore(Math.pow(2, prevTile.getId()+1));
                }
            }
        }
        scoreAdded = getScore() - tempAdded;
        pushRight();
        if (isSpawnable(temp1, tiles)) {
            numMoves += 1;
            spawnTile();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Helper function for mergeRight to swap the blank tile with the tile furthest to the right
     * that is not blank
     */
    private void pushRight() {
        for (int row = 0; row < Board2048.NUM_ROWS; row++) {
            int farthest = tiles[row].length-1;
            for (int col = tiles[row].length - 1; col >= 0; col--) {
                if (tiles[row][col].getId() != 0) {
                    Tile2048 tempTile = getTile(row, farthest);
                    tiles[row][farthest] = tiles[row][col];
                    tiles[row][col] = tempTile;
                    farthest--;
                }
            }
        }
    }

    public static void resetScoreAdded() {
        scoreAdded = 0;
    }

    /**
     * Merges tiles together when a upwards swipe is intitiated
     */
    public void mergeUp() {
        Tile[][] temp1 = makeTempCopy(tiles);
        pushUp();
        int tempAdded = score;
        for (int col = 0; col < Board2048.NUM_COLS; col++) {
            for (int row = 1; row < Board2048.NUM_ROWS; row++) {
                Tile2048 prevTile = getTile(row-1, col);
                Tile2048 currTile = getTile(row, col);
                if (prevTile.getId() == currTile.getId() & prevTile.getId() != 0) {
                    tiles[row-1][col] = new Tile2048(prevTile.getId()+1);
                    tiles[row][col] = new Tile2048(0);
                    addScore(Math.pow(2, prevTile.getId()+1));
                }
            }
        }
        scoreAdded = getScore() - tempAdded;
        pushUp();
        if (isSpawnable(temp1, tiles)) {
            numMoves += 1;
            spawnTile();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Helper function for mergeUp() to merge together two tiles
     */
    private void pushUp() {
        for (int col = 0; col < Board2048.NUM_COLS; col++) {
            int farthest = 0;
            for (int row = 0; row < Board2048.NUM_ROWS; row++) {
                if (tiles[row][col].getId() != 0) {
                    Tile2048 tempTile = getTile(farthest, col);
                    tiles[farthest][col] = tiles[row][col];
                    tiles[row][col] = tempTile;
                    farthest++;
                }
            }
        }
    }

    /**
     * Merges tiles together when a down swipe is intitiated
     */
    public void mergeDown() {
        // make a copy of the current state as to not mutate it
        Tile[][] temp1 = makeTempCopy(tiles);
        numMoves += 1;
        pushDown();
        int tempAdded = score;
        for (int col = 0; col < Board2048.NUM_COLS; col++) {
            for (int row = Board2048.NUM_ROWS - 2; row >= 0; row--) {
                Tile2048 prevTile = getTile(row+1, col);
                Tile2048 currTile = getTile(row, col);
                if (prevTile.getId() == currTile.getId() & prevTile.getId() != 0) {
                    tiles[row+1][col] = new Tile2048(prevTile.getId() + 1);
                    tiles[row][col] = new Tile2048(0);
                    addScore(Math.pow(2, prevTile.getId()+1));
                }
            }
        }
        scoreAdded = getScore() - tempAdded;
        pushDown();
        if (isSpawnable(temp1, tiles)) {
            numMoves += 1;
            spawnTile();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Adds score after a move is made.
     * @param pow the power of two added after a move
     */
    private void addScore(double pow) {
        Board2048.score += pow;
    }

    /**
     * Helper function for mergeDown() to merge together two tiles in a vertical direction
     */
    private void pushDown() {
        for (int col = 0; col < Board2048.NUM_COLS; col++) {
            int farthest = Board2048.NUM_ROWS - 1;
            for (int row = Board2048.NUM_ROWS - 1; row >= 0; row--) {
                if (tiles[row][col].getId() != 0) {
                    Tile2048 tempTile = getTile(farthest, col);
                    tiles[farthest][col] = getTile(row, col);
                    tiles[row][col] = tempTile;
                    farthest--;
                }
            }
        }
    }

    /**
     * Randomly spawn a new tile with 80% it's a 2 and 20% it's a 4
     */
    private void spawnTile() {
        ArrayList<int[]> emptySpots = getEmptySpots();
        double ran = Math.random();
        Tile2048 newTile = ran >= 0.8 ? new Tile2048(2) : new Tile2048(1);
        int ranIndex = new Random().nextInt(emptySpots.size());
        this.tiles[emptySpots.get(ranIndex)[0]][emptySpots.get(ranIndex)[1]] = newTile;
        setChanged();
        notifyObservers();
    }

    /**
     * Helper method for checking if a new tile should be spawned
     */
    private boolean isSpawnable (Tile[][] temp1, Tile[][] tiles) {
        for (int row = 0; row < Board2048.NUM_ROWS; row++) {
            for (int col = 0; col < Board2048.NUM_COLS; col++) {
                if (temp1[row][col].getId() != tiles[row][col].getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns an array of all of the empty spots in the board
     * @return ArrayList
     */
    private ArrayList<int[]> getEmptySpots() {
        ArrayList empty = new ArrayList();
        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
            for (int col = 0; col != Board2048.NUM_COLS; col++) {
                if (this.tiles[row][col].getId() == 0) {
                    int[] pos = {row, col};
                    empty.add(pos);
                }
            }
        }
        return empty;
    }

    /**
     * Checks if there are any holes in the board
     * @return boolean
     */
    private boolean hasHoles() {
        boolean flag = false;
        outer:
        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
            for (int col = 0; col != Board2048.NUM_COLS; col++) {
                if (tiles[row][col].getId() == 0) {
                    flag = true;
                    break outer;
                }
            }
        }
        return  flag;
    }

    /**
     * Returns whether or not the board is in a state of no more moves
     * @return boolean
     */
    public boolean isStuck() {
        return !hasHoles() && isStuckHorizontal() && isStuckVertical();
    }

    /**
     * Checks whether there are any ways to merge horizontal tiles
     * @return boolean
     */
    private boolean isStuckHorizontal() {
        boolean stuck = true;
        outer:
        for (int row = 0; row != Board2048.NUM_ROWS; row++) {
            for (int col = 0; col != Board2048.NUM_COLS - 1; col++) {
                if (tiles[row][col].getId() == tiles[row][col + 1].getId()
                        || tiles[row][col].getId() == 0 || tiles[row][col + 1].getId() == 0) {
                    stuck = false;
                    break outer;
                }
            }
        }
        return stuck;
    }

    /**
     * Checks whether there are ways to merge vertical tiles
     * @return boolean
     */
    private boolean isStuckVertical() {
        boolean stuck = true;
        outer:
        for (int col = 0; col != Board2048.NUM_COLS; col++) {
            for (int row = 0; row != Board2048.NUM_ROWS - 1; row++) {
                if (tiles[row][col].getBackground() == tiles[row + 1][col].getBackground()
                        || tiles[row][col].getId() == 0 || tiles[row + 1][col].getId() == 0) {
                    stuck = false;
                    break outer;
                }
            }
        }
        return stuck;
    }

    /**
     * Return an iterator for this BoardFC.
     *
     * @return iterator for this BoardFC.
     */
    @NonNull
    public Iterator<Tile> iterator() {
        return new IteratorTile(this.tiles);
    }


}
