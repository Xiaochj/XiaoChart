package com.chart.xiao.xiaochart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.chart.xiao.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Pie Chart
 *
 * Created by cjxiao on 2016/6/22.
 */

public class PieChartView extends BaseView {

    private List<Float> mPies = new ArrayList<Float>();
    //转换为百分比的数据
    private List<Float> mTempPies = new ArrayList<Float>();

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addDatas(List datas) {
        this.mPies = datas;
        int tempSum = 0;
        for(int i = 0;i < mPies.size();i++){
            tempSum += mPies.get(i);
        }
        for(int j = 0;j<mPies.size();j++){
            mTempPies.add(360*mPies.get(j)/tempSum);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //将背景坐标系干掉
        setRemoveXY(true);
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(R.color.linegraph_line));
        mPaint.setStyle(Paint.Style.STROKE);
        int radius = 0;
        if(getMeasuredHeight()< getMeasuredWidth()){
            radius = getMeasuredHeight()/2-PADDING_LEHGTH_Y;
        }else{
            radius = getMeasuredWidth()/2-PADDING_LEHGTH_X;
        }
        canvas.drawCircle(PADDING_LEHGTH_X+radius,PADDING_LEHGTH_Y+radius,radius,mPaint);
        if(mPies.size() > 0) {
            Paint tempPaint = new Paint();
            tempPaint.setStyle(Paint.Style.FILL);
            tempPaint.setColor(Color.argb(255, (int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random())));
            canvas.drawArc(new RectF(PADDING_LEHGTH_X, PADDING_LEHGTH_Y, PADDING_LEHGTH_X + 2 * radius, PADDING_LEHGTH_Y + 2 * radius), 0, mTempPies.get(0), true, tempPaint);
            float tempStart = 0, tempEnd = mTempPies.get(0);
            for (int i = 1; i < mTempPies.size(); i++){
                tempPaint.setColor(Color.argb(255, (int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random())));
                tempStart += mTempPies.get(i - 1);
                tempEnd += mTempPies.get(i);
                canvas.drawArc(new RectF(PADDING_LEHGTH_X, PADDING_LEHGTH_Y, PADDING_LEHGTH_X + 2 * radius, PADDING_LEHGTH_Y + 2 * radius), tempStart, tempEnd, true, tempPaint);
            }
        }
    }
}
