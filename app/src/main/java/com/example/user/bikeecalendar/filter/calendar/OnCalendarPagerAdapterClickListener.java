package com.example.user.bikeecalendar.filter.calendar;

import android.view.View;

import com.example.user.bikeecalendar.filter.calendar.month.DayItem;

/**
 * Created by User on 2016-04-22.
 */
public interface OnCalendarPagerAdapterClickListener {
    void onCustomPagerAdapterClick(View view, DayItem item);
    void prev(View view);
    void next(View view);
}
