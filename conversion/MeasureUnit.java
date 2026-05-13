package com.iridiscense.unitoperations.conversion;

import com.iridiscense.unitoperations.utils.OnTest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * http://rchavarria.github.io/blog/2013/01/22/formatear-y-parsear-enumerados-en-java-con-valores-personalizados/
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 10/1/21.
 * Copyright (c)  Hector Bonifacio. 10/1/21, All rights reserved.
 */
public enum MeasureUnit {
    MPa("mpa", "MPa", ScienceParam.PRESSURE),
    atmosphere("at", "atm", ScienceParam.PRESSURE),
    bar("bar", "bar", ScienceParam.PRESSURE),
    millibar("mbar", "mbar", ScienceParam.PRESSURE),
    newtonPerm2("npm2", "N/m²", ScienceParam.PRESSURE),
    kiloPascal("kpa", "kPa", ScienceParam.PRESSURE),
    mmHg("mmHg", "mmHg", ScienceParam.PRESSURE),
    psi("psi", "psi", ScienceParam.PRESSURE),
    lbf_per_in2("lbf", "lbf/in²", ScienceParam.PRESSURE),
    kgf_per_cm2("kgf", "kgf/cm²", ScienceParam.PRESSURE),

    celsius("C", "°C", ScienceParam.TEMPERATURE),
    celsius_degree("°C", "°C", ScienceParam.TEMPERATURE),
    fahrenheit("F", "°F", ScienceParam.TEMPERATURE),
    fahrenheit_degree("°F", "°F", ScienceParam.TEMPERATURE),
    kelvin("K", "U+212A", ScienceParam.TEMPERATURE);

    private final String unicode;
    private final String spelling;

    private static final Map<String, MeasureUnit> dictionary;

    static {
        dictionary = new HashMap<String, MeasureUnit>();
        for (MeasureUnit measureUnit : values()) {
            dictionary.put(measureUnit.unicode, measureUnit);
        }
    }

    private MeasureUnit(String spelling, final String unicode, ScienceParam scienceParam) {
        this.spelling = spelling;
        this.unicode = unicode;
    }

    /**
     *
     * @return las unidades  interpretadas a partir del unicode
     */
    public String getUnicode() {
        try {
            return Unicode.getSymbol(unicode);
        } catch (UnsupportedEncodingException uee) {
            return uee.getLocalizedMessage();
        }
    }

    /**
     *
     * @return la forma elemental en que el usuario introduce las unidades con la
     * intención de dar las unidades correctas
     */
    public String getSpelling() {
        return spelling;
    }

    @OnTest
    public static Map<String, MeasureUnit> getDictionary() {
        return dictionary;
    }

    public static MeasureUnit fromString(String spelling) {
        MeasureUnit measureUnit = dictionary.get(spelling);
        if (measureUnit == null) {
            throw new IllegalArgumentException("Wrong value: " + spelling);
        }
        return measureUnit;
    }
}