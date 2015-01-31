package com.example.lebars_r.epiandroid;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lebars_r on 30/01/2015.
 */
public class My_view {
    public void put_data_in_view(TextView _view, String _data){
        if (_view == null){
            Log.d("--VIEW--", "NULL POINTER TEXT " + _data);
        }
        else {
        _view.setText(_data);}
    }
    public void put_picture_in_view(ImageView _view, Drawable _img){
            if (_view == null) {
                Log.d("--VIEW--", "NULL POINTER DRAWER");
            }
            else {
                _view.setImageDrawable(_img);

            }
    }
 /*  public void put_Adapter_in_listview(ListView _view, ArrayAdapter _data){
        _view.setAdapter(_data);
    }*/
}
