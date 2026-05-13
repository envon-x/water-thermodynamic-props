package com.iridiscense.unitoperations.thermodynamics.water.equations.metastable_vapour;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 10/12/21.
 * Copyright (c)  Hector Bonifacio. 10/12/21, All rights reserved.
 */
public class CoefficientMeta {


    // Table 2.12
    public static final double[] Ii = {
            1, 1, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5
    };

    //Table 2.12. n_i coefficients for Region 2
    public static final double[] Ji = {
            0, 2, 5, 11, 1, 7, 16, 4, 16, 7, 10, 9, 10

    };

    //Table 2.12.
    public static final double[] Ni = {
            -0.733_622_601_865_06E-2,
            -0.882_238_319_431_46E-1,
            -0.723_345_552_132_45E-1,
            -0.408_131_785_344_55E-2,
            0.200_978_033_802_07E-2,
            -0.530_459_218_986_42E-1,
            -0.761_904_090_869_70E-2,
            -0.634_980_376_573_13E-2,
            -0.860_430_930_285_88E-1,
            0.753_215_815_227_70E-2,
            -0.792_383_754_461_39E-2,
            -0.228_881_607_784_47E-3,
            -0.264_565_014_828_10E-2
    };



}
