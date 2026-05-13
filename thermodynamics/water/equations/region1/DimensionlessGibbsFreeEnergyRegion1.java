package com.iridiscense.unitoperations.thermodynamics.water.equations.region1;

/**
 * Region 1
 * This class was created for Environmental Engenering - UMSA project
 * Created by bon on 3/18/21.
 * Copyright (c)  Hector Bonifacio. 3/18/21, All rights reserved.
 */
 class DimensionlessGibbsFreeEnergyRegion1 {

    private final static double Tx = 1386.00D;  // T* , K
    private final static double Px = 16.53D;    // P* , MPa

    /**
     * For Eq. (2.3)
     * @param T in Kelvins
     * @return
     */
    protected static double tau(double T) {
        return Tx / T;
    }

    /**
     * For Eq. (2.3)
     * @param P in MPa
     * @return
     */
    protected static double pi(double P) {
        return P / Px;
    }


    /**
     * From table 2.4
     * @param T, absolute temperature, in Kelvins
     * @param P, pressure in MPa.
     * @return The dimensionless Gibbs free energy, 𝛾 , for Eq. (2.3),
     */
    public static double gamma(double T, double P) {
        double pi, tau;
        double sum = 0;

        pi = pi(P);
        tau = tau(T);
        System.out.println("value Pi = " + pi);
        System.out.println("value tau = " + tau);
        for (int i = 0; i < 34; i++) {
            sum = sum + (CoefficientsRegion1.Ni[i]) * Math.pow((7.1-pi), CoefficientsRegion1.Ii[i]) * Math.pow((tau-1.222), CoefficientsRegion1.Ji[i]);
        }
        return sum;
    }


    /**
     * From table 2.4
     * @param T, absolute temperature, in Kelvins
     * @param P, pressure in MPa.
     * @return The dimensionless Gibbs free energy, 𝛾_{𝜋} , for Eq. (2.3),
     */
    public static double gammaPi(double T, double P) {
        double pi, tau;
        double sum = 0;

        pi = pi(P);
        tau = tau(T);
        System.out.println("value Pi = " + pi);
        System.out.println("value tau = " + tau);
        for (int i = 0; i < 34; i++) {
            sum = sum + (-1)* CoefficientsRegion1.Ii[i]*(CoefficientsRegion1.Ni[i]) * Math.pow((7.1-pi), CoefficientsRegion1.Ii[i]-1) * Math.pow((tau-1.222), CoefficientsRegion1.Ji[i]);
        }
        return sum;
    }


    /**
     * From table 2.4
     * @param T, absolute temperature, in Kelvins
     * @param P, pressure in MPa.
     * @return The dimensionless Gibbs free energy, 𝛾_{𝜋𝜋} , for Eq. (2.3),
     */
    public static double gammaPiPi(double T, double P) {
        double pi, tau;
        double sum = 0;

        pi = pi(P);
        tau = tau(T);
        System.out.println("value Pi = " + pi);
        System.out.println("value tau = " + tau);
        for (int i = 0; i < 34; i++) {
            sum = sum + CoefficientsRegion1.Ii[i] * (CoefficientsRegion1.Ii[i]-1) * (CoefficientsRegion1.Ni[i]) * Math.pow((7.1-pi), CoefficientsRegion1.Ii[i]-2) * Math.pow((tau-1.222), CoefficientsRegion1.Ji[i]);
        }
        return sum;
    }


    /**
     * From table 2.4
     * @param T, absolute temperature, in Kelvins
     * @param P, pressure in MPa.
     * @return The dimensionless Gibbs free energy, 𝛾_{𝜏} , for Eq. (2.3),
     */
    public static double gammaTau(double T, double P) {
        double pi, tau;
        double sum = 0;

        pi = pi(P);
        tau = tau(T);
        for (int i = 0; i < 34; i++) {
            sum = sum + (CoefficientsRegion1.Ni[i]) * Math.pow((7.1-pi), CoefficientsRegion1.Ii[i]) * CoefficientsRegion1.Ji[i] * Math.pow((tau-1.222), CoefficientsRegion1.Ji[i]-1);
        }
        return sum;
    }


    /**
     * From table 2.4
     * @param T, absolute temperature, in Kelvins
     * @param P, pressure in MPa.
     * @return The dimensionless Gibbs free energy, 𝛾_{𝜏𝜏} , for Eq. (2.3),
     */
    public static double gammaTauTau(double T, double P) {
        double pi, tau;
        double sum = 0;

        pi = pi(P);
        tau = tau(T);
        for (int i = 0; i < 34; i++) {
            sum = sum + (CoefficientsRegion1.Ni[i]) * Math.pow((7.1-pi), CoefficientsRegion1.Ii[i]) * CoefficientsRegion1.Ji[i] * (CoefficientsRegion1.Ji[i] - 1) * Math.pow((tau-1.222), CoefficientsRegion1.Ji[i]-2);
        }
        return sum;
    }


    /**
     * From table 2.4
     * @param T, absolute temperature, in Kelvins
     * @param P, pressure in MPa.
     * @return The dimensionless Gibbs free energy, 𝛾_{𝜋𝜏} , for Eq. (2.3),
     */
    public static double gammaPiTau(double T, double P) {
        double pi, tau;
        double sum = 0;
        pi = pi(P);
        tau = tau(T);
        for (int i = 0; i < 34; i++) {
            sum = sum + (-1) * (CoefficientsRegion1.Ni[i]) *  CoefficientsRegion1.Ii[i] * Math.pow((7.1-pi), CoefficientsRegion1.Ii[i] - 1) * CoefficientsRegion1.Ji[i] * Math.pow((tau-1.222), CoefficientsRegion1.Ji[i]-1);
        }
        return sum;
    }


}
