package com.iridiscense.unitoperations.thermodynamics;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.iridiscense.unitoperations.utils.OnTest;
import com.iridiscense.unitoperations.variables.PhysicalVariable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Todas las instancias de esta clase  y esta misma clase estan a prueba
 */
@OnTest
public class UniversalGasConstantFromFile extends PhysicalVariable {

    public static final String LOG = UniversalGasConstantFromFile.class.getSimpleName();

    private UniversalGasConstantFromFile(double value, String measureUnit) {
        super(value, measureUnit);
    }

    private static PhysicalVariable loadConstantFromAssets(AssetManager am, String path, String unit) {

        PhysicalVariable entry = null;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(am.open(path), "UTF-8"));

            String line = reader.readLine();

            while (line != null) {
                // process line
                String[] split = line.split("|");

                if (split[0].equals(unit)) {
                    entry = new UniversalGasConstantFromFile(Double.parseDouble(split[1]), split[0]);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());

        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG, e.toString());
                }
            }
        }

        return entry;
    }

    /**
     *
     * @param a
     * @param unit
     * @return
     */
    @OnTest
    public static PhysicalVariable getConstant(Context a, String unit) {
        return loadConstantFromAssets(a.getAssets(), "constantR.txt", unit);
    }
}