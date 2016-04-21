package com.example.user.bikeecalendar.customcalendar;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.user.bikeecalendar.R;
import com.example.user.bikeecalendar.customcalendar.pager.CustomPagerAdapter;
import com.example.user.bikeecalendar.customcalendar.pager.CustomPagerItem;
import com.example.user.bikeecalendar.customcalendar.pager.OnArrowClickListener;
import com.example.user.bikeecalendar.customcalendar.pager.recycler.CalendarItem;
import com.example.user.bikeecalendar.customcalendar.pager.recycler.OnCustomPagerAdapterClickListener;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomCalendarActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnArrowClickListener, OnCustomPagerAdapterClickListener {
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private static final String TAG = "CUSTOM_C_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar);

        ButterKnife.bind(this);

        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter();
        customPagerAdapter.setOnArrowClickListener(this);
        customPagerAdapter.setOnCustomPagerAdapterClickListener(this);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        Date beforeEndDate = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date currentStartDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date currentEndDate = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date afterStartDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date afterEndDate = calendar.getTime();
        Log.d(TAG, "beforeEndDate : " + beforeEndDate
                + "\ncurrentStartDate : " + currentStartDate
                + "\ncurrentEndDate : " + currentEndDate
                + "\nafterStartDate : " + afterStartDate
                + "\nafterEndDate : " + afterEndDate);
        for (int i = 0; i <= 12; i++) {
            customPagerAdapter.add(new CustomPagerItem(beforeEndDate, currentStartDate, currentEndDate, afterStartDate));

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

        viewPager.setAdapter(customPagerAdapter);
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
    public void onCustomPagerAdapterClick(View view, CalendarItem item) {
        Log.d(TAG, "dd : " + item.getDd());
    }
}
