package com.iridiscense.unitoperations.physicschemmistry;

import android.util.Log;

import com.iridiscense.unitoperations.conversion.util.UnitRefactor;
import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.utils.OnTest;
import com.iridiscense.unitoperations.variables.Altitude;
import com.iridiscense.unitoperations.variables.Pressure;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 9/27/21.
 * Copyright (c)  Hector Bonifacio. 9/27/21, All rights reserved.
 */
@SuppressWarnings("DuplicateBranchesInSwitch")
public class Atmosphere {

    public static final String TAG = Atmosphere.class.getSimpleName();
    /**
     * Diseño de un sistema de ventilación y aire acondicionado para el quirófano y sala
     * de terapia intensiva de la Clínica Colonial.
     *
     * @param altitude over sea level
     * @return the pressure of atmosphere in MPa
     */
    public static Pressure pressure(Altitude altitude) {
        double value = 0;

        switch (altitude.measureUnit) {
            case "m":
                value =  0.101325 * Math.pow((1 - 2.25577E-5 * altitude.value), 5.2559); // MPa
                return new Pressure(value, "MPa");
            case "km":
                value =  0.101325 * Math.pow((1 - 2.25577E-5 * altitude.value * 1000), 5.2559); // MPa
                return new Pressure(value, "MPa");
            case "ft":
                value =  0.101325 * Math.pow((1 - 2.25577E-5 * altitude.value *  0.3048), 5.2559); // MPa
                return new Pressure(value, "MPa");
            default:
                return null;
        }
    }

    /**
     *
     * @param altitude on sea level
     * @param pressureUnit the unit of output pressure value
     * @return pressure with unit
     */
    @OnTest
    public static Pressure pressure(Altitude altitude, String pressureUnit) {
        double value = 0;
        Pressure psl = pressureSeaLevel(pressureUnit);
        switch (altitude.measureUnit) {
            case "m":
                value = psl.value * Math.pow((1 - 2.25577E-5 * altitude.value), 5.2559);
                return new Pressure(value, psl.measureUnit);
            case "km":
                value =  psl.value * Math.pow((1 - 2.25577E-5 * altitude.value * 1000), 5.2559);
                return new Pressure(value, psl.measureUnit);
            case "ft":
                value = psl.value * Math.pow((1 - 2.25577E-5 * altitude.value *  0.3048), 5.2559);
                return new Pressure(value, psl.measureUnit);
            case "feet":
                value = psl.value * Math.pow((1 - 2.25577E-5 * altitude.value *  0.3048), 5.2559);
                return new Pressure(value, psl.measureUnit);
            default:
                Log.d(TAG, "pressure: Altitude unit is not proper.");
                return new Pressure(ThermoMath.NN, "No Units. Add the unit for ask. " + Class.class.getName());
        }
    }

    /**
     *
     * @param unit unit for pressure on sea level
     * @return the pressure value in pressureUnit param
     */
    public static Pressure pressureSeaLevel(String unit) {
        double value = 0;

        // Hacer tratamiento a unit;
        unit = UnitRefactor.refactor(unit);

        switch (unit) {// As like as MeasureUnit.unicode
            case "kPa":
                value = 101.325;
                return new Pressure(value, unit);
            case "MPa":
                value = 0.101325;
                return new Pressure(value, unit);
            case "bar":
                value = 1.01325;
                return new Pressure(value, unit);
            case "atm":
                value = 1.0;
                return new Pressure(value, unit);
            case "mmHg":
                value = 760.0;
                return new Pressure(value, unit);
            case "PSI":
                value = 14.6959;
                return new Pressure(value, unit);
            default:
                return new Pressure(ThermoMath.NN, unit);
        }
    }
}