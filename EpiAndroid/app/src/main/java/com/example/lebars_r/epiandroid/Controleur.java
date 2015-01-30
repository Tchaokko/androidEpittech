package com.example.lebars_r.epiandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class Controleur extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private RequestParams Param = new RequestParams();
    private AsyncHttpClient client = new AsyncHttpClient();
    private User _user = new User();
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;



    private void affPicture()
    {
/*        RequestParams Param = new RequestParams();
        Param.put("token", _user.getToken());
        Param.put("login", _user.getLogin());
       client.get("https://epitech-api.herokuapp.com/photo", Param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("--SUCCESS--", "picture");
                ImageView tmp = (ImageView) findViewById(R.id.profile_picture);
                tmp.setVisibility(View.VISIBLE);
                try {
                    _user.setPhoto(response.getString("url").replaceAll(" ", ""));
                    Log.d("--URL--", _user.getPhoto());
                    URL url = new URL(_user.getPhoto());
                    Object data = url.getContent();
                    InputStream stream = (InputStream) data;
                    Drawable img = Drawable.createFromStream(stream, "src");
                    tmp.setImageDrawable(img);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/

/*            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("--ERROR--", "picture");
            }
        });*/
    }

    protected void recupMessage(){
/*        RequestParams Param = new RequestParams();
        Param.put("token", _user.getToken());
        client.post("https://epitech-api.herokuapp.com/messages", Param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("--SUCCESS--", "INFO BEGIN");
                JSONObject data;

              try {

                  TextView tmp = (TextView) findViewById(R.id.aff_message);
                    tmp.setText("Notification : ");
                    for (int i = 0; i < response.length(); ++i){
                        data = response.getJSONObject(i);
                        String temp = data.getString("title");
                        temp = temp.replaceAll("<([^<]*)>", "");
                        tmp.setText(tmp.getText()+ "\n----------------\n" + temp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
/*            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("--FAILURE INFOS--", "Infos failure");
            }
        });*/
    }

    private void handle_token_info(JSONObject response) {
        JSONObject data;
/*        try {
            data = response.getJSONObject("infos");
            _user.setUid(data.getString("uid"));
            _user.setGid(data.getString("gid"));
            _user.setSemester(data.getString("semester"));
            _user.setIp(response.getString("ip"));

            TextView tmp = (TextView) findViewById(R.id.aff_login);
            tmp.setText("login : " + _user.getLogin());

            tmp = (TextView)findViewById(R.id.aff_uid);
            tmp.setText("Uid : " +  _user.getUid());

            tmp = (TextView)findViewById(R.id.aff_gid);
            tmp.setText("Gid : " + _user.getGid());

            tmp = (TextView) findViewById(R.id.aff_semester);
            tmp.setText("semester : " + _user.getSemester() );

            tmp = (TextView) findViewById(R.id.aff_ip);
            tmp.setText("ip : " + _user.getIp());

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    private void handle_token_current(JSONObject response) {
        JSONObject data;
/*        try {
            data = response.getJSONObject("current");

            TextView tmp = (TextView) findViewById(R.id.aff_logtime);
            String temp = data.getString("active_log");
            String regex ="\\" + ".(.*$)";
            temp = temp.replaceAll(regex, "");
            _user.setLogtime(temp);
            tmp.setText("log time : " + _user.getLogtime() + "h");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    private void handleInfos()
    {
/*        RequestParams Param = new RequestParams();
        Log.d("--TOKEN--", _user.getToken());
        Param.put("token", _user.getToken());
        client.post("https://epitech-api.herokuapp.com/infos", Param, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("--SUCCESS--", "INFO BEGIN");
                handle_token_info(response);
                handle_token_current(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("--FAILURE INFOS--", "Infos failure");
            }
        });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controleur);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        Intent intent = getIntent();
        _user.setLogin("lebars_r"/*intent.getStringExtra("login")*/);
        _user.setPassword("toto"/*intent.getStringExtra("pwd")*/);
        _user.setToken("3evsebjkecjr7l3jcm71f4pmc7"/*intent.getStringExtra("token")*/);
        StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(pol);
        affPicture();
        handleInfos();
        recupMessage();
     //   Planning planning = new Planning();
        RequestParams Param = new RequestParams();
        Log.d("--TOKEN--", _user.getToken());
        Param.put("token", _user.getToken());

/*        client.post("https://epitech-api.herokuapp.com/infos", Param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("--SUCCESS--", "INFO BEGIN");
                JSONObject data;
                try {
                    data = response.getJSONObject("infos");
                    TextView tmp = (TextView) findViewById(R.id.aff_login);
                    tmp.setText("login : " + _user.getLogin());

                    tmp = (TextView)findViewById(R.id.aff_uid);
                    tmp.setText("uid :" + data.getString("uid"));
                    Log.d("--HISTORY--", "0");
                    data = response.getJSONObject("current");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("--FAILURE INFOS--", "Infos failure");
            }
        });*/
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = Login.newInstance();
                Log.d("'--DRAWER--", "CASE 1");
                break;
            case 1:
                fragment = Profile.newInstance();
                Log.d("'--DRAWER--", "CASE 2");
                break;
            case 2:
                fragment = Planning.newInstance();
                Log.d("'--DRAWER--", "CASE 3");
                break;
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        Log.d("'--DRAWER--", "SELECTED");

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.controleur, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_controleur, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Controleur) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
