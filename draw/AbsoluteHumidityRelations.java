package com.iridiscense.unitoperations.draw;

import com.iridiscense.unitoperations.calc.Calculate;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicConstant;
import com.iridiscense.unitoperations.thermodynamics.water.equations.WaterConstant;
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
public class AbsoluteHumidityRelations {

    private static AbsoluteHumidityFunction atRelativeHumidityConstant;
    private static AbsoluteHumidityFunction atSpecificEnthalpyConstant;
    private static AbsoluteHumidityFunction getAtSpecificVolumeConstant;

    /**
     *  Calcula la  humedad absoluta, cuando se considera, la humedad relativa como constante
     * @return
     */
    public static AbsoluteHumidityFunction atRelativeHumidityConstant() {
        if (atRelativeHumidityConstant == null) {
            atRelativeHumidityConstant = new AbsoluteHumidityFunction() {
                @Override
                public AbsoluteHumidity calculate(Pressure atmospherePressure, DryBulbTemperature dryBulbTemperature, double... fixedVariable) {
                    Pressure pressureSaturate = Calculate.pressureSaturate(dryBulbTemperature, atmospherePressure.measureUnit);
                    double value = ((fixedVariable[0] * pressureSaturate.value)
                            / (atmospherePressure.value - fixedVariable[0] * pressureSaturate.value )) * (0.62);
                    if (value < 0) {
                        System.out.println("---: HR: hr: " + value);
                        System.out.println("---: HR: hr1: " + fixedVariable[0]);
                        System.out.println("---: HR: atm: f " + fixedVariable[0] * pressureSaturate.value);
                    }
                    return new AbsoluteHumidity(value,"kg(v)/kg(as)");
                }
            };
        }
        return atRelativeHumidityConstant;
    }

    /**
     *
     * @return la humedad absoluta cuando la entalpia es una constante o cuando a la entalpia se ha asignado un valor
     */
    public static AbsoluteHumidityFunction atSpecificEnthalpy() {
        if (atSpecificEnthalpyConstant == null) {
            atSpecificEnthalpyConstant = new AbsoluteHumidityFunction() {
                @Override
                public AbsoluteHumidity calculate(Pressure atmospherePressure, DryBulbTemperature dryBulbTemperature, double... fixedVariable) {
                    double value =  (fixedVariable[0] - 0.24d*dryBulbTemperature.value) / (0.46d * dryBulbTemperature.value + 597.2d);

                    if (value < 0) {
                        System.out.println("---: H:" + value);
                    }
                    return new AbsoluteHumidity(value,"kg(v)/kg(as)");
                }
            };
        }


        return atSpecificEnthalpyConstant;
    }

    /**
     *
     * @return absolute humidity when Specific Volume is constant
     */
    public static AbsoluteHumidityFunction atSpecificVolumeConstant() {
        if (getAtSpecificVolumeConstant == null) {
            getAtSpecificVolumeConstant = new AbsoluteHumidityFunction() {
                @Override
                public AbsoluteHumidity calculate(Pressure atmospherePressure, DryBulbTemperature dryBulbTemperature, double... fixedVariable) {
                    double R = selectUniversalConstant(atmospherePressure);
                    double T = convertTemperature(dryBulbTemperature);
                    double value = ((fixedVariable[0] * atmospherePressure.value / (R * (dryBulbTemperature.value + 273.15)))  - (1 / WaterConstant.M_air)) * WaterConstant.M_water;

                    if (value < 0) {
                        System.out.println("---: V:" + value);
                    }

                    return new AbsoluteHumidity(value,"kg(v)/kg(as)");
                }
            };
        }
        return getAtSpecificVolumeConstant;
    }

    /**
     * Conversion a unidades absolutas
     * @param dryBulbTemperature en unidades relativas
     * @return temperatura en unidades absolutas
     */
    private static double convertTemperature(DryBulbTemperature dryBulbTemperature) {
        switch (dryBulbTemperature.measureUnit) {
            case ("C"):
                return dryBulbTemperature.value + 273.15;

            case ("°C"):
                return dryBulbTemperature.value + 273.15;

            case ("F"):
                return dryBulbTemperature.value + 460;  // Rankine

            case  "°F":
                return dryBulbTemperature.value + 460;  // Rankine
            default:
                throw new RuntimeException();
        }
    }

    /**
     *
     * @param atmospherePressure permite seleccionar la constante unmiversal de los gases
     *                           apropiado.
     * @return R constante universal de los gases en base a la presion dada
     */
    private static double selectUniversalConstant(Pressure atmospherePressure) {
        switch (atmospherePressure.measureUnit) {
            case "atm":
                return ThermodynamicConstant.Ratm;
            case "mmHg":
                return ThermodynamicConstant.R_mmHg;
            case "kPa":
                return ThermodynamicConstant.RkPa;
            case "psi":
                return ThermodynamicConstant.R_psia;
            default:
//                System.out.println("[ERROR]: " + this.getClass().getSimpleName());
                throw new RuntimeException();
        }
    }

}
