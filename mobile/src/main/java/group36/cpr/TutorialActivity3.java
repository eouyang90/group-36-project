package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by austinhle on 4/15/16.
 */
public class TutorialActivity3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_activity_3);

//        ImageButton startCPRButton = (ImageButton) findViewById(R.id.turorial3);
//        startCPRButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent;
//                sendIntent = new Intent(getBaseContext(), TutorialActivity2.class);
//                Log.d("MainActivity", "Starting up StartCPRActivity1");
//                startActivity(sendIntent);
//            }
//        });
    }

    //handle option selection
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        menu.findItem(R.id.home).setIcon(R.drawable.home);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
