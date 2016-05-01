package group36.cpr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;

public class MainActivity extends WearableActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("onCreate", "Started MainActivity");

        // Start up the compression activity after 5 second delay.
        Runnable mLaunchTask = new Runnable() {
            public void run() {
                Intent i = new Intent(getApplicationContext(), CompressionActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        Handler mHandler = new Handler();
        mHandler.postDelayed(mLaunchTask, 5000);
    }
}
