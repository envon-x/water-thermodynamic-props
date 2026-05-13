package com.iridiscense.unitoperations.thermodynamics.water.equations.region5;

/**
 * Eq 2.15 & 2.16
 * This class was created for Comanda project
 * Created by bon on 9/3/21.
 * Copyright (c)  Hector Bonifacio. 9/3/21, All rights reserved.
 */
public class DimensionlessGibbsFreeEnergyRegion5 {

    private final static double Tx = 1000;  // T* , K
    private final static double Px = 1D;    // P* , MPa


    /**
     * The ideal part of gas
     * Table 2.25
     * @return 𝛾° of the dimensionless Gibbs free energy
     */
    public static double gammaIdeal(double temperature, double pressure) {
        double gammaIdeal = Math.log(pi(pressure));

        for (int i = 0; i < 6; i++) {
            gammaIdeal = gammaIdeal
                    + CoefficientsRegion5.Ni_id[i]
                    * Math.pow(tau(temperature), CoefficientsRegion5.Ji_id[i]);
        }
        return gammaIdeal;
    }

    /**
     * The ideal part of gas
     * Table 2.25
     * @param pressure in MPa
     * @return pi⁻¹
     */
    public static double gammaIdealPi(double pressure) {
        double pi = pi(pressure);
        return 1/pi;
    }

    /**
     * The ideal part of gas
     * Table 2.25
     * @param pressure in MPa
     * @return pi⁻¹
     */
    public static double gammaIdealPiPi(double pressure) {
        double pi = pi(pressure);
        return -1/Math.pow(pi,2);
    }

    /**
     * The ideal part od gas
     * Table 2.25
     * @param temperature in Kelvins
     * @return 𝛾°ⲧ of the dimensionless Gibbs free energy
     */

    public static double gammaIdealTau(double temperature) {
        double gammaIdealTau = 0;

        for (int i = 0; i < 6; i++) {
            gammaIdealTau = gammaIdealTau + CoefficientsRegion5.Ni_id[i]
                    * CoefficientsRegion5.Ji_id[i]
                    * Math.pow(tau(temperature), CoefficientsRegion5.Ji_id[i] - 1d
            );
        }
        return gammaIdealTau;
    }

    public static double gammaIdealTauTau(double temperature) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 6; i++) {
            sum = sum + CoefficientsRegion5.Ni_id[i] * CoefficientsRegion5.Ji_id[i] * (CoefficientsRegion5.Ji_id[i] - 1) * Math.pow(tau, CoefficientsRegion5.Ji_id[i] -2);
        }
        return sum;
    }


    /**
     * The ideal part of gas
     * Table 2.25
     * @return pi_tau with zero value
     */
    public static double gammaIdealPiTau() {
        return 0D;
    }

    //RESIDUAL PART

    /**
     * Table 6.26 in the file American version
     * Table 3.38. Coefficients and exponents of Eq. (3.27) in Germany version. In this version
     * is a little different in the number of coefficients
     * @return
     */
    public static double gammaResidual(double temperature, double pressure) {
        double gammaResidual = 0;

        for (int i = 0; i < 6 ; i++) {
            gammaResidual = gammaResidual
                    + CoefficientsRegion5.Ni_res[i]
                    * Math.pow(pi(pressure), CoefficientsRegion5.Ii_res[i])
                    * Math.pow(tau(temperature), CoefficientsRegion5.Ji_res[i]);
        }
        return gammaResidual;
    }

    public static double gammaResidualPi(double temperature, double pressure) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 6 ; i++) {
            sum = sum + CoefficientsRegion5.Ni_res[i]
                    * CoefficientsRegion5.Ii_res[i]
                    * Math.pow(pi(pressure), (CoefficientsRegion5.Ii_res[i] - 1))
                    * Math.pow((tau - 0.5), CoefficientsRegion5.Ji_res[i]);
        }
        return sum;
    }

    public static double gammaResidualPiPi(double temperature, double pressure) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 6 ; i++) {
            sum = sum
                    + CoefficientsRegion5.Ni_res[i]
                    * CoefficientsRegion5.Ii_res[i]
                    * ( CoefficientsRegion5.Ii_res[i] - 1)
                    * Math.pow(pi(pressure), (CoefficientsRegion5.Ii_res[i] - 2))
                    * Math.pow((tau), CoefficientsRegion5.Ji_res[i]);
        }
        return sum;
    }

    /**
     * The residual part od gas
     * Table 2.26 in the american version
     * @return 𝛾^ⲧ of the dimensionless Gibbs free energy
     */
    public static double gammaResidualTau(double temperature, double pressure) {
        double acum = 0;

        for (int i = 0; i < 6 ; i++) {
            acum = acum
                    + CoefficientsRegion5.Ni_res[i]
                    * Math.pow(pi(pressure), CoefficientsRegion5.Ii_res[i])
                    * CoefficientsRegion5.Ji_res[i]
                    * Math.pow((tau(temperature)), CoefficientsRegion5.Ji_res[i] -1d)
            ;
        }
        return acum;
    }

    public static double gammaResidualTauTau(double temperature, double pressure) {
        double pi, tau;
        double sum = 0;
        pi = pi(pressure);
        tau = tau(temperature);

        for (int i = 0; i < 6; i++) {
            sum = sum + CoefficientsRegion5.Ni_res[i]
                    * Math.pow(pi, CoefficientsRegion5.Ii_res[i])
                    * CoefficientsRegion5.Ji_res[i]
                    * ( CoefficientsRegion5.Ji_res[i] -1)
                    * Math.pow(tau, CoefficientsRegion5.Ji_res[i] -2);

        }
        return sum;
    }

    public static double gammaResidualPiTau(double temperature, double pressure) {
        double pi, tau;
        double sum = 0;
        tau = tau(temperature);
        pi = pi(pressure);
        for (int i = 0; i < 6; i++) {
            sum = sum + CoefficientsRegion5.Ni_res[i]
                    * CoefficientsRegion5.Ii_res[i]
                    * Math.pow(pi, CoefficientsRegion5.Ii_res[i] - 1)
                    * CoefficientsRegion5.Ji_res[i]
                    * Math.pow(tau, CoefficientsRegion5.Ji_res[i] -1);
        }
        return sum;
    }


    /**
     * For Eq. in table (2.25) and (2.26)
     * @return
     */
    protected static double tau(double temperature) {
        return Tx/temperature;
    }

    /**
     * For Eq. in table (2.25) and (2.26)
     * @return
     */
    protected static double pi(double pressure) {
        return pressure/Px;
    }


}
