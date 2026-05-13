package com.iridiscense.unitoperations.variables;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 9/28/21.
 * Copyright (c)  Hector Bonifacio. 9/28/21, All rights reserved.
 */
public abstract class PhysicalVariable {
    public double value;
    public String measureUnit;

    public PhysicalVariable(double value, String measureUnit) {
        this.value = value;
        this.measureUnit = measureUnit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }


}
