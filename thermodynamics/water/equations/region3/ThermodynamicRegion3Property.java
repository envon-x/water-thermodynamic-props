package com.iridiscense.unitoperations.thermodynamics.water.equations.region3;

import androidx.annotation.NonNull;

import com.iridiscense.unitoperations.thermodynamics.Property;
import com.iridiscense.unitoperations.thermodynamics.Range;
import com.iridiscense.unitoperations.thermodynamics.SpecificProperty;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicConstant;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicVariablesManager;
import com.iridiscense.unitoperations.thermodynamics.water.equations.boundary23.BoundaryRegion23;

/**
 * This class was created for Comanda project
 * Created by bon on 8/27/21.
 * Copyright (c)  Hector Bonifacio. 8/27/21, All rights reserved.
 */
public class ThermodynamicRegion3Property extends Property implements Range, ThermodynamicVariablesManager, SpecificProperty {
    private boolean isValid = false;
    private volatile double massDensity = 0d;
    /**
     * See the equation 2.11 and the table 2.16, page xx of Thermodynamics properties
     * @param temperature in Kelvins
     * @param massDensity kg/m³
     */
    public ThermodynamicRegion3Property(double temperature, double massDensity) {
        this.temperature = temperature;
        this.pressure = (DimensionlessHelmholtzFreeEnergy.delta(massDensity)
                * DimensionlessHelmholtzFreeEnergy.phiDelta(temperature, massDensity))
                * massDensity
                * ThermodynamicConstant.R
                * temperature
                / 1000;
        validity();
        this.massDensity= massDensity;
    }

    /**
     *
     * @param value can be temperature in Kelvins or presure in KPa
     * @param variable specify what variable is the first argument
     */
//    public ThermodynamicSpecificPropertyRegion3(double value, String variable) {
//        switch (variable) {
//            case "T": // value = temperature in K
//                if(value <= 350 + 273.15) {
//                    this.temperature = value;
//                    this.pressure = saturatedPressure(value);
//                    validity(temperature,pressure);
//                    this.DimensionlessHelmholtzFreeEnergy =
//                            new DimensionlessHelmholtzFreeEnergy(value, pressure);
//                }
//                break;
//
//            case "D": // MPa
//                if (value <= 16.529) {
//                    this.temperature = saturatedTemperature(value);
//                    this.pressure = value;
//                    validity(temperature,pressure);
//                    this.DimensionlessHelmholtzFreeEnergy = new DimensionlessHelmholtzFreeEnergy(temperature, value);
//                }
//                break;
//            default:
//                System.out.println(ThermodynamicSpecificPropertyRegion3.class.getSimpleName() + " | Algo salió mal.");
//                break;
//
//        }
//    }



    /**
     * Validity for region 2
     * @return
     */
    @Override
    public boolean validity() {

        if (623.15 <= temperature && temperature <= 863.15) {
            if (BoundaryRegion23.pressureB23(temperature) <=  pressure && pressure <= 100d) { //pressure in MPa
                isValid = true;
            } else {
                isValid = false;
                System.out.println("3rd Region pressure = " + pressure + " is out of range. " + pressure + " ≤ temperature ≤ 623.15" + "Reduce the Temperature or increase the pressure");
            }
        }
        return isValid;
    }

    @Override
    public double specificVolume() {
        return 1 / massDensity;
    }

    @Override
    public double specificEnthalpy() {
        return (DimensionlessHelmholtzFreeEnergy.tau(temperature)
                * DimensionlessHelmholtzFreeEnergy.phiTau(temperature, massDensity)
                + DimensionlessHelmholtzFreeEnergy.delta(massDensity)
                * DimensionlessHelmholtzFreeEnergy.phiDelta(temperature, massDensity))
                * ThermodynamicConstant.R
                * temperature;
    }

    @Override
    public double specificInternalEnergy() {
        return 0;
    }

    @Override
    public double specificEntropy() {
        return (DimensionlessHelmholtzFreeEnergy.tau(temperature)
                * DimensionlessHelmholtzFreeEnergy.phiTau(temperature, massDensity)
                - DimensionlessHelmholtzFreeEnergy.phi(temperature, massDensity))
                * ThermodynamicConstant.R;
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
        return temperature;
    }

    @Override
    public double getPressure() {
        return pressure;
    }

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getMassDensity() {
        return massDensity;
    }

    public void setMassDensity(double massDensity) {
        this.massDensity = massDensity;
    }


    @NonNull
    @Override
    public String toString() {
        return ThermodynamicRegion3Property.class.getName();
    }
}
