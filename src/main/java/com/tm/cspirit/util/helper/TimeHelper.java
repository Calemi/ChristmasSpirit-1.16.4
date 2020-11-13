package com.tm.cspirit.util.helper;

import java.util.Calendar;

public class TimeHelper {

    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static String getFormattedDay(int day) {

        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (day % 100) {
            case 11:
            case 12:
            case 13:
                return day + "th";
            default:
                return day + sufixes[day % 10];
        }
    }
}
