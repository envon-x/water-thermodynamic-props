package com.iridiscense.unitoperations.thermodynamics.water.equations.metastable_vapour;

import com.iridiscense.unitoperations.thermodynamics.Property;
import com.iridiscense.unitoperations.thermodynamics.Range;
import com.iridiscense.unitoperations.thermodynamics.SpecificProperty;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicVariablesManager;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 10/12/21.
 * Copyright (c)  Hector Bonifacio. 10/12/21, All rights reserved.
 */
public class ThermodynamicRegionMetastableProperty extends Property implements Range, ThermodynamicVariablesManager, SpecificProperty {


    private boolean isValid = false;

    public ThermodynamicRegionMetastableProperty(double temperature, double pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        isValid = validity();
    }



    @Override
    public boolean validity() {
        return false;
    }

    @Override
    public double specificVolume() {
        return 0;
    }

    @Override
    public double specificEnthalpy() {
        return 0;
    }

    @Override
    public double specificInternalEnergy() {
        return 0;
    }

    @Override
    public double specificEntropy() {
        return 0;
    }

    @Override
    public double specificIsobaricHeatCapacity() {
        return 0;
    }

    @Override
    public double specificIsochoricHeatCapacity() {
        return 0;
    }

    @Override
    public double speedOfSound() {
        return 0;
    }

    @Override
    public double isentropicExponent() {
        return 0;
    }

    @Override
    public double isobaricCubicExpansionCoefficient() {
        return 0;
    }

    @Override
    public double isothermalCompressibility() {
        return 0;
    }

    @Override
    public double getTemperature() {
        return 0;
    }

    @Override
    public double getPressure() {
        return 0;
    }

    @Override
    public void setTemperature(double temperature) {

    }

    @Override
    public void setPressure(double pressure) {

    }
}
