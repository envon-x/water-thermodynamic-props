package com.iridiscense.unitoperations.thermodynamics.water.equations.backward;

/**
 * This class was created for Comanda project
 * Table 2.31 Coefficients and exponents of the backward equation T1(p,h) in its dimensionless form, Eq. (2.19)
 * Created by bon on 8/30/21.
 * Copyright (c)  Hector Bonifacio. 8/30/21, All rights reserved.
 */
public class BCoefficientT1 {

    public final static double [] Ii = {
            0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 5, 6,
    };

    public final static double [] Ji = {
            0, 1, 2, 6, 22, 32, 0, 1, 2, 3, 4, 10, 32, 10, 32, 10, 32, 32, 32, 32,
    };

    public final static double [] Ni = {
            -0.238_724_899_245_21E3,
             0.404_211_886_379_45E3,
             0.113_497_468_817_18E3,
            -0.584_576_160_480_39E1,
            -0.152_854_824_131_40E-3,
            -0.108_667_076_953_77E-5,
            -0.133_917_448_726_02E2,
             0.432_110_391_835_59E2,
            -0.540_100_671_705_06E2,
             0.305_358_922_039_16E2,
            -0.659_647_494_236_38E1,
             0.939_654_008_783_63E-2,
             0.115_736_475_053_40E-6,
            -0.258_586_412_820_73E-4,
            -0.406_443_630_847_99E-8,
             0.664_561_861_916_35E-7,
             0.806_707_341_030_27E-10,
            -0.934_777_712_139_47E-12,
             0.582_654_420_206_01E-14,
            -0.150_201_859_535_03E-16,


    };
}
