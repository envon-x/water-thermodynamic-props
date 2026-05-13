
package com.iridiscense.unitoperations;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.iridiscense.unitoperations.plotter.charts.LineChart;
import com.iridiscense.unitoperations.plotter.components.XAxis;
import com.iridiscense.unitoperations.plotter.data.Entry;
import com.iridiscense.unitoperations.plotter.data.LineData;
import com.iridiscense.unitoperations.plotter.data.LineDataSet;
import com.iridiscense.unitoperations.thermodynamics.water.equations.WaterConstant;
import com.iridiscense.unitoperations.thermodynamics.water.equations.backward.region3.ThermodynamicBBoundaryEq;
import com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4.SaturatedRegion;
import com.iridiscense.unitoperations.thermodynamics.water.equations.boundary4.auxiliar.SaturatedDensity;
import com.iridiscense.unitoperations.thermodynamics.water.equations.region1.ThermodynamicRegion1Property;
import com.iridiscense.unitoperations.thermodynamics.water.equations.region2.ThermodynamicRegion2Property;
import com.iridiscense.unitoperations.thermodynamics.water.equations.region3.ThermodynamicRegion3Property;
import com.iridiscense.unitoperations.thermodynamics.water.equations.region5.ThermodynamicRegion5Property;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivityLineChart extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_simple_line);

        double incr = 1d;
        double Ts_C = 50d; // Celsius Degree
        double Ts_K = -999.999d;
        double Ps_MPa = -999.999d;


        //test

        lineChart = findViewById(R.id.line_chart);

        ArrayList<Entry> info = new ArrayList<>();

        //Testing region 4 (saturatedj vapor)
        ThermodynamicRegion1Property region1;
        ThermodynamicRegion2Property region2;
        ThermodynamicRegion3Property region3;
        ThermodynamicRegion5Property region5;


        float H1 = 0f, H2 = 0f;
        float S1 = 0, S2 = 0f;
        double D = 1 /*1 kg/m³*/, Ps3 = 0; //solo para la iteracion


        writeToFile( "T, [°C] \t" +
                "T, [K] \t" +
                "P, [bar] \t" +
                "P, [mmHg] \t" +
                "\uD835\uDFBA', kg/m³ \t" +
                "\uD835\uDFBA\", kg/m³ \t" +
                "h', KJ/kg \t" +
                "h\", KJ/kg \t" +
                "\uD835\uDEABh, KJ/kg \t" +
                "s', KJ/kg-K \t" +
                "s\", KJ/kg-K \t" +
                "\uD835\uDEABs, KJ/kg-K"
        );


        do {
            //Para la temperatura critics
            if (Ts_C > 373.15) {
                Ts_C = 373.946;
            }

            Ts_K = Ts_C + 273.15d;
            Ps_MPa =  SaturatedRegion.pressureSaturate(Ts_K); //MPa. 1MPa = 10 bar (factor)
            if (0 <= Ts_C  && Ts_C  <= 350d) {
                region1 = new ThermodynamicRegion1Property(Ts_K, "T");
                region2 = new ThermodynamicRegion2Property(Ts_K, "T");

                H1 = (float) region1.specificEnthalpy();
                H2 = (float) region2.specificEnthalpy();

                S1 = (float) region1.specificEntropy();
                S2 = (float) region2.specificEntropy();
                writeToFile(Ts_C + " \t" +
                        (float)Ts_K + " \t" +
                        (float)(Ps_MPa*10) + " \t" + //Convert to bar
                        (float)(Ps_MPa*7501) + " \t" + //Convert to bar
                        (float)SaturatedDensity.saturatedMassDensityLiquid(Ts_K) + " \t" +
                        (float)SaturatedDensity.saturatedMassDensityVapor(Ts_K) + " \t" +
                        H1 + " \t" +
                        H2 + " \t" +
                        (H2 - H1) + " \t" +
                        S1 + " \t" +
                        S2 + " \t" +
                        (float)(S2 - S1) + " \t"
                );
            }

            if (Ts_C  > 350d) {

                //Con (T, Ps)Iterar para las densidades D', D"
                Ps3 = Ps_MPa;

                region3 = new ThermodynamicRegion3Property(Ts_K, SaturatedDensity.saturatedMassDensityLiquid(Ts_K));
                H1 = (float) region3.specificEnthalpy();
                S1 = (float) region3.specificEntropy();

                region3 = new ThermodynamicRegion3Property(Ts_K, SaturatedDensity.saturatedMassDensityVapor(Ts_K));

                H2 = (float) region3.specificEnthalpy();
                S2 = (float) region3.specificEntropy();

                writeToFile(Ts_C + " \t" +
                        (float)Ts_K + " \t" +
                        (float)(Ps_MPa*10) + " \t" +
                        (float)SaturatedDensity.saturatedMassDensityLiquid(Ts_K) + " \t" +
                        (float)SaturatedDensity.saturatedMassDensityVapor(Ts_K) + " \t" +
                        H1 + " \t" +
                        H2 + " \t" +
                        (H2 - H1) + " \t" +
                        S1 + " \t" +
                        S2 + " \t" +
                        (float)(S2 - S1) + " \t"
                );

            }
            info.add(new Entry(S1, (float) Ts_C));
            info.add(new Entry(S2, (float)Ts_C));
//            info.add(new Entry(Ts_K, Ps_MPa));
            Ts_C = Ts_C + incr;
        } while (Ts_K < WaterConstant.Tc ); // 373.946 C, is the critical temperature in celsius degree

//        info.add(new Entry(4.4120f, 373.946f));

        LineDataSet lineDataSet = new LineDataSet(info, "UMSA");
//        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setLabel("S, KJ/kg-K");


        //Draw
        XAxis axis = new XAxis();

        LineData lineData = new LineData(lineDataSet);
        lineData.calcMinMaxY(0, 380);
        lineChart.setData(lineData);

//        lineChart.animateX(2000);



    }

    private double iterPs(double h) {
        ThermodynamicBBoundaryEq bBoundaryEq = new ThermodynamicBBoundaryEq(h);
        return bBoundaryEq.pressurePs3();

    }


    /**
     * Doesnt work
     * To store in SDCARD
     * @param text
     */
    public void appendLog(String text) { ///sdcard/Download
        File logFile = new File("sdcard/Download/log.file");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.flush(); //added
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Write to data/data/appname
     * @param message added to the next line
     */
    private void writeToFile(String message) {
        try {
            OutputStreamWriter outputStreamWriter;
//            InputStream inputStream = openFileInput("ThermodynamicInfo.txt");
//            if (inputStream == null) {
                outputStreamWriter = new OutputStreamWriter(openFileOutput("ThermodynamicInfo.txt", Context.MODE_APPEND));
//            } else {
//                outputStreamWriter = new OutputStreamWriter(
//                        new FileOutputStream("ThermodynamicInfo.txt",
//                                true));
//            }

            outputStreamWriter.write(message + "\n");
            outputStreamWriter.close();

        } catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() throws IOException {
        String result = "";
        InputStream inputStream = openFileInput("todolist.txt");
        if(inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temp = "";
            StringBuilder stringBuilder = new StringBuilder();

            while((temp = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(temp);
                stringBuilder.append("\n");
            }

            inputStream.close();
            result = stringBuilder.toString();
        }
        return result;
    }
}