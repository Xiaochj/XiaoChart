package com.chart.xiao.xiaochart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.chart.xiao.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Histogram
 *
 * Created by cjxiao on 2016/6/20.
 */

public class HistogramView extends BaseView {

    private List<Integer> datas = new ArrayList<Integer>();

    public HistogramView(Context context) {
        super(context);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addDatas(List datas) {
        this.datas = datas;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setVerticalBackVisible(false);
        mPaint.setColor(getResources().getColor(R.color.linegraph_line));
        mPaint.setStyle(Paint.Style.FILL);
        if (datas.size() > 0) {
            mPaint.setStrokeWidth(2);
            for(int i = 0;i < datas.size();i++){
                if(datas.get(i) >= minY && datas.get(i) <= maxY) {
                    int tempYWhole = (datas.get(i) - minY) / scaleY;
                    int tempYSurplus = (datas.get(i) - minY) % scaleY;
                    float factY = spacing_y * (tempYWhole + (float) tempYSurplus / scaleY);
                    RectF rectF = new RectF(PADDING_LEHGTH_X + (i + (float)1 / 4) * (float)spacing_x, getMeasuredHeight() - PADDING_LEHGTH_Y,PADDING_LEHGTH_X + (i + (float)3 / 4) * (float)spacing_x,getMeasuredHeight()-PADDING_LEHGTH_Y-factY);
                    canvas.drawRect(rectF,mPaint);
                }
            }
        }
    }
    
}















