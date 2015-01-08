package com.rchat.raymondkam.rchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class WelcomeActivity extends ActionBarActivity {

    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "aRQVVl8OgPaQEPczNvhf9E9SH0UppR23cEhoGbOA", "cBXdd3L4Tn3jkD4yvfRRobZ1BYmW6yv5losvqxY5");
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            startConvoList();
        }
        else {
            setContentView(R.layout.activity_welcome);

            Button signUp = (Button) findViewById(R.id.signUpButton);
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText usernameField = (EditText) findViewById(R.id.editText);
                    EditText passwordField = (EditText) findViewById(R.id.editText2);
                    String username = usernameField.getText().toString();
                    String password = passwordField.getText().toString();
                    if (!username.isEmpty() && !password.isEmpty()) {
                        ParseUser user = new ParseUser();
                        user.setEmail("raymond.kam@digiflare.com");
                        user.setUsername(username);
                        user.setPassword(password);
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Log.e(TAG, "Sign up successful");
                                } else {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        });
                    }
                }
            });

            Button signInButton = (Button) findViewById(R.id.signInButton);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText usernameField = (EditText) findViewById(R.id.editText);
                    EditText passwordField = (EditText) findViewById(R.id.editText2);
                    String username = usernameField.getText().toString();
                    String password = passwordField.getText().toString();
                    if (!username.isEmpty() && !password.isEmpty()) {
                        ParseUser.logInInBackground(username, password, new LogInCallback() {
                            @Override
                            public void done(ParseUser parseUser, ParseException e) {
                                if (parseUser != null) {
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, "Login succesful");
                                    startConvoList();
                                } else {
                                    Log.e(TAG, e.getMessage());
                                }
                            }
                        });
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startConvoList() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
