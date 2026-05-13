package com.iridiscense.unitoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;

public class ThermoMainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native void init(AssetManager assetManager);

    public static native void surfaceCreated();

    public static native void surfaceChanged(int width, int height);

    public static native void drawFrame();

    public static native void pause();

    public static native void resume();

    public native String stringFromJNI();

//    public native double valueFromJNI();


    GLSurfaceView mView;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//                setContentView(R.layout.activity_main);
////        LineGraph lineGraph = findViewById(R.id.lineGraph);
//        ArrayList<DataPoint> points = new ArrayList<>();
//        points.add(new DataPoint(10,10));
//        points.add(new DataPoint(100,50));
//        points.add(new DataPoint(100,100));
//        points.add(new DataPoint(150,200));
////        lineGraph.setPoints(points);
//
////        LineGraph lineGraph = new LineGraph(getApplicationContext(),700,700); //Pass view width and view height as parameters
//        //Then add the view to your layout
//
////        layout.addView(lineGraph);
//
//
//    }


//        @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Example of a call to a native method
//        TextView tv = findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
//    }

//    @Override
//    protected void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        mView = new GLSurfaceView(getApplication());
//        mView.setEGLContextClientVersion(2);
//        mView.setRenderer(new GLSurfaceView.Renderer() {
//
//            @Override
//            public void onSurfaceCreated(GL10 gl10, javax.microedition.khronos.egl.EGLConfig eglConfig) {
//                double T[] = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 225, 230, 235, 240, 245, 250, 255, 260, 265, 270, 275, 280, 285, 290, 295, 300, 305, 310, 315, 320, 325, 330, 335, 340, 345, 350, 355, 360, 365, 370, 373.95, };
//                double Ps;
//                for (int i = 0; i < T.length; i++) {
//                    Ps = SaturatedRegion.pressureSaturate(T[i] + 273.15);
//                    System.out.println("Ps: " + Ps + "\n");
//                }
//
//                double P[] = {0.59, 0.6, 0.611, 0.611212677, 1, 2, 5, 10, 15, 20, 50, 100, 500, 1000, 10000, 20000, 22064, 22064.1}; // kPa
//
//                double Ts;
//                for (int i = 0; i < P.length; i++) {
//                    Ts = SaturatedRegion.temperatureSaturate(P[i] * 0.001);
//                    System.out.println("Ts: " + Ts + "\n");
//                }
//                drawFrame();
//                surfaceCreated();
//            }
//
//
//            @Override
//            public void onSurfaceChanged(GL10 gl, int width, int height) {
//                surfaceChanged(width, height);
//
//                String name = "methane";
//                double T = 300; //K
//                double v = 2.5;  // m³
//                SoaveRedlichKwong redlichKwong = new SoaveRedlichKwong(getApplicationContext(), name, T);
//                System.out.println(">>>>>>> p" + redlichKwong.pressure(T, v));
////                System.out.println(">>>>>>> " + redlichKwong.pressure(T, v));
//
//                /*
////                double T = 300; // K
//                double P = 3;   // MPa
//                ThermodynamicPropertiesManager thermodynamicProperties = new ThermodynamicPropertiesManager(T, P);
//                System.out.println("TP result v: " + thermodynamicProperties.specificVolume());
//                System.out.println("TP result h: " + thermodynamicProperties.specificEnthalpy());
//                System.out.println("TP result u: " + thermodynamicProperties.specificInternalEnergy());
//                System.out.println("TP result s: " + thermodynamicProperties.specificEntropy());
//                System.out.println("TP result Cp: " + thermodynamicProperties.specificIsobaricHeatCapacity());
//                System.out.println("TP result w: " + thermodynamicProperties.speedOfSound());
//                System.out.println("TP result k: " + thermodynamicProperties.isentropicExponent());
//                System.out.println("TP result alpha: " + thermodynamicProperties.isobaricCubicExpansionCoefficient());
//                System.out.println("TP result k_T: " + thermodynamicProperties.isothermalCompressibility());
//                System.out.println(" ********************* ");
//
//                thermodynamicProperties.setTemperature(503.17);
//                thermodynamicProperties.setPressure(2.7968);
//                System.out.println("TP new TP: " + thermodynamicProperties.getPressure());
//                System.out.println("TP result v: " + thermodynamicProperties.specificVolume());
//                System.out.println("TP result h: " + thermodynamicProperties.specificEnthalpy());
//                System.out.println("TP result u: " + thermodynamicProperties.specificInternalEnergy());
//                System.out.println("TP result s: " + thermodynamicProperties.specificEntropy());
//                System.out.println("TP result Cp: " + thermodynamicProperties.specificIsobaricHeatCapacity());
//                System.out.println("TP result w: " + thermodynamicProperties.speedOfSound());
//                System.out.println("TP result k: " + thermodynamicProperties.isentropicExponent());
//                System.out.println("TP result alpha: " + thermodynamicProperties.isobaricCubicExpansionCoefficient());
//                System.out.println("TP result k_T: " + thermodynamicProperties.isothermalCompressibility());
//
//                System.out.println("Boundary Pressure: " + BoundaryRegion.pressureB23(360D + 273.15D));
//                System.out.println("Boundary Tempe: " + BoundaryRegion.temperatureB23(20D));
//*/
//
//            }
//
//            @Override
//            public void onDrawFrame(GL10 gl) {
//
//            }
//
//
//        });
//        mView.queueEvent(new Runnable() {
//            @Override
//            public void run() {
//                init(getAssets());
//            }
//        });
//        setContentView(mView);
//    }

    @Override
    protected void onPause() {
        super.onPause();
//        mView.onPause();
//        mView.queueEvent(new Runnable() {
//            @Override
//            public void run() {
//                pause();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mView.onResume();
//        mView.queueEvent(new Runnable() {
//            @Override
//            public void run() {
//                resume();
//            }
//        });

    }
}
