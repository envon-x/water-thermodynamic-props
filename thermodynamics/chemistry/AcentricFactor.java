package com.iridiscense.unitoperations.thermodynamics.chemistry;

/**
 * This class was created for Environment Engineering at School Engineering
 * of Universidad Mayor de San Andrés project
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public class AcentricFactor {
    private String ref;
    private String bib_reference;
    private double value;

    public AcentricFactor(String ref, String bib_reference, double value) {
        this.ref = ref;
        this.bib_reference = bib_reference;
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public String getBib_reference() {
        return bib_reference;
    }

    public double getValue() {
        return value;
    }
}
