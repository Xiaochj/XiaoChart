package com.chart.xiao.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private String[] mChartStr = {"折线图","柱状图","饼状图"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout(this);
    }

    private void initLayout(Context context){
        mContext = context;
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.listview);
        mAdapter = new ChartAdapter(context,android.R.layout.simple_list_item_1,mChartStr);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new ItemClick());
    }

    class ItemClick implements AdapterView.OnItemClickListener{

        Intent mIntent;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    mIntent = new Intent(MainActivity.this,LineGraphActivity.class);
                    mContext.startActivity(mIntent);
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }

    class ChartAdapter extends ArrayAdapter<String>{
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,null);
            }
            TextView tv = (TextView)convertView.findViewById(android.R.id.text1);
            tv.setText(mChartStr[position]);
            return convertView;
        }

        public ChartAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

    }
}
