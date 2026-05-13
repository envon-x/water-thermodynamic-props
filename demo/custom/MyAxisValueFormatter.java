package com.iridiscense.unitoperations.demo.custom;

//import com.github.mikephil.charting.components.AxisBase;
//import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import com.iridiscense.unitoperations.plotter.components.AxisBase;
import com.iridiscense.unitoperations.plotter.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{

    private final DecimalFormat mFormat;

    public MyAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + " $";
    }
}
