package com.example.user.bikeecalendar.filter.calendar.month;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.bikeecalendar.R;
import com.example.user.bikeecalendar.etc.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2016-04-22.
 */
public class DayViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.view_holder_day_day_text_view)
    TextView dayTextView;

    private OnDayViewClickListener onDayViewClickListener;

    public DayViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void setView(DayItem item) {
        dayTextView.setText("" + item.getDay());
        if (!item.isValid()) {
            if (Build.VERSION.SDK_INT < 23) {
                dayTextView.setTextColor(MyApplication.getmContext().getResources().getColor(R.color.bikeeLightGray));
            } else {
                dayTextView.setTextColor(MyApplication.getmContext().getResources().getColor(R.color.bikeeLightGray, null));
            }
        }
    }

    @OnClick(R.id.view_holder_day_day_text_view)
    void onClick(View view) {
        onDayViewClickListener.onViewClick(view);
    }

    public void setOnDayViewClickListener(OnDayViewClickListener onDayViewClickListener) {
        this.onDayViewClickListener = onDayViewClickListener;
    }
}
