package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestUserManager {
    private UserManager userManager;

    private void setUp() {
        this.userManager = new UserManager();
    }

    private void tearDown() {
        this.userManager = new UserManager();
    }

    @Test
    public void testGetUserList() {
        setUp();
        assertEquals(new ArrayList<User>(), this.userManager.getUserList());
        tearDown();
    }

    @Test
    public void testAddUser() {
        setUp();
        this.userManager.addUser("user", "user");
        assertEquals(1, this.userManager.getUserList().size());
        assertEquals(1, this.userManager.getCredentials().size());
        tearDown();
    }

    @Test
    public void testGetUser() {
        setUp();
        this.userManager.addUser("user", "user");
        assertEquals("user", this.userManager.getUser("user").getName());
        tearDown();
    }

    @Test
    public void testHasUser() {
        setUp();
        this.userManager.addUser("user", "user");
        assertTrue(this.userManager.hasUser("user"));
        tearDown();
    }

    @Test
    public void testGetCurrentUser() {
        setUp();
        assertNull(this.userManager.getCurrentUser());
        tearDown();
    }

    @Test
    public void testSetCurrentUser() {
        setUp();
        this.userManager.addUser("user", "user");
        this.userManager.setCurrentUser("user");
        assertEquals("user", this.userManager.getCurrentUser());
        tearDown();
    }

    @Test
    public void testCheckCredentials() {
        setUp();
        this.userManager.addUser("user", "user");
        assertTrue(this.userManager.checkCredentials("user", "user"));
        assertFalse(this.userManager.checkCredentials("user", "false"));
        assertFalse(this.userManager.checkCredentials("csc207", "random"));
        tearDown();
    }
}
