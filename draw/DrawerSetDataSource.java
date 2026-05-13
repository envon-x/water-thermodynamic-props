package com.iridiscense.unitoperations.draw;

import com.iridiscense.unitoperations.plotter.data.LineData;
import com.iridiscense.unitoperations.plotter.data.ValuePosition;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 11/5/21.
 * Copyright (c)  Hector Bonifacio. 11/5/21, All rights reserved.
 */
public interface DrawerSetDataSource {

    /**
     * Permite establecer el tipo de datos que se graficará en Linechart
     * @return
     */
    public LineData data();

    ValuePosition getXValuePosition();
    ValuePosition getYValuePosition();

}
