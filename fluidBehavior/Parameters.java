package com.iridiscense.unitoperations.fluidBehavior;

/**
 * This class was created for Comanda project.
 * Implements condition for evaluate parameters of LVE equation.
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public interface Parameters {


    /**
     * a is function of composition only
     *
     * @param Tc   temperature critic always in [K]
     * @param Pc   pressure critic specified by unit param
     * @return constant a for Redlich–Kwong, Redlich–Kwong and Peng-Robinson Equations.
     */
    public double a(double Tc, double Pc);


    /**
     * @param Tc   temperature critic always in [K]
     * @param Pc   pressure critic specified by unit param
     * @return constant b for Redlich–Kwong, Redlich–Kwong and Peng-Robinson Equations.
     */
    public double b(double Tc, double Pc);

}
