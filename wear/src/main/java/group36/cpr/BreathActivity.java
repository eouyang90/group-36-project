package group36.cpr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class BreathActivity extends WearableActivity {
    private final int UPDATE_INTERVAL_MS = 1500; // Update every second.
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
//        ImageView bg = (ImageView) findViewById(R.id.breaths_bg);
//        switch (countedBreaths) {
//            case 0:
//                Log.d("updateStatus", "Breath 0");
//                bg.setBackground(getResources().getDrawable(R.drawable.breaths0, null));
//                break;
//            case 1:
//                Log.d("updateStatus", "Breath 1");
//                bg.setBackground(getResources().getDrawable(R.drawable.breaths1, null));
//                break;
//            case 2:
//                Log.d("updateStatus", "Breath 2");
//                bg.setBackground(getResources().getDrawable(R.drawable.breaths2, null));
//                stopRepeatingUpdates();
//                Intent i = new Intent(this, CompressionActivity.class);
//                startActivity(i);
//                return;
//        }
        Log.d("updateStatus", "Number of breaths: " + countedBreaths);
        TextView numBreaths = (TextView) findViewById(R.id.num_breaths);
        numBreaths.setText("" + (NUM_BREATHS - countedBreaths));
        if (countedBreaths == 2) {
            stopRepeatingUpdates();
            Log.d("updateStatus", "Starting up CompressionActivity");
            Intent i = new Intent(this, CompressionActivity.class);
            startActivity(i);
        } else {
            countedBreaths++;
        }
    }

    public void startRepeatingUpdates() {
        mStatusChecker.run();
    }

    public void stopRepeatingUpdates() {
        updateHandler.removeCallbacks(mStatusChecker);
    }
}
