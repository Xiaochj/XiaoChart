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

    //注释的方块宽度
    private static final int RECTF_WIDTH = 20;
    //注释方块间的距离
    private static final int RECTF_PADDING = 5;

    private List<Float> mPies = new ArrayList<Float>();
    //转换为百分比的数据
    private List<Float> mTempPies = new ArrayList<Float>();
    //存储颜色的list
    private List<Integer> mPieColors = new ArrayList<Integer>();

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
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
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
            tempPaint.setAntiAlias(true);
            RectF rectF = new RectF(PADDING_LEHGTH_X, PADDING_LEHGTH_Y, PADDING_LEHGTH_X + 2 * radius, PADDING_LEHGTH_Y + 2 * radius);
            //一定要从-90°开始，不然会出现最后一个圆弧画不出来的bug
            float tempStart = -90;
            int tempColor = 0;
            for (int i = 0; i < mTempPies.size(); i++){
                tempColor = Color.argb(255, (int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
                mPieColors.add(tempColor);
                tempPaint.setColor(tempColor);
                float tempEnd = mTempPies.get(i);
                canvas.drawArc(rectF, tempStart, tempEnd, true, tempPaint);
                canvas.drawArc(rectF, tempStart, tempEnd, true, mPaint);
                tempStart += tempEnd;
            }
            Paint fontPaint = new Paint();
            fontPaint.setStyle(Paint.Style.STROKE);
            fontPaint.setAntiAlias(true);
            fontPaint.setTextSize(16);
            fontPaint.setColor(getResources().getColor(android.R.color.tertiary_text_dark));
            for(int j = 0;j < mPieColors.size();j++){
                tempPaint.setColor(mPieColors.get(j));
                RectF colorRectF = new RectF(2 * (PADDING_LEHGTH_X + radius),PADDING_LEHGTH_Y+j*(RECTF_WIDTH+RECTF_PADDING),2 * (PADDING_LEHGTH_X + radius)+RECTF_WIDTH,PADDING_LEHGTH_Y+j*(RECTF_WIDTH+RECTF_PADDING)+RECTF_WIDTH);
                canvas.drawRoundRect(colorRectF,3,3,tempPaint);
                canvas.drawText(String.valueOf(5*mTempPies.get(j)/18).substring(0,5)+"%",2*(PADDING_LEHGTH_X + radius)+RECTF_WIDTH+RECTF_PADDING,PADDING_LEHGTH_Y+j*(RECTF_WIDTH+RECTF_PADDING)+fontPaint.getFontMetrics().descent-fontPaint.getFontMetrics().ascent,fontPaint);
            }
        }
        mPieColors = null;
    }
}
