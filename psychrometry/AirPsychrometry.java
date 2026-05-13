package com.iridiscense.unitoperations.psychrometry;

import com.iridiscense.unitoperations.calc.Calculate;
import com.iridiscense.unitoperations.conversion.util.UnitRefactor;
import com.iridiscense.unitoperations.physicschemmistry.Atmosphere;
import com.iridiscense.unitoperations.thermodynamics.UniversalGasConstant;
import com.iridiscense.unitoperations.utils.OnTest;
import com.iridiscense.unitoperations.variables.Altitude;
import com.iridiscense.unitoperations.variables.Enthalpy;
import com.iridiscense.unitoperations.variables.MolecularWeight;
import com.iridiscense.unitoperations.variables.Volume;
import com.iridiscense.unitoperations.variables.WetBulbTemperature;
import com.iridiscense.unitoperations.variables.DewPointTemperature;
import com.iridiscense.unitoperations.variables.DryBulbTemperature;
import com.iridiscense.unitoperations.variables.Pressure;

/**
 * This class was created for Comanda project
 * Created by bon on 9/8/21.
 * Copyright (c)  Hector Bonifacio. 9/8/21, All rights reserved.
 */
public class AirPsychrometry extends Psychrometry {

    private Altitude altitude;

    /**
     *
     * @param dryBulbTemperature
     * @param relativeHumidity
     * @param atmospherePressure
     */
    public AirPsychrometry(DryBulbTemperature dryBulbTemperature,
                           double relativeHumidity,
                           Pressure atmospherePressure) {
        this.pressure = atmospherePressure;
        this.dryBulbTemperature = dryBulbTemperature;
        this.relativeHumidity = relativeHumidity;
    }

    /**
     *
     * @param dryBulbTemperature
     * @param wetBulbTemperature
     * @param atmospherePressure
     */
    public AirPsychrometry(DryBulbTemperature dryBulbTemperature,
                           WetBulbTemperature wetBulbTemperature,
                           Pressure atmospherePressure) {
        this.pressure = atmospherePressure;
        this.dryBulbTemperature = dryBulbTemperature;
        this.wetBulbTemperature = wetBulbTemperature;
    }

    /**
     *
     * @param dryBulbTemperature
     * @param dewPointTemperature
     * @param atmospherePressure
     */
    public AirPsychrometry(DryBulbTemperature dryBulbTemperature,
                           DewPointTemperature dewPointTemperature,
                           Pressure atmospherePressure) {
        this.pressure = atmospherePressure;
        this.dryBulbTemperature = dryBulbTemperature;
        this.dewPointTemperature = dewPointTemperature;
    }

    /**
     *
     * @param dryBulbTemperature
     * @param relativeHumidity
     * @param altitude
     * @param pressureUnit
     */
    public AirPsychrometry(DryBulbTemperature dryBulbTemperature,
                           double relativeHumidity,
                           Altitude altitude, String pressureUnit) {
        this.altitude = altitude;
        pressure = localPressure(altitude, pressureUnit);
        this.dryBulbTemperature = dryBulbTemperature;
        this.relativeHumidity = relativeHumidity;
        this.dewPointTemperature = calculateDewPointTemperature();
    }

    /**
     *
     * @param dryBulbTemperature
     * @param wetBulbTemperature
     * @param altitude
     * @param pressureUnit
     */
    public AirPsychrometry(DryBulbTemperature dryBulbTemperature,
                           WetBulbTemperature wetBulbTemperature,
                           Altitude altitude, String pressureUnit) {
        this.altitude = altitude;
        pressure = localPressure(altitude, pressureUnit);
        this.dryBulbTemperature = dryBulbTemperature;
        this.wetBulbTemperature = this.wetBulbTemperature;
    }

