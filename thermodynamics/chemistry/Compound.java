package com.iridiscense.unitoperations.thermodynamics.chemistry;

import java.util.ArrayList;

/**
 * This class was created for Comanda project
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public class Compound {
    private String ident;
    private String formula;
    private String casNumber;
    private String name;
    private ArrayList<String> aliases;
    private double molWeight;
    private CriticalProperty critical;
    private AcentricFactor acentricFactor;

    public Compound(String ident, String formula, String casNumber, String name, ArrayList<String> aliases, double molWeight, CriticalProperty critical, AcentricFactor acentricFactor) {
        this.ident = ident;
        this.formula = formula;
        this.casNumber = casNumber;
        this.name = name;
        this.aliases = aliases;
        this.molWeight = molWeight;
        this.critical = critical;
        this.acentricFactor = acentricFactor;
    }

    public String getIdent() {
        return ident;
    }

    public String getFormula() {
        return formula;
    }

    public String getCasNumber() {
        return casNumber;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public double getMolWeight() {
        return molWeight;
    }

    public CriticalProperty getCritical() {
        return critical;
    }

    public AcentricFactor getAcentricFactor() {
        return acentricFactor;
    }
}
