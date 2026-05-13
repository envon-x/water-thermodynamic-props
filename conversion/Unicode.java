package com.iridiscense.unitoperations.conversion;

import java.io.UnsupportedEncodingException;

/**
 * This class was created for Comanda project
 * Created by bon on 9/24/21.
 * Copyright (c)  Hector Bonifacio. 9/24/21, All rights reserved.
 */
public class Unicode {

    /**
     * https://www.javatpoint.com/java-unicode
     * generate properly Symbol for physical unit from unicode string
     * @param code
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getSymbol(String code) throws UnsupportedEncodingException {
        byte[] charset = code.getBytes();
        return new String(charset, "UTF-8");
    }
}
