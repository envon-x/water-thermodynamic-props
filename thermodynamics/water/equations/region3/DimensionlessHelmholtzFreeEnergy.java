package com.iridiscense.unitoperations.thermodynamics.water.equations.region3;

import com.iridiscense.unitoperations.thermodynamics.water.equations.WaterConstant;

/**
 * This class was created for Comanda project
 * Created by bon on 8/26/21.
 * Copyright (c)  Hector Bonifacio. 8/26/21, All rights reserved.
 */
public class DimensionlessHelmholtzFreeEnergy {

    /**
     * Basic equation for region 3
     * @return delta value
     */
    protected static double delta(double massDensity) {
        return massDensity/ WaterConstant.Dc;
    }

    /**
     * For Eq. (2.3)
     * @return
     */
    protected static double tau(double temperature) {
        return WaterConstant.Tc / temperature;
    }

    /**
     * 𝟇 its name is phi
     * First Equation of table 2.17.
     * @param massDensity
     * @return
     */
    public static double phi( double temperature, double massDensity) {
        double phi = CoefficientsRegion3.Ni[0]* Math.log(delta(massDensity));
        for (int i = 1; i < 40; i++) {
            phi = phi + CoefficientsRegion3.Ni[i]
                    * Math.pow(delta(massDensity), CoefficientsRegion3.Ii[i])
                    * Math.pow(tau(temperature), CoefficientsRegion3.Ji[i]);
        }
        return phi;
    }

    /**
     * ϕ_{𝜹}
     * @return
     */
    public static double phiDelta(double temperature, double massDensity) {
        double phiDelta = CoefficientsRegion3.Ni[0] * (1 / delta(massDensity));
        for (int i = 1; i < 40; i++) {
            phiDelta = phiDelta + CoefficientsRegion3.Ni[i]
                    * CoefficientsRegion3.Ii[i]
                    * Math.pow(delta(massDensity), CoefficientsRegion3.Ii[i] - 1d)
                    * Math.pow(tau(temperature), CoefficientsRegion3.Ji[i]);
        }
        return phiDelta;
    }

    /**
     * ϕⲧ
     * @return
     */
    public static double phiDeltaDelta(double temperature, double massDensity) {
        double phiDeltaDelta = -CoefficientsRegion3.Ni[0] * Math.pow(delta(massDensity), -2);
        for (int i = 1; i < 40; i++) {// de 2 en adelante
            phiDeltaDelta = phiDeltaDelta + CoefficientsRegion3.Ni[i]
                    * CoefficientsRegion3.Ii[i]
                    * (CoefficientsRegion3.Ii[i] -1d)
                    * Math.pow(delta(massDensity), CoefficientsRegion3.Ii[i] - 2d)
                    *Math.pow(tau(temperature), CoefficientsRegion3.Ji[i]);
        }
        return phiDeltaDelta;
    }


    /**
     * Table 2.17
     * 𝟇 its name is phi
     * Φ𝛕 ϕⲧ
     * @param massDensity is the Mass Density in kg/m³
     * @return
     */
    public static double phiTau(double temperature, double massDensity) {
        double acum = 0;
        for (int i = 1; i < 40; i++) {
            acum = acum + CoefficientsRegion3.Ni[i]
                    * Math.pow(delta(massDensity), CoefficientsRegion3.Ii[i])
                    * CoefficientsRegion3.Ji[i]
                    * Math.pow(tau(temperature), (CoefficientsRegion3.Ji[i] - 1d));
        }
        return acum;
    }

    /**
     * Table 2.17
     * ϕⲧⲧ
     * @param massDensity is the Mass Density in kg/m³
     * @return
     */
    public static double phiTauTau(double temperature, double massDensity) {
        double phiTauTau = 0;
        for (int i = 1; i < 40; i++) {
            phiTauTau = phiTauTau
                    + CoefficientsRegion3.Ni[i]
                    * Math.pow(delta(massDensity), CoefficientsRegion3.Ii[i])
                    * CoefficientsRegion3.Ji[i]
                    * (CoefficientsRegion3.Ji[i] -1d)
                    * Math.pow(tau(temperature), (CoefficientsRegion3.Ji[i] - 2d));
        }
        return phiTauTau;

    }

    /**
     * Table 2.17
     * 𝞀ρ𝛅𝛿𝜹𝝳ᵟϕⲧⲧ
     * @param massDensity () is the Mass density volume in kg/m³
     * @return
     */
    public static double phiDeltaTau(double temperature, double massDensity) {
        double phiDeltaTau = 0;
        for (int i = 1; i < 40; i++) {
            phiDeltaTau = phiDeltaTau
                    + CoefficientsRegion3.Ni[i]
                    * CoefficientsRegion3.Ii[i]
                    * Math.pow(delta(massDensity), CoefficientsRegion3.Ii[i] - 1d)
                    * (CoefficientsRegion3.Ji[i] - 1d)
                    * Math.pow(tau(temperature), (CoefficientsRegion3.Ji[i] - 1d));
        }
        return phiDeltaTau;

    }

}
