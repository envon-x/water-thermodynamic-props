package com.iridiscense.unitoperations.draw;

import android.graphics.Color;

import com.iridiscense.unitoperations.PsychrometryFactory;
import com.iridiscense.unitoperations.calc.Calculate;
import com.iridiscense.unitoperations.plotter.components.YAxis;
import com.iridiscense.unitoperations.plotter.data.Entry;
import com.iridiscense.unitoperations.plotter.data.LineData;
import com.iridiscense.unitoperations.plotter.data.LineDataSet;
import com.iridiscense.unitoperations.plotter.data.PieDataSet;
import com.iridiscense.unitoperations.plotter.data.ValuePosition;
import com.iridiscense.unitoperations.plotter.interfaces.datasets.ILineDataSet;
import com.iridiscense.unitoperations.variables.AbsoluteHumidity;
import com.iridiscense.unitoperations.variables.DryBulbTemperature;
import com.iridiscense.unitoperations.variables.Pressure;
import com.iridiscense.unitoperations.variables.Temperature;

import java.util.ArrayList;

/**
 * This class was created for Unit Operation project
 * Universidad Mayor de San Andrés, La Paz - Bolivia
 * Chemistry, Evironmental, Food and Petrochemical Engineering
 * Created by bon on 9/29/21.
 * Copyright (c)  Hector Bonifacio. 9/29/21, All rights reserved.
 */
public class PsychrometryDrawerSetDataSource implements DrawerSetDataSource {


    private ValuePosition mXValuePosition = ValuePosition.INSIDE_SLICE;
    private ValuePosition mYValuePosition = ValuePosition.INSIDE_SLICE;


    private final Pressure atmospherePressure;
//    private AirPsychrometry airPsychrometry;
    private Temperature boilTemperature;
    private AbsoluteHumidityFunction absoluteHumidityFunction;
    private AbsoluteHumidity absoluteHumidity;
    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//    private DryBulbTemperature[] dryBulbTemperature;
    AbsoluteHumidityFunction absoluteHumiditySaturated = AbsoluteHumidityRelations.atRelativeHumidityConstant();

    public PsychrometryDrawerSetDataSource(final Pressure atmospherePressure) {
        this.atmospherePressure = atmospherePressure;
        boilTemperature = Calculate.temperatureSaturate(atmospherePressure, "C");
//        fillDryBulbTemperature();
    }

//    @Deprecated
//    private void fillDryBulbTemperature() {
//        double temperature = 0;
//        double step = 0.01;
//        int index = 0;
//        int size = (int) ((boilTemperature.value - temperature)/step); // + 1;
//        dryBulbTemperature = new DryBulbTemperature[size];
//    }

