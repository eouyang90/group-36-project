package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by austinhle on 4/15/16.
 */
public class tutorial_cpr extends Activity {
    private String selection = "Adult";
    private String cr = "x";
    private String cd = "x";
    private String br = "x";
    private String bd = "x";
    private Boolean state = false;
    private String watchMode = "defaultMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = false;
        setContentView(R.layout.tutorial_cpr_practice);
        Intent intent = getIntent();
        selection = intent.getStringExtra("selection"); //adult, child, infant

        //set texts
        TextView compression_reps = (TextView)findViewById(R.id.compression_reps_t);
        TextView compression_desc = (TextView)findViewById(R.id.compression_desc_t);
        TextView breath_rep = (TextView)findViewById(R.id.breath_rep_t);
        TextView breath_desc = (TextView)findViewById(R.id.breath_desc_t);
        if (selection.equals("Adult")) {
            cr = "30";
            cd = "depth: 2 inches\nrate: 100 per min";
            br = "2";
            bd = "rate: 1 second\nper breath";

        } else if (selection.equals("Chind")) {
            cr = "30";
            cd = "depth: 2 inches\nrate: 100 per min";
            br = "2";
            bd = "rate: 1 second\nper breath";
        } else {
            cr = "30";
            cd = "depth: 1.5 inches\nrate: 100 per min";
            br = "2";
            bd = "rate: 1 second\nper breath";
        }
        compression_reps.setText(cr);
        compression_desc.setText(cd);
        breath_rep.setText(br);
        breath_desc.setText(bd);

        //set the button color and text
        final Button start_CPR_button = (Button)findViewById(R.id.startCPR_t);
        start_CPR_button.setText("Start " + selection + " CPR");
        start_CPR_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == false) {
                    state = true;
                    start_CPR_button.setBackgroundResource(R.drawable.rounded_red);
                    start_CPR_button.setText("Stop " + selection + " CPR");

                    //start watch
                    watchMode = "CPR_start_" + selection;
                    Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    watchIntent.putExtra("mode", watchMode);
                    startService(watchIntent);
                    Log.d("start watch", watchMode);
                    Toast toast = Toast.makeText(getBaseContext(), "The CPR cycle is started\non your watch!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else {
                    state = false;
                    //stop watch
                    watchMode = "CPR_stop";
                    Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    watchIntent.putExtra("mode", watchMode);
                    startService(watchIntent);
                    Log.d("stop watch", "watch stop");

                    //TODO: now automatically go to history detail page, should be triggered by watch
                    Intent sendIntent = new Intent(getBaseContext(), TutorialActivity2.class);
                    sendIntent.putExtra("selection", selection);
                    startActivity(sendIntent);
                }
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
