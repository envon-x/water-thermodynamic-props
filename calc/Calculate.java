package com.iridiscense.unitoperations.calc;

import com.iridiscense.unitoperations.conversion.util.UnitRefactor;
import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4.SaturatedRegion;
import com.iridiscense.unitoperations.utils.MeasureUnitException;
import com.iridiscense.unitoperations.variables.Pressure;
import com.iridiscense.unitoperations.variables.Temperature;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 9/25/21.
 * Copyright (c)  Hector Bonifacio. 9/25/21, All rights reserved.
 */
public class Calculate {
//set unit as final, puede simplificar o aumetar la eficiencia
    public static Pressure pressureSaturate(Temperature temperature, String unitPressure) {

        unitPressure = UnitRefactor.refactor(unitPressure);

        double value = 0;
        switch (temperature.measureUnit) {
            case ("K"):
                value = SaturatedRegion.pressureSaturate(temperature.value);
                value = pressureIn(value, unitPressure);
                break;
            case "°C":
                value = SaturatedRegion.pressureSaturate(temperature.value + 273.15d); //convertiendo a K
                value = pressureIn(value, unitPressure);
                break;
            case "°F":
                value = SaturatedRegion.pressureSaturate((temperature.value - 32)*(5/9) + 273.15d); //convertiendo a K
                value = pressureIn(value, unitPressure);
                break;
            default:
                value = ThermoMath.NN;
                try {
                    throw new MeasureUnitException("It doesn't have the unit for evaluate for " + unitPressure, unitPressure);
                } catch (MeasureUnitException e) {
                    e.printStackTrace();
                }
                break;
        }

        return new Pressure(value, unitPressure); // MPa

    }

    /**
     * cambiar de nombre el metodo a convertTo
     * @param value is always in MPa to converted to another unit value
     * @param unitPressure goal unit
     * @return the value converted to given unit
     */
    private static double pressureIn(double value, String unitPressure) {
        switch (unitPressure) {
            case "atm":
                return value * 9.86923;
            case "bar":
                return value * 10;
            case "mmHg":
                return value * 7500.64;
            case "kPa":
                return value * 1000;
            case "N/m²":
                return value * 1E6;
            case "psi":
                return value * 145.038;
            case "kgf/cm²":
                return value * 10.1972;
            default:
                value = ThermoMath.NN;
                try {
                    throw new MeasureUnitException("It doesn't have the unit for evaluate for " + unitPressure, unitPressure);
                } catch (MeasureUnitException e) {
                    e.printStackTrace();
                }
                return value;
        }
    }

    public static Temperature temperatureSaturate(Pressure pressure, String unitTemperature) {

        unitTemperature = UnitRefactor.refactor(unitTemperature);

        double value = 0;
        switch (pressure.measureUnit) {//Convertir en cada uno de los casos a MPa la presion
            case ("kPa"):
                value = SaturatedRegion.temperatureSaturate(pressure.value * 0.001); // Convertiendo a MPa
                value = temperatureIn(value, unitTemperature);
                break;
            case "atm":
                value = SaturatedRegion.temperatureSaturate(pressure.value * 0.101325); //convertiendo a MPa
                value = temperatureIn(value, unitTemperature);
                break;
            case "mmHg":
                value = SaturatedRegion.temperatureSaturate(pressure.value / 7500.64); //convertiendo a MPa
                value = temperatureIn(value, unitTemperature);
                break;
            case "psi":
                value = SaturatedRegion.temperatureSaturate(pressure.value / 145.038); //convertiendo a MPa
                value = temperatureIn(value, unitTemperature);
                break;
            default:
                try {
                    throw new MeasureUnitException("It doesn't have the unit for evaluate for " + unitTemperature, unitTemperature);
                } catch (MeasureUnitException e) {
                    e.printStackTrace();
                }
                value = ThermoMath.NN;
                break;
        }

        return new Temperature(value, unitTemperature); // MPa

    }

    /**
     *
     * @param value is always in Kelvin
     * @param unitTemperature unit temperature to convert
     * @return the value converted to given unit
     */
    private static double temperatureIn(double value, String unitTemperature) {
        switch (unitTemperature) {
            case "°C":
                value = value - 273.15;
                return value; // in C
            case "°F":
                value = 32 + (9/5)*(value - 273);
                return value; // in F
            default:
                try {
                    throw new MeasureUnitException("It doesn't have the unit for evaluate for " + unitTemperature, unitTemperature);
                } catch (MeasureUnitException e) {
                    e.printStackTrace();
                }
                return ThermoMath.NN;
        }
    }
//
//    public static String showValueWithUnitsIn(Pressure p) {
//        switch (p.unit) {
//            case atmosphere:
//                return Double.toString(p.value * 9.86923) + " atm";
//            case bar:
//                return Double.toString(p.value * 10) + " bar";
//            case millibar:
//                return Double.toString(p.value * 10000) + " mbar";
//            case mmHg:
//                return Double.toString(p.value * 7500.64) + " mmHg";
//            case kiloPascal:
//                return Double.toString(p.value * 1000) + " kPa";
//            case newtonPerm2:
//                return Double.toString(p.value * 1E6) + " N/m²";
//            case psi:
//                return Double.toString(p.value * 145.038) + " psi";
//            case lbf_per_in2:
//                return Double.toString(p.value * 145.038) + " lbf/in²";
//            case kgf_per_cm2:
//                return Double.toString(p.value * 10.1972) + " kgf/cm²";
//            default:
//                return "no units for the value";
//        }
//    }
}
