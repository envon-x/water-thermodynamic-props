package com.iridiscense.unitoperations.plotter.interfaces.dataprovider;

import com.iridiscense.unitoperations.plotter.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
