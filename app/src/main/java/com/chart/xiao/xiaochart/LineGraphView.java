package com.chart.xiao.xiaochart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.chart.xiao.sample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Line Graph
 *
 * created by xiaochj
 */

public class LineGraphView extends View {

	//竖直方向坐标到边界的距离
	private static int PADDING_LEHGTH_Y = 25;
	//水平方向坐标到边界距离
	private static int PADDING_LEHGTH_X = 25;
	//x轴坐标点分成多少分
	private static int DIVISION_X =6;
	//y轴坐标点分成多少分
	private static final int DIVISION_Y = 6;

	private Paint mPaint;
	private List<Point> mPoint = new ArrayList<Point>();
	private int maxY = 8,minY = 0;
	private List<String> dateX = Arrays.asList("1","2","3","4","5","6","7");

	public LineGraphView(Context context,List<Point> point){
		super(context);
		this.mPoint = point;
	}

	public LineGraphView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public LineGraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LineGraphView(Context context) {
		super(context);
	}

	/**
	 * 往里面添加点
	 * @param point
	 */
	public void addPoints(List<Point> point){
		this.mPoint = point;
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
		int scaleY = (maxY-minY)/DIVISION_Y;
		PADDING_LEHGTH_X = (int) (mPaint.measureText(String.valueOf((DIVISION_Y-1)*scaleY+minY))+5);
		//屏幕上x轴的等分距离px
		int spacing_x = (getMeasuredWidth()-PADDING_LEHGTH_X) / DIVISION_X;
		//屏幕上y轴的等分距离px
		int spacing_y = (getMeasuredHeight()-PADDING_LEHGTH_Y) / DIVISION_Y;
		//画y轴的坐标点
		mPaint.setTextAlign(Paint.Align.RIGHT);//设置文本居右对齐
		mPaint.setColor(getResources().getColor(R.color.linegraph_text));
		canvas.drawText(minY+"", mPaint.measureText(minY+""), getMeasuredHeight()-PADDING_LEHGTH_Y, mPaint);
		for(int y = 1; y < DIVISION_Y; y++){
			canvas.drawText(String.valueOf(y*scaleY+minY), mPaint.measureText(String.valueOf(y*scaleY+minY)), getMeasuredHeight()-PADDING_LEHGTH_Y-spacing_y*y+10, mPaint);
		}
		canvas.drawText(String.valueOf(DIVISION_Y*scaleY+minY), mPaint.measureText(String.valueOf(DIVISION_Y*scaleY+minY)), getMeasuredHeight()-PADDING_LEHGTH_Y-spacing_y*DIVISION_Y+15, mPaint);
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
		for(int i = 1;i<=DIVISION_X;i++){
			canvas.drawLine(PADDING_LEHGTH_X+spacing_x*i, getMeasuredHeight()-PADDING_LEHGTH_Y, PADDING_LEHGTH_X+spacing_x*i, 0, mPaint);
		}
		//画x轴坐标
		mPaint.setColor(getResources().getColor(R.color.linegraph_text));
		canvas.drawLine(PADDING_LEHGTH_X,getMeasuredHeight()-PADDING_LEHGTH_Y, getMeasuredWidth(), getMeasuredHeight()-PADDING_LEHGTH_Y, mPaint);
		//画背景的横线条纹
		mPaint.setColor(getResources().getColor(R.color.linegraph_xy_divider));
		for(int i = 1;i<=DIVISION_Y;i++){
			canvas.drawLine(PADDING_LEHGTH_X,getMeasuredHeight()-PADDING_LEHGTH_Y-spacing_y*i, getMeasuredWidth(), getMeasuredHeight()-PADDING_LEHGTH_Y-spacing_y*i, mPaint);
		}
		mPaint.setColor(getResources().getColor(R.color.linegraph_line));
		mPaint.setStyle(Paint.Style.STROKE);
		if(mPoint.size() > 0){
			mPaint.setStrokeWidth(2);
			//将点与点之间连线，完成折线图
			for(int i = 1; i< mPoint.size();i++){
				int tempYWhole1 = (mPoint.get(i-1).y - minY) / scaleY;
				int tempYSurplus1 = (mPoint.get(i-1).y - minY) % scaleY;
				float factY1 = spacing_y * (tempYWhole1 + (float)tempYSurplus1 / scaleY);
				int tempYWhole2 = (mPoint.get(i).y - minY) / scaleY;
				int tempYSurplus2 = (mPoint.get(i).y - minY) % scaleY;
				float factY2 = spacing_y * (tempYWhole2 + (float)tempYSurplus2 / scaleY);
				canvas.drawLine(PADDING_LEHGTH_X+spacing_x*(mPoint.get(i-1).x-1), getMeasuredHeight()-PADDING_LEHGTH_Y -factY1, PADDING_LEHGTH_X+spacing_x*(mPoint.get(i).x-1), getMeasuredHeight()-PADDING_LEHGTH_Y -factY2, mPaint);
			}
			//将点画上去
			Paint fillPaint = new Paint();
			fillPaint.setColor(getResources().getColor(R.color.linegraph_point));
			fillPaint.setStyle(Paint.Style.FILL);
			for(Point point:mPoint){
				if(point.y >= minY && point.y <= maxY) {
					int tempYWhole = (point.y - minY) / scaleY;
					int tempYSurplus = (point.y - minY) % scaleY;
					float factY = spacing_y * (tempYWhole + (float)tempYSurplus / scaleY);
					Log.d("lineGraphView",tempYWhole+"----"+tempYSurplus+"----"+factY);
					canvas.drawCircle(PADDING_LEHGTH_X+spacing_x*(point.x-1), getMeasuredHeight()-PADDING_LEHGTH_Y-factY, 5, mPaint);
					canvas.drawCircle(PADDING_LEHGTH_X+spacing_x*(point.x-1), getMeasuredHeight()-PADDING_LEHGTH_Y-factY,4,fillPaint);
				}
			}
			mPaint.setStyle(Paint.Style.FILL);
			Path path = new Path();
			//将区域初始放在第一个点的x轴上对应位置
			path.moveTo(PADDING_LEHGTH_X+spacing_x*(mPoint.get(0).x-1), getMeasuredHeight()-PADDING_LEHGTH_Y);
			//划线
			for(int i= 0;i<mPoint.size();i++){
				int tempYWhole = (mPoint.get(i).y - minY) / scaleY;
				int tempYSurplus = (mPoint.get(i).y - minY) % scaleY;
				float factY = spacing_y * (tempYWhole + (float)tempYSurplus / scaleY);
				path.lineTo(PADDING_LEHGTH_X+spacing_x*(mPoint.get(i).x-1), getMeasuredHeight()-PADDING_LEHGTH_Y-factY);
			}
			//将最后一个点的位置和x轴的该点位置连上
			path.lineTo(PADDING_LEHGTH_X+spacing_x*(mPoint.get(mPoint.size()-1).x-1), getMeasuredHeight()-PADDING_LEHGTH_Y);
			mPaint.setAlpha(25);
			//连成闭合区域
			canvas.drawPath(path, mPaint);
		}
	}
}





















