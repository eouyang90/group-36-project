package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BreathActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath);

        ImageButton startCPRButton = (ImageButton) findViewById(R.id.breath);
        startCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent;
                sendIntent = new Intent(getBaseContext(), CompressionActivity.class);
                Log.d("MainActivity", "Starting up StartCPRActivity1");
                startActivity(sendIntent);
            }
        });
    }
}
