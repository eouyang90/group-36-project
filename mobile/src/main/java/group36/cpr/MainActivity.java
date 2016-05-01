package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startCPRButton = (Button) findViewById(R.id.startCPR);
        startCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent;
                sendIntent = new Intent(getBaseContext(), StartCPRActivity2.class);
                Log.d("MainActivity", "Start CPR");
                startActivity(sendIntent);
            }
        });

        Button myHistoryButton = (Button) findViewById(R.id.history);
        myHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), HistoryMainActivity.class);
                Log.d("MainActivity", "Start History");
                startActivity(sendIntent);
            }
        });
//
        Button tutorialButton = (Button) findViewById(R.id.tutorial);
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), TutorialActivity1.class);
                Log.d("MainActivity", "Start Tutorial");
                startActivity(sendIntent);
            }
        });
    }
}
