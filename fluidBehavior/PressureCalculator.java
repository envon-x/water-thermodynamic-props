package com.iridiscense.unitoperations.fluidBehavior;

/**
 * This class was created for Comanda project
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public interface PressureCalculator {

    /**
     * Compute the pressure at conditions given by its params.
     * @param T in [K]
     * @param v
     * @return the pressure value.
     */
    public double pressure(double T, double v);
}
