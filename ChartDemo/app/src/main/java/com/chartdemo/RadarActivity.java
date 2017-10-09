package com.chartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MPAndroidChart：RadarChart  折线图
 * create by txw on 2017-08-25
 */
public class RadarActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.radar_chart)
    RadarChart mRadarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        ButterKnife.bind(this);

        mTextView.setTextColor(Color.WHITE);
        mTextView.setBackgroundColor(Color.rgb(60, 65, 82));

        mRadarChart.getDescription().setEnabled(false);

        mRadarChart.setWebLineWidth(1f);
        mRadarChart.setWebColor(Color.LTGRAY);
        mRadarChart.setWebLineWidthInner(1f);
        mRadarChart.setWebColorInner(Color.LTGRAY);
        mRadarChart.setWebAlpha(100);

        setData();

        mRadarChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mRadarChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"Burger", "Steak", "Salad", "Pasta", "Pizza"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = mRadarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.radar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (IDataSet<?> set : mRadarChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                mRadarChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (mRadarChart.getData() != null) {
                    mRadarChart.getData().setHighlightEnabled(!mRadarChart.getData().isHighlightEnabled());
                    mRadarChart.invalidate();
                }
                break;
            }
            case R.id.actionToggleRotate: {
                if (mRadarChart.isRotationEnabled())
                    mRadarChart.setRotationEnabled(false);
                else
                    mRadarChart.setRotationEnabled(true);
                mRadarChart.invalidate();
                break;
            }
            case R.id.actionToggleFilled: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) mRadarChart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                mRadarChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlightCircle: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) mRadarChart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    set.setDrawHighlightCircleEnabled(!set.isDrawHighlightCircleEnabled());
                }
                mRadarChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                if (mRadarChart.saveToPath("title" + System.currentTimeMillis(), "")) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();
                break;
            }
            case R.id.actionToggleXLabels: {
                mRadarChart.getXAxis().setEnabled(!mRadarChart.getXAxis().isEnabled());
                mRadarChart.notifyDataSetChanged();
                mRadarChart.invalidate();
                break;
            }
            case R.id.actionToggleYLabels: {

                mRadarChart.getYAxis().setEnabled(!mRadarChart.getYAxis().isEnabled());
                mRadarChart.invalidate();
                break;
            }
            case R.id.animateX: {
                mRadarChart.animateX(1400);
                break;
            }
            case R.id.animateY: {
                mRadarChart.animateY(1400);
                break;
            }
            case R.id.animateXY: {
                mRadarChart.animateXY(1400, 1400);
                break;
            }
            case R.id.actionToggleSpin: {
                mRadarChart.spin(2000, mRadarChart.getRotationAngle(), mRadarChart.getRotationAngle() + 360, Easing.EasingOption
                        .EaseInCubic);
                break;
            }
        }
        return true;
    }

    private void setData() {
        float mul = 80;
        float min = 20;
        int cnt = 5;

        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mul) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mul) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mRadarChart.setData(data);
        mRadarChart.invalidate();
    }
}
