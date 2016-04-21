package com.example.user.bikeecalendar.filter.calendar.month;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.bikeecalendar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-04-22.
 */
public class MonthAdapter extends RecyclerView.Adapter<DayViewHolder> {
    private List<DayItem> list;
    private OnMonthAdapterClickListener onMonthAdapterClickListener;

    public MonthAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_day, parent, false);

        DayViewHolder holder = new DayViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, final int position) {
        holder.setView(list.get(position));
        holder.setOnDayViewClickListener(new OnDayViewClickListener() {
            @Override
            public void onViewClick(View view) {
                onMonthAdapterClickListener.onCalendarAdapterClick(view, list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll (List<DayItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnMonthAdapterClickListener(OnMonthAdapterClickListener onMonthAdapterClickListener) {
        this.onMonthAdapterClickListener = onMonthAdapterClickListener;
    }
}
