package com.iridiscense.unitoperations.plotter.interfaces.dataprovider;

import com.iridiscense.unitoperations.plotter.components.YAxis;
import com.iridiscense.unitoperations.plotter.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
