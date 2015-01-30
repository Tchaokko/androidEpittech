package com.example.lebars_r.epiandroid;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lebars_r on 30/01/2015.
 */
public class My_view {
    public void put_data_in_view(TextView _view, String _data){
        _view.setText(_data);
    }
    public void put_picture_in_view(ImageView _view, Drawable _img){
        _view.setImageDrawable(_img);
    }
}
