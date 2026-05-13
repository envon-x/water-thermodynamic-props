package com.iridiscense.unitoperations.thermodynamics.water.equations.region5;

/**
 * This class was created for Comanda project
 * Created by bon on 9/3/21.
 * Copyright (c)  Hector Bonifacio. 9/3/21, All rights reserved.
 */
public class CoefficientsRegion5 {


    // Table 3.37. Coefficients and exponents of Eq. (3.26)
    // Table 2.22 Coefficients and exponents of the ideal-gas part 𝛾° , Eq. (2.16)
    public final static double [] Ji_id = {
            0,
            1,
            -3,
            -2,
            -1,
            2,

    };
    // Table 2.22 Coefficients and exponents of the ideal-gas part 𝛾° , Eq. (2.16)
    // Table 3.37. Coefficients and exponents of Eq. (3.26)

    public final static double [] Ni_id = {
            -0.131_799_836_742_01E2,
            0.685_408_416_344_34E1,
            -0.248_051_489_334_66E-1,
            0.369_015_349_803_33,
            -0.311_613_182_139_25E1,
            -0.329_616_265_389_17,
    };


    // Table 2.23 Coefficients and exponents of the residual-gas part 𝛾° , Eq. (2.17)
    public final static double [] Ii_res = {
            1,
            1,
            1,
            2,
            2,
            3,
    };


    // Table 3.38. Coefficients and exponents of Eq. (3.27)
    // Table 2.23 Coefficients and exponents of the residual-gas part 𝛾° , Eq. (2.17)
    public final static double [] Ji_res = {
            1,
            2,
            3,
            3,
            9,
            3,
    };

    // Table 3.38. Coefficients and exponents of Eq. (3.27)
    public final static double [] Ni_res_old = {
            -0.125_631_835_895_92E-3,
             0.217_746_787_145_71E-2,
            -0.459_428_208_999_10E-2,
            -0.397_248_283_595_69E-5,
             0.129_192_282_897_84E-6,
    };

    // Table 2.23 Coefficients and exponents of the residual-gas part 𝛾° , Eq. (2.17)
    public final static double [] Ni_res = {
             0.157_364_048_552_59E-2,
             0.901_537_616_739_44E-3,
            -0.502_700_776_776_48E-2,
             0.224_400_374_094_85E-5,
            -0.411_632_754_534_71E-5,
             0.379_194_548_229_55E-7,

    };

}
