package com.example.user.bikeecalendar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatedCalendarActivity extends AppCompatActivity implements CalendarPickerView.OnDateSelectedListener {
    @Bind(R.id.calendar_content)
    View calendarContent;

    private CalendarPickerView currentCalendarPickerView, oldCalendarPickerView;
    private Calendar calendar;
    private Date start, end, select;
    private Animation anim;
    private boolean isStartDatePicked = false;
    private boolean isEndDatePicked = false;
    boolean openStartCalendar = false;
    boolean openEndCalendar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_calendar);

        ButterKnife.bind(this);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        start = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        end = calendar.getTime();

        currentCalendarPickerView = (CalendarPickerView) findViewById(R.id.current_calendar_view);
        currentCalendarPickerView.setOnDateSelectedListener(this);
        oldCalendarPickerView = (CalendarPickerView) findViewById(R.id.old_calendar_view);
        currentCalendarPickerView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        oldCalendarPickerView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        oldCalendarPickerView.setVisibility(View.GONE);
    }

    @OnClick({R.id.calendar_start_date_summary,
            R.id.calendar_end_date_summary})
    void openCloseCalendar(View view) {
        switch (view.getId()) {
            case R.id.calendar_start_date_summary:
                if (openStartCalendar) {
                    openStartCalendar = false;
                    closeCalendar();
                } else {
                    openStartCalendar = true;
                    if (openEndCalendar) {
                        openEndCalendar = false;
                        closeCalendar();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                openCalendar();
                            }
                        }, 500);
                    } else {
                        openCalendar();
                    }
                }
                break;
            case R.id.calendar_end_date_summary:
                if (openEndCalendar) {
                    openEndCalendar = false;
                    closeCalendar();
                } else {
                    openEndCalendar = true;
                    if (openStartCalendar) {
                        openStartCalendar = false;
                        closeCalendar();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                openCalendar();
                            }
                        }, 500);
                    } else {
                        openCalendar();
                    }
                }
                break;
        }
    }

    public void openCalendar() {
        anim = AnimationUtils.loadAnimation(this, R.anim.calendar_top_in);
        anim.setFillAfter(true);
        calendarContent.startAnimation(anim);
        calendarContent.setVisibility(View.VISIBLE);
    }

    public void closeCalendar() {
        anim = AnimationUtils.loadAnimation(this, R.anim.calendar_top_out);
        anim.setFillAfter(true);
        calendarContent.startAnimation(anim);
        calendarContent.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                calendarContent.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @OnClick({R.id.activity_filter_calendar_prev_button,
            R.id.activity_filter_calendar_next_button})
    void changeCalendarMonth(View view) {
        int currentViewId = view.getId();

        oldCalendarPickerView.setVisibility(View.VISIBLE);
        oldCalendarPickerView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        if (select != null && select.getTime() >= start.getTime() && select.getTime() <= end.getTime()) {
            oldCalendarPickerView.selectDate(select);
        }

        calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MONTH,
                (currentViewId == R.id.activity_filter_calendar_prev_button) ? -1 : 1);
        start = calendar.getTime();
        calendar.setTime(end);
        calendar.add(Calendar.MONTH,
                (currentViewId == R.id.activity_filter_calendar_prev_button) ? -1 : 1);
        end = calendar.getTime();
        currentCalendarPickerView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        if (select != null && select.getTime() >= start.getTime() && select.getTime() <= end.getTime()) {
            currentCalendarPickerView.selectDate(select);
        }

        anim = AnimationUtils.loadAnimation(this,
                (currentViewId == R.id.activity_filter_calendar_prev_button) ? R.anim.calendar_left_in : R.anim.calendar_right_in);
        anim.setFillAfter(true);
        currentCalendarPickerView.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this,
                (currentViewId == R.id.activity_filter_calendar_prev_button) ? R.anim.calendar_right_out : R.anim.calendar_left_out);
        anim.setFillAfter(false);
        oldCalendarPickerView.startAnimation(anim);
        oldCalendarPickerView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                oldCalendarPickerView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void onDateSelected(Date date) {
        if (isStartDatePicked == false) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Toast.makeText(this, "State Date : " + simpleDateFormat.format(date), Toast.LENGTH_SHORT).show();
            select = date;
            isStartDatePicked = true;
        } else if (isEndDatePicked == false) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Toast.makeText(this, "End Date : " + simpleDateFormat.format(date), Toast.LENGTH_SHORT).show();
            select = date;
            isEndDatePicked = true;
        }
    }

    @Override
    public void onDateUnselected(Date date) {
    }
}
