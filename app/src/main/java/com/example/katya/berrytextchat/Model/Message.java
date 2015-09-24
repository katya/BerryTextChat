package com.example.katya.berrytextchat.Model;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Katya on 9/12/15.
 */
public class Message{

    private String text;
    private Date timestamp;
    private int userId;
    public final String FORMAT = "dd/MM/yyyy, HH:mm:ss";

    public Message (int id, String text, Date date){
        this.userId = id;
        this.text = text;
        this.timestamp = date;
    }

    public Message (){
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public String getTimeAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(timestamp);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
