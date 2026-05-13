package com.iridiscense.unitoperations.thermodynamics.water.equations.boundary23;

import com.iridiscense.unitoperations.thermodynamics.SpecificProperty;
import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.thermodynamics.Property;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicVariablesManager;


/**
 *
 * This class was created for Environmental Engenering - UMSA project
 *
 * Auxiliary Equation for the Boundary between Regions 2 and 3
 *
 * Created by bon on 3/19/21.
 * Copyright (c)  Hector Bonifacio. 3/19/21, All rights reserved.
 */
public class BoundaryRegion23 extends Property implements ThermodynamicVariablesManager, SpecificProperty {

    private final static double Tx = 1.00D; // T* , K
    private final static double Px = 1.00D; // P* , MPa
    private static boolean isValid = false;

    /**
     *
     * @param temperature
     * @param pressure
     */
    public BoundaryRegion23(double temperature, double pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
    }

    /**
     * Check if temperature within of range.
     * @param T, absolute temperature; must be in Kelvins
     * @return the true whether the pressure id valid
     */
    private static boolean validityTemperature(double T) {
        boolean isValidT = false;
        if (T >= 623.15d  && T <= 863.15d) {
            isValidT = true;
        } else {
            isValidT = false;
            System.out.println(BoundaryRegion23.class.getSimpleName() + ", T is not out of range. ");
        }
        return  isValidT;

    }

    /**
     * Check if pressure within of range.
     * For Eq. of the Boundary between Regions 2 and 3
     * @param P in MPa
     * @return the true whether the pressure id valid
     */
    private static boolean validityPressure(double P) {
        boolean isValidP = false;
        if (P >= 16.5292 && P <= 100.00) {
            isValidP = true;
        } else {
            isValidP = false;
            System.out.println(BoundaryRegion23.class.getSimpleName() + ", P is not out of range. ");
        }
        return isValidP;
    }

    /**
     * Eq. 2.1
     * @param T, must be in Kelvin's. [K]
     * @return the boundary pressure in MPa on boundary 2-3
     */
    public static double pressureB23(double T) {
        double pB23 = 0d;
        double theta = theta(T);

        if (validityTemperature(T)) {
            pB23 = CoefficientsBoundary23.Ni[0]
                    + CoefficientsBoundary23.Ni[1]* theta
                    + CoefficientsBoundary23.Ni[2] * Math.pow(theta, 2);

            return pB23 * Px;
        } else {
            System.out.println(BoundaryRegion23.class.getSimpleName() + "Boundary Pressure false");
            return ThermoMath.NN;
        }
    }

    /**
     * Eq. 2.2
     * @param P, must be in MPa.
     * @return
     */
    public static double temperatureB23(double P) {
        double pi = pi(P);

        if (validityPressure(P)) {
            return CoefficientsBoundary23.Ni[3]
                    + Math.pow((pi - CoefficientsBoundary23.Ni[4]) / CoefficientsBoundary23.Ni[2], 0.5);
        } else {
            System.out.println(BoundaryRegion23.class.getSimpleName() + ", P = " + P + " is out of range. Please select another value along 16.5292 MPa <= P <= 100, MPa");
            return ThermoMath.NN;
        }
    }

    /**
     * For sec. 2.2.1
     * @param P in MPa
     * @return
     */
    protected static double pi(double P) {
        return P/Px;
     }


    protected static double theta(double T) {
        return T/Tx;
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
