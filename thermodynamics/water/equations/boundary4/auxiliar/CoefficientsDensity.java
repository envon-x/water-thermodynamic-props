package com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4.auxiliar;

/**
 * This class was created for Comanda project
 * Created by bon on 9/3/21.
 * Copyright (c)  Hector Bonifacio. 9/3/21, All rights reserved.
 */
public class CoefficientsDensity {

    //Alternative for Vapor pressure
    public final static double[] Ai = {
            -7.85951783,
             1.84408259,
            -11.7866497,
             22.6807411,
            -15.9618719,
            1.80122502
    };


    // densityLiquid
    //This coefficients are for eq. (2)
    public final static double[] Bi =
            {
                    1.99274064,
                    1.09965342,
                    -0.510839303,
                    -1.75493479,
                    -45.5170352,
                    -6.74694450E5
            };

    // 4.2 Density of the saturated vapor
    public final static double[] Ci =
            {
                    -2.03150240,
                    -2.68302940,
                    -5.38626492,
                    -17.2991605,
                    -44.7586581,
                    -63.9201063,
            };


}
