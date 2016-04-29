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
    private final int NUM_COMPRESSIONS = 30;
    private final double MIN_COMPRESSION_RATE = 100.0 / 60.0; // 100 compressions per 60 seconds
    private final int UPDATE_INTERVAL_MS = 1000;
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
                Log.d("run", "Updating status periodically.");
                updateStatus();
            } finally {
                updateHandler.postDelayed(mStatusChecker, UPDATE_INTERVAL_MS);
            }
        }
    };

    private int completedCompressions;
    private long startTime;
    private boolean startingBreathActivity;

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
            public void onShake(int count) {
                registerCompressions(count);
            }
        });

        updateHandler = new Handler();
        startRepeatingUpdates();

        completedCompressions = 0;
        startTime = System.currentTimeMillis();
        startingBreathActivity = false;
    }

    public void registerCompressions(int count) {
        completedCompressions += count;
        long elapsedTime = System.currentTimeMillis() - startTime;
        Log.d("registerCompressions", "Elapsed time: " + elapsedTime);
        if (completedCompressions >= NUM_COMPRESSIONS || elapsedTime > 18 * 1000) {
            completedCompressions = 0;
            stopRepeatingUpdates();
            Log.d("registerCompressions", "Starting up BreathActivity");
            Intent i = new Intent(this, BreathActivity.class);
            startActivity(i);
            return;
        }
        TextView countDisplay = (TextView) findViewById(R.id.num_compressions);
        countDisplay.setText("" + (NUM_COMPRESSIONS - completedCompressions));
    }

    public void updateStatus() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        int expectedNumCompressions = (int)(MIN_COMPRESSION_RATE * elapsedTime);

        Log.d("registerCompressions", "Elapsed time: " + elapsedTime);
        if (!startingBreathActivity && (completedCompressions >= NUM_COMPRESSIONS || elapsedTime > 18 * 1000)) {
            completedCompressions = 0;
            stopRepeatingUpdates();
            Intent i = new Intent(this, BreathActivity.class);
            startActivity(i);
            startingBreathActivity = true;
            return;
        }

        BoxInsetLayout bg = (BoxInsetLayout) findViewById(R.id.container);
        if (expectedNumCompressions > completedCompressions + TOLERANCE) {
            bg.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else if (expectedNumCompressions < completedCompressions - TOLERANCE) {
            bg.setBackgroundColor(getResources().getColor(R.color.red));
        } else {
            bg.setBackgroundColor(getResources().getColor(R.color.green));
        }

        completedCompressions++;
        TextView countDisplay = (TextView) findViewById(R.id.num_compressions);
        countDisplay.setText("" + (NUM_COMPRESSIONS - completedCompressions));
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
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
