package com.example.user.bikeecalendar.filter.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.user.bikeecalendar.R;
import com.example.user.bikeecalendar.filter.calendar.month.DayItem;
import com.example.user.bikeecalendar.filter.calendar.month.MonthAdapter;
import com.example.user.bikeecalendar.filter.calendar.month.OnMonthAdapterClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2016-04-22.
 */
public class CalendarPagerAdapter extends PagerAdapter {
    @Bind(R.id.view_calendar_pager_year_month_text_view)
    TextView yearMonthTextView;

    private List<CalendarPagerItem> list;
    private OnCalendarPagerAdapterClickListener onCalendarPagerAdapterClickListener;

    private static final String TAG = "CALENDAR_P_ADAPTER";

    public CalendarPagerAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_calendar_pager, null);

        ButterKnife.bind(this, view);

        SimpleDateFormat simpleYearMonthFormat = new SimpleDateFormat("yyyy.MM");
        yearMonthTextView.setText(simpleYearMonthFormat
                .format(list.get(position)
                        .getCurrentStartDate()
                        .getTime()));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        CustomGridLayoutManager customGridLayoutManager = new CustomGridLayoutManager(container.getContext(), 7, GridLayout.VERTICAL, false);

        recyclerView.setLayoutManager(customGridLayoutManager);

        MonthAdapter monthAdapter = new MonthAdapter();
        monthAdapter.setOnMonthAdapterClickListener(new OnMonthAdapterClickListener() {
            @Override
            public void onCalendarAdapterClick(View view, DayItem item) {
                onCalendarPagerAdapterClickListener.onCustomPagerAdapterClick(view, item);
            }
        });
        monthAdapter.addAll(getCalenderItems(position));

        recyclerView.setAdapter(monthAdapter);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @OnClick({R.id.view_calendar_pager_prev_image_view,
            R.id.view_calendar_pager_next_image_view})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_calendar_pager_prev_image_view:
                onCalendarPagerAdapterClickListener.prev(view);
                break;
            case R.id.view_calendar_pager_next_image_view:
                onCalendarPagerAdapterClickListener.next(view);
                break;
        }
    }

    public void add(CalendarPagerItem item) {
        list.add(item);
    }

    public void setOnCalendarPagerAdapterClickListener(OnCalendarPagerAdapterClickListener onCalendarPagerAdapterClickListener) {
        this.onCalendarPagerAdapterClickListener = onCalendarPagerAdapterClickListener;
    }

    private List<DayItem> getCalenderItems(int position) {
        List<DayItem> dayItems = new ArrayList<>();

        SimpleDateFormat simpleDayOfTheWeekFormat = new SimpleDateFormat("E", java.util.Locale.getDefault());
        SimpleDateFormat simpleDayFormat = new SimpleDateFormat("dd", java.util.Locale.getDefault());

        String beforeEndDayOfTheWeek = simpleDayOfTheWeekFormat
                .format(list.get(position)
                        .getBeforeEndDate()
                        .getTime());
//        String currentStartDayOfTheWeek = simpleDayOfTheWeekFormat
//                .format(list.get(position)
//                        .getCurrentStartDate()
//                        .getTime());
//        String currentEndDayOfTheWeek = simpleDayOfTheWeekFormat
//                .format(list.get(position)
//                        .getCurrentEndDate()
//                        .getTime());
        String afterStartDayOfTheWeek = simpleDayOfTheWeekFormat
                .format(list.get(position)
                        .getAfterStartDate()
                        .getTime());
        int beforeEndDay = Integer.parseInt(simpleDayFormat
                .format(list.get(position)
                        .getBeforeEndDate()
                        .getTime()));
//        int currentStartDay = Integer.parseInt(simpleDayFormat
//                .format(list.get(position)
//                        .getCurrentStartDate()
//                        .getTime()));
        int currentEndDay = Integer.parseInt(simpleDayFormat
                .format(list.get(position)
                        .getCurrentEndDate()
                        .getTime()));
//        int afterStartDay = Integer.parseInt(simpleDayFormat
//                .format(list.get(position)
//                        .getAfterStartDate()
//                        .getTime()));

        int beforeCount;
        switch (beforeEndDayOfTheWeek) {
            case "일":
                beforeCount = 1;
                break;
            case "월":
                beforeCount = 2;
                break;
            case "화":
                beforeCount = 3;
                break;
            case "수":
                beforeCount = 4;
                break;
            case "목":
                beforeCount = 5;
                break;
            case "금":
                beforeCount = 6;
                break;
            case "토":
                beforeCount = 0;
                break;
            default:
                beforeCount = 0;
                break;
        }

        int afterCount;
        switch (afterStartDayOfTheWeek) {
            case "일":
                afterCount = 0;
                break;
            case "월":
                afterCount = 6;
                break;
            case "화":
                afterCount = 5;
                break;
            case "수":
                afterCount = 4;
                break;
            case "목":
                afterCount = 3;
                break;
            case "금":
                afterCount = 2;
                break;
            case "토":
                afterCount = 1;
                break;
            default:
                afterCount = 0;
                break;
        }

        for (int i = 1; i <= beforeCount; i++)
            dayItems.add(i - 1, new DayItem(beforeEndDay - (beforeCount - i), false));
        for (int i = 1; i <= currentEndDay; i++)
            dayItems.add(beforeCount + (i - 1), new DayItem(i, true));
        for (int i = 1; i <= afterCount; i++)
            dayItems.add(beforeCount + currentEndDay + (i - 1), new DayItem(i, false));

        return dayItems;
    }

    private class CustomGridLayoutManager extends GridLayoutManager {
        public CustomGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }
}
