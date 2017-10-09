package com.chartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MPAndroidChart：LineChart  折线图
 * create by txw on 2017-08-25
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.line_chart_show)
    LineChart mLineChartShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //填充数据
        initData(20, 1000);
    }

    /**
     * 设置模拟数据
     *
     * @param count 个数
     * @param range 范围
     */
    private void initData(int count, float range) {
        //first step: wrap each data object you have into an Entry object
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val = (float) ((Math.random() * range)) + 3;
            values.add(new Entry(i, val));
        }
        //second step: add the List<Entry> you created to a LineDataSet object
        LineDataSet dataSet;
        if (mLineChartShow.getData() != null && mLineChartShow.getData().getDataSetCount() > 0) {
            dataSet = (LineDataSet) mLineChartShow.getData().getDataSetByIndex(0);
            dataSet.setValues(values);
            mLineChartShow.getData().notifyDataChanged();
            mLineChartShow.notifyDataSetChanged();
        } else {
            dataSet = new LineDataSet(values, "测试数据");
            //设置虚线：数据点连接的效果
            dataSet.enableDashedLine(10f, 5f, 0f);
            //高亮虚线：点击之后的效果
            dataSet.enableDashedHighlightLine(10f, 5f, 0f);
            dataSet.setColor(Color.RED);
            dataSet.setCircleColor(Color.BLACK);
            dataSet.setLineWidth(1f);
            //设置数据点大小
            dataSet.setCircleRadius(5f);
            //设置数据点里面的圆孔是否显示(注意此处跟数据点半径有关，半径大一些才会有效果)
            dataSet.setDrawCircleHole(true);
            dataSet.setValueTextSize(9f);
            //填充折线下方的区域
            dataSet.setDrawFilled(true);
            dataSet.setFillColor(Color.RED);
        }
        ArrayList<ILineDataSet> iDataSet = new ArrayList<>();
        iDataSet.add(dataSet);

        //add the LineDataSet object (or objects) you created to a LineData object
        LineData lineData = new LineData(iDataSet);
        mLineChartShow.setData(lineData);
        Description des = new Description();
        des.setText("txw");
        mLineChartShow.setDescription(des);
        //refresh
        mLineChartShow.invalidate();
    }
}
