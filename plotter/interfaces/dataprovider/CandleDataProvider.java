package com.iridiscense.unitoperations.plotter.interfaces.dataprovider;

import com.iridiscense.unitoperations.plotter.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
