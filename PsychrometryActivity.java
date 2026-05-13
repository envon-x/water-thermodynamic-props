package com.iridiscense.unitoperations;

import android.graphics.Color;
import android.os.Bundle;

import com.iridiscense.unitoperations.draw.AbsoluteHumidityRelations;
import com.iridiscense.unitoperations.draw.PsychrometryDrawerSetDataSource;
import com.iridiscense.unitoperations.plotter.charts.LineChart;
import com.iridiscense.unitoperations.plotter.components.Legend;
import com.iridiscense.unitoperations.plotter.components.XAxis;
import com.iridiscense.unitoperations.plotter.components.YAxis;
import com.iridiscense.unitoperations.plotter.data.Entry;
import com.iridiscense.unitoperations.plotter.data.ValuePosition;
import com.iridiscense.unitoperations.plotter.highlight.Highlight;
import com.iridiscense.unitoperations.plotter.listener.OnChartValueSelectedListener;
import com.iridiscense.unitoperations.variables.Pressure;

import androidx.appcompat.app.AppCompatActivity;

public class PsychrometryActivity extends AppCompatActivity implements OnChartValueSelectedListener /* OnChartGestureListener*//*, View.OnTouchListener*/ {

    LineChart lineChart;
    PsychrometryDrawerSetDataSource psychrometryDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychrometry);

        lineChart = findViewById(R.id.line_chart);
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setDrawGridBackground(false);

        Pressure atmPressure = new Pressure(495, "mmHg");

        psychrometryDrawer = new PsychrometryDrawerSetDataSource(atmPressure);
        psychrometryDrawer.setYValuePosition(ValuePosition.OUTSIDE_SLICE);

        lineChart.setData(psychrometryDrawer.data()); //getting data as LIneData
        refreshChart();
    }

    private void refreshChart() {

        lineChart.getDescription().setEnabled(false);                                               // no description text
        lineChart.setTouchEnabled(true);                                                            // enable touch gestures

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        lineChart.setPinchZoom(true);                                                               // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setBackgroundColor(Color.BLACK);                                                  // set an alternative background color

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//        mv.setlineChartView(lineChart); // For bounds control
//        lineChart.setMarker(mv); // Set the marker to the lineChart

        XAxis xl = lineChart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        xl.setAxisMinimum(0f);

        YAxis leftAxis = lineChart.getAxisLeft();
//        leftAxis.setInverted(true);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setAxisMaximum(1);
        leftAxis.setMaxWidth(0.05f); //adicionado
        leftAxis.setEnabled(false);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(true); //para habilitar el eje derecho. se ha visto que coexisten el eje derecho y el izquierdo
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        rightAxis.setAxisMaximum(1f);
        rightAxis.setAxisMinimum(0.00f);
        // // restrain the maximum scale-out factor
        // lineChart.setScaleMinima(3f, 3f);
        //
        // // center the view to a specific position inside the lineChart
        // lineChart.centerViewPort(10, 50);


//        Legend l = lineChart.getLegend();                                                           // get the legend (only possible after setting data)
//        l.setForm(Legend.LegendForm.LINE);                                                          // modify the legend ...
        lineChart.invalidate();                                                                     // don't forget to refresh the drawing
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

//    private final int[] colors = new int[] {
//            ColorTemplate.VORDIPLOM_COLORS[0],
//            ColorTemplate.VORDIPLOM_COLORS[1],
//            ColorTemplate.VORDIPLOM_COLORS[2]
//    };


//    @Override
//    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//
//    }
//
//    @Override
//    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//
//    }
//
//    @Override
//    public void onChartLongPressed(MotionEvent me) {
//
//    }
//
//    @Override
//    public void onChartDoubleTapped(MotionEvent me) {
//
//    }
//
//    @Override
//    public void onChartSingleTapped(MotionEvent me) {
//
//    }
//
//    @Override
//    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
//
//    }
//
//    @Override
//    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
//
//    }
//
//    @Override
//    public void onChartTranslate(MotionEvent me, float dX, float dY) {
//
//    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        if (!(lineChart.getLowestVisibleX() == lineChart.getXAxis().getAxisMinimum() || lineChart.getHighestVisibleX() == lineChart.getXAxis().getAxisMaximum())) {
//            // Do your work here
//            Toast.makeText(getApplicationContext(), "Hello Scroll to end check working", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return true;    }
}
