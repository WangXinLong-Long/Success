package com.silianchuangye.sumao.success.fragments.homepage.auction;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.silianchuangye.sumao.success.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotographFragment extends Fragment {
    private ComboLineColumnChartView mComboLineColumnChartView;

    private ColumnChartData mColumnChartData;
    private boolean hasAxex = true;
    private boolean hasAxesnames = true;
    private boolean hasLabels = true;
    private boolean hasLabelForSelected = true;
    Axis axisX1;
    Axis axisY1;

    public PhotographFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_photograph, container, false);
        mComboLineColumnChartView = ((ComboLineColumnChartView) view.findViewById(R.id.comboLineColumnChartView));
        makeComboChartView();
        return view;
    }

   public void makeComboChartView(){
        mComboLineColumnChartView.setValueSelectionEnabled(true);
        mComboLineColumnChartView.setZoomEnabled(true);
        //设置折线图的数据
        List<PointValue> pointValues = new ArrayList<>();
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);

        axisX.setLineColor(Color.BLACK);


        pointValues.add(new PointValue(0, 15));
        pointValues.add(new PointValue(1, 32));
        pointValues.add(new PointValue(2, 38));
        pointValues.add(new PointValue(3, 94));
        pointValues.add(new PointValue(4, 25));
        pointValues.add(new PointValue(5, 6));
        pointValues.add(new PointValue(6, 96));
        pointValues.add(new PointValue(7, 25));
        pointValues.add(new PointValue(8, 74));
        pointValues.add(new PointValue(9, 39));
        pointValues.add(new PointValue(10, 02));
        pointValues.add(new PointValue(11, 31));
        List<Line> lines = new ArrayList<>();
        Line line = new Line(pointValues);
        line.setColor(Color.GREEN);
        line.setStrokeWidth(2);
        line.setFilled(false);
        line.setCubic(true);
        line.setPointColor(Color.RED);
        line.setPointRadius(3);
        line.setHasLabels(true);
        line.setHasLines(true);
        line.setHasPoints(true);
        line.setShape(ValueShape.CIRCLE);
        line.setHasLabelsOnlyForSelected(true);
        lines.add(line);
        LineChartData chartData = new LineChartData(lines);
        chartData.setAxisYLeft(axisY);
        chartData.setAxisXBottom(axisX);
        chartData.setValueLabelTypeface(Typeface.MONOSPACE);

        int numSubcolus = 1;//设置每个柱状图显示的颜色数量
        int numColumns = 30;//柱状图的数量
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; i++) {
            values = new ArrayList<>();
            for (int j = 0; j < numSubcolus; j++) {
                SubcolumnValue value = new SubcolumnValue((float) Math.random()*50f+5, ChartUtils.COLOR_ORANGE);
                values.add(value);
            }
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }
        mColumnChartData = new ColumnChartData(columns);
        if (hasAxex)
        {
            axisX1  = new Axis();
            AxisValue value1 = new AxisValue(0f);
            value1.setLabel("1");//设置显示的文本，默认为柱状图的位置
            AxisValue value2 = new AxisValue(5.0f);
            value2.setLabel("6");
            AxisValue value3 = new AxisValue(10.0f);
            value3.setLabel("11");//设置显示的文本，默认为柱状图的位置
            AxisValue value4 = new AxisValue(15.0f);
            value4.setLabel("16");
            AxisValue value5 = new AxisValue(20.0f);
            value5.setLabel("21");//设置显示的文本，默认为柱状图的位置
            AxisValue value6 = new AxisValue(25.0f);
            value6.setLabel("26");
            AxisValue value7 = new AxisValue(30.0f);
            value7.setLabel("31");


            List<AxisValue> axisValues = new ArrayList<>();
            axisValues.add(value1);
            axisValues.add(value2);
            axisValues.add(value3);
            axisValues.add(value4);
            axisValues.add(value5);
            axisValues.add(value6);
            axisValues.add(value7);

            axisX1.setValues(axisValues);
            axisY1 = new Axis().setHasLines(true);
            if (hasAxesnames)
            {
                axisX1.setName("");
                axisY1.setName("");
            }
            mColumnChartData.setAxisXBottom(axisX1);
        }else {
            mColumnChartData.setAxisXBottom(null);
            mColumnChartData.setAxisYRight(null);
        }

        ComboLineColumnChartData comboLineColumnChartData = new ComboLineColumnChartData();
        comboLineColumnChartData.setLineChartData(chartData);
        comboLineColumnChartData.setColumnChartData(mColumnChartData);
        comboLineColumnChartData.setAxisYLeft(axisY);
        comboLineColumnChartData.setAxisXBottom(axisX1);
        mComboLineColumnChartView.setComboLineColumnChartData(comboLineColumnChartData);
    }
}
