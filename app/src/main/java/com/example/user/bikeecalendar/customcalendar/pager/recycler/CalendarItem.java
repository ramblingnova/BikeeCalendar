package com.example.user.bikeecalendar.customcalendar.pager.recycler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2016-04-22.
 */
public class CalendarItem {
    @Getter
    @Setter(AccessLevel.PUBLIC)
    int dd;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    boolean valid;

    public CalendarItem(int dd, boolean valid) {
        this.dd = dd;
        this.valid = valid;
    }
}
