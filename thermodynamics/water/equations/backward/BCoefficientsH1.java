package com.iridiscense.unitoperations.thermodynamics.water.equations.backward;

/**
 * This class was created for Comanda project
 * Created by bon on 8/29/21.
 * Copyright (c)  Hector Bonifacio. 8/29/21, All rights reserved.
 */
public class BCoefficientsH1 {

//    Table 2.67 Coefficients and exponents of the boundary equation h'ı(s) in its dimensionless form, Eq. (2.40)
    public final static double [] Ii = {
        0, 0, 1, 1, 2, 2, 3, 3, 4, 4,
        4, 5, 5, 7, 8, 12, 12, 14, 14, 16,
        20, 20, 22, 24, 28, 32, 32,
};

    public final static double [] Ji = {
            0, 0, 1, 1, 2, 2, 3, 3, 4, 4,
            4, 5, 5, 7, 8, 12, 12, 14, 14, 16,
            20, 20, 22, 24, 28, 32, 32,
    };

    public final static double [] Ni = {
            0.332_171_191_705_237,
            0.611_217_706_323_496E-3,
            -0.882_092_478_906_822E1,
            -0.455_628_192_543_250,
            -0.263_483_840_850_452E-4,
            -0.223_949_661_148_062E2,
            -0.428_398_660_164_013E1,
            -0.616_679_338_856_916,
            -0.146_823_031_104_040E2,
            0.284_523_138_727_299E3,
            -0.113_398_503_195_444E3,
            0.115_671_380_760_859E4,
            0.395_551_267_359_325E3,
            -0.154_891_257_229_285E1,
            0.194_486_637_751_291E2,
            -0.357_915_139_457_043E1,
            -0.335_369_414_148_819E1,
            -0.664_426_796_332_460,
            0.323_321_885_383_934E5,
            0.331_766_744_667_084E4,
            -0.223_501_257_931_087E5,
            0.573_953_875_852_936E7,
            0.173_226_193_407_919E3,
            -0.363_968_822_121_321E-1,
            0.834_596_332_878_346E-6,
            0.503_611_916_682_674E1,
            0.655_444_787_064_505E2
    };
}
