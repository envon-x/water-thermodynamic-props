package com.iridiscense.unitoperations.psychrometry.water;

/**
 * This class was created for Comanda project
 * Created by bon on 9/8/21.
 * Copyright (c)  Hector Bonifacio. 9/8/21, All rights reserved.
 */
public class SaturatedProperty {

    /**
     *
     * @param temperatura in ℃
     * @return im mmHg
     */
    public static double Pv(double temperatura) {
        return 5.0521 + 0.1189*temperatura + 0.0273*Math.pow(temperatura,2) - 0.0003*Math.pow(temperatura,3) + (7E-6)*Math.pow(temperatura,4);
    }

    public static double specificEntalphy(double temperatura) {
        return 597.02 - (0.5402*temperatura) - 0.0004 * Math.pow(temperatura,2);
    }

    public static double CpAir(double temperatura) {
        return 6.386 - (0.001762*temperatura) - ((0.2656E-6)*Math.pow(temperatura,2));
    }


    /**
     *
     * @param temperatura
     * @return
     */
    public static double CpWater(double temperatura) {
        return 7.256 - (0.002298*temperatura) - ((0.2831E-6)*Math.pow(temperatura,2));
    }


}
