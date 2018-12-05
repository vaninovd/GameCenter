package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The main activity for the sign up/sign in page
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * UserManager to manage all users on this device.
     */
    public static UserManager usersManager;

    /**
     * File name for users save.
     */
    public static final String USER_SAVE = "users.ser";

    /**
     * Temp file name for users save.
     */
    public static final String USER_SAVE_TEMP = "users_temp.ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        loadFromFile(USER_SAVE);
        if (usersManager == null) {
            usersManager = new UserManager();
        }
        usersManager.addUser("admin", "admin");
        setContentView(R.layout.activity_landing);
        addSignInButtonListener();
        addSignUpButtonListener();
    }

    /**
     * Activates the Sign in button.
     */
    private void addSignInButtonListener() {
        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.email);
                EditText pass = findViewById(R.id.pass);
                if (!usersManager.hasUser(username.getText().toString())) {
                    makeNoAccountToast();
                } else if (usersManager.checkCredentials(username.getText().toString(), pass.getText().toString())) {
                    usersManager.setCurrentUser(username.getText().toString());
                    setSaveFilename(usersManager.getCurrentUser());
                    switchToGamesActivity();
//                    switchToSlidingTiles();
                } else {
                    makeIncorrectPasswordToast();
                }
            }
        });
    }

    /**
     * Switches to Games Activity
     */
    private void switchToGamesActivity() {
        Intent tmp = new Intent(this, GamesActivity.class);
        startActivity(tmp);
    }

    /**
     * Activates the Sign up button.
     */
    private void addSignUpButtonListener() {
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignUp();
            }
        });
    }

    /**
     * Switches to Sign up activity.
     */
    private void switchToSignUp() {
        Intent tmp = new Intent(this, SignUpActivity.class);
        saveToFile(USER_SAVE_TEMP);
        startActivity(tmp);
    }

    /**
     * Switches to Sliding Tiles Starting Activity
     */
    private void switchToSlidingTiles() {
        Intent tmp = new Intent(this, StartingActivitySlidingTiles.class);
        startActivity(tmp);
    }

    /**
     * Makes the incorrect password toast.
     */
    private void makeIncorrectPasswordToast() {
        Toast.makeText(this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Makes the no account exists toast.
     */
    private void makeNoAccountToast() {
        Toast.makeText(this, "This account does not exist.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Saves this userManager to filename.
     * @param fileName the name of the file.
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(usersManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Loads UserManager users from filename
     * @param fileName the name of the file.
     */
    public void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                usersManager = (UserManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Sets the save filename, based on which user is the current active user.
     * @param filename The filename we wish to change to.
     */
    public void setSaveFilename(String filename) {
        StartingActivitySlidingTiles.SAVE_FILENAME_SLIDING_TILES = filename + "SlidingTiles.ser";
        StartingActivityFC.SAVE_FILENAME_FC = filename + "FC.ser";
        StartingActivity2048.SAVE_FILENAME_2048 = filename + "2048.ser";
        StartingActivityFC.TEMP_SAVE_FILENAME = filename + "FC_temp.ser";
        StartingActivity2048.TEMP_SAVE_FILENAME = filename + "2048_temp.ser";
        StartingActivitySlidingTiles.TEMP_SAVE_FILENAME = filename + "_temp.ser";
    }
}
