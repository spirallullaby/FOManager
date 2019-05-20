package com.example.financemanager.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperMethods {

    public static Calendar GetCalendarFromString(String dateString) throws ParseException {
        Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]||3[0-1])-(0[1-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$");
        Matcher mat = pattern.matcher(dateString);
        if (!mat.matches()) {
            throw new ParseException("The date didn't match!", 0);
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(dateString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        } catch (ParseException ex) {
            throw ex;
        }
    }
}
