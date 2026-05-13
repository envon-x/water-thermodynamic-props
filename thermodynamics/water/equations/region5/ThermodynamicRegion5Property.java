package com.iridiscense.unitoperations.thermodynamics.water.equations.region5;

import androidx.annotation.NonNull;

import com.iridiscense.unitoperations.thermodynamics.Property;
import com.iridiscense.unitoperations.thermodynamics.Range;
import com.iridiscense.unitoperations.thermodynamics.SpecificProperty;
import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicConstant;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicVariablesManager;

/**
 * This class was created for Comanda project
 * Created by bon on 9/3/21.
 * Copyright (c)  Hector Bonifacio. 9/3/21, All rights reserved.
 */
public class ThermodynamicRegion5Property extends Property implements Range, ThermodynamicVariablesManager, SpecificProperty {

    private boolean isValid = false;

    public ThermodynamicRegion5Property(double temperature, double pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.isValid = validity();
    }

    @Override
    public boolean validity() {
        boolean validP_T = false;
        if (1073.15 <= temperature &&  temperature <= 2273.15) {
            if ( 0 <= pressure && pressure <= 50) {
                validP_T  = true;
            } else {
                validP_T = false;
            }
        }
        return validP_T;
    }

    @Override
    public double specificVolume() {
        return 0;
    }

    @Override
    public double specificEnthalpy() {
        double specificEnthalpy = 0d;
        if (isValid) {
            specificEnthalpy = DimensionlessGibbsFreeEnergyRegion5.tau(temperature)
                    * ( DimensionlessGibbsFreeEnergyRegion5.gammaIdealTau(temperature)
                    + DimensionlessGibbsFreeEnergyRegion5.gammaResidualTau(temperature, pressure)
            )
                    * ThermodynamicConstant.R * temperature;
            return specificEnthalpy;
        }
        return ThermoMath.NN;
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

    public boolean getIsValid() {
        return isValid;
    }


    @NonNull
    @Override
    public String toString() {
        return ThermodynamicRegion5Property.class.getName();
    }
}
