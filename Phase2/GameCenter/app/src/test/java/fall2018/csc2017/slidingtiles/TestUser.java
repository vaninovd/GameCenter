package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUser {
    private User user;
    private User user2;
    GamesActivity activity;

    public void setUp() {
        user = new User("user");
        user2 = new User("user2");
        user.addScore("Flipping", 20);
        activity = new GamesActivity();
        GamesActivity.currGame = "2048";
    }

    @Test
    public void testGetName() {
        setUp();
        String name = user.getName();
        assertEquals(name, "user");
    }

    @Test
    public void testSetName() {
        setUp();
        String name = "testuser";
        user.setName(name);
        assertEquals(user.getName(), name);
    }

    @Test
    public void testGetScoreContains() {
        setUp();
        user.addScore("slidingtiles", 20);
        int score = user.getScore("slidingtiles");
        assertEquals(20, score);
    }

    @Test
    public void testGetScoreNotContains() {
        setUp();
        int score = user.getScore("slidingtiles");
        assertEquals(0, score);
    }

    @Test
    public void testAddScoreLarger() {
        setUp();
        user.addScore("Flipping", 25);
        int newScore = user.getScore("Flipping");
        assertEquals(25, newScore);
    }



    @Test
    public void TestAddScore() {
        setUp();
        user.addScore("2048", 2048);
        assertEquals(2048, user.getScore("2048"));
    }

    @Test
    public void TestCompareToLarger() {
        setUp();
        user.addScore("2048", 2048);
        user2.addScore("2048", 4096);
        int test = user.compareTo(user2);
        assertEquals(1, test);
    }

    @Test
    public void TestCompareToSmaller() {
        setUp();
        user.addScore("2048", 2048);
        user2.addScore("2048", 4096);
        int test = user2.compareTo(user);
        assertEquals(-1, test);
    }

    @Test
    public void TestCompareToEqual() {
        setUp();
        user.addScore("2048", 2048);
        user2.addScore("2048", 2048);
        int test = user2.compareTo(user);
        assertEquals(0, test);
    }
}
