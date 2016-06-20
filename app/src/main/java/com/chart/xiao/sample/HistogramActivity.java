package com.chart.xiao.sample;

import android.os.Bundle;

import com.chart.xiao.xiaochart.HistogramView;

import java.util.Arrays;
import java.util.List;

/**
 * Histogram sample
 *
 * Created by cjxiao on 2016/6/20.
 */

public class HistogramActivity extends BaseActivity {
    private HistogramView mHistogram;
    private List<String> mX = Arrays.asList("0","60","70","80","90","99");
    private List<Integer> datas = Arrays.asList(6,15,21,31,18);
    private int maxY = 40;
    private int minY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    private void initLayout(){
        setContentView(R.layout.sample_histogram);
        mHistogram = (HistogramView) findViewById(R.id.histogram);
        mHistogram.addDateX(mX);
        mHistogram.addMaxAndMinY(maxY,minY);
        mHistogram.addDatas(datas);
    }
}
