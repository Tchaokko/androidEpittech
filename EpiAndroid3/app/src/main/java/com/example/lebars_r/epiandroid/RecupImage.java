package com.example.lebars_r.epiandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
    private String url;
    private ImageView viewer;

    public void setViewer(ImageView viewer) {
        this.viewer = viewer;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    protected void onPreExecute() {
        //display progress dialog.

    }

    @Override
    protected Void doInBackground(Void... params) {
        final DefaultHttpClient client = new DefaultHttpClient();
        final HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w("ImageDownloader", "Error " + statusCode +
                        " while retrieving bitmap from " + url);
                return null;
            }
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    // getting contents from the stream
                    inputStream = entity.getContent();

                    // decoding stream data back into image Bitmap that android understands
                    picture = BitmapFactory.decodeStream(inputStream);


                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            // You Could provide a more explicit error message for IOException
            getRequest.abort();
            Log.e("ImageDownloader", "Something went wrong while" +
                    " retrieving bitmap from " + url + e.toString());
        }

            return null;
    }

    /*        URL my_url = null;
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
        return null;*/

    public Bitmap getPicture(){
       return this.picture;
   }
    protected void onPostExecute(Void result) {
        // dismiss progress dialog and update ui
        if (picture != null){
            viewer.setImageBitmap(picture);
        }
    }
}
