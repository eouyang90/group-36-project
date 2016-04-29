package group36.cpr;

/**
 * Created by austinhle on 4/28/16.
 */
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ShakeDetector implements SensorEventListener {
    // Need to experiment with this value empirically
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.0F;

    private static final int SHAKE_BUFFER_TIME_MS = 500; // Half a second

    private OnShakeListener mListener;
    private long lastShakeTime;

    public void setOnShakeListener(OnShakeListener listener) {
        this.mListener = listener;
    }

    public interface OnShakeListener {
        void onShake();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onSensorChanged(SensorEvent se) {
//        Log.d("onSensorChanged", "SensorEvent detected!");
        if (mListener != null) {
            float gX = se.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = se.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = se.values[2] / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                Log.d("onSensorChanged", "Shake detected!");
                long currTime = System.currentTimeMillis();

                // Ignore shake events too close to each other (500ms).
                if (lastShakeTime - currTime < SHAKE_BUFFER_TIME_MS) {
                    return;
                }

                lastShakeTime = currTime;

                mListener.onShake();
            }
        }
    }
}