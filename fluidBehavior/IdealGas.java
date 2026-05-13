package com.iridiscense.unitoperations.fluidBehavior;

/**
 * This class was created for Comanda project
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public class IdealGas {

    public static double R = 8.314;         // kPa m³/kmol-K
    public static double Ratm = 0.082;      // atm-l/mol-K
    public static double RmmHg = 62.4;      // mmHg/mol-K

    /**
     *
     * @param T, temperature in [K]
     * @param v, specific volume in [m³/kg]
     * @return P, pressure in [kPa]
     */
    public static double pressure(double T, double v) {
        return R*T/v;
    }

    /**
     *
     * @param T, temperature in [K]
     * @param v, specific volume in [l/kg]
     * @return P, pressure in [kPa]
     */
    public static double pressure(double T, double v, String unit) {

        switch (unit){
            case "atm": // atm
                return Ratm*T/v;
            case "kPa":
                return R*T/v;
//            case "MPa":
//                return null;
//            case "mmHg":
//                return ;
//            case "psi":
//                return ;
//            case "torr":
//                return ;
//            case "Bar":
//                return ;
//            case "mBar":
//                return ;
//            case "mH2O":
//            case "inchH2O":
//                return ;
//            case "kgf/cm^2":
//                return ;
        }
    return 0;
    }

    /**
     *
     * @param T, temperature in [K]
     * @param v, specific volume in [m³/kg]
     * @param n, mole in [mole]
     * @return P, pressure in [kPa]
     */
    public static double pressureInkPa(double T, double v, double n) {
        return n*R*T/v;
    }

    /**
     *
     * @param T, temperature in [K]
     * @param  P, pressure in [kPa]
     * @return v, specific volume in [m³/kg]
     */
    public static double volume(double T, double P) {

        return R*T/P;
    }

    /**
     *
     * @param T, temperature in [K]
     * @param  P, pressure in [kPa]
     * @param n, mole in [mole]
     * @return v, volume in [m³]
     */
    public static double volume(double T, double P, double n) {

        return n*R*T/P;
    }

    /**
     *
     * @param T, temperature in [K]
     * @param  P, pressure in [kPa]
     * @param  V, volume in [m³]
     * @return n, mole in [kmole/kg]
     */
    public static double mole(double T, double P, double V) {
        return P*V/(R*T);
    }
}
