package com.chart.xiao.xiaochart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;

import com.chart.xiao.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Line Graph
 *
 * Created by cjxiao on 2016/6/16.
 */

public class LineGraphView extends BaseView {

	private List<Point> mPoint = new ArrayList<Point>();

	public LineGraphView(Context context,List<Point> point){
		super(context);
		this.mPoint = point;
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
	@Override
	public void addDatas(List point) {
		this.mPoint = point;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
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
		mPoint = null;
	}
}





















