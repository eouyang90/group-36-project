package group36.cpr;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.widget.TextView;

public class CompressionActivity extends WearableActivity {
    private final int TOTAL_NUM_COMPRESSIONS = 30;
    private final double MIN_COMPRESSION_RATE = 100.0 / 60.0; // 100 compressions per 60 seconds
    private final int UPDATE_INTERVAL_MS = 600; // Update every .6 seconds.
    private final int TOLERANCE = 2;

    // The following variables are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

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
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                Log.d("onShake", "Listener picked up on shake");
                registerCompressions();
            }
        });

        updateHandler = new Handler();
        startRepeatingUpdates();

        countedCompressions = 0;
        completedCompressions = 0;
        startTime = System.currentTimeMillis();
    }

    public void registerCompressions() {
        completedCompressions++;

        long elapsedTime = System.currentTimeMillis() - startTime;
        int expectedNumCompressions = (int) (MIN_COMPRESSION_RATE * elapsedTime);
        Log.d("registerCompressions", "Elapsed time: " + elapsedTime);


        BoxInsetLayout bg = (BoxInsetLayout) findViewById(R.id.container);
        bg.setBackgroundColor(getResources().getColor(R.color.green));
//        if (expectedNumCompressions > completedCompressions + TOLERANCE) {
//            bg.setBackgroundColor(getResources().getColor(R.color.yellow)); // Too slow.
//        } else if (expectedNumCompressions < completedCompressions - TOLERANCE) {
//            bg.setBackgroundColor(getResources().getColor(R.color.red)); // Too fast.
//        } else {
//            bg.setBackgroundColor(getResources().getColor(R.color.green));
//        }
    }

    public void updateStatus() {
        countedCompressions++;

        // Update display to show many compressions should be remaining
        TextView countDisplay = (TextView) findViewById(R.id.num_compressions);
        countDisplay.setText("" + (TOTAL_NUM_COMPRESSIONS - countedCompressions));

        if (countedCompressions == TOTAL_NUM_COMPRESSIONS) {
            countedCompressions = 0;
            Intent i = new Intent(this, BreathActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            stopRepeatingUpdates();
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
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        stopRepeatingUpdates();
        Log.d("onPause", "");
        super.onPause();
    }

    @Override
    public void onStop() {
        mSensorManager.unregisterListener(mShakeDetector);
        stopRepeatingUpdates();
        Log.d("onStop", "");
        super.onStop();
    }
}
