package com.iridiscense.unitoperations.thermodynamics.chemistry;

/**
 * This class was created for Comanda project
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public class CriticalProperty {
    private String ref;
    private String bib_reference;
    private double temperature;
    private double pressure;
    private double compressibility;

    public CriticalProperty(double temperature, double pressure, double compressibility) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.compressibility = compressibility;
    }

    public CriticalProperty(String ref, String bib_reference, double temperature, double pressure, double compressibility) {
        this.ref = ref;
        this.bib_reference = bib_reference;
        this.temperature = temperature;
        this.pressure = pressure;
        this.compressibility = compressibility;
    }

    public String getRef() {
        return ref;
    }

    public String getBib_reference() {
        return bib_reference;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getCompressibility() {
        return compressibility;
    }
}
