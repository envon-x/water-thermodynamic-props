package com.iridiscense.unitoperations.plotter.interfaces.dataprovider;

import com.iridiscense.unitoperations.plotter.utils.Transformer;
import com.iridiscense.unitoperations.plotter.components.YAxis.AxisDependency;
import com.iridiscense.unitoperations.plotter.data.BarLineScatterCandleBubbleData;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    //abstract class
    BarLineScatterCandleBubbleData getData();
}
