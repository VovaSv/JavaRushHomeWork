package com.javarush.test.Test2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vladimis on 8/7/2016.
 */
public class DateTime
{
    public static final String SLASHES_DATE_TIME_FORMAT = "dd/MM/yyyy HH:MM:SS";
    public static final String SLASHES_DATE_TIME_FORMAT2 = "dd/MM/yyyy HH:mm:ss";
    public static final String SLASHES_DATE_TIME_FORMAT3 = "EEEE dd MMM yyyy";

    public static final String SLASHES_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_WITH_TIMEZONE_ISO8601 = "yyyy-MM-dd'T'HH:mm:ssX";

    private static final String CONCAT_YEAR_MONTH_DAY = "yyyyMMdd";
    private static final String CONCAT_HOURS_MINUTES = "h:mm a";

    private static final String TIMESLOT_SUB_CUT_INDEX = ".";
    private static final int DATE_CUT_START = 0;
    private static final int DATE_CUT_END = 10;

    public static void main(String[] args)
    {
        Date output = null;
        SimpleDateFormat formatDate = new SimpleDateFormat(SLASHES_DATE_TIME_FORMAT3);
        try {
            System.out.println(formatDate.parse("Wednesday 17 Aug 2016"));
        }
        catch(java.text.ParseException parseEx){
            System.out.println(parseEx);
        }


    }
}
