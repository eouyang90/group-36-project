package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by austinhle on 4/15/16.
 */
public class StartCPRActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpr_activity_2);

        ImageButton startCPRButton = (ImageButton) findViewById(R.id.CPR2Background);
        startCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent;
                sendIntent = new Intent(getBaseContext(), StartCPRActivity3.class);
                Log.d("MainActivity", "Starting up StartCPRActivity1");
                startActivity(sendIntent);
            }
        });
    }
}
