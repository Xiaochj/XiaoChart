package com.chart.xiao.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by cjxiao on 2016/6/16.
 */

public class BaseActivity extends Activity implements View.OnClickListener{

    private TextView mBackTv;
    private LinearLayout mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int resId) {
        initLayout();
        LayoutInflater.from(this).inflate(resId, mContentLayout);
    }

    @Override
    public void setContentView(View view) {
        initLayout();
        mContentLayout.addView(view);
    }

    private void initLayout(){
        super.setContentView(R.layout.activity_base);
        mContentLayout = (LinearLayout) findViewById(R.id.content);
        mBackTv = (TextView)findViewById(R.id.back_textview);
        mBackTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_textview:
                finish();
                break;
        }
    }
}
