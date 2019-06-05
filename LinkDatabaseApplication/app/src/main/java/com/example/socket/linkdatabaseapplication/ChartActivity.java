package com.example.socket.linkdatabaseapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.socket.linkdatabaseapplication.activity.BarChartManager;
import com.example.socket.linkdatabaseapplication.activity.LineChartManager;
import com.example.socket.linkdatabaseapplication.activity.MyAxisValueFormatter;
import com.example.socket.linkdatabaseapplication.activity.XAxisValueFormatter;
import com.example.socket.linkdatabaseapplication.activity.XYMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity implements
        OnChartValueSelectedListener {
    private String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"};
    private String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private Typeface mTfRegular;
    private Typeface mTfLight;
    protected BarChart mChart;
    private HorizontalBarChart hBarChart;
    private LineChart mlineChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
       // mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
       // mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mChart = findViewById(R.id.chart1);
        mlineChart = findViewById(R.id.lineChart);

        //initBarChart();
        testBarChart();
        testLineChart();
    }
        /**
         * 初始化柱形图控件属性
         */
        private void initBarChart() {
            mChart.setOnChartValueSelectedListener(this);
            mChart.setDrawBarShadow(false);
            mChart.setDrawValueAboveBar(true);
            mChart.getDescription().setEnabled(false);
            // if more than 60 entries are displayed in the chart, no values will be
            // drawn
            mChart.setMaxVisibleValueCount(60);
            // scaling can now only be done on x- and y-axis separately
            mChart.setPinchZoom(false);
            mChart.setDrawGridBackground(false);

//        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

            //自定义坐标轴适配器，配置在X轴，xAxis.setValueFormatter(xAxisFormatter);
            IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter();

            XAxis xAxis = mChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
           // xAxis.setTypeface(mTfLight);//可以去掉，没什么用
            xAxis.setDrawAxisLine(false);
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(xAxisFormatter);


            //自定义坐标轴适配器，配置在Y轴。leftAxis.setValueFormatter(custom);
            IAxisValueFormatter custom = new MyAxisValueFormatter();

            //设置限制临界线
            LimitLine limitLine = new LimitLine(3f, "临界点");
            limitLine.setLineColor(Color.GREEN);
            limitLine.setLineWidth(1f);
            limitLine.setTextColor(Color.GREEN);

            //获取到图形左边的Y轴
            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.addLimitLine(limitLine);
            leftAxis.setTypeface(mTfLight);//可以去掉，没什么用
            leftAxis.setLabelCount(8, false);
            leftAxis.setValueFormatter(custom);
            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            leftAxis.setSpaceTop(15f);
            leftAxis.setAxisMinimum(0f);

            //获取到图形右边的Y轴，并设置为不显示
            mChart.getAxisRight().setEnabled(false);

            //图例设置
            Legend legend = mChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside(false);
            legend.setForm(Legend.LegendForm.SQUARE);
            legend.setFormSize(9f);
            legend.setTextSize(11f);
            legend.setXEntrySpace(4f);

            //如果点击柱形图，会弹出pop提示框.XYMarkerView为自定义弹出框
            XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
            mv.setChartView(mChart);
            mChart.setMarker(mv);
            setBarChartData();
        }




    private void setBarChartData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();


        //在这里设置自己的数据源,BarEntry 只接收float的参数，
        //图形横纵坐标默认为float形式，如果想展示文字形式，需要自定义适配器，
        yVals1.add(new BarEntry(0, 4));
        yVals1.add(new BarEntry(1, 2));
        yVals1.add(new BarEntry(2, 6));
        yVals1.add(new BarEntry(3, -1));


        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2019");
            set1.setDrawIcons(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);//可以去掉，没什么用
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
    }

    private void testBarChart() {
        //BarChartManager barChartManager1 = new BarChartManager(mChart);
        BarChartManager barChartManager2 = new BarChartManager(mChart);

        //设置x轴的数据
        ArrayList<String> xValues0 = new ArrayList<>();
        xValues0.add("早晨");
        xValues0.add("上午");
        xValues0.add("中午");
        xValues0.add("下午");
        xValues0.add("晚上");

        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                yValue.add((float) (Math.random() * 8)+2);
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("柱状一");
        names.add("柱状二");
        names.add("柱状三");
        names.add("柱状四");

        //创建多条柱状的图表
        //barChartManager1.showBarChart(xValues, yValues.get(0), names.get(1), colors.get(3));
        barChartManager2.showBarChartN(xValues0, yValues,names);
    }

    /**
     * 测试折线图
     */
    private void testLineChart() {
        LineChartManager lineChartManager1 = new LineChartManager(mlineChart);
        LineChartManager lineChartManager2 = new LineChartManager(mlineChart);

        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {//10组数据
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                yValue.add((float) (Math.random() * 80));
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");
        names.add("折线二");
        names.add("折线三");
        names.add("折线四");

        //创建单条折线的图表
        lineChartManager1.showLineChart(xValues, yValues.get(0), names.get(0), colors.get(0));
        lineChartManager1.setYAxis(100, 0, 11);
        lineChartManager1.setDescription("温度");
        // lineChartManager1.setXAxis(10,0,xValues.size());
        lineChartManager1.setHightLimitLine(70,"高温报警",Color.RED);

        //创建多条折线的图表
        // lineChartManager2.showLineChart(xValues, yValues, names, colors);
        // lineChartManager2.setYAxis(100, 0, 11);

////////////////////////////////////////////////////////////////////////////////////////////////
        //lineChartManager2.showLineChart(xValues, yValues, names, colors);
        //lineChartManager2.setYAxis(100, 0, 11);
       // lineChartManager2.setDescription("温度");

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
