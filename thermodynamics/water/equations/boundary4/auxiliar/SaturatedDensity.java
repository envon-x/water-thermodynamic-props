package com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4.auxiliar;

import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.thermodynamics.water.equations.WaterConstant;

/**
 * This class was created for Comanda project
 * Region 3, book source 1.555926.pdf
 * Created by bon on 9/3/21.
 * Copyright (c)  Hector Bonifacio. 9/3/21, All rights reserved.
 */
public class SaturatedDensity {


    private static double theta(double temperature) {
        return temperature/ WaterConstant.Tc;
    }

    /**
     * From: International Equations for the Saturation Properties
     * of Ordinary Water Substance. Revised According to
     * the International Temperature Scale of 1990.
     * Addendum to J. Phys. Chem. Ref. Data 16, 893 (1987)
     * @param temperature
     * @return
     */
    private static double tau(double temperature) {
        return 1 - theta(temperature);
    }


    /**
     * Sec.  4.1, eq. (2)
     * @param temperature in Kelvins
     * @return the density of saturated liquid, kg/m³
     */
    public static double saturatedMassDensityLiquid(double temperature) {

        double acum = 1;
        double tau = SaturatedDensity.tau(temperature);

        if (temperature >= 273.16 && temperature <= 647.096) {
            for (int i = 0; i < 6; i++) {
                acum = acum + CoefficientsDensity.Bi[i] * Math.pow(tau, Exponent.Bi[i]/3);
            }
            return WaterConstant.Dc * acum;
        } else {
            return ThermoMath.NN;
        }
    }

    /**
     * Sec.  4.1, eq. (3)
     * @param temperature in Kelvins
     * @return the density of saturated vapor, kg/m³
     */
    public static double saturatedMassDensityVapor(double temperature) {

        double acum = 0;
        double tau = SaturatedDensity.tau(temperature);

        if (temperature >= 273.16 && temperature <= 647.096) {
            for (int i = 0; i < 6; i++) {
                acum = acum + CoefficientsDensity.Ci[i] * Math.pow(tau, Exponent.Ci[i]/6);
            }
            return WaterConstant.Dc * Math.exp(acum);
        } else {
            return ThermoMath.NN;
        }
    }
    /**
     * The International Association for the Properties of Water and Steam
     * St. Petersburg, Russia
     * September 1992
     * Revised. Supplementary Release on Saturation Properties of Ordinary Water Substance
     * Unrestricted publication allowed in all countries. Issued by the International
     * Association for the Properties of Water and Steam.
     * Sec.  4.1, eq. (1)
     * @param temperature in Kelvins
     * @return the pressure of saturated , MPa
     */
    public static double saturatedPressure(double temperature) {

        double acum = 0;
        double tau = SaturatedDensity.tau(temperature);

        if (temperature >= 273.16 && temperature <= 647.096) {
            for (int i = 0; i < 6; i++) {
                acum = acum + CoefficientsDensity.Ai[i] * Math.pow(tau, Exponent.Ai[i]/2);
            }
            return WaterConstant.Pc * Math.exp(acum * (WaterConstant.Tc / temperature));
        } else {
            return ThermoMath.NN;
        }
    }
}
