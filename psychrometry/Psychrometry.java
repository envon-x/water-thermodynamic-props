package com.iridiscense.unitoperations.psychrometry;

import com.iridiscense.unitoperations.variables.DewPointTemperature;
import com.iridiscense.unitoperations.variables.DryBulbTemperature;
import com.iridiscense.unitoperations.variables.Pressure;
import com.iridiscense.unitoperations.variables.WetBulbTemperature;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 10/3/21.
 * Copyright (c)  Hector Bonifacio. 10/3/21, All rights reserved.
 */
public abstract class Psychrometry {
    protected Pressure pressure;
    protected double relativeHumidity;
    protected DryBulbTemperature dryBulbTemperature;
    protected WetBulbTemperature wetBulbTemperature;
    protected DewPointTemperature dewPointTemperature;


    public Pressure getPressure() {
        return pressure;
    }

    public double getRelativeHumidity() {
        return relativeHumidity;
    }

//    public DryBulbTemperature getDryBulbTemperature() {
//        return dryBulbTemperature;
//    }

    public WetBulbTemperature getWetBulbTemperature() {
        return wetBulbTemperature;
    }

    public DewPointTemperature getDewPointTemperature() {
        return dewPointTemperature;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public void setRelativeHumidity(double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public void setDryBulbTemperature(DryBulbTemperature dryBulbTemperature) {
        this.dryBulbTemperature = dryBulbTemperature;
    }

    public void setWetBulbTemperature(WetBulbTemperature wetBulbTemperature) {
        this.wetBulbTemperature = wetBulbTemperature;
    }

    public void setDewPointTemperature(DewPointTemperature dewPointTemperature) {
        this.dewPointTemperature = dewPointTemperature;
    }
}
