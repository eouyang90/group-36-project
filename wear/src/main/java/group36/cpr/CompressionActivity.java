package group36.cpr;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.widget.TextView;
import android.view.animation.LinearInterpolator;


//https://youtu.be/7Hh2fFXfahk
public class CompressionActivity extends WearableActivity {
    private final int TOTAL_NUM_COMPRESSIONS = 30;
    private final double MIN_COMPRESSION_RATE = 100.0 / 60.0; // 100 compressions per 60 seconds
    private final int UPDATE_INTERVAL_MS = 600; // Update every .6 seconds.
    private final int TOLERANCE = 2;

    // The following variables are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    // The following variables are used for updating the background status color
    private Handler updateHandler;
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus();
            } finally {
                updateHandler.postDelayed(mStatusChecker, UPDATE_INTERVAL_MS);
            }
        }
    };

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        private static final float SHAKE_THRESHOLD_GRAVITY = 2.0F;

        public void onSensorChanged(SensorEvent se) {
            float gX = se.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = se.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = se.values[2] / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
//                Log.d("onSensorChanged", "Shake detected!");
                registerCompressions();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private int countedCompressions; // System count of how many should have been completed
    private int completedCompressions; // How many user has actually completed as detected by watch sensors
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compression);

        Log.d("onCreate", "Starting CompressionActivity");

        // Initialize shake detection system.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        updateHandler = new Handler();
        startRepeatingUpdates();

        countedCompressions = 0;
        completedCompressions = 0;
        startTime = System.currentTimeMillis();
    }

    public void registerCompressions() {
        completedCompressions++;
        updateBackground();
    }

    public void updateStatus() {
        countedCompressions++;

        // Update display to show many compressions should be remaining
        TextView countDisplay = (TextView) findViewById(R.id.num_compressions);
        countDisplay.setText("" + (TOTAL_NUM_COMPRESSIONS - countedCompressions));

        updateBackground();

        if (countedCompressions == TOTAL_NUM_COMPRESSIONS) {
            countedCompressions = 0;
            Intent i = new Intent(this, BreathActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            stopRepeatingUpdates();
            mSensorManager.unregisterListener(mSensorListener);
        }
    }

    public void updateBackground() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        Log.d("updateBackground", "Elapsed time: " + elapsedTime);
        int expectedNumCompressions = (int) (MIN_COMPRESSION_RATE * elapsedTime / 1000.0);
        Log.d("updateBackground", "Expected: " + expectedNumCompressions + " | Completed: " + completedCompressions);

        BoxInsetLayout bg = (BoxInsetLayout) findViewById(R.id.container);
        if (expectedNumCompressions > completedCompressions + TOLERANCE) {
            bg.setBackgroundColor(getResources().getColor(R.color.yellow)); // Too slow.
        } else if (expectedNumCompressions < completedCompressions - TOLERANCE) {
            bg.setBackgroundColor(getResources().getColor(R.color.red)); // Too fast.
        } else {
            bg.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    public void startRepeatingUpdates() {
        mStatusChecker.run();
    }

    public void stopRepeatingUpdates() {
        updateHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "");
        startTime = System.currentTimeMillis();
        mSensorManager.registerListener(mSensorListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        stopRepeatingUpdates();
        Log.d("onPause", "");
        super.onPause();
    }

    @Override
    public void onStop() {
        mSensorManager.unregisterListener(mSensorListener);
        stopRepeatingUpdates();
        Log.d("onStop", "");
        super.onStop();
    }
}
