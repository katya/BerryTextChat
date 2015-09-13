package com.example.katya.berrytextchat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.Date;

/**
 * An ItemDecoration that draws dividers between items
 * Check how to change padding between msgs
 */

public class MsgSeparator extends RecyclerView.ItemDecoration {
    private Drawable mSeparator;

    public MsgSeparator(Context context){
        mSeparator = context.getResources().getDrawable(R.drawable.msg_separator, null);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        MsgDateFormatting formatting = new MsgDateFormatting();

        for (int i = 0; i < childCount; i++) {
            boolean doSeparate = true;
            View child = parent.getChildAt(i);

           // have previous view:
            Date current = formatting.getCurrentDate(child);
            if(i != 0) {
                Date previous = formatting.getCurrentDate(parent.getChildAt(i - 1));
                if(formatting.isSameDay(current, previous, 0)){
                    doSeparate = false;
                }
            }
            // separator before message:
            if(doSeparate) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getTop() - params.topMargin - (mSeparator.getIntrinsicHeight()/2);
                int bottom = top + mSeparator.getIntrinsicHeight();

                String strDate = formatting.setMsgSeparator(current);
                Paint datePaint = buildPaint();
                mSeparator.setBounds(left, top, right, bottom);

                mSeparator.draw(c);
                int textX = ((right - left)/2);
                c.drawText(strDate, textX, bottom, datePaint);
            }
        }
    }

    private Paint buildPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);

        return paint;
    }
}
