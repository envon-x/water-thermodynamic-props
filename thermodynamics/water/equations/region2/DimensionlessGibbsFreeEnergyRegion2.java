package com.iridiscense.unitoperations.thermodynamics.water.equations.region2;

/**
 * Table (2.9) - region 2
 * This class was created for Environmental Engenering - UMSA project
 * Created by bon on 3/20/21.
 * Copyright (c)  Hector Bonifacio. 3/20/21, All rights reserved.
 */
public class DimensionlessGibbsFreeEnergyRegion2 {

    private final static double Tx = 540D;  // T* , K
    private final static double Px = 1D;    // P* , MPa


    /**
     * The ideal part of gas
     * Table 2.9
     * @return 𝛾° of the dimensionless Gibbs free energy
     */
    public static double gammaIdeal(double temperature, double pressure) {
        double gammaIdeal = Math.log(pi(pressure));

        for (int i = 0; i < 9; i++) {
            gammaIdeal = gammaIdeal
                    + CoefficientsRegion2.Ni_id[i]
                    * Math.pow(tau(temperature), CoefficientsRegion2.Ji_id[i]);
        }
        return gammaIdeal;
    }

    public static double gammaIdealPi(double pressure) {
        double pi = pi(pressure);
        return 1/pi;
    }

    public static double gammaIdealPiPi(double pressure) {
        double pi = pi(pressure);
        return -1/Math.pow(pi,2);
    }

    /**
     * The ideal part od gas
     * Table 2.9
     * @return 𝛾°ⲧ of the dimensionless Gibbs free energy
     */
    public static double gammaIdealTau(double temperature) {
        double gammaIdealTau = 0;

        for (int i = 0; i < 9; i++) {
            gammaIdealTau = gammaIdealTau + CoefficientsRegion2.Ni_id[i]
                    * CoefficientsRegion2.Ji_id[i]
                    * Math.pow(tau(temperature), CoefficientsRegion2.Ji_id[i] - 1d
            );
        }
        return gammaIdealTau;
    }

    public static double gammaIdealTauTau(double temperature) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 9; i++) {
            sum = sum + CoefficientsRegion2.Ni_id[i] * CoefficientsRegion2.Ji_id[i] * (CoefficientsRegion2.Ji_id[i] - 1) * Math.pow(tau, CoefficientsRegion2.Ji_id[i] -2);
        }
        return sum;
    }


    public static double gammaIdealPiTau() {
        return 0D;
    }

    //RESIDUAL PART

    /**
     *
     * @return
     */
    public static double gammaResidual(double temperature, double pressure) {
        double gammaResidual = 0;

        for (int i = 0; i < 43; i++) {
            gammaResidual = gammaResidual
                    + CoefficientsRegion2.Ni_res[i]
                    * Math.pow(pi(pressure), CoefficientsRegion2.Ii_res[i])
                    * Math.pow((tau(temperature) - 0.5d), CoefficientsRegion2.Ji_res[i]);
        }
        return gammaResidual;
    }

    public static double gammaResidualPi(double temperature, double pressure) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 43; i++) {
            sum = sum + CoefficientsRegion2.Ni_res[i] * CoefficientsRegion2.Ii_res[i] * Math.pow(pi(pressure), (CoefficientsRegion2.Ii_res[i] - 1)) * Math.pow((tau - 0.5), CoefficientsRegion2.Ji_res[i]);
        }
        return sum;
    }

    public static double gammaResidualPiPi(double temperature, double pressure) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 43; i++) {
            sum = sum + CoefficientsRegion2.Ni_res[i] * CoefficientsRegion2.Ii_res[i] * ( CoefficientsRegion2.Ii_res[i] - 1) * Math.pow(pi(pressure), (CoefficientsRegion2.Ii_res[i] - 2)) * Math.pow((tau - 0.5), CoefficientsRegion2.Ji_res[i]);
        }
        return sum;
    }

    /**
     * The residual part od gas
     * Table 2.9
     * @return 𝛾^ⲧ of the dimensionless Gibbs free energy
     */
    public static double gammaResidualTau(double temperature, double pressure) {
        double gammaResidualTau = 0;

        for (int i = 0; i < 43; i++) {
            gammaResidualTau = gammaResidualTau
                    + CoefficientsRegion2.Ni_res[i]
                    * Math.pow(pi(pressure), CoefficientsRegion2.Ii_res[i])
                    * CoefficientsRegion2.Ji_res[i]
                    * Math.pow((tau(temperature) - 0.5d), CoefficientsRegion2.Ji_res[i] -1d
            );
        }
        return gammaResidualTau;
    }

    /**
     * Table 2.7 Coefficients and exponents of the residual part J r , Eq. (2.8)
     * @param temperature in kelvins
     * @param pressure in MPa
     * @return
     */
    public static double gammaResidualTauTau(double temperature, double pressure) {
        double pi, tau;
        double sum = 0;
        pi = pi(pressure);

        tau = tau(temperature);
        for (int i = 0; i < 9; i++) {
            sum = sum + CoefficientsRegion2.Ni_res[i]
                    * Math.pow(pi, CoefficientsRegion2.Ii_res[i])
                    * CoefficientsRegion2.Ji_res[i]
                    * ( CoefficientsRegion2.Ji_res[i] -1)
                    * Math.pow((tau - 0.5), CoefficientsRegion2.Ji_res[i] -2);

        }
        return sum;
    }

    /**
     * Table 2.7 Coefficients and exponents of the residual part J r , Eq. (2.8)
     * @param temperature
     * @param pressure
     * @return
     */
    public static double gammaResidualPiTau(double temperature, double pressure) {
        double tau;
        double sum = 0;

        for (int i = 0; i < 9; i++) {
            sum = sum + CoefficientsRegion2.Ni_res[i] *  CoefficientsRegion2.Ii_res[i]
                    * Math.pow(pi(pressure), CoefficientsRegion2.Ii_res[i] - 1)
                    * CoefficientsRegion2.Ji_res[i] * Math.pow((tau(temperature) - 0.5), CoefficientsRegion2.Ji_res[i] -1);
        }
        return sum;
    }


    /**
     * For Eq. (2.3) and (2.7)
     * @return
     */
    protected static double tau(double temperature) {
        return Tx/temperature;
    }

    /**
     * For Eq. (2.6) and (2.7)
     * @return
     */
    protected static double pi(double pressure) {
        return pressure/Px;
    }

}
