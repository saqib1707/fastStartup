package com.example.saqib1707.faststartup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity {
    Button login;
    CheckBox check;
    EditText user, pwd;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.button_login);
        user = (EditText) findViewById(R.id.editText_userid);
        pwd = (EditText) findViewById(R.id.editText_password);
        check=(CheckBox)findViewById(R.id.checkBox);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (user.getText().toString().equals("saqib1707") && pwd.getText().toString().equals("dishani")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong ID", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private void saveInSharedPreferences()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        prefs
                .edit()
                .putString(PREF_USER_NAME, user)
                .putString(PREF_PASSWORD, pwd)
                .apply();
    }
    private void loadFromSharedPreferences()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        user = prefs.getString(PREF_USER_NAME, "NONE");
        pwd = prefs.getString(PREF_PASSWORD, "NONE");
        check.setChecked(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.saqib1707.faststartup/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
        loadFromSharedPreferences();

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.saqib1707.faststartup/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
        if (check.isChecked()) {
            saveInSharedPreferences();
        }
    }
}
