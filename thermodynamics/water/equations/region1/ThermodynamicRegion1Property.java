package com.iridiscense.unitoperations.thermodynamics.water.equations.region1;

import com.iridiscense.unitoperations.thermodynamics.SpecificProperty;
import com.iridiscense.unitoperations.thermodynamics.Property;
import com.iridiscense.unitoperations.thermodynamics.Range;
import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicVariablesManager;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicConstant;
import com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4.SaturatedRegion;
import com.iridiscense.unitoperations.thermodynamics.water.equations.region2.ThermodynamicRegion2Property;

/**
 * This class was created for Environmental Engenering - UMSA project
 * Created by bon on 3/19/21.
 * Copyright (c)  Hector Bonifacio. 3/19/21, All rights reserved.
 */
public class ThermodynamicRegion1Property extends Property implements Range, ThermodynamicVariablesManager, SpecificProperty {

    private boolean isValid = false;// ahora esto se necesita llevar a la clase ThermodynamicsPropertyRegionOne

    /**
     * See the equation 2.3 and the table 2.5, page 15 of Thermodynamics properties
     *
     * @param temperature in Kelvin
     * @param pressure    in MPa
     */
    public ThermodynamicRegion1Property(double temperature, double pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        validity();
    }

    /**
     * @param value;   If it is pressure, in MPa. If it is temperature in kelvin
     * @param variable sets the first parameter to temperature or pressure
     */
    public ThermodynamicRegion1Property(double value, String variable) {
        switch (variable) {
            case "T": // value = temperature in K
                if (value <= 350 + 273.15) {
                    this.temperature = value;
                    this.pressure = saturatedPressure_MPa(value, "K");
                    validity();
                }
                break;

            case "P": // MPa
                if (value <= 16.529) {
                    this.temperature = saturatedTemperature(value);
                    this.pressure = value;
                    validity();
                }
                break;
            default:
                System.out.println(ThermodynamicRegion2Property.class.getSimpleName() + " | Algo salió mal.");
                break;

        }
    }


    @Override
    public boolean validity() {

        double Ts = saturatedTemperature(pressure);
        double Ps = saturatedPressure(temperature, "K");

        if (273.15 <= temperature && temperature <= 623.15) {
            if (pressure >= Ps && pressure <= 100.00) {
                isValid = true;
            } else {
                isValid = false;
                System.out.println(DimensionlessGibbsFreeEnergyRegion1.class.getSimpleName() + ": Failed Result: (T, Ts) = ("
                        + temperature + ", " + Ts + "), && (P,Ps) = (" + pressure + ", " + Ps + ")");

            }
        } else {
            isValid = false;
            System.out.println(ThermodynamicRegion1Property.class.getSimpleName() + ", T = " + temperature + " is out of range. 273.15 ≤ T ≤ 623.15");
        }
        return isValid;
    }


