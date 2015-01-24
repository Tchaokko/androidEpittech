package com.example.lebars_r.epiandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by the cambio on 24/01/2015.
 */
public class RecupImage extends AsyncTask<Void,Void,Void> {
    private Bitmap picture;

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    protected void onPreExecute() {
        //display progress dialog.

    }

    @Override
    protected Void doInBackground(Void... params) {
        URL my_url = null;
        try {
            my_url = new URL(this.url);
            HttpURLConnection my_connection = (HttpURLConnection) my_url.openConnection();
            my_connection.setDoInput(true);
            my_connection.connect();
            InputStream input = my_connection.getInputStream();
            picture = BitmapFactory.decodeStream(input);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   public Bitmap getPicture(){
       return this.picture;
   }
    protected void onPostExecute(Void result) {
        // dismiss progress dialog and update ui
    }
}
