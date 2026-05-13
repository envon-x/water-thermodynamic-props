package com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4;

import com.iridiscense.unitoperations.thermodynamics.ThermoMath;
import com.iridiscense.unitoperations.thermodynamics.water.equations.WaterConstant;

/**
 * Region Four.
 * Working with equations: (2.12), (2.13), (2.14).
 * This class was created for Environmental Engenering - UMSA project.
 * Created by bon on 3/18/21.
 * Copyright (c)  Hector Bonifacio. 3/18/21, All rights reserved.
 */
public class SaturatedRegion {

    private final static double Tx = 1.00D; // T* , K
    private final static double Px = 1.00D; // P* , MPa
    private static boolean isValid = false;

    /**
     * Check whether the temperature is valid.
     * @param T, in K. Temperature data evaluated.
     * @return boolean value for evaluated temperature. If it is validity, returns true.
     */
    private static boolean validityTemperatureRange(double T) {
        isValid = false;
        if (273.15 <= T && T <= WaterConstant.Tc) {
            System.out.println(SaturatedRegion.class.getSimpleName()+ ": T = " + T + ", [K it's OK.");
            isValid = true;
        } else {
            System.out.println(SaturatedRegion.class.getSimpleName()+":   T = " + T + ", [K] is out of range.");
            isValid = false;
        }
        return isValid;
    }

    /**
     * Evaluate validity pressure for given date
     * 0.611212677E-9 <= P doesn' t work properly; returns validity for 0.61,
     * 0.611 or among values, when that is not correct.
     * Correct pressure data is: 611.212 677 Pa <= P <= 22.064 MPa
     * @param P, pressure data evaluated for the given range
     * @return
     */
    private static boolean validityPressureRange(double P) {
        isValid = false;
        if (611.212677E-6 <= P && P <= WaterConstant.Pc) {
            System.out.println(Class.class.getSimpleName() + ", P = " + P + ", [MPa] is OK.");
            isValid = true;
        } else {
            System.out.println(Class.class.getSimpleName() + "P = " + P + ", [MPa] is out of range.");
            isValid = false;
        }
        return isValid;
    }

    /**
     *
     * @param Ts in [K], saturated temperature evaluated.
     *      *            its range is: 273.15 <= T <= 647.096, K.
     * @return theta is dimensionless value  to calculate Ps, MPa.
     */
    private static double theta(double Ts) {
        if (validityTemperatureRange(Ts)) {
            double val = (Ts/Tx)+(CoefficcientSat.Ni[8] /((Ts/Tx)- CoefficcientSat.Ni[9]));
            System.out.println(Class.class.getSimpleName() + "Ts : " + Ts + " theta: " + val);
            return (Ts/Tx)+(CoefficcientSat.Ni[8] /((Ts/Tx)- CoefficcientSat.Ni[9]));
        } else {
            System.out.println(Class.class.getSimpleName() + "Ts = " + Ts + " is out of range. Please select another value along 273.15 <= T <= 647.096, K");
            return ThermoMath.NN;
        }
    }

    /**
     *
     * @param Ps in [Mpa], saturated pressure evaluated.
     *      *            its range is: 611.212 677E-6 MPa <= P <= 22.064, MPa.
     * @return beta is dimensionless value  to calculate Ps, MPa.
     */
    private static double beta(double Ps) {
        if (validityPressureRange(Ps)) {
            return Math.pow(Ps/Px, 0.25D);
        } else {
            System.out.println(Class.class.getSimpleName() + "Ps = " + Ps + " is out of range. Please select another value along 611.212 677E-6 MPa <= P <= 22.064, MPa");
            return ThermoMath.NN;
        }
    }

    /**
     * Calculate Pressure Saturate from data temperature
     * @param T data in Kelvin's
     * @return Pressure Saturate in MPa
     */
    public static double pressureSaturate(double T /*in K*/) {
        double value;
        if (validityTemperatureRange(T)) {
            double a = SaturatedRegion.aT(T);
            double b = SaturatedRegion.bT(T);
            double c = SaturatedRegion.cT(T);
            value = Px * Math.pow((2 * c/(-b + Math.pow((b*b - 4*a*c), 0.5))), 4);
            return value;
        } else {
            return ThermoMath.NN;
        }
    }


    /**
     * Calculate Pressure Saturate from data temperature
     * @param temperature data: K for Kelvin's and C for Celsius degree
     * @return Pressure Saturate in mmHg
     */
    @Deprecated
    public static double pressureSaturate_mmHg(double temperature /*in K or C*/, String unit) {
        double value = 0;
        switch (unit) {
            case "K":
                value = SaturatedRegion.pressureSaturate(temperature);
                value = value * 7501; //factor para convertir de MPa a mmHg
                return value;
            case "C":
                value = SaturatedRegion.pressureSaturate(temperature + 273.15d); //convertiendo a K
                value = value * 7501; //factor para convertir de MPa a mmHg
                return value;
            default:
                value = ThermoMath.NN;
                return value;
        }
    }



