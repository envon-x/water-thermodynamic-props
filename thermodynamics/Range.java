package com.iridiscense.unitoperations.thermodynamics;

/**
 * This class was created for Comanda project
 * Created by bon on 3/18/21.
 * Copyright (c)  Hector Bonifacio. 3/18/21, All rights reserved.
 *
 * Range Validity for the evaluate value in the equations
 */
public interface Range {

    /**
     * Sel le ha retirado los parametros para no ser establecidos desde afuera del modelo, una vez construido el objeto
     * @return the boolean value of parameters like as temperature and pressure with are working the region
     */
    public boolean validity();


}
