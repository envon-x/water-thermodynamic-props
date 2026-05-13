package com.iridiscense.unitoperations.thermodynamics;

import android.content.Context;

import com.iridiscense.unitoperations.thermodynamics.chemistry.Compound;

/**
 * This class was created for Comanda project
 * Created by bon on 3/26/21.
 * Copyright (c)  Hector Bonifacio. 3/26/21, All rights reserved.
 */
public interface FactoryCompound {
    /**
     * Makes compound from json files.
     * @param context where needs de compound
     * @param compoundName for reading the file
     * @return the compound of file name.
     */
    public Compound factory(Context context, String compoundName);

}
