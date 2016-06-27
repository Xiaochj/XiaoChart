package com.chart.xiao.sample;

import android.os.Bundle;

import com.chart.xiao.xiaochart.PieChartView;

import java.util.Arrays;
import java.util.List;

/**
 * piechart sample
 * Created by cjxiao on 2016/6/22.
 */

public class PieChartActivity extends BaseActivity {
    private PieChartView mPieChartView;
    private List<Float> mPies = Arrays.asList((float)21,(float)12,(float)35,(float)28,(float)18);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    private void initLayout(){
        setContentView(R.layout.sample_piechart);
        mPieChartView = (PieChartView)findViewById(R.id.piechart);
        mPieChartView.addDatas(mPies);
    }
}
