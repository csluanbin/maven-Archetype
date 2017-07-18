package com.maven.luanbin.archetype.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luanbin on 17/6/23.
 */
public class DateUtil {

    public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

    public static Date stringToDate(String dateStr, String format){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date date = formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dataToString(Date date){

        if(date == null){
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_TIME);
        String dateString = formatter.format(date);
        return dateString;
    }
}
