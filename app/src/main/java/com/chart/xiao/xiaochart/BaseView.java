package com.chart.xiao.xiaochart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.chart.xiao.sample.R;

import java.util.Arrays;
import java.util.List;

/**
 * 所有view的父view，绘出基本的二维坐标系
 * Created by cjxiao on 2016/6/20.
 */

public abstract class BaseView extends View {

    //竖直方向坐标到边界的距离
    protected static int PADDING_LEHGTH_Y = 25;
    //水平方向坐标到边界距离
    protected static int PADDING_LEHGTH_X = 25;
    //x轴坐标点分成多少分
    protected static int DIVISION_X =6;
    //y轴坐标点分成多少分
    protected static final int DIVISION_Y = 6;
    //y轴每段的实际代表长度
    protected int scaleY = 0;
    //屏幕上x轴和y轴的等分距离px
    protected int spacing_x = 0,spacing_y = 0;
    //是否显示垂直和水平背景线
    protected boolean isVerticalBackVisible = true,isHorizontalBackVisible = true;

    protected Paint mPaint;
    protected int maxY = 8,minY = 0;
    protected List<String> dateX = Arrays.asList("1","2","3","4","5","6","7");

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 抽象方法，往坐标系中添加数据
     * @param datas
     */
    public abstract void addDatas(List datas);

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//		this.setMeasuredDimension(widthSize, heightSize);
    }

    /**
     * 加入y轴的最大值和最小值，便于计算间距
     * @param maxY
     * @param minY
     */
    public void addMaxAndMinY(int maxY,int minY){
        this.maxY = maxY;
        this.minY = minY;
    }

    /**
     * 加入x轴的坐标
     * @param dateX
     */
    public void addDateX(List<String> dateX){
        this.dateX = dateX;
        DIVISION_X = dateX.size()-1;
    }

    public boolean isVerticalBackVisible() {
        return isVerticalBackVisible;
    }

    public void setVerticalBackVisible(boolean verticalBackVisible) {
        isVerticalBackVisible = verticalBackVisible;
    }

    public boolean isHorizontalBackVisible() {
        return isHorizontalBackVisible;
    }

    public void setHorizontalBackVisible(boolean horizontalBackVisible) {
        isHorizontalBackVisible = horizontalBackVisible;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        //设置是否有锯齿
        mPaint.setAntiAlias(true);
        //设置画笔的宽度
        mPaint.setStrokeWidth((float) 1.0);
        //设置画笔的字体大小，20px
        mPaint.setTextSize(20);
        float fontHeight = mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top;
        //y轴每段的实际代表长度
        scaleY = (maxY-minY)/DIVISION_Y;
        PADDING_LEHGTH_X = (int) (mPaint.measureText(String.valueOf((DIVISION_Y-1)*scaleY+minY))+5);
        //屏幕上x轴的等分距离px
        spacing_x = (getMeasuredWidth()-PADDING_LEHGTH_X-5) / DIVISION_X;
        //屏幕上y轴的等分距离px
        spacing_y = (getMeasuredHeight()-PADDING_LEHGTH_Y) / DIVISION_Y;
        //画y轴的坐标点
        mPaint.setTextAlign(Paint.Align.RIGHT);//设置文本居右对齐
        mPaint.setColor(getResources().getColor(R.color.linegraph_text));
        canvas.drawText(minY+"", mPaint.measureText(minY+""), getMeasuredHeight()-PADDING_LEHGTH_Y, mPaint);
        for(int y = 1; y < DIVISION_Y; y++){
            canvas.drawText(String.valueOf(y*scaleY+minY), mPaint.measureText(String.valueOf(y*scaleY+minY)), getMeasuredHeight()-PADDING_LEHGTH_Y-spacing_y*y+10, mPaint);
        }
        canvas.drawText(String.valueOf(maxY), mPaint.measureText(String.valueOf(maxY)), getMeasuredHeight()-PADDING_LEHGTH_Y-spacing_y*DIVISION_Y+15, mPaint);
        //画x轴坐标点
        mPaint.setTextAlign(Paint.Align.CENTER);//设置文本居中对齐
        canvas.drawText(dateX.get(0), PADDING_LEHGTH_X, getMeasuredHeight(), mPaint);
        for(int x = 1;x < DIVISION_X; x++){
            canvas.drawText(dateX.get(x), PADDING_LEHGTH_X+spacing_x*x, getMeasuredHeight(), mPaint);
        }
        canvas.drawText(dateX.get(DIVISION_X), PADDING_LEHGTH_X+spacing_x*(DIVISION_X)-5, getMeasuredHeight(), mPaint);
        //设置画笔颜色
        mPaint.setColor(getResources().getColor(R.color.linegraph_xy_divider));
        //画y轴坐标
        canvas.drawLine(PADDING_LEHGTH_X, getMeasuredHeight()-PADDING_LEHGTH_Y, PADDING_LEHGTH_X, 0, mPaint);
        //画背景的竖线条纹
        if(isVerticalBackVisible()) {
            for (int i = 1; i <= DIVISION_X; i++) {
                canvas.drawLine(PADDING_LEHGTH_X + spacing_x * i, getMeasuredHeight() - PADDING_LEHGTH_Y, PADDING_LEHGTH_X + spacing_x * i, 0, mPaint);
            }
        }
        //画x轴坐标
        mPaint.setColor(getResources().getColor(R.color.linegraph_text));
        canvas.drawLine(PADDING_LEHGTH_X,getMeasuredHeight()-PADDING_LEHGTH_Y, getMeasuredWidth(), getMeasuredHeight()-PADDING_LEHGTH_Y, mPaint);
        //画背景的横线条纹
        if(isHorizontalBackVisible()) {
            mPaint.setColor(getResources().getColor(R.color.linegraph_xy_divider));
            for (int i = 1; i <= DIVISION_Y; i++) {
                canvas.drawLine(PADDING_LEHGTH_X, getMeasuredHeight() - PADDING_LEHGTH_Y - spacing_y * i, getMeasuredWidth(), getMeasuredHeight() - PADDING_LEHGTH_Y - spacing_y * i, mPaint);
            }
        }
    }
}
