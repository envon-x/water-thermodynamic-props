package com.iridiscense.unitoperations.fluidBehavior;

import android.content.Context;

import com.iridiscense.unitoperations.thermodynamics.FactoryCompound;
import com.iridiscense.unitoperations.thermodynamics.ThermodynamicConstant;
import com.iridiscense.unitoperations.thermodynamics.chemistry.Compound;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class was created for Comanda project.
 *
 * This equation was developed in 1949; has three volume roots and two of those may be
 * complexes.
 *
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public class RedlichKwong implements Parameters, PressureCalculator, FactoryCompound {

    private Compound compound;
    private Context context;    //El contexto de donde mostrarse los cálculos

    public RedlichKwong(Compound compound) {
        this.compound = compound;
    }

    public RedlichKwong(Context context, String name) {
        this.context = context;
        //Create a new Gson object
        Compound compound = factory(context, name);
        this.compound = compound;
    }

    public Compound getCompound() {
        return compound;
    }

    public void setCompound(Compound compound) {
        this.compound = compound;
    }

    /**
     * a is function of composition only
     *
     * @param Tc   temperature critic always in [K]
     * @param Pc   pressure critic specified by unit param
     * @return constant a for Redlich–Kwong Equation
     */
    @Override
    public double a(double Tc, double Pc) {
        return 0.42748 * Math.pow(ThermodynamicConstant.RkPa, 2) * Math.pow(Tc, 2.5) / (Pc/1000D); //Pc esta en Pa, pero se necesita en kPa
    }

    /**
     * @param Tc   temperature critic always in [K]
     * @param Pc   pressure critic specified by unit param in Pa
     * @return constant b for Redlich–Kwong Equation
     */
    @Override
    public double b(double Tc, double Pc) {
        return 0.08664 * ThermodynamicConstant.RkPa * Tc / (Pc/1000D);
    }

    /**
     * @param T, temperature in [K]
     * @param v, specific volume in [m³/kg]
     * @return P, pressure in [kPa]
     */
    @Override
    public double pressure(double T, double v) {
        double tc = compound.getCritical().getTemperature();
        double pc = compound.getCritical().getPressure();
        double a = a(tc, pc);
        double b = b(tc, pc);
        System.out.println(">>>> Tc " + tc);
        System.out.println(">>>> Pc " + pc);
        System.out.println(">>>> a " + a);
        System.out.println(">>>> b " + b);
        return ThermodynamicConstant.RkPa * T / (v - b) - a / (Math.sqrt(T)* v * (v + b));
    }


    @Override
    public Compound factory(Context context, String compoundName) {
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("compound/" + compoundName + ".json")));
            //convert the json to  Java object (Employee)
            return gson.fromJson(br, Compound.class);
        } catch (IOException e) {
            System.out.println("Not found " + compoundName + " compound. Probable the name is not correct.");
            e.printStackTrace();
        }
        return null;
    }
}