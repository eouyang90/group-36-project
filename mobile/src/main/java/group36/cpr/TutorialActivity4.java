package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by austinhle on 4/15/16.
 */
public class TutorialActivity4 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_activity_4);

        ImageButton startCPRButton = (ImageButton) findViewById(R.id.tutorial4);
        startCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent;
                sendIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(sendIntent);
            }
        });

        Button myHistoryButton = (Button) findViewById(R.id.try_breath);
        myHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start watch
                Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                watchIntent.putExtra("mode", "try_breath");
                startService(watchIntent);
            }
        });
    }
}
