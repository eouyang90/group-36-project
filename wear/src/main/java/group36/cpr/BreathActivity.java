package group36.cpr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

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
        DonutProgress progress = (DonutProgress) findViewById(R.id.donut_progress);
        TextView numBreaths = (TextView) findViewById(R.id.num_breaths);

        if (countedBreaths == 0) {
            progress.setProgress(0);
            numBreaths.setText("2");
        } else if (countedBreaths == 1) {
            progress.setProgress(1);
            progress.setInnerBottomText("rescue breath left");
            numBreaths.setText("1");
        } else if (countedBreaths == 2) {
            progress.setProgress(2);
            progress.setInnerBottomText("rescue breaths left");
            numBreaths.setText("0");
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
