package com.dision.android.rtlviewpager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String convertDateStringToReadableText(String timestamp) {

        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "cccc, d MMM";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(timestamp);
            str = outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return str;
    }
}
