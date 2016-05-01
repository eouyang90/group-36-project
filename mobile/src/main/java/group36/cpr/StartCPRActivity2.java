package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by austinhle on 4/15/16.
 */
public class StartCPRActivity2 extends Activity {
    private String selection = "Adult";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpr_activity_2);
        //Intent intent = getIntent();
        //selection = intent.getStringExtra("selection"); //adult, child, infant

        //yes
        ImageView env_yes = (ImageView) findViewById(R.id.environment_yes);
        env_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), StartCPRActivity1.class);
                //sendIntent.putExtra("selection", selection);
                Log.d("StartCPRActivity2", "selected yes");
                startActivity(sendIntent);
            }
        });

        //no
        ImageView env_no = (ImageView) findViewById(R.id.environment_no);
        env_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getBaseContext(), "Restart when the\nenvironment is clear!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Intent sendIntent = new Intent(getBaseContext(), MainActivity.class);
                Log.d("StartCPRActivity2", "selected no, return to main view");
                startActivity(sendIntent);
            }
        });
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
