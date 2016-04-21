package com.example.user.bikeecalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.bikeecalendar.filter.FilterActivity;
import com.example.user.bikeecalendar.other.AnimatedCalendarActivity;
import com.example.user.bikeecalendar.other.PopupCalendarDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private PopupCalendarDialogFragment dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.activity_main_animated_calendar_button,
            R.id.activity_main_popup_calendar_button,
            R.id.activity_main_custom_calendar_button})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_animated_calendar_button:
                intent = new Intent(this, AnimatedCalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_popup_calendar_button:
                dialog = new PopupCalendarDialogFragment();
                dialog.show(getSupportFragmentManager(), "TAG");
                break;
            case R.id.activity_main_custom_calendar_button:
                intent = new Intent(this, FilterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
