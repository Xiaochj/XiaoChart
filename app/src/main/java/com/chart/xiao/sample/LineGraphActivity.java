package com.chart.xiao.sample;

import android.graphics.Point;
import android.os.Bundle;

import com.chart.xiao.xiaochart.LineGraphView;

import java.util.Arrays;
import java.util.List;

/**
 * Line Graph sample
 *
 * Created by cjxiao on 2016/6/16.
 */

public class LineGraphActivity extends BaseActivity {

    private LineGraphView mLineGraph;
    private List<Point> mPoint = Arrays.asList(new Point(1,915),new Point(2,1025),new Point(3,1011),new Point(4,1185),new Point(5,988));
    private List<String> mX = Arrays.asList("1","2","3","4","5");
    private int maxY = 1253;
    private int minY = 876;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    private void initLayout(){
        setContentView(R.layout.sample_linegraph);
        mLineGraph = (LineGraphView)findViewById(R.id.linegraph);
        mLineGraph.addDateX(mX);
        mLineGraph.addMaxAndMinY(maxY,minY);
        mLineGraph.addPoints(mPoint);
    }
}
