package com.example.lebars_r.epiandroid;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by the cambio on 31/01/2015.
 */
public class Schedule {
    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getWeekStart(int add){
        Calendar calendar = Calendar.getInstance();
        Integer dayOfWeek;
        calendar.add(Calendar.DATE, 7 * add);
        dayOfWeek = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        String weekStart = year.toString() + "-" + month.toString() + "-" + day.toString();
        return (weekStart);
    }

    public String getWeekEnd(int add){
        Calendar calendar = Calendar.getInstance();
        Integer dayOfWeek;
        calendar.add(Calendar.DATE, 7 * add);
        dayOfWeek = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.add(Calendar.DATE, 6);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        String weekEnd = year.toString() + "-" + month.toString() + "-" + day.toString();
        return (weekEnd);
    }

    Integer start;
}
