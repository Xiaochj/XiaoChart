package com.chart.xiao.xiaochart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chart.xiao.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Histogram
 *
 * Created by cjxiao on 2016/6/20.
 */

public class HistogramView extends BaseView{

    private List<Integer> mDatas = new ArrayList<Integer>();
    private Holder mHolder;
    private List<Holder> mHolderList = new ArrayList<Holder>();

    public HistogramView(Context context) {
        super(context);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addDatas(List datas) {
        this.mDatas = datas;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setVerticalBackVisible(false);
        mPaint.setColor(getResources().getColor(R.color.linegraph_line));
        mPaint.setStyle(Paint.Style.FILL);
        if (mDatas.size() > 0) {
            mPaint.setStrokeWidth(2);
            for(int i = 0;i < mDatas.size();i++){
                mHolder = new Holder();
                if(mDatas.get(i) >= minY && mDatas.get(i) <= maxY) {
                    int tempYWhole = (mDatas.get(i) - minY) / scaleY;
                    int tempYSurplus = (mDatas.get(i) - minY) % scaleY;
                    float factY = spacing_y * (tempYWhole + (float) tempYSurplus / scaleY);
                    RectF rectF = new RectF(PADDING_LEHGTH_X + (i + (float)1 / 4) * (float)spacing_x, getMeasuredHeight() - PADDING_LEHGTH_Y,PADDING_LEHGTH_X + (i + (float)3 / 4) * (float)spacing_x,getMeasuredHeight()-PADDING_LEHGTH_Y-factY);
                    mHolder.leftX = PADDING_LEHGTH_X + (i + (float)1 / 4) * (float)spacing_x;
                    mHolder.rightX = PADDING_LEHGTH_X + (i + (float)3 / 4) * (float)spacing_x;
                    mHolder.bottomY = getMeasuredHeight() - PADDING_LEHGTH_Y;
                    mHolder.topY = getMeasuredHeight()-PADDING_LEHGTH_Y-factY;
                    mHolderList.add(mHolder);
                    canvas.drawRect(rectF,mPaint);
                }
            }
        }
    }

    PopupWindow popupWindow;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float downX = event.getX();
        float downY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for(int i = 0;i < mHolderList.size();i++) {
                    if (downX >= mHolderList.get(i).leftX && downX <= mHolderList.get(i).rightX && downY >= mHolderList.get(i).topY && downY <= mHolderList.get(i).bottomY) {
                        TextView tv = new TextView(this.getContext());
                        tv.setLayoutParams(new ViewGroup.LayoutParams(-2,-2));
                        tv.setText(mDatas.get(i)+"");
                        tv.setBackgroundColor(Color.GRAY);
                        tv.setPadding(20,5,20,5);
                        popupWindow = new PopupWindow(tv,tv.getLayoutParams().width,tv.getLayoutParams().height);
                        popupWindow.showAtLocation(this, Gravity.NO_GRAVITY,(int)mHolderList.get(i).leftX+dipToPx(20),(int)mHolderList.get(i).topY+dipToPx(70));
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if(popupWindow.isShowing())
                    popupWindow.dismiss();
                break;
        }
        return true;
    }

    private int dipToPx(int dipValue){
        final float scale = this.getContext().getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    class Holder{
        float leftX;
        float rightX;
        float topY;
        float bottomY;
    }
}















