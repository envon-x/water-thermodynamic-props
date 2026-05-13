package com.iridiscense.unitoperations.conversion.util;

import com.iridiscense.unitoperations.conversion.MeasureUnit;

import java.io.UnsupportedEncodingException;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 10/2/21.
 * Copyright (c)  Hector Bonifacio. 10/2/21, All rights reserved.
 */
public class UnitRefactor {
    /**
     * Posiblemente se lleve al paquete util, dado que será usado para diversos cálculos de variables físicas
     * trasquila el spell intentando convertir el spell a las unidades de medicion válidas
     * @param spell lo que se intenta interpretar
     * @return las unidades solicitadas a partir del spell
     * @throws UnsupportedEncodingException
     */
    public static String refactor(String spell) {

        spell = spell.toLowerCase();

        try {
            for (MeasureUnit mu : MeasureUnit.values()) {
                if (mu.getSpelling().toLowerCase().equals(spell)) {
                    return mu.getUnicode();
                }
            }

        } catch (Exception e) {
            throw e;//new MeasureUnitException( "No units. ", spell);
        }
        //Default return
        return "No Units. " + UnitRefactor.class.getName(); // getClass().getName();
    }


}
