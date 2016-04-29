package group36.cpr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;

public class MainActivity extends WearableActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start up the compression activity after 1 second delay.
        Runnable mLaunchTask = new Runnable() {
            public void run() {
                Intent i = new Intent(getApplicationContext(), CompressionActivity.class);
                startActivity(i);
            }
        };

        Handler mHandler = new Handler();
        mHandler.postDelayed(mLaunchTask, 1000);
    }
}
