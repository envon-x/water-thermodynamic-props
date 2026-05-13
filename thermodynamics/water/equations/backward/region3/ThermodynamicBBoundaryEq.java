package com.iridiscense.unitoperations.thermodynamics.water.equations.backward.region3;

import com.iridiscense.unitoperations.thermodynamics.Range;

/**
 * This class was created for Comanda project
 * Created by bon on 9/1/21.
 * Copyright (c)  Hector Bonifacio. 9/1/21, All rights reserved.
 */
public class ThermodynamicBBoundaryEq implements Range {

    private double enthalpy;
    private boolean isValid = false;


    public ThermodynamicBBoundaryEq(double enthalpy) {
        this.enthalpy = enthalpy;
        this.isValid = validity();
    }
//test
    public double pressurePs3() {
        double acum = 0;
        double eta = BBoundaryPs3Dimensionless.eta(enthalpy);
        for (int i = 0; i < 14; i++) {
            acum = acum + BCoefficientsPs3.Ni[i]
                    * Math.pow((eta - 1.02d), BCoefficientsPs3.Ii[i])
                    * Math.pow((eta - 0.608d), BCoefficientsPs3.Ji[i]);

        }
        return acum * 22D;
    }

    @Override
    public boolean validity() {
        boolean isValidH =false;
        if (enthalpy >= 1670.858_218d && enthalpy <= 2563.592_004d) { // kJ/kg
            isValidH = true;
        }else {
            isValidH = false;
        }
        return  isValidH;
    }
}
