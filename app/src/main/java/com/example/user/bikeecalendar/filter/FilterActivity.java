package com.example.user.bikeecalendar.filter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.user.bikeecalendar.R;
import com.example.user.bikeecalendar.filter.calendar.CalendarPagerAdapter;
import com.example.user.bikeecalendar.filter.calendar.CalendarPagerItem;
import com.example.user.bikeecalendar.filter.calendar.month.DayItem;
import com.example.user.bikeecalendar.filter.calendar.OnCalendarPagerAdapterClickListener;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnCalendarPagerAdapterClickListener {
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private CalendarPagerAdapter calendarPagerAdapter;
    private Calendar calendar;
    private Date beforeEndDate;
    private Date currentStartDate;
    private Date currentEndDate;
    private Date afterStartDate;
    private Date afterEndDate;

    private static final String TAG = "FILTER_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ButterKnife.bind(this);

        calendarPagerAdapter = new CalendarPagerAdapter();
        calendarPagerAdapter.setOnCalendarPagerAdapterClickListener(this);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        beforeEndDate = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        currentStartDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        currentEndDate = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        afterStartDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        afterEndDate = calendar.getTime();
        Log.d(TAG, "beforeEndDate : " + beforeEndDate
                + "\ncurrentStartDate : " + currentStartDate
                + "\ncurrentEndDate : " + currentEndDate
                + "\nafterStartDate : " + afterStartDate
                + "\nafterEndDate : " + afterEndDate);
        for (int i = 0; i <= 12; i++) {
            calendarPagerAdapter.add(new CalendarPagerItem(beforeEndDate, currentStartDate, currentEndDate, afterStartDate));

            beforeEndDate = currentEndDate;
            currentStartDate = afterStartDate;
            currentEndDate = afterEndDate;
            calendar.add(Calendar.DATE, 1);
            afterStartDate = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            afterEndDate = calendar.getTime();
            Log.d(TAG, "beforeEndDate : " + beforeEndDate
                    + "\ncurrentStartDate : " + currentStartDate
                    + "\ncurrentEndDate : " + currentEndDate
                    + "\nafterStartDate : " + afterStartDate
                    + "\nafterEndDate : " + afterEndDate);
        }

        viewPager.setAdapter(calendarPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    int position;
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.position = position;
        Log.d(TAG, "position : " + position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void prev(View view) {
        if (position > 0)
        viewPager.setCurrentItem(position - 1);
    }

    @Override
    public void next(View view) {
        if (position < 12)
        viewPager.setCurrentItem(position + 1);
    }

    @Override
    public void onCustomPagerAdapterClick(View view, DayItem item) {
        Log.d(TAG, "day : " + item.getDay());
    }
}