    /**
     * At humidRelativity constant values for lines on Chart
     */
    private void relativeHumidity() {
        this.absoluteHumidityFunction = AbsoluteHumidityRelations.atRelativeHumidityConstant();
        double[] constRelativeHumidity = {0.10, 0.20, 0.30, 0.40, 0.50, 0.60, 0.70, 0.80, 0.9, 1.0};

        for (int j = 0; j < constRelativeHumidity.length; j++) {//Numero o cantidad de humedad relativa
            ArrayList<Entry> values = new ArrayList<>();

            for (int i = 0; i < boilTemperature.value; i++) { //

                absoluteHumidity = absoluteHumidityFunction.calculate(                              // Calculando la humedad absoluta
                        atmospherePressure,
                        new DryBulbTemperature(i, boilTemperature.measureUnit), // solo por la boilTemperature.measureUnit unidad
                        constRelativeHumidity[j]);

                // option1
                // values.add(new Entry(i, (float) absoluteHumidity));
                Entry hr_entry = PsychrometryFactory.createEntry("HR_" + j + i); //El nombre tiene que ser único para cada uno de loos puntos de entrada
                hr_entry.setX(i);
                hr_entry.setY((float)absoluteHumidity.value);
                values.add(hr_entry);
            }  // i

            LineDataSet d = new LineDataSet(values, "HR " + (j + 1));
            d.setLineWidth(0.5f);
            d.setColor(-60164);
            d.setDrawCircles(false);
            d.setDrawValues(true);
            d.setCubicIntensity(10.2f);
            dataSets.add(d); //Muy Importante para graficar

//            d.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        } // j

    }


    /**
     * To draw lines at Specific Volume constant values
     */
    private void specificVolumeLines() {
        absoluteHumidityFunction = AbsoluteHumidityRelations.atSpecificVolumeConstant();
//        AbsoluteHumidityFunction absoluteHumiditySaturated = AbsoluteHumidityRelations.atRelativeHumidityConstant();

        double[] specificVolume = {0.5, 0.6, 0.7, 0.75, 0.8, 0.85, 0.90, 0.95, 1, 1.05, 1.1, 1.15, 1.2, 1.25, 1.3, 1.35, 1.40, 1.45, 1.50, 1.55, 1.60, 1.65, 1.70, 1.75}; // m³/kg(aire seco)
        double t = 0;
        double drawLimiter = 0;

        for (int k = 0; k < specificVolume.length; k++) {

            ArrayList<Entry> values_Y = new ArrayList<>();
            for (t = 0; t < boilTemperature.value; t = t + 0.05) { //t no puede ser zero, provoca infinity (por que no se puede dividir entre zero)
//            while (t < boilTemperature.value) { // Este no funciona y no se dabe porqué

                absoluteHumidity = absoluteHumidityFunction.calculate(atmospherePressure, new DryBulbTemperature(t, boilTemperature.measureUnit), specificVolume[k]);
//
//                if (absoluteHumidity.value < 0) {
//                    absoluteHumidity.value = 0;
//                }
                //Este bloque está para delimitar la lineas de donde a donde debe graficarse
                drawLimiter = absoluteHumiditySaturated.calculate(atmospherePressure, new DryBulbTemperature((double)t, boilTemperature.measureUnit), 1.00).value;
                if (absoluteHumidity.value <= drawLimiter) {
                    Entry hr_entry = PsychrometryFactory.createEntry("SV_" + k + t); //El nombre tiene que ser único para cada uno de loos puntos de entrada
                    hr_entry.setX((float) t);
                    hr_entry.setY((float) absoluteHumidity.value);
                    values_Y.add(hr_entry);
                }
                t = t + 0.05;
            }
            LineDataSet d_h = new LineDataSet(values_Y, "volumen específico " + (k + 1));
            d_h.setAxisDependency(YAxis.AxisDependency.LEFT);
            d_h.setColor(-10164);
            d_h.setLineWidth(0.5f);
            d_h.setDrawValues(false);
            d_h.setCubicIntensity(10.2f);
            d_h.setDrawCircles(false);
            dataSets.add(d_h); //Muy Importante para graficar
        }
    }

    /**
     * To draw lines at enthalpy constant values
     */
    private void specificEnthalpyLines() {
        this.absoluteHumidityFunction = AbsoluteHumidityRelations.atSpecificEnthalpy();
        double enthalpyDrawLimiter = 0;
        int stepLineMarker = 0;
        double[] specificEnthalpyFixedValue = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100,
                                                105, 110, 115, 120, 125, 130, 135, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200,
                205, 210, 215, 220, 225, 230, 235, 240, 245, 250, 255, 260, 265, 270, 275, 280, 285, 290, 295, 300

        };
        double th = 0;
        for (int k = 0; k < specificEnthalpyFixedValue.length; k++) {

            ArrayList<Entry> values_Y = new ArrayList<>();

            for (th = 0; th < boilTemperature.value; th = th + 0.05) {

                absoluteHumidity = absoluteHumidityFunction.calculate(
                        atmospherePressure,
                        new DryBulbTemperature(th, boilTemperature.measureUnit),
                        specificEnthalpyFixedValue[k]
                );

                enthalpyDrawLimiter = absoluteHumiditySaturated.calculate(
                        atmospherePressure,
                        new DryBulbTemperature((double)th,
                                boilTemperature.measureUnit),
                        1.0).value;//1.12; //1.0 para que solo grafique hasta esa linea


                if (absoluteHumidity.value <= enthalpyDrawLimiter) {
                    Entry hr_entry = PsychrometryFactory.createEntry("H_" + k + th); //El nombre tiene que ser único para cada uno de loos puntos de entrada
                    hr_entry.setX((float)th);
                    hr_entry.setY((float) absoluteHumidity.value);
                    values_Y.add(hr_entry);
                }
            }
            LineDataSet d_h = new LineDataSet(values_Y, "entalpia " + (k + 1));
            d_h.setAxisDependency(YAxis.AxisDependency.LEFT);
            d_h.setColor(Color.rgb(0, 254, 99));
            d_h.setLineWidth(0.5f);
            d_h.setDrawValues(false);
            d_h.setCubicIntensity(10.2f);
            d_h.setDrawCircles(false);
            dataSets.add(d_h); //Muy Importante para graficar
        }
    }

    private void drawAtConstant() {
        relativeHumidity();
        specificEnthalpyLines();
        specificVolumeLines();
//        enthalpyMarker();
    }

    @Override
    public LineData data(){
        drawAtConstant();
        return  new LineData(dataSets);
    }


    /**
     * Only for show and read enthalpy marker
     */
    private void enthalpyMarker() {
        this.absoluteHumidityFunction = AbsoluteHumidityRelations.atRelativeHumidityConstant();
        double marker = 1.1;


            ArrayList<Entry> values = new ArrayList<>();

            for (int i = 0; i < boilTemperature.value; i++) { //

                absoluteHumidity = absoluteHumidityFunction.calculate(                              // Calculando la humedad absoluta
                        atmospherePressure,
                        new DryBulbTemperature(i, boilTemperature.measureUnit), // solo por la boilTemperature.measureUnit unidad
                        marker);

                // option1
                // values.add(new Entry(i, (float) absoluteHumidity));
                Entry hr_entry = PsychrometryFactory.createEntry("H_marker" + marker + i); //El nombre tiene que ser único para cada uno de loos puntos de entrada
                hr_entry.setX(i);
                hr_entry.setY((float)absoluteHumidity.value);
                values.add(hr_entry);
            }  // i

            LineDataSet d = new LineDataSet(values, "H_marker " + (marker + 1));
            d.setLineWidth(0.5f);
            d.setColor(-60150);
            d.setDrawCircles(false);
            d.setDrawValues(true);
            d.setCubicIntensity(10.2f);
            dataSets.add(d); //Muy Importante para graficar
    }

    @Override
    public ValuePosition getXValuePosition() {
        return mXValuePosition;
    }

    public void setXValuePosition(ValuePosition xValuePosition) {
        this.mXValuePosition = xValuePosition;
    }

    @Override
    public ValuePosition getYValuePosition() {
        return mYValuePosition;
    }

    public void setYValuePosition(ValuePosition yValuePosition) {
        this.mYValuePosition = yValuePosition;
    }


}
