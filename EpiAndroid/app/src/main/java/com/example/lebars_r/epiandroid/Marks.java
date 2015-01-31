package com.example.lebars_r.epiandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lebars_r on 31/01/2015.
 */
public class Marks {
   ArrayList<String> _list = new ArrayList<String>();
   int  length = 0;

   public void putItemInList(String item) {
       _list.add(item);
       length += 1;
    }
   public String getItemInList(int i){
       return _list.get(i);
   }
   public int getLength(){
    return length;
   }
   public ArrayList<String> getList(){
        return _list;
    }
}
