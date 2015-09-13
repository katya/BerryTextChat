package com.example.katya.berrytextchat.Model;

import android.content.Context;
import android.util.Log;

import com.example.katya.berrytextchat.MsgAdapter;
import com.example.katya.berrytextchat.MsgDateFormatting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Katya on 9/12/15.
 */
public enum MsgDataStore {

    INSTANCE;

    private MsgAdapter cursorAdapter = null;


    private ArrayList<Message> dataSet = addStaticData();
    private Context activity;


    MsgDataStore() {
        addStaticData();

    }

    public static MsgDataStore getInstance()
    {
        return INSTANCE;
    }

    public void setRecyclerAdapter(MsgAdapter adapter){
        cursorAdapter = adapter;
    }


    private  static ArrayList<Message> addStaticData() {
        MsgDateFormatting formatting = new MsgDateFormatting(MsgDateFormatting.MSG_FULL_FORMAT);
        ArrayList<Message> mDataSet = new ArrayList<>();
        try {
            mDataSet.add(new Message(1, "Message1", formatting.parse("12/09/2015, 00:00:01")));
            mDataSet.add(new Message(0, "Message1", formatting.parse("11/09/2015, 00:00:01")));
            mDataSet.add(new Message(0, "Message1", formatting.parse("13/09/2015, 00:00:01")));
            mDataSet.add(new Message(1, "Message1", formatting.parse("10/09/2015, 00:00:01")));
            mDataSet.add(new Message(1, "Message1", formatting.parse("12/09/2015, 00:00:02")));
            mDataSet.add(new Message(0, "Message1", formatting.parse("12/09/2015, 00:00:03")));
            mDataSet.add(new Message(1, "Message1", formatting.parse("12/09/2015, 00:00:04")));
            mDataSet.add(new Message(0, "Message1", formatting.parse("12/09/2015, 00:00:01")));
            mDataSet.add(new Message(1, "Message1", formatting.parse("11/09/2015, 00:00:01")));
            mDataSet.add(new Message(1, "Message1", formatting.parse("13/09/2015, 00:00:01")));
            mDataSet.add(new Message(1, "Message1", formatting.parse("10/09/2015, 00:00:01")));
            mDataSet.add(new Message(0, "Message1", formatting.parse("12/09/2015, 00:00:02")));
            mDataSet.add(new Message(1, "Message1", formatting.parse("12/09/2015, 00:00:03")));
            mDataSet.add(new Message(0, "Message1", formatting.parse("12/09/2015, 00:00:04")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Collections.sort(mDataSet, Message.Comparators.DATE);
        return mDataSet;
    }

    public void addMessage(Message msg){
        Log.v("MsgDataStore", "Add message: "+ msg.getText() + "of user: "+ msg.getUserId() + "to DB");
        dataSet.add(msg);
        cursorAdapter.notifyDataSetChanged();
    }

    public ArrayList<Message> getList(){
        return dataSet;
    }

}
