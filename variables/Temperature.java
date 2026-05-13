package com.iridiscense.unitoperations.variables;

import com.iridiscense.unitoperations.conversion.util.UnitRefactor;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 9/25/21.
 * Copyright (c)  Hector Bonifacio. 9/25/21, All rights reserved.
 */
public class Temperature extends PhysicalVariable {

    public Temperature(double value, String unit) {
        super(value, UnitRefactor.refactor(unit));
    }

}
