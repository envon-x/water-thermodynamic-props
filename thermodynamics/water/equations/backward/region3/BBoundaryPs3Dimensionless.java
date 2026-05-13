package com.iridiscense.unitoperations.thermodynamics.water.equations.backward.region3;

/**
 * This class was created for Comanda project
 * Created by bon on 9/1/21.
 * Copyright (c)  Hector Bonifacio. 9/1/21, All rights reserved.
 */
public class BBoundaryPs3Dimensionless {

    private static double px = 22; // MPa. The Boundary Equation Ps,3(h)
    private static double hx = 2600; // KJ/Kg. The Boundary Equation Ps,3(h)

    protected static double eta(double enthalpy) {
        return  enthalpy/hx;
    }

    protected static double pi(double pressure) {
        return  pressure/px;
    }
}
