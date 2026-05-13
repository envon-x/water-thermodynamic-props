package com.iridiscense.unitoperations.thermodynamics.water.equations;

/**
 * This class was created for Thermodynamics project
 * Created by bon on 3/6/21.
 * Copyright (c)  Hector Bonifacio. 3/6/21, All rights reserved.
 */
public class WaterConstant {
    public static double R = 0.461_526;     /* kJ/kg-K,     The specific gas constant */
    public static double Rm = 8.314_51;     /* kJ/kmol-K,   Molar gas constant */
    public static double M_water = 18.015_257;    /* kg/kmol,     Molar mass of ordinary water*/
    public static double M_air = 28.8;    /* kg/kmol,     Molar mass of ordinary Air*/

    // Critical parameters
    public static double Tc = 647.096;      /* K,           critical Temperature */
    public static double Pc = 22.064;       /* MPa,         Critical Pressure */
    public static double Dc = 322;          /* kg/m^3,      Critical density */
    public static double Vc = 0.055900621;  /* m^3/kmol,    Critical Volume */

    // triple-point
    public static double Tt = 273.16;       /* K,      */
    public static double Pt = 611.657;      /* Pa,     */



}
