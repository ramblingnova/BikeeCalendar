package com.example.user.bikeecalendar.filter.calendar.month;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2016-04-22.
 */
public class DayItem {
    @Getter
    @Setter(AccessLevel.PUBLIC)
    int day;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    boolean valid;

    public DayItem(int day, boolean valid) {
        this.day = day;
        this.valid = valid;
    }
}