    /**
     * @param temperature in Kelvins
     * @return pressure in MPa
     */
    public static double saturatedPressure_mmHg(double temperature /*in K or C*/, String unit) {

        switch (unit) {
            case "C":
                if (temperature <= (350)) { // validity of the equation (2.6) for the given temperature
                    return 7501 * SaturatedRegion.pressureSaturate_mmHg(temperature + 273.15, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
            case "K":
                if (temperature <= (350 + 273.15)) { // validity of the equation (2.6) for the given temperature
                    return 7501 * SaturatedRegion.pressureSaturate_mmHg(temperature, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
                return ThermoMath.NN;
            default:
                return ThermoMath.NN;
        }

    }

    /**
     *
     * @param temperature determinated by unit parameter. C for celsius or K for Kelvin
     * @param unit
     * @return pressure in MPa
     */
    public static double saturatedPressure_MPa(double temperature /*in K or C*/, String unit) {

        switch (unit) {
            case "C":
                if (temperature <= (350)) { // validity of the equation (2.6) for the given temperature
                    return SaturatedRegion.pressureSaturate_mmHg(temperature + 273.15, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
            case "K":
                if (temperature <= (350 + 273.15)) { // validity of the equation (2.6) for the given temperature
                    return SaturatedRegion.pressureSaturate_mmHg(temperature, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
                return ThermoMath.NN;
            default:
                return ThermoMath.NN;
        }

    }

    /**
     *
     * @param temperature determinated by unit parameter. C for celsius or K for Kelvin
     * @param unit
     * @return pressure in MPa
     */
    public static double saturatedPressure(double temperature /*in K or C*/, String unit) {

        switch (unit) {
            case "C":
                if (temperature <= (350)) { // validity of the equation (2.6) for the given temperature
                    return SaturatedRegion.pressureSaturate_mmHg(temperature + 273.15, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
            case "K":
                if (temperature <= (350 + 273.15)) { // validity of the equation (2.6) for the given temperature
                    return SaturatedRegion.pressureSaturate_mmHg(temperature, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
                return ThermoMath.NN;
            default:
                return ThermoMath.NN;
        }

    }

    /**
     *
     * @param temperature determinated by unit parameter. C for celsius or K for Kelvin
     * @param unit
     * @return pressure in MPa
     */
    public static double saturatedPressure_bar(double temperature /*in K or C*/, String unit) {

        switch (unit) {
            case "C":
                if (temperature <= (350)) { // validity of the equation (2.6) for the given temperature
                    return 10 * SaturatedRegion.pressureSaturate_mmHg(temperature + 273.15, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
            case "K":
                if (temperature <= (350 + 273.15)) { // validity of the equation (2.6) for the given temperature
                    return 10 * SaturatedRegion.pressureSaturate_mmHg(temperature, unit);
                } else {
                    System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
                }
            default:
                return ThermoMath.NN;
        }

    }

    /**
     * Only for for pressure
     *
     * @param pressure in MPa
     * @return temperature in Kelvins
     */
    public static double saturatedTemperature(double pressure) {
        if (pressure <= 16.529d) { //validity for the given pressure in MPa
            return SaturatedRegion.temperatureSaturate(pressure);
        } else {
            System.out.println("La temperatura está fuera de rango para la ecuacion 2.6");
        }
        return ThermoMath.NN;
    }


    /**
     * From table 2.3
     *
     * @return the specific volume in m³/kg
     */
    @Override
    public double specificVolume() {
        if (isValid) {
            double factor = 0.001; //To convert kJ to MPa of R to simplify units.  1kJ = 0.001 MPa.m³ // 1kJ = 1kPa.m³
            return (DimensionlessGibbsFreeEnergyRegion1.pi(pressure) * DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure) * ThermodynamicConstant.R * temperature / pressure) * factor;
        } else {
            return ThermoMath.NN;
        }
    }

    /**
     * From table 2.3
     *
     * @return the specific Enthalpy in KJ/kg
     */
    @Override
    public double specificEnthalpy() {
        if (validity()) {
            return DimensionlessGibbsFreeEnergyRegion1.tau(temperature) * DimensionlessGibbsFreeEnergyRegion1.gammaTau(temperature, pressure) * ThermodynamicConstant.R * temperature;
        }
        return ThermoMath.NN;
    }

    /**
     * From table 2.3
     *
     * @return the specific internal energy in KJ/kg
     */
    @Override
    public double specificInternalEnergy() {
        if (isValid) {
            return (DimensionlessGibbsFreeEnergyRegion1.tau(temperature) * DimensionlessGibbsFreeEnergyRegion1.gammaTau(temperature, pressure) - DimensionlessGibbsFreeEnergyRegion1.pi(pressure) * DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure)) * ThermodynamicConstant.R * temperature;
        }
        return ThermoMath.NN;
    }

    /**
     * From table 2.3
     *
     * @return the specific entropy in KJ/kg-K
     */
    @Override
    public double specificEntropy() {
        if (isValid) {
            return (DimensionlessGibbsFreeEnergyRegion1.tau(temperature)
                    * DimensionlessGibbsFreeEnergyRegion1.gammaTau(temperature, pressure)
                    - DimensionlessGibbsFreeEnergyRegion1.gamma(temperature, pressure))
                    * ThermodynamicConstant.R;
        }
        return ThermoMath.NN;
    }

    @Override
    public double specificIsobaricHeatCapacity() {
        if (isValid) {
            return (-1) * (Math.pow(DimensionlessGibbsFreeEnergyRegion1.tau(temperature), 2) * DimensionlessGibbsFreeEnergyRegion1.gammaTauTau(temperature, pressure)) * ThermodynamicConstant.R;
        }
        return ThermoMath.NN;
    }

    @Override
    public double specificIsochoricHeatCapacity() {
        if (isValid) {
            return (-1) * (Math.pow(DimensionlessGibbsFreeEnergyRegion1.tau(temperature), 2) * DimensionlessGibbsFreeEnergyRegion1.gammaTauTau(temperature, pressure)) * ThermodynamicConstant.R;
        }
        return ThermoMath.NN;
    }

    @Override
    public double speedOfSound() {

        if (isValid) {
            double num = Math.pow(DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure), 2);
            double denom = (
                    Math.pow((DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure) - DimensionlessGibbsFreeEnergyRegion1.tau(temperature) * DimensionlessGibbsFreeEnergyRegion1.gammaPiTau(temperature, pressure)), 2)
                            /
                            (Math.pow(DimensionlessGibbsFreeEnergyRegion1.tau(temperature), 2) * DimensionlessGibbsFreeEnergyRegion1.gammaTauTau(temperature, pressure))
            ) -
                    DimensionlessGibbsFreeEnergyRegion1.gammaPiPi(temperature, pressure);

            return Math.sqrt((num / denom) * ThermodynamicConstant.R * temperature * 1000); // factor 1000, to convert kJ/kg = 1000 m²/s²
        }
        return ThermoMath.NN;
    }

    @Override
    public double isentropicExponent() {
        if (isValid) {
            double num = DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure);
            double denom = (
                    DimensionlessGibbsFreeEnergyRegion1.pi(pressure) * Math.pow((DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure) - DimensionlessGibbsFreeEnergyRegion1.tau(temperature) * DimensionlessGibbsFreeEnergyRegion1.gammaPiTau(temperature, pressure)), 2)
                            /
                            (Math.pow(DimensionlessGibbsFreeEnergyRegion1.tau(temperature), 2) * DimensionlessGibbsFreeEnergyRegion1.gammaTauTau(temperature, pressure))
            ) -
                    DimensionlessGibbsFreeEnergyRegion1.pi(pressure) * DimensionlessGibbsFreeEnergyRegion1.gammaPiPi(temperature, pressure);

            return (num / denom);
        }
        return ThermoMath.NN;
    }

    @Override
    public double isobaricCubicExpansionCoefficient() {
        if (isValid) {
            return (1 - (DimensionlessGibbsFreeEnergyRegion1.tau(temperature) * DimensionlessGibbsFreeEnergyRegion1.gammaPiTau(temperature, pressure) / DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure))) / temperature;
        }
        return ThermoMath.NN;
    }

    @Override
    public double isothermalCompressibility() {
        if (isValid) {
            return -(DimensionlessGibbsFreeEnergyRegion1.pi(pressure) * DimensionlessGibbsFreeEnergyRegion1.gammaPiPi(temperature, pressure) / DimensionlessGibbsFreeEnergyRegion1.gammaPi(temperature, pressure)) / pressure;
        }
        return ThermoMath.NN;
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

    /**
     * If temperature, pressure are within along the range, returns true.
     *
     * @return
     */
    public boolean isValid() {
        return isValid;
    }
}
