package com.example.katya.berrytextchat.Model;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Katya on 9/12/15.
 */
public class Message implements Comparable<Message>{

    private String text;
    private Date timestamp;
    private int userId;
    private String timeFormat;

    public Message (int id, String text, Date date){
        this.userId = id;
        this.text = text;
        this.timestamp = date;
        timeFormat = "dd/MM/yyyy, HH:mm:ss";
    }

    public Message (){

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        return sdf.format(timestamp);
    }

    public String getTimeFormat(){return timeFormat;}

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(@NonNull Message message) {
       int cmp = this.userId - message.userId;
        if(cmp != 0) {return cmp;}
        cmp = this.text.compareTo(message.text);
        if(cmp != 0 ) {return cmp;}
        return this.timestamp.compareTo(message.timestamp);
    }

    public static class Comparators {
        public static final Comparator<Message> ID = new Comparator<Message>() {
            @Override
            public int compare(Message msg1, Message msg2) {
                return msg1.userId - msg2.userId;
            }
        };
        public static final Comparator<Message> DATE = new Comparator<Message>() {
            @Override
            public int compare(Message msg1, Message msg2) {
                return msg1.timestamp.compareTo(msg2.timestamp);
            }
        };
    }
}