    /**
     *
     * @param dryBulbTemperature
     * @param dewPointTemperature
     * @param altitude
     * @param pressureUnit
     */
    public AirPsychrometry(DryBulbTemperature dryBulbTemperature,
                           DewPointTemperature dewPointTemperature,
                           Altitude altitude, String pressureUnit) {
        this.altitude = altitude;
        pressure = localPressure(altitude, pressureUnit);
        this.dryBulbTemperature = dryBulbTemperature;
        this.dewPointTemperature = dewPointTemperature;
    }

    /**
     *
     * @param altitude on sea level
     * @param pressureUnit pressure unit at sea level
     * @return Pressure value and unit of given sea level
     */
    private Pressure localPressure(Altitude altitude, String pressureUnit) {
        Pressure p =  new Pressure(Atmosphere.pressure(altitude, pressureUnit).value, UnitRefactor.refactor(pressureUnit));
        return p;
    }



    /**
     *
     * @param humid fraction of 1 (0 to 1)
     * @return
     */
    public double partialPressure(double humid, String unitPressure) {
        Pressure saturatePressure =  Calculate.pressureSaturate(dryBulbTemperature, unitPressure);
        double p_sat = saturatePressure.value;
        double Pv = humid * p_sat;
        return Pv;
    }

    /**
     * The constant is relativeHumidity param
     * @param humid 0 - 1, dimensionless
     * @return
     */
    @Deprecated //en favor de AbsoluteHumidityRelations
    public double absoluteHumidity(double humid) {
        Pressure saturatePressure =  Calculate.pressureSaturate(dryBulbTemperature, pressure.measureUnit);
        double p = saturatePressure.value;
        double Pv = humid * p;
        return (Pv/(pressure.value - Pv)) * 0.621945d;
    }


    public double absoluteHumidity(Pressure pressure, Pressure localPressure) {
        return pressure.value/(localPressure.value - pressure.value);
    }

    /**
     * From: Disenio del sistema de ventilacion
     * page 17
     * @param dryBulbTemperature
     * @return
     */
    public double absoluteHumidity(DryBulbTemperature dryBulbTemperature) {
        double h_g = 2501 + 1.86 * dryBulbTemperature.value;
        double value = (10.0 - 1.006* dryBulbTemperature.value)/h_g;
        return value/h_g;

 }

    public double absoluteHumidity(MolecularWeight weightA, MolecularWeight weightB, double molarHumidity) {
        return (weightA.value / weightB.value) * molarHumidity;
    }


    public double relativityHumidity(Pressure pressure, Pressure saturatedPressure) {
        return pressure.value / saturatedPressure.value;
    }


    @OnTest
    public Enthalpy specificEnthalpy(DryBulbTemperature dbt, double absoluteHumidity) {
        double value = 1.006 * dbt.value + absoluteHumidity * (2501 + 1.86 * dbt.value);
        String unit = "kJ/kg";
        return new Enthalpy(value, unit);
    }

    public Enthalpy specificEnthalpy(double absoluteHumidity) {
        double value = 1.006 * dryBulbTemperature.value + absoluteHumidity * (2501 + 1.86 * dryBulbTemperature.value);
        String unit = "kJ/kg";
        return new Enthalpy(value, unit);
    }



    @OnTest
    public Volume specificVolume(DryBulbTemperature dbt, String unitVolume) {
        double value = UniversalGasConstant.atm_cm_cubic_PER_mol_K.value * (dbt.value + 273.15);
        return new Volume(value, unitVolume);
    }



    /**
     *
     * @return
     */
    public DewPointTemperature calculateDewPointTemperature() {
        switch (dryBulbTemperature.measureUnit) {
            case "°C":
                double pω = Calculate.pressureSaturate(dryBulbTemperature, "kPa").value;
                double α = Math.log(pω);
                double C7 = 6.54;
                double C8 = 14.526;
                double C9 = 0.7389;
                double C10 = 0.09486;
                double C11 = 0.4569;
                double value = C7 + C8 * α + C9 * Math.pow(α,2) + C10 * Math.pow(α,3) + C11 * Math.pow(pω,0.1984);
                return new DewPointTemperature(value, "°C");
            case "°F":
                return null;
            default:
                return null;
        }
    }

}
