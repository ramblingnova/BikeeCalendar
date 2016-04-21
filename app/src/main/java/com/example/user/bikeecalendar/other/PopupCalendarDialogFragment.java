package com.example.user.bikeecalendar.other;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.user.bikeecalendar.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2016-04-20.
 */
public class PopupCalendarDialogFragment extends DialogFragment {
    @Bind(R.id.fragment_popup_calendar_dialog_old_calendar_picker_view)
    CalendarPickerView oldCalendarView;
    @Bind(R.id.fragment_popup_calendar_dialog_current_calendar_picker_view)
    CalendarPickerView currentCalendarView;

    private Calendar calendar;
    private Date start;
    private Date end;
    private Animation anim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popup_calendar_dialog, container, false);

        ButterKnife.bind(this, view);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        start = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        end = calendar.getTime();

        currentCalendarView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        oldCalendarView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
        oldCalendarView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Dialog dialog = getDialog();
        dialog.getWindow().setLayout(
                getResources().getDimensionPixelSize(R.dimen.fragment_popup_calendar_dialog_width),
                getResources().getDimensionPixelSize(R.dimen.fragment_popup_calendar_dialog_height)
        );
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        params.x = 0;
        params.y = 0;
        dialog.getWindow().setAttributes(params);
    }

    @OnClick({R.id.fragment_popup_calendar_prev_button,
            R.id.fragment_popup_calendar_next_button})
    void changeCalendarMonth(View view) {
        int currentViewId = view.getId();

        oldCalendarView.setVisibility(View.VISIBLE);
        oldCalendarView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);

        calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MONTH,
                (currentViewId == R.id.fragment_popup_calendar_prev_button) ? -1 : 1);
        start = calendar.getTime();
        calendar.setTime(end);
        calendar.add(Calendar.MONTH,
                (currentViewId == R.id.fragment_popup_calendar_prev_button) ? -1 : 1);
        end = calendar.getTime();
        currentCalendarView.init(start, end)
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);

        anim = AnimationUtils.loadAnimation(getContext(),
                (currentViewId == R.id.fragment_popup_calendar_prev_button) ? R.anim.calendar_left_in : R.anim.calendar_right_in);
        anim.setFillAfter(true);
        currentCalendarView.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(getContext(),
                (currentViewId == R.id.fragment_popup_calendar_prev_button) ? R.anim.calendar_right_out : R.anim.calendar_left_out);
        anim.setFillAfter(false);
        oldCalendarView.startAnimation(anim);

        oldCalendarView.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                oldCalendarView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