    /**
     *
     * @param P, pressure evaluated in MPa
     * @return temperature in Kekvins
     */
    public static double temperatureSaturate(double P) {
        if (validityPressureRange(P)) {
            double d = SaturatedRegion.dP(P);
            return Tx * (CoefficcientSat.Ni[9] + d - Math.sqrt(Math.pow((CoefficcientSat.Ni[9] + d), 2) - 4*(CoefficcientSat.Ni[8] + CoefficcientSat.Ni[9] * d)))/2;
        } else {
            return ThermoMath.NN;
        }
    }

    /**
     * Calculate the A coefficient for saturated-pressure equation (2.13)
     * @param Ts, in [K], saturated temperature evaluated.
     *            its range is: 273.15 <= T <= 647.096, K.
     * @return constant A value.
     */
    public static double aT(double Ts) {
        double theta = theta(Ts);
        if (Math.abs(theta - ThermoMath.NN) < ThermoMath.TOLERANCE) {
            return ThermoMath.NN;
        }
        return Math.pow(theta,2) + CoefficcientSat.Ni[0]*theta + CoefficcientSat.Ni[1];
    }

    /**
     * Calculate the B coefficient for saturated-pressure equation (2.13)
     * @param Ts, in [k], saturated temperature evaluated.
     *            its range is: 273.15 <= T <= 647.096, K.
     * @return constant B value, for calculating Ps, MPa.
     */
    public static double bT(double Ts) {
        double theta = theta(Ts);
        if (Math.abs(theta - ThermoMath.NN) < ThermoMath.TOLERANCE) {
            return ThermoMath.NN;
        }
        return CoefficcientSat.Ni[2]*Math.pow(theta,2) + CoefficcientSat.Ni[3]*theta + CoefficcientSat.Ni[4];
    }

    /**
     *  Calculate the C coefficient for saturated-pressure equation (2.13)
     * @param Ts, in [K], saturated temperature evaluated.
     *            its range is: 273.15 <= T <= 647.096, K.
     * @return constant C value, for calculating Ps, MPa.
     */
    public static double cT(double Ts) {
        double theta = theta(Ts);
        if (Math.abs(theta - ThermoMath.NN) < ThermoMath.TOLERANCE) {
            return ThermoMath.NN;
        }
        return CoefficcientSat.Ni[5]*Math.pow(theta,2) + CoefficcientSat.Ni[6]*theta + CoefficcientSat.Ni[7];
    }

    /**
     * @param Ps, in [k], saturated Pressure evaluated.
     *            its range is: 611.212 677E-6 Pa <= P <= 22.064, MPa.
     * @return constant C value, for calculating Ts, K.
     */
    public static double aP(double Ps) {
        double beta = beta(Ps);
        if (isValid) {
            return Math.pow(beta,2) + CoefficcientSat.Ni[2]*beta + CoefficcientSat.Ni[5];
        }
        return ThermoMath.NN;
    }

    /**
     * @param Ps, in [k], saturated Pressure evaluated.
     *            its range is: 611.212 677E-6 Pa <= P <= 22.064, MPa.
     * @return constant B value, for calculating Ts, K.
     */
    public static double bP(double Ps) {
        double beta = beta(Ps);
        if (isValid) {
            return CoefficcientSat.Ni[0]*Math.pow(beta,2) + CoefficcientSat.Ni[3]*beta + CoefficcientSat.Ni[6];
        }
        return ThermoMath.NN;
    }

    /**
     * @param Ps, in [k], saturated Pressure evaluated.
     *            its range is: 611.212 677E-6 Pa <= P <= 22.064, MPa.
     * @return constant C value, for calculating Ts, K.
     */
    public static double cP(double Ps) {
        double beta = beta(Ps);
        if (isValid) {
            return CoefficcientSat.Ni[1]*Math.pow(beta,2) + CoefficcientSat.Ni[4]*beta + CoefficcientSat.Ni[7];
        }
        return ThermoMath.NN;
    }

    /**
     * @param Ps, in [k], saturated Pressure evaluated.
     *            its range is: 611.212 677E-6 Pa <= P <= 22.064, MPa.
     * @return constant D value, for calculating Ts, K.
     */
    public static double dP(double Ps) {
        double a = SaturatedRegion.aP(Ps);
        double b = SaturatedRegion.bP(Ps);
        double c = SaturatedRegion.cP(Ps);

        if (isValid) {
            double d = 2 * c/((-1)*b - Math.sqrt(Math.pow(b, 2) - 4 * a * c));
            return d;
        }
        return ThermoMath.NN;
    }

}
