package com.example.katya.berrytextchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katya.berrytextchat.Model.Message;

import java.util.Collections;
import java.util.List;

/**
 * Created by Katya on 9/12/15.
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgViewHolder> {

    private LayoutInflater inflater;
    private List<Message> dataSet = Collections.emptyList();

    public MsgAdapter(Context context, List<Message> list){
        dataSet = list;
        inflater = LayoutInflater.from(context);
        setHasStableIds(true);

    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.msg_row, parent, false);
        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        Message msgItem = dataSet.get(position);
        holder.textView.setText(msgItem.getText());
        holder.dataView.setText(msgItem.getTimeAsString());
        holder.compositeMsg(msgItem);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    @Override
    public long getItemId(int position) {
        return dataSet.get(position).hashCode();
    }

}
