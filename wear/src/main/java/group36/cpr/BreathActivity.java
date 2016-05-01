package group36.cpr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class BreathActivity extends WearableActivity {
    private final int UPDATE_INTERVAL_MS = 1500; // Update every 1.5 seconds.
    private final int NUM_BREATHS = 2;

    // The following variables are used for updating the background status image
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

    private int countedBreaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath);

        Log.d("onCreate", "Starting BreathActivity");

        updateHandler = new Handler();
        startRepeatingUpdates();

        countedBreaths = 0;
    }

    public void updateStatus() {
//        Log.d("updateStatus", "Number of breaths: " + countedBreaths);
        TextView numBreaths = (TextView) findViewById(R.id.num_breaths);

        if (countedBreaths == 0) {
            numBreaths.setText("2");
        } else if (countedBreaths == 1) {
            numBreaths.setText("1");
//            Uncomment when on actual device; I get OOM on emulator with this right now.
//            bg.setBackground(getResources().getDrawable(R.drawable.breaths1, null));
        } else if (countedBreaths == 2) {
            numBreaths.setText("0");
//            Uncomment when on actual device; I get OOM on emulator with this right now.
//            bg.setBackground(getResources().getDrawable(R.drawable.breaths2, null));
        } else if (countedBreaths == 3) {
            Intent i = new Intent(this, CompressionActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            stopRepeatingUpdates();
        }

        countedBreaths++;
    }

    public void startRepeatingUpdates() {
        mStatusChecker.run();
    }

    public void stopRepeatingUpdates() {
        updateHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    public void onPause() {
        stopRepeatingUpdates();
        super.onPause();
    }

    @Override
    public void onStop() {
        stopRepeatingUpdates();
        super.onStop();
    }
}
