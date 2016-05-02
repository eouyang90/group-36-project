package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by austinhle on 4/15/16.
 */
public class TutorialActivity1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_activity_1);


        //adult
        ImageView adult_selection = (ImageView) findViewById(R.id.adult_select_tutorial);
        adult_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), TutorialActivity2.class);
                sendIntent.putExtra("selection", "Adult");
                Log.d("TutorialActivity1", "Selected adult");
                startActivity(sendIntent);
            }
        });

        //child
        ImageView child_selection = (ImageView) findViewById(R.id.child_select_tutorial);
        child_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), TutorialActivity2.class);
                sendIntent.putExtra("selection", "Child");
                Log.d("TutorialActivity1", "Selected child");
                startActivity(sendIntent);
            }
        });

        //infant
        ImageView infant_selection = (ImageView) findViewById(R.id.infant_select_tutorial);
        infant_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), TutorialActivity2.class);
                sendIntent.putExtra("selection", "Infant");
                Log.d("TutorialActivity1", "Selected infant");
                startActivity(sendIntent);
            }
        });
//        ImageButton startCPRButton = (ImageButton) findViewById(R.id.tutorial1);
//        startCPRButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent;
//                sendIntent = new Intent(getBaseContext(), TutorialActivity3.class);
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
