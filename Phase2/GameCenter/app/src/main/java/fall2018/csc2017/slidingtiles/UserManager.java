package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class to manage Users in our GameCenter. Includes a hashmap to store credentials, a list of users,
 * and a current user, i.e., the one who is currently logged in.
 */
public class UserManager implements Serializable {
    private ArrayList<User> users;
    private HashMap<String, String> credentials;
    public String currentUser;

    public UserManager () {
        this.users = new ArrayList<>();
        this.credentials = new HashMap<>();
    }

    /**
     * Returns the user list.
     * @return the list of registered users.
     */
    public ArrayList<User> getUserList() {
        return this.users;
    }

    /**
     * Adds a new user to the UserManager.
     * @param user the username of the new user.
     * @param pass the password of the new user.
     */
    public void addUser(String user, String pass) {
        if (!credentials.containsKey(user)) {
            User newUser = new User(user);
            this.credentials.put(user, pass);
            this.users.add(newUser);
        }
    }

    /**
     * Returns a user based on their username. If the user does not exist, returns null.
     * @param username the username of the desired user.
     * @return The user corresponding to the username given, null otherwise.
     */
    public User getUser(String username) {
        User ret = null;
        for (User i : this.users) {
            if (i.getName().equals(username)) {
                ret = i;
            }
        }
        return ret;
    }

    /**
     * Returns whether a given user exists.
     * @param username
     * @return
     */
    public boolean hasUser(String username) {
        return credentials.containsKey(username);
    }

    /**
     * Sets the current logged in user.
     * @param username the username of the user.
     */
    public void setCurrentUser(String username) {this.currentUser = username;}

    /**
     * Returns the current active user.
     * @return returns the current user.
     */
    public String getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Checks if the given password matches to a username in password, false if the user does not exist.
     * @param username the username entered
     * @param password the password entered
     * @return if the username and password are a key-value pair in our credential hashmap.
     */
    public boolean checkCredentials(String username, String password) {
        if (this.hasUser(username)) {
            return credentials.get(username).equals(password);
        } else {return false;}
    }

    /**
     * Gets the user's login credentials in form of user, pass
     * @return hashmap
     */
    public HashMap<String, String> getCredentials() {
        return this.credentials;
    }
}
