package com.example.lebars_r.epiandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraCharacteristics;
import android.inputmethodservice.Keyboard;
import android.os.StrictMode;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Controleur extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private RequestParams Param;;
    private AsyncHttpClient client = new AsyncHttpClient();
    private Model _model = new Model();
    private My_view _view = new My_view();
    private Schedule My_schedule = new Schedule();
    private Integer week = 0;
    EditText err;
    EditText pwd;
    EditText log;
    boolean logged = false;
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
            Param = new RequestParams();
            Param.put("token", _model.getToken());
            Param.put("login", _model.getLogin());
            client.get("https://epitech-api.herokuapp.com/photo", Param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.d("--SUCCESS--", "picture");
                    try {
                        _model.setPhoto(response.getString("url").replaceAll(" ", ""));
                        Log.d("--URL--", _model.getPhoto());
                        URL url = new URL(_model.getPhoto());
                        Object data = url.getContent();
                        InputStream stream = (InputStream) data;
                        Drawable img = Drawable.createFromStream(stream, "src");
                        _model.setUserPicture(img);
                        _view.put_picture_in_view((ImageView) findViewById(R.id.profile_picture), _model.getUserPicture());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d("--ERROR--", "picture");
                    _model.setFailSetup(true);
                }
            });

    }

    protected void recupMessage(){
            RequestParams Param = new RequestParams();
            Param.put("token", _model.getToken());
            client.post("https://epitech-api.herokuapp.com/messages", Param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.d("--SUCCESS--", "INFO BEGIN");
                    JSONObject data;

                    try {
                        String str;
                        str = "Notification : ";
                        for (int i = 0; i < response.length(); ++i) {
                            data = response.getJSONObject(i);
                            String tmp = data.getString("title");
                            tmp = tmp.replaceAll("<([^<]*)>", "");
                            str = str + "\n----------------\n" + tmp;
                        }
                        _model.setNotification(str);
                        _view.put_data_in_view((TextView) findViewById(R.id.aff_message), _model.getNotification());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d("--FAILURE INFOS--", "Infos failure");
                    _model.setFailSetup(true);
                }
            });
    }

    private void handle_token_info(JSONObject response) {
        JSONObject data;
        try {
            data = response.getJSONObject("infos");
            _model.setUid(data.getString("uid"));
            _model.setGid(data.getString("gid"));
            _model.setSemester(data.getString("semester"));
            _model.setIp(response.getString("ip"));

            _view.put_data_in_view((TextView) findViewById(R.id.aff_login), "Login : " + _model.getLogin());
            _view.put_data_in_view((TextView)findViewById(R.id.aff_uid), "Uid : " +  _model.getUid());
            _view.put_data_in_view((TextView)findViewById(R.id.aff_gid), "Gid : " +  _model.getGid());
            _view.put_data_in_view((TextView) findViewById(R.id.aff_semester), "Semester : " + _model.getSemester());
            _view.put_data_in_view((TextView)findViewById(R.id.aff_ip), "Ip : " +  _model.getIp());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handle_token_current(JSONObject response) {
        JSONObject data;
        try {
            data = response.getJSONObject("current");
            String tmp = data.getString("active_log");
            String regex ="\\" + ".(.*$)";
            tmp = tmp.replaceAll(regex, "");
            _model.setLogtime(tmp);
            _view.put_data_in_view((TextView)findViewById(R.id.aff_logtime), "Log time : " +  _model.getLogtime() + "h");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handleInfos() {
        RequestParams Param = new RequestParams();
        Log.d("--TOKEN--", _model.getToken());
        Param.put("token", _model.getToken());
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
                _model.setFailSetup(true);
            }
        });
    }


    public void LogMe(View view) {
        logged = false;
        pwd = (EditText) findViewById(R.id.password_field);
        log = (EditText) findViewById(R.id.login_field);
        err = (EditText) findViewById(R.id.error_label);
        _model.setLogin(log.getText().toString());
        _model.setPassword(pwd.getText().toString());
        Param = new RequestParams();
        Param.put("login", _model.getLogin());
        Param.put("password", _model.getPassword());
        client.post("https://epitech-api.herokuapp.com/login", Param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                err.setVisibility(View.INVISIBLE);
                logged = true;
                _model.setIsSetupUser(false);
                Log.d("--SUCCESS--", "SUCCESS");
                try {
                    _model.setToken(response.getString("token"));
                    Log.d("--TOKEN--", _model.getToken());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onNavigationDrawerItemSelected(1);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                err.setVisibility(View.VISIBLE);
                _view.put_data_in_view(pwd, "");
                Log.d("--FAILURE--", "ERROR");
            }
        });
    }

    private void aff_profile(){
       affPicture();
       handleInfos();
       recupMessage();
        if (!_model.isFailSetup()) {
            _model.setIsSetupUser(true);
            _model.setFailSetup(false);
        }
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

        StrictMode.ThreadPolicy pol = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(pol);

    }



    private void sortPlanning(JSONArray Planning){
        //String aff_planning;
        String      start;
        String      end;
/*        Calendar cal = Calendar.getInstance();
        List<List<String>> data = new ArrayList<>();
        List<List<JSONObject>> myList;*/
        LinearLayout layout = (LinearLayout) findViewById(R.id.planningLayout);
        layout.removeAllViews();
        String aff;
        Integer y = 0;
        for (Integer i = 1; i < Planning.length(); i++) {
            try {
                final JSONObject temp = Planning.getJSONObject(i);
                if (temp.getString("module_registered") == "true") {
                    y++;
                    LinearLayout newLayout = new LinearLayout(getApplicationContext());
                    newLayout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView activityName = new TextView(getApplicationContext());
                    newLayout.addView(activityName);
                    final ImageButton[] subscribe = new ImageButton[3];
                    subscribe[0] = new ImageButton(getApplicationContext());
                    subscribe[0].setImageResource(R.drawable.sub);
                    //subscribe[0].setBackgroundResource();
                    subscribe[1] = new ImageButton(getApplicationContext());
                    subscribe[1].setImageResource(R.drawable.unsub);
                    subscribe[2] = new ImageButton(getApplicationContext());
                    subscribe[2].setImageResource(R.drawable.token);
                    for (Integer index = 0; index < subscribe.length; index++){
                        newLayout.addView(subscribe[index]);
                        final Integer finalIndex = index;
                        subscribe[index].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                subscribePressed(temp, finalIndex);
                            }
                        });
                    }
                    layout.addView(newLayout);
                    start = temp.getString("start");
                    end = temp.getString("end");
                    aff = start + "\n" + end + "\n" + temp.getString("acti_title") + "\n";
                    _view.put_data_in_view(activityName, aff);
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        Log.d("--SIZE--",y.toString() );
    }

    private void subscribePressed(JSONObject temp, Integer finalIndex) {
        if (finalIndex == 0)
            subscribeActivities(temp);
        else if (finalIndex == 1)
            unsubscribeActivities(temp);
        else
            sendToken(temp);
    }
    private void subscribeActivities(JSONObject temp){

        try {
            RequestParams param = new RequestParams();
            param.put("token",_model.getToken());
            param.put("scolaryear",temp.getString("scolaryear"));
            param.put("codemodule",temp.getString("codemodule"));
            param.put("codeinstance", temp.getString("codeinstance"));
            param.put("codeevent", temp.getString("codeevent"));
            param.put("codeacti",temp.getString("codeacti"));
            client.post("https://epitech-api.herokuapp.com/event", param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("--GREAT SUCCESS--", "NEW SUB");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("--FAIL--", "CAN'T SUB");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void sendToken(final JSONObject temp) {
        try {
            final PopupWindow popup = new PopupWindow(this);
            LinearLayout test =  (LinearLayout) findViewById(R.id.planningLayout);
            EditText text = new EditText(getApplicationContext());
            text.setKeyListener(DigitsKeyListener.getInstance());
            text.setOnEditorActionListener(new TextView.OnEditorActionListener(){
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (actionId){
                        case EditorInfo.IME_NULL:
                            String text = v.getText().toString();
                            RequestParams param = new RequestParams();
                            param.put("token",_model.getToken());
                            try {
                                param.put("scolaryear",temp.getString("scolaryear"));
                                param.put("codemodule",temp.getString("codemodule"));
                                param.put("codeinstance",temp.getString("codeinstance"));
                                param.put("codeacti",temp.getString("codeacti"));
                                param.put("codeevent", temp.getString("codeevent"));
                                param.put("tokenvalidationcode", text);
                                client.post("https://epitech-api.herokuapp.com/token", Param, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        popup.dismiss();
                                        Log.d("--GREAT SUCCESS--", "GOOD TOKEN");
                                    }
                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                        Log.d("--FAIL--", "WRONG TOKEN");
                                    }
                                });
                                } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("--QUIT--", "PLEASE");
                    }
                    return false;
                }
            });
            popup.setContentView(text);
            popup.getContentView().setFocusableInTouchMode(true);
            popup.setFocusable(true);
            popup.update(50,50,600,100);
            popup.showAtLocation(test, Gravity.CENTER, 10, 10);
            Log.d("--TOKEN--", temp.getString("acti_title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void unsubscribeActivities(JSONObject temp) {
        try {
            Log.d("--UNSUB--", temp.getString("acti_title"));
            RequestParams param = new RequestParams();
            param.put("token",_model.getToken());
            Header[] headers = new Header[0];
            param.put("scolaryear",temp.getString("scolaryear"));
            param.put("codemodule",temp.getString("codemodule"));
            param.put("codeinstance",temp.getString("codeinstance"));
            param.put("codeacti",temp.getString("codeacti"));
            param.put("codeevent", temp.getString("codeevent"));
            Log.d("--TEST--", temp.getString("acti_title"));
            client.delete(this, "https://epitech-api.herokuapp.com/event", headers, param, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("--GREAT SUCCESS--", "UN SUB");
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("--FAIL--", "CAN'T UN SUB");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void prevWeek(View v){
            Log.d("--PREVWEEK--", "check");
            week -= 1;
            aff_planning();
        }

    public void nextWeek(View v){
        Log.d("--NEXTWEEK--", "check");
        week +=1;
        aff_planning();
    }

    private void aff_planning(){
        Param = new RequestParams();
        Integer dayOfWeek;
        Param.put("token", _model.getToken());
        Param.put("start",My_schedule.getWeekStart(week));
        Param.put("end",  My_schedule.getWeekEnd(week));
        My_schedule.setStart(2);
        client.get("https://epitech-api.herokuapp.com/planning", Param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("--SUCCESS--", "SUCCESS");
                sortPlanning(response);
                Log.d("--TOKEN--", _model.getToken());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                err.setVisibility(View.VISIBLE);
                Log.d("--FAILURE--", "ERROR");
            }
        });

        }

   @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        if (position > 0 && !logged) {
            return ;
        }
        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = Login.newInstance();
                logged = false;
                break;
            case 1:
                fragment = Profile.newInstance();
                aff_profile();
                break;
            case 2:
                fragment = Planning.newInstance();
                aff_planning();
                break;
            case 3:
                fragment = Grades.newInstance();
                aff_grade();
                break;

        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private void aff_grade() {
        RequestParams Param = new RequestParams();
        Param.put("token", _model.getToken());
        client.get("https://epitech-api.herokuapp.com/marks", Param, new JsonHttpResponseHandler() {
           @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONObject data;
                try {
                    String str = "";
                    JSONArray tmp = response.getJSONArray("notes");
                    for (int i = 0; i < tmp.length(); ++i) {
                        data = tmp.getJSONObject(i);
                        str = data.getString("titlemodule") + "\n";
                        str += data.getString("title") + "\n";
                        str += data.getString("final_note") + "\n";
                        str += data.getString("correcteur") + "\n";
                        str += data.getString("date") + "\n";
                        str += "\n----------\n";
                        _model.putItemInList(str);
                        }
                    Integer taat = _model.getLengthFromMarks();
                    Log.d("TEST-LENGTH", taat.toString());
                    str = "";
                   for (int i = _model.getLengthFromMarks() - 1; i >= 0; i--){
                    str  +=  _model.getItemInList(i);
                   }
                    _view.put_data_in_view((TextView) findViewById(R.id.aff_grade), str);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("--TEST--", "1");
            }
        });
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
            case 4:
                mTitle = getString(R.string.title_section4);
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
