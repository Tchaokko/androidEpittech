package com.example.lebars_r.epiandroid;

import com.loopj.android.http.RequestParams;

import android.app.Activity;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    EditText log;
    EditText pwd;
    EditText err;
    String userToken;
    AsyncHttpClient client = new AsyncHttpClient();
    User student = new User();


    private final List<JSONObject> list = new ArrayList<JSONObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
}
            public void LogMe(View view) {
                RequestParams identifiant = new RequestParams();
                log = (EditText)findViewById(R.id.login_field);
                pwd = (EditText)findViewById(R.id.password_field);
                err = (EditText)findViewById(R.id.error_label);
                identifiant .put("login", log.getText().toString());
                identifiant .put("password", pwd.getText().toString());

                client.post("https://epitech-api.herokuapp.com/login", identifiant, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        err.setVisibility(View.INVISIBLE);
                        Log.d("--SUCCESS--", "SUCCESS");
                        try {
                            userToken = response.getString("token");
                            Log.d("--TOKEN--", userToken);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent profileIntent = new Intent(MainActivity.this, Profile.class);
                        profileIntent.putExtra("login",log.getText().toString());
                        profileIntent.putExtra("pwd", log.getText().toString());
                        profileIntent.putExtra("token",userToken);
                        startActivity(profileIntent);
                        MainActivity.this.finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        err.setVisibility(View.VISIBLE);
                        pwd.setText("");
                        Log.d("--FAILURE--", "ERROR");
                    }
                });
                }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
