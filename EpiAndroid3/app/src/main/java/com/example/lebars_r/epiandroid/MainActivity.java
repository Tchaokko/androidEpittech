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
                        RequestParams testParam = new RequestParams();
                        testParam.put("token",userToken);
                        Intent profileIntent = new Intent(MainActivity.this, Profile.class);
                        startActivity(profileIntent);
                        MainActivity.this.finish();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        err.setVisibility(View.VISIBLE);
                        pwd.setText("");
                        Log.d("--FAILURE--", "ERROR");
                    }
                });
                }
        /*public void LogMe(View view) {
            RequestParams identifiant = new RequestParams();
            log = (EditText)findViewById(R.id.login_field);
            pwd = (EditText)findViewById(R.id.password_field);
            err = (EditText)findViewById(R.id.error_label);
            identifiant .put("login", log.getText().toString());
            identifiant .put("password", pwd.getText().toString());

            client.post("https://epitech-api.herokuapp.com/login", identifiant, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("--SUCCESS--", "SUCCESS");
                    err.setVisibility(View.INVISIBLE);
                    String response = new String(responseBody);
                    try{
                        JSONObject token = new JSONObject(response);
                        userToken = token.getString("token");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    Log.d("--TOKEN--", userToken);
                    RequestParams testParam = new RequestParams();
                    testParam.put("token",userToken);
                    Intent profileIntent = new Intent(MainActivity.this, Profile.class);
                    startActivity(profileIntent);

                    client.post("https://epitech-api.herokuapp.com/infos", testParam, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String response2 = new String(responseBody);
                            try {
                                JSONObject info = new JSONObject(response2);
                                String check = info.getString("board");
                                Log.d("--INFOS--", check);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.d("--FAILURE INFOS--", "Infos failure");
                        }
                    });
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    err.setVisibility(View.VISIBLE);
                    pwd.setText("");
                    Log.d("--FAILURE--", "ERROR");

                }
            });
        }*/

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
