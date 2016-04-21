package com.example.user.bikeecalendar.customcalendar.pager.recycler;

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
public class CalendarViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.view_holder_calendar_dd)
    TextView dd;

    OnCalendarViewClickListener onCalendarViewClickListener;

    public CalendarViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void setView(CalendarItem item) {
        dd.setText("" + item.getDd());
        if (!item.isValid()) {
            if (Build.VERSION.SDK_INT < 23) {
                dd.setTextColor(MyApplication.getmContext().getResources().getColor(R.color.bikeeLightGray));
            } else {
                dd.setTextColor(MyApplication.getmContext().getResources().getColor(R.color.bikeeLightGray, null));
            }
        }
    }

    @OnClick(R.id.view_holder_calendar_dd)
    void onClick(View view) {
        onCalendarViewClickListener.onViewClick(view);
    }

    public void setOnCalendarViewClickListener(OnCalendarViewClickListener onCalendarViewClickListener) {
        this.onCalendarViewClickListener = onCalendarViewClickListener;
    }
}
