package com.example.katya.berrytextchat;

import android.content.Context;
import android.database.MatrixCursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katya.berrytextchat.Model.Message;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Katya on 9/12/15.
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgViewHolder> {

    private LayoutInflater inflater;
    private MatrixCursor mCursor;
    MsgDateFormatting formatting;

    public MsgAdapter(Context context){
        inflater = LayoutInflater.from(context);
        formatting = new MsgDateFormatting();

    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.msg_row, parent, false);
        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        if(mCursor== null) {
            throw  new IllegalStateException("this should only be called when the cursor is valid");
        }
        if(!mCursor.moveToPosition(position)) {
            throw  new IllegalStateException("this should only be called when the cursor is valid");
        }
        Message msgItem = createMsgFromCursor(mCursor);

        holder.textView.setText(msgItem.getText());
        holder.dataView.setText(msgItem.getTimeAsString());

        holder.compositeMsg(msgItem);
    }

    private Message createMsgFromCursor(MatrixCursor mCursor){
        Message msg = new Message();

        msg.setUserId(mCursor.getInt(1));
        msg.setText(mCursor.getString(2));
        msg.setTimestamp(formatting.formatStringToDate(mCursor.getString(3), msg.FORMAT));
        return msg;
    }

    @Override
    public int getItemCount() {
        if(mCursor!=null) {
            return mCursor.getCount();
        }
        return 0;
    }

    public void setData(MatrixCursor cursor) {
        mCursor = cursor;
    }
}
