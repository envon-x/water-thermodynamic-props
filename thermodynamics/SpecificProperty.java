package com.iridiscense.unitoperations.thermodynamics;

/**
 * This class was created for Comanda project
 * thermodynamic Properties
 * Created by bon on 3/20/21.
 * Copyright (c)  Hector Bonifacio. 3/20/21, All rights reserved.
 */
public interface SpecificProperty {

    /**
     * The Eq. was getting from table 2.3.
     * @return specific volume in [m³/kg]
     */
    public double specificVolume();

    public double specificEnthalpy();

    public double specificInternalEnergy();

    public double specificEntropy();

    /**
     * Cp
     * @return
     */
    public double specificIsobaricHeatCapacity();

    /**
     * Cp
     * @return
     */
    public double specificIsochoricHeatCapacity();

    public double speedOfSound();

    public double isentropicExponent();

    public double isobaricCubicExpansionCoefficient();

    public double isothermalCompressibility();

}
