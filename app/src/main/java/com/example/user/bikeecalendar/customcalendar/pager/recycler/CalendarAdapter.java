package com.example.user.bikeecalendar.customcalendar.pager.recycler;

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
public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    List<CalendarItem> list;
    OnCalendarAdapterClickListener onCalendarAdapterClickListener;

    public CalendarAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_calendar, parent, false);

        CalendarViewHolder holder = new CalendarViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, final int position) {
        holder.setView(list.get(position));
        holder.setOnCalendarViewClickListener(new OnCalendarViewClickListener() {
            @Override
            public void onViewClick(View view) {
                onCalendarAdapterClickListener.onCalendarAdapterClick(view, list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public void add(int dd) {
//        list.add(new CalendarItem(dd));
//        notifyDataSetChanged();
//    }

    public void addAll (List<CalendarItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnCalendarAdapterClickListener(OnCalendarAdapterClickListener onCalendarAdapterClickListener) {
        this.onCalendarAdapterClickListener = onCalendarAdapterClickListener;
    }
}
