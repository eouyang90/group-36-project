package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button startCPRButton = (Button) findViewById(R.id.start_cpr_activity_1_button);
//        startCPRButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), StartCPRActivity1.class);
//                Log.d("MainActivity", "Starting up StartCPRActivity1");
//                startActivity(sendIntent);
//            }
//        });
//
//        Button myHistoryButton = (Button) findViewById(R.id.my_history_button);
//        myHistoryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), HistoryMainActivity.class);
//                Log.d("MainActivity", "Starting up StartCPRActivity1");
//                startActivity(sendIntent);
//            }
//        });
//
//        Button tutorialButton = (Button) findViewById(R.id.tutorial_activity_1_button);
//        tutorialButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), TutorialActivity1.class);
//                Log.d("MainActivity", "Starting up StartCPRActivity1");
//                startActivity(sendIntent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
