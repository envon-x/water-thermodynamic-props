package com.iridiscense.unitoperations.fluidBehavior;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class was created for Comanda project
 * Created by bon on 3/24/21.
 * Copyright (c)  Hector Bonifacio. 3/24/21, All rights reserved.
 */
public class Utils {
    /**
     *
     * @param context
     * @param fileName
     * @return the string needs parsered by gson or moshi, oetx
     */
    static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }


}