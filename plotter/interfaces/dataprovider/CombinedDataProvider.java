package com.iridiscense.unitoperations.plotter.interfaces.dataprovider;

import com.iridiscense.unitoperations.plotter.data.CombinedData;

/**
 * Created by philipp on 11/06/16.
 */
public interface CombinedDataProvider extends LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {

    CombinedData getCombinedData();
}
