package com.example.katya.berrytextchat;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.katya.berrytextchat.Model.Message;

/**
 * Created by Katya on 9/12/15.
 * TBD - change image position to the right from message
 */
public class MsgViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textView;
    TextView dataView;
    View msgBoxView;
    View msgRowView;

    public MsgViewHolder(View view) {
        super(view);
        this.msgRowView = view.findViewById(R.id.msg_row);
        this.msgBoxView  = view.findViewById(R.id.msg_box_layout);
        this.imageView = (ImageView) view.findViewById(R.id.chat_image);
        this.textView = (TextView) view.findViewById(R.id.text);
        this.dataView = (TextView) view.findViewById(R.id.timestamp);
    }

    public void compositeMsg(Message msgItem) {
        LinearLayout root_msg = (LinearLayout) msgBoxView.getParent();
        LinearLayout root_img = (LinearLayout) imageView.getParent();

        if((msgItem!= null) && msgItem.getUserId() == 0)
        {
            root_msg.setGravity(Gravity.RIGHT);
            root_img.setGravity(Gravity.RIGHT);
            msgBoxView.setBackgroundResource(R.drawable.msg_box_green);
            root_img.removeView(imageView);
            imageView.setBackgroundResource(R.drawable.ic_berry_green);
            root_img.addView(imageView);

        }
    }


}