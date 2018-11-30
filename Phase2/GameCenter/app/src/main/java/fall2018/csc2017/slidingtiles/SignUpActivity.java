package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Sign up activity. Allows users to create a new account for GameCenter.
 * Extends LoginActivity for access to credentials.
 */
public class SignUpActivity extends LoginActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadFromFile(USER_SAVE_TEMP);
        setContentView(R.layout.sign_up_activity);
        addFinishSignUpButtonListener();
    }

    /**
     * Activates the finish sign up button listener.
     */
    private void addFinishSignUpButtonListener() {
        Button finishSignUpButton = findViewById(R.id.finishSignUpButton);
        finishSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishSignUp();
            }
        });
    }

    /**
     * Finishes the sign up process by adding username and password to the credentials hashmap.
     * Username and password must be longer than 5 characters and password must match the confirm
     * password EditText field.
     */
    private void finishSignUp() {
        EditText user = findViewById(R.id.userEntered);
        EditText pass = findViewById(R.id.passEntered);
        EditText confPass = findViewById(R.id.confirmPassEntered);
        if (usersManager.hasUser(user.getText().toString())) {
            makeAccountExistsToast();
        } else if (user.getText().toString().length() < 5
                || pass.getText().toString().length() < 5
                || !pass.getText().toString().equals(confPass.getText().toString())) {
            makeValidCredentialsToast();
        } else {
            usersManager.addUser(user.getText().toString(), pass.getText().toString());
            saveToFile(USER_SAVE);
            makeAccountCreatedToast();
            switchToMain();
        }
    }

    /**
     * Switches back to the main sign in/sign up activity.
     */
    private void switchToMain() {
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);
    }

    /**
     * Makes the account created toast.
     */
    private void makeAccountCreatedToast() {
        Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Makes the account already exists toast.
     */
    private void makeAccountExistsToast() {
        Toast.makeText(this, "Sorry, this account already exists!", Toast.LENGTH_LONG).show();
    }

    /**
     * Makes the "Please enter valid credentials" toast.
     */
    private void makeValidCredentialsToast() {
        Toast.makeText(this, "Please enter valid credentials.", Toast.LENGTH_SHORT).show();
    }
}
