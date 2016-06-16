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
    private List<Point> mPoint = Arrays.asList(new Point(1,136),new Point(2,187),new Point(3,156));
    private List<String> mX = Arrays.asList("1","2","3");
    private int maxY = 200;
    private int minY = 125;

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
