package com.iridiscense.unitoperations.draw;

import com.iridiscense.unitoperations.variables.AbsoluteHumidity;
import com.iridiscense.unitoperations.variables.DryBulbTemperature;
import com.iridiscense.unitoperations.variables.PhysicalVariable;
import com.iridiscense.unitoperations.variables.Pressure;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 11/5/21.
 * Copyright (c)  Hector Bonifacio. 11/5/21, All rights reserved.
 */
public interface AbsoluteHumidityFunction {


    /**
     *
     * @param atmospherePressure es la presion a la que se crea el diagrama psichrometico
     * @param dryBulbTemperature representa el eje X
     * @param fixedVariable, otras variables por establecer para realizar el cálculo
     * @return el valor de la humedad absoluta
     */
    public AbsoluteHumidity calculate(Pressure atmospherePressure, DryBulbTemperature dryBulbTemperature, double ... fixedVariable);
}
