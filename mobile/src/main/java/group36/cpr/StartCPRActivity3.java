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

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by austinhle on 4/15/16.
 */
public class StartCPRActivity3 extends Activity {
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
        setContentView(R.layout.cpr_activity_3);
        Intent intent = getIntent();
        selection = intent.getStringExtra("selection"); //adult, child, infant

        //set texts
        TextView compression_reps = (TextView)findViewById(R.id.compression_reps);
        TextView compression_desc = (TextView)findViewById(R.id.compression_desc);
        TextView breath_rep = (TextView)findViewById(R.id.breath_rep);
        TextView breath_desc = (TextView)findViewById(R.id.breath_desc);
        if (selection.equals("Adult")) {
            cr = "30";
            cd = "depth: 2 inches\nrate: 100 per min";
            br = "2";
            bd = "rate: 1 second\nper breath";

        } else if (selection.equals("Child")) {
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
        final Button start_CPR_button = (Button)findViewById(R.id.startCPR);
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

                    /*
                    final HistoryDbHelper mDbHelper = new HistoryDbHelper(getApplicationContext());
                    // Gets the data repository in write mode
                    final SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    //***REMOVE ON FINAL PUSH***
                    //Each time drop table and readd it emptily with newest structure.
                    mDbHelper.onUpgrade(db, 1, 1);
                    //Creates a new table with certain structure:
                    //db.execSQL(Entries.SQL_CREATE_ENTRIES);

                    final Button button = (Button) findViewById(R.id.button);
                    final long[] timeKeeper = new long[1];
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if (isRunning) {
                                long endTime = System.currentTimeMillis();
                                //previously used System.nanoTime() --> more precise (10^6 more sigfigs)

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                                Date endDate = new Date(endTime);

                                long elapsedTime = (endTime - timeKeeper[0])/1000;
                                long numMins = elapsedTime/60;
                                long numSecs = elapsedTime%60;
                                String elTime = numMins + " mins, " + numSecs + " secs";

                                // Create a new map of values, where column names are the keys
                                ContentValues values = new ContentValues();
                                values.put(Entries.COLUMN_DATETIME_ID, sdf.format(endDate));
                                values.put(Entries.COLUMN_ELTIME, elTime);

                                // Insert the new row, returning the primary key value of the new row
                                long newRowId;
                                newRowId = db.insert(
                                        Entries.TABLE_NAME,null,
                                        values);

                                timeKeeper[0] = 0L;
                                button.setText("Start Me!");
                            } else {
                                button.setText("Stop Me!");
                                timeKeeper[0] = System.currentTimeMillis();
                            }
                            isRunning = !isRunning;
                        }
                    });

                    final Button next = (Button) findViewById(R.id.historybtn);
                    next.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v) {
                            isRunning = false;
                            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                            startActivity(intent);
                        }
                    });
                     */

                    //TODO: now automatically go to history detail page, should be triggered by watch
                    Intent sendIntent = new Intent(getBaseContext(), HistoryDetailedActivity.class);
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
