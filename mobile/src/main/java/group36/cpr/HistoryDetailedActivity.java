package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by austinhle on 4/15/16.
 */
public class HistoryDetailedActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detailed_activity);

        /*
        LayoutInflater inflater = getLayoutInflater();

        String timeDate = getIntent().getStringExtra("time");

        final HistoryDbHelper mDbHelper = new HistoryDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        */

        /*
        * We will need to get rescue breaths, patient type, total elapsed time, and finish date from
        * the first table, Entries, and the remainder of the information (frequency accuracy, depth
        * accuracy, data points (and number of compressions from number of data points)) from the
        * second table, DepthTimes. As a result, no joining will be involved, since the unique
        * datetime serves as a key for both tables.
        */

        /*
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DepthTimes.COLUMN_TIME,
                DepthTimes.COLUMN_DEPTH
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DepthTimes.COLUMN_DATETIME_ID + " DESC";

        String whereArg = DepthTimes.COLUMN_DATETIME_ID + "=?";

        String [] whereVals = {
                timeDate
        };

        Cursor c = db.query(
                DepthTimes.TABLE_NAME,                    // The table to query
                projection,                               // The columns to return
                whereArg,                                 // The columns for the WHERE clause
                whereVals,                                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        LinearLayout main = (LinearLayout) findViewById(R.id.hd_body);

        if (c.moveToFirst()) {

            while (c.isAfterLast() == false) {
                View view = inflater.inflate(R.layout.activity_hd_entry, null);
                final String timems = c.getString(c.getColumnIndexOrThrow(DepthTimes.COLUMN_TIME));
                TextView text = (TextView) view.findViewById(R.id.timems);
                text.setText(timems);
                final String depth = c.getString(c.getColumnIndexOrThrow(DepthTimes.COLUMN_DEPTH));
                TextView depthText = (TextView) view.findViewById(R.id.depth);
                depthText.setText(depth);
                main.addView(view);
                c.moveToNext();
            }
        }

        //***REMOVE ON FINAL PUSH***
        //db.delete deletes all the rows of the table but keeps the table:
        db.delete(DepthTimes.TABLE_NAME, null, null);
        //dropping the table removes the table itself, but the db remains:
        //db.execSQL("DROP TABLE IF EXISTS " + Entries.TABLE_NAME);
        */

        
         GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 16),
                new DataPoint(1, 17),
                new DataPoint(2, 22),
                new DataPoint(3, 23),
                new DataPoint(4, 16),
                new DataPoint(5, 15),
                new DataPoint(6, 17),
                new DataPoint(7, 28),
                new DataPoint(8, 10),
                new DataPoint(9, 18),
                new DataPoint(10, 18),
                new DataPoint(11, 18),
                new DataPoint(12, 20),
                new DataPoint(13, 18),
                new DataPoint(14, 16),
                new DataPoint(15, 18),
                new DataPoint(16, 18),
                new DataPoint(17, 17),
                new DataPoint(18, 18),
                new DataPoint(19, 18)

        });


        graph.addSeries(series);

        ImageButton startCPRButton = (ImageButton) findViewById(R.id.history_detail);
        startCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent;
                sendIntent = new Intent(getBaseContext(), MainActivity.class);
                Log.d("MainActivity", "Starting up StartCPRActivity1");
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
