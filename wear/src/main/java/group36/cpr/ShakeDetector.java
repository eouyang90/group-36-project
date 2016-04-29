package group36.cpr;

/**
 * Created by austinhle on 4/28/16.
 */
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {
    // Need to experiment with this value empirically
    private static final float SHAKE_THRESHOLD_GRAVITY = 1.5F;

    private static final int SHAKE_BUFFER_TIME_MS = 500; // Half a second
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000; // 3 seconds

    private OnShakeListener mListener;
    private long lastShakeTime;
    private int shakeCount;

    public void setOnShakeListener(OnShakeListener listener) {
        this.mListener = listener;
    }

    public interface OnShakeListener {
        void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onSensorChanged(SensorEvent se) {
        if (mListener != null) {
            float gX = se.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = se.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = se.values[2] / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                long currTime = System.currentTimeMillis();

                // Ignore shake events too close to each other (500ms).
                if (lastShakeTime - currTime < SHAKE_BUFFER_TIME_MS) {
                    return;
                }

                // Reset the shake count after 3 seconds of no shakes.
                if (lastShakeTime - currTime >= SHAKE_COUNT_RESET_TIME_MS) {
                    shakeCount = 0;
                }

                lastShakeTime = currTime;
                shakeCount++;

                mListener.onShake(shakeCount);
            }
        }
    }
}