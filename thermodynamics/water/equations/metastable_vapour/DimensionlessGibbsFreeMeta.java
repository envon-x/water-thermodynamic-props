package com.iridiscense.unitoperations.thermodynamics.water.equations.metastable_vapour;

import com.iridiscense.unitoperations.thermodynamics.water.equations.region2.CoefficientsRegion2;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 10/12/21.
 * Copyright (c)  Hector Bonifacio. 10/12/21, All rights reserved.
 */
public class DimensionlessGibbsFreeMeta {

    private final static double Tx = 540D;  // T* , K
    private final static double Px = 1D;    // P* , MPa

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


    public static double gammaResidual(double temperature, double pressure) {
        double gammaResidual = 0;

        for (int i = 0; i < 13; i++) {
            gammaResidual = gammaResidual
                    + CoefficientMeta.Ni[i]
                    * Math.pow(pi(pressure), CoefficientMeta.Ii[i])
                    * Math.pow((tau(temperature) - 0.5d), CoefficientMeta.Ji[i]);
        }
        return gammaResidual;
    }

    public static double gammaResidualPi(double temperature, double pressure) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 13; i++) {
            sum = sum + CoefficientMeta.Ni[i] * CoefficientMeta.Ii[i] * Math.pow(pi(pressure), (CoefficientMeta.Ii[i] - 1)) * Math.pow((tau - 0.5), CoefficientMeta.Ji[i]);
        }
        return sum;
    }

    public static double gammaResidualPiPi(double temperature, double pressure) {
        double tau;
        double sum = 0;

        tau = tau(temperature);
        for (int i = 0; i < 13; i++) {
            sum = sum + CoefficientMeta.Ni[i] * CoefficientMeta.Ii[i] * ( CoefficientMeta.Ii[i] - 1) * Math.pow(pi(pressure), (CoefficientMeta.Ii[i] - 2)) * Math.pow((tau - 0.5), CoefficientMeta.Ji[i]);
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

        for (int i = 0; i < 13; i++) {
            gammaResidualTau = gammaResidualTau
                    + CoefficientMeta.Ni[i]
                    * Math.pow(pi(pressure), CoefficientMeta.Ii[i])
                    * CoefficientMeta.Ji[i]
                    * Math.pow((tau(temperature) - 0.5d), CoefficientMeta.Ji[i] -1d
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
            sum = sum + CoefficientMeta.Ni[i]
                    * Math.pow(pi, CoefficientMeta.Ii[i])
                    * CoefficientMeta.Ji[i]
                    * ( CoefficientMeta.Ji[i] -1)
                    * Math.pow((tau - 0.5), CoefficientMeta.Ji[i] -2);

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
            sum = sum + CoefficientMeta.Ni[i] *  CoefficientMeta.Ii[i]
                    * Math.pow(pi(pressure), CoefficientMeta.Ii[i] - 1)
                    * CoefficientMeta.Ji[i] * Math.pow((tau(temperature) - 0.5), CoefficientMeta.Ji[i] -1);
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

