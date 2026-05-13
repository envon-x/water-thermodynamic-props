package com.iridiscense.unitoperations.thermodynamics.water.equations.region2;

import androidx.annotation.NonNull;

import com.iridiscense.unitoperations.thermodynamics.SpecificProperty;
import com.iridiscense.unitoperations.thermodynamics.Range;
import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicConstant;
import com.iridiscense.unitoperations.thermodynamics.Property;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicVariablesManager;
import com.iridiscense.unitoperations.thermodynamics.water.equations.boundary23.BoundaryRegion23;
import com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4.SaturatedRegion;

/**
 * This class was created for Environmental Engenering - UMSA project
 * Created by bon on 8/25/21.
 * Copyright (c)  Hector Bonifacio. 8/25/21, All rights reserved.
 */
public class ThermodynamicRegion2Property extends Property implements Range, ThermodynamicVariablesManager, SpecificProperty {

    private boolean isValid = false;

    /**
     * See the equation xx and the table xx, page xx of Thermodynamics properties
     * @param temperature in Kelvins
     * @param pressure in MPa
     */
    public ThermodynamicRegion2Property(double temperature, double pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        validity();
    }

    /**
     *
     * @param value can be temperature in Kelvins or presure in KPa
     * @param variable specify what variable is the first argument
     a
     */
    public ThermodynamicRegion2Property(double value, String variable) {
        switch (variable) {
            case "T": // value = temperature in K
                if(value <= 350d + 273.15d) {
                    this.temperature = value;
                    this.pressure = saturatedPressure(value);
                    validity();
//                    this.DimensionlessGibbsFreeEnergyRegion2 =
//                                    new DimensionlessGibbsFreeEnergyRegion2(value, pressure);
                }
                break;

            case "P": // MPa
                if (value <= 16.529) {
                    this.temperature = saturatedTemperature(value);
                    this.pressure = value;
                    validity();
//                    this.DimensionlessGibbsFreeEnergyRegion2 = new DimensionlessGibbsFreeEnergyRegion2(temperature, value);
                }
                break;
            default:
                System.out.println(ThermodynamicRegion2Property.class.getSimpleName() + " | Algo salió mal.");
                break;

        }
    }



    /**
     * Validity for region 2
     * @return
     */
    @Override
    public boolean validity() {
        double Ps = saturatedPressure(temperature);
        double Ts = saturatedTemperature(pressure);

        if (273.15 <= temperature && temperature <= 623.15) {
            if ( pressure >= 0.00 && pressure <= Ps) {
                System.out.println("2nd Region pressure = " + pressure + ", [MPa] it's OK. Ps = " + Ps);
                isValid = true;
            } /* else {
                isValid = false;
                System.out.println("Failed Result: (temperature, Ts) = (" + temperature + ", " + Ts + "), && (pressure,Ps) = (" + pressure  + ", " + Ps+ ")");
                System.out.println("2nd Region pressure = " + pressure + " is out of range. " + Ps + " ≤ temperature ≤ 623.15" + "Reduce the Temperature or increase the pressure");
            }*/
        }  else if (623.15 <= temperature && temperature <= 863.15) { //page (17/31->book/pdf)
            if ( pressure >= 0.00 && pressure <= BoundaryRegion23.pressureB23(temperature)) {
                System.out.println("2nd Region pressure = " + pressure + ", [MPa] it's OK. Ps = " + Ps);
                isValid = true;
            } else {
                isValid = false;
                System.out.println("Failed Result: (temperature, Ts) = (" + temperature + ", " + Ts + "), && (pressure,Ps) = (" + pressure  + ", " + Ps+ ")");
                System.out.println("2nd Region pressure = " + pressure + " is out of range. " + Ps + " ≤ temperature ≤ 623.15" + "Reduce the Temperature or increase the pressure");
            }
        } else if (863.15 <= temperature && temperature <= 1073.15) {
            System.out.println("2nd Region temperature = " + temperature + ", [K] it's OK.");
            if ( pressure >= 0.00 && pressure <= 100) { // MPa
                System.out.println("2nd Region pressure = " + pressure + ", [MPa] it's OK. Ps = " + Ps);
                isValid = true;
            } else {
                isValid = false;
                System.out.println("Failed Result: (temperature, Ts) = (" + temperature + ", " + Ts + "), && (pressure,Ps) = (" + pressure  + ", " + Ps+ ")");
                System.out.println("2nd Region pressure = " + pressure + " is out of range. " + Ps + " ≤ temperature ≤ 623.15" + "Reduce the Temperature or increase the pressure");
            }
        }

        return isValid;
    }





























    /**
     *
     * @param temperature in Kelvins
     * @return pressure in MPa
     */
    public static double saturatedPressure(double temperature) {

        if (temperature <= (350 + 273.15)) { // validity of the equation (2.6) for the given temperature
            return SaturatedRegion.pressureSaturate(temperature);
        } else {
            System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
        }
        return ThermoMath.NN;
    }

    /**
     * Only for for pressure
     * @param pressure in MPa
     * @return temperature in Kelvins
     */
    public static double saturatedTemperature(double pressure) {
        if (pressure <= 16.529d) { //validity for the given pressure in MPa
            return  SaturatedRegion.temperatureSaturate(pressure);
        } else {
            System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
        }
        return ThermoMath.NN;
    }

    @Override
    public double specificVolume() {
        return 0;
    }


    @Override
    public double specificEnthalpy() {
        if (isValid) {
            return DimensionlessGibbsFreeEnergyRegion2.tau(temperature)
                    * ( DimensionlessGibbsFreeEnergyRegion2.gammaIdealTau(temperature)
                            + DimensionlessGibbsFreeEnergyRegion2.gammaResidualTau(temperature, pressure)
                      )
                    * ThermodynamicConstant.R * temperature;
        }
        return ThermoMath.NN;
    }

    /**
     * From table 2.8
     * @return Specific internal energy in KJ/Kg
     */
    @Override
    public double specificInternalEnergy() {
        if (isValid) {
            return (DimensionlessGibbsFreeEnergyRegion2.tau(temperature) * (DimensionlessGibbsFreeEnergyRegion2.gammaIdealTau(temperature) + DimensionlessGibbsFreeEnergyRegion2.gammaResidualTau(temperature, pressure))
                    -
                       DimensionlessGibbsFreeEnergyRegion2.pi(pressure) *(DimensionlessGibbsFreeEnergyRegion2.gammaIdealPi(pressure) + DimensionlessGibbsFreeEnergyRegion2.gammaResidualPi(temperature, pressure))
            ) * ThermodynamicConstant.R * temperature;
        }
        return ThermoMath.NN;
    }

    @Override
    public double specificEntropy() {
        if (isValid) {
            return (
                    DimensionlessGibbsFreeEnergyRegion2.tau(temperature)* (
                            DimensionlessGibbsFreeEnergyRegion2.gammaIdealTau(temperature) + DimensionlessGibbsFreeEnergyRegion2.gammaResidualTau(temperature, pressure)
                      )
                    - (
                            DimensionlessGibbsFreeEnergyRegion2.gammaIdeal(temperature, pressure) + DimensionlessGibbsFreeEnergyRegion2.gammaResidual(temperature, pressure)
                      )
            ) * ThermodynamicConstant.R
            ;
        }
        return ThermoMath.NN;
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

    public boolean isValid() {
        return isValid;
    }


    @NonNull
    @Override
    public String toString() {
        return ThermodynamicRegion2Property.class.getName();
    }


}
