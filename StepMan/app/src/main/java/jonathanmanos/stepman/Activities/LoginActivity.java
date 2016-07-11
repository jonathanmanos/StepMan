package jonathanmanos.stepman.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.database.Cursor;
import android.os.AsyncTask;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import jonathanmanos.stepman.Data.StepManCharacter;
import jonathanmanos.stepman.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    public static Activity logIn;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */


    // UI references.
    private AutoCompleteTextView nameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private String name;
    private SharedPreferences mPrefs;
    private boolean firstTime;
    private Spinner spinnerColor;
    private Spinner spinnerDifficulty;
    public final static String EXTRA_MESSAGE = "com.jonathanmanos.stepman.MESSAGE";

    private StepManCharacter stepMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        logIn = this;

        super.onCreate(savedInstanceState);

        stepMan = new StepManCharacter(getApplicationContext());

        name = stepMan.getStepManName();

        if(!name.isEmpty())
        {
            firstTime = false;
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
            logIn.finish();
        }
        else {
            firstTime = true;
            setContentView(R.layout.activity_login);

            spinnerColor = (Spinner)findViewById(R.id.spinnerColor);
            String[] colors = new String[]{"Black", "Red", "Green", "Blue", "Luigi"};
            ArrayAdapter<String>adapterColor = new ArrayAdapter<String>(LoginActivity.this,
                    android.R.layout.simple_spinner_item,colors);

            adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerColor.setAdapter(adapterColor);

            spinnerDifficulty = (Spinner)findViewById(R.id.spinnerDifficulty);
            String[] difficulties = new String[]{"Easy", "Normal", "Hard", "Impossible"};
            ArrayAdapter<String>adapterDifficulty = new ArrayAdapter<String>(LoginActivity.this,
                    android.R.layout.simple_spinner_item,difficulties);

            adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDifficulty.setAdapter(adapterDifficulty);

            // Set up the login form.
            nameView = (AutoCompleteTextView) findViewById(R.id.name);

            Button loginButton = (Button) findViewById(R.id.email_sign_in_button);
            loginButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNameValid(nameView.getText().toString())) {

                        // Store values at the time of the login attempt.
                        String name = nameView.getText().toString();
                        String color = spinnerColor.getSelectedItem().toString();
                        String difficulty = spinnerDifficulty.getSelectedItem().toString();

                        long unixStartTime = System.currentTimeMillis() / 1000L;
                        /*
                        mEditor.putLong("unixStartTime", unixStartTime);
                        mEditor.putString("name", name);
                        mEditor.putString("color", color);
                        mEditor.putString("difficulty", difficulty);
                        mEditor.apply();
                        */
                        stepMan.setStepManStepsAtUnixStartTime(unixStartTime);
                        stepMan.setStepManName(name);
                        stepMan.setStepManColor(color);
                        stepMan.setStepManDifficulty(difficulty);
                        stepMan.saveStepMan();

                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        EditText editText = (EditText) findViewById(R.id.name);
                        String message = editText.getText().toString();
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                    else{
                        nameView.setError(getString(R.string.error_field_required));
                    }
                }
            });

            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);
        }
    }

    private boolean isNameValid(String name) {
        return name.length() > 0;
    }

    public String getName() {
        return name;
    }

}




