package com.iridiscense.unitoperations.thermodynamics.water.equations;

/**
 * This class was created for Comanda project
 * Created by bon on 3/6/21.
 * Copyright (c)  Hector Bonifacio. 3/6/21, All rights reserved.
 */
public class ReducedProperties {

    /**
     * The boundary between regions
     * 273.15 K^3  T  1073.15 K   &   0 < P  100 MPa
     * 1073.15 K < T  2273.15 K    &   0 < P  50 MPa
     * @return
     */
//    public static double quadraticPT() {
//        double Pb;
//        double Pc = reducedPressure(1); // P, MPa
//
//    }

    /**
     * Presion reducida
     * @param P, MPa
     * @return
     */
    public static double reducedPressure(double P) {
        return P/ WaterConstant.Pc;
    }

    /**
     * Presion reducida
     * @param T, absolute temperature, K
     * @return
     */
    public static double reducedTemperature(double T) {
        return T/ WaterConstant.Tc;
    }


}
