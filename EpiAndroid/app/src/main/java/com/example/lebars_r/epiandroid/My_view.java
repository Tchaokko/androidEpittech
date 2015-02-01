package com.example.lebars_r.epiandroid;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lebars_r on 30/01/2015.
 */
public class My_view {
    public void put_data_in_view(TextView _view, String _data){
        if (_view != null){
        _view.setText(_data);}
    }
    public void put_picture_in_view(ImageView _view, Drawable _img){
            if (_view != null) {
                _view.setImageDrawable(_img);
            }
    }

    public void add_linear_layout_to_linear_layout(LinearLayout layout, LinearLayout layout_to_add){
        if (layout != null && layout_to_add != null){
            layout.addView(layout_to_add);
        }
    }

    public void remove_view_to_layout(LinearLayout layout){
        layout.removeAllViews();
    }
}