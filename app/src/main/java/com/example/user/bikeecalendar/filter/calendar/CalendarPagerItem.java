package com.example.user.bikeecalendar.filter.calendar;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2016-04-22.
 */
public class CalendarPagerItem {
    @Getter
    @Setter(AccessLevel.PUBLIC)
    Date beforeEndDate;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    Date currentStartDate;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    Date currentEndDate;
    @Getter
    @Setter(AccessLevel.PUBLIC)
    Date afterStartDate;

    public CalendarPagerItem(Date beforeEndDate, Date currentStartDate, Date currentEndDate, Date afterStartDate) {
        this.beforeEndDate = beforeEndDate;
        this.currentStartDate = currentStartDate;
        this.currentEndDate = currentEndDate;
        this.afterStartDate = afterStartDate;
    }
}
