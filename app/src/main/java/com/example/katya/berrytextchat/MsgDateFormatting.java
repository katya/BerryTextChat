package com.example.katya.berrytextchat;

import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Katya on 9/12/15.
 */
public class MsgDateFormatting extends SimpleDateFormat{
    public static final String MSG_FULL_FORMAT = "dd/MM/yyyy, HH:mm:ss";
    public static final String MSG_DAY_FORMAT = "dd/MM/yyyy";
    String currentFormat;

    public MsgDateFormatting(){
        super();
    }
    public MsgDateFormatting(String format){
        super(format);
        currentFormat = format;
    }


    public Date getCurrentDate(View view){
        Date currentDate=null;
        TextView date = (TextView) view.findViewById(R.id.timestamp);
        String time = date.getText().toString();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(MSG_FULL_FORMAT);
            currentDate = sdf.parse(time);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return currentDate;
    }


    public boolean isSameDay(Date date1, Date date2, int addDays) {
        Calendar cal1 = GregorianCalendar.getInstance();
        Calendar cal2 = GregorianCalendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        // use second date for transformation:
        if(addDays != 0) {
            cal2.add(Calendar.DATE, addDays);
        }
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public String setMsgSeparator(Date date){
        String day;
        Date today = new Date();
        if(isSameDay(date, today, 0)){
            day = "Today";
        }
        else if(isSameDay(date, today, -1)) {
            day = "Yesterday";
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat(MSG_DAY_FORMAT);
            day = sdf.format(date);
        }
        return day;
    }

    public Date formatStringToDate(String str, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
