package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by austinhle on 4/15/16.
 */
public class StartCPRActivity4 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_cpr4);

        ImageButton startCPRButton = (ImageButton) findViewById(R.id.CPR4Background);
        startCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset watch
                Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                watchIntent.putExtra("mode", "CPR_stop");
                startService(watchIntent);

                Intent sendIntent;
                sendIntent = new Intent(getBaseContext(), HistoryDetailedActivity.class);
                startActivity(sendIntent);
            }
        });

        //start watch
        Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
        watchIntent.putExtra("mode", "CPR_start");
        startService(watchIntent);
    }
}
