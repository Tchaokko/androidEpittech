package com.example.lebars_r.epiandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by the cambio on 25/01/2015.
 */
public class Planning extends ActionBarActivity {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private User _user = new User();
    private RequestParams Param = new RequestParams();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_planning);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        Intent intent = getIntent();
        _user.setLogin(intent.getStringExtra("login"));
        _user.setPassword(intent.getStringExtra("pwd"));
        _user.setToken(intent.getStringExtra("token"));
        StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(pol);
        recupPlanning();
    }

    private void recupPlanning() {
        AsyncHttpClient client = new AsyncHttpClient();
        Param.put("token", _user.getToken());
        Param.put("start", "2015-01-26");
        Param.put("end", "2015-02-1");
        client.post("https://epitech-api.herokuapp.com/infos", Param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
                super.onSuccess(statusCode, headers, response);
                JSONObject data;
                TextView tmp = (TextView) findViewById(R.id.aff_planning);

                for (int i = 0; i < response.length(); ++i){
                    try {
                        data = response.getJSONObject(i);
                        String temp = data.getString("acti_title");
                        tmp.setText(tmp.getText() + "\n" + temp);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("--FAILURE PLANNING--", "Planning failure");

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
