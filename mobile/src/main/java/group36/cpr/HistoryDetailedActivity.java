package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * Created by austinhle on 4/15/16.
 */
public class HistoryDetailedActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detailed_activity);

        /*
        * We will need to get rescue breaths, patient type, total elapsed time, and finish date from
        * the first table, Entries, and the remainder of the information (frequency accuracy, depth
        * accuracy, data points (and number of compressions from number of data points)) from the
        * second table, DepthTimes. As a result, no joining will be involved, since the unique
        * datetime serves as a key for both tables.
        */

        LayoutInflater inflater = getLayoutInflater();
        String timeDate = getIntent().getStringExtra("time");
        final HistoryDbHelper mDbHelper = new HistoryDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //DepthTimes Query
        String[] projection_depthTimes = {
                DepthTimes.COLUMN_TIME,
                DepthTimes.COLUMN_DEPTH
        };
        String sortOrder = DepthTimes.COLUMN_DATETIME_ID + " DESC";
        String whereArg = DepthTimes.COLUMN_DATETIME_ID + "=?";
        String [] whereVals = {timeDate};
        Cursor c_depthTimes = db.query(
                DepthTimes.TABLE_NAME,                    // The table to query
                projection_depthTimes,                               // The columns to return
                whereArg,                                 // The columns for the WHERE clause
                whereVals,                                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        //Entries Query
        String[] projection_eitries = {
                Entries.COLUMN_ELTIME,
                Entries.COLUMN_SUBJECT_TYPE,
                Entries.COLUMN_RESCUE_BREATHS
        };
        Cursor c_entries = db.query(
                Entries.TABLE_NAME,                       // The table to query
                projection_eitries,                       // The columns to return
                whereArg,                                 // The columns for the WHERE clause
                whereVals,                                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        //get all views by id
        ImageView profile_pic = (ImageView)findViewById(R.id.type_pic);
        TextView date = (TextView)findViewById(R.id.date);
        TextView minute = (TextView)findViewById(R.id.minute);
        TextView patient_type = (TextView)findViewById(R.id.patient_type);
        TextView depth_accuracy = (TextView)findViewById(R.id.depth_accuracy);
        TextView frequency_accuracy = (TextView)findViewById(R.id.frequency_accuracy);
        TextView compressions = (TextView)findViewById(R.id.compressions);
        TextView breaths = (TextView)findViewById(R.id.breaths);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        GridLabelRenderer gr = graph.getGridLabelRenderer();
        gr.setGridColor(R.color.chenPrimaryDark);
        gr.setVerticalLabelsColor(R.color.chenPrimaryDark);
        gr.setHorizontalLabelsColor(R.color.chenPrimaryDark);

        //set contents
        //TODO: set profile_pic, date, minute, patient_type, depth_accuracy, frequency_accuracy, compressions, breaths

        //set data points, create an ArrayList
        ArrayList<DataPoint> data = new ArrayList<DataPoint>();
        if (c_depthTimes.moveToFirst()) {
            while (c_depthTimes.isAfterLast() == false) {
                //TODO: I don't know the format of the time and depth
//                final String time = c_depthTimes.getString(c_depthTimes.getColumnIndexOrThrow(DepthTimes.COLUMN_TIME));
//                final String depth = c_depthTimes.getString(c_depthTimes.getColumnIndexOrThrow(DepthTimes.COLUMN_DEPTH));
//                data.add(new DataPoint(time, depth));
                c_depthTimes.moveToNext();
            }
        }

        //convert the arraylist to an Array, and add to LineGraphSeries
        DataPoint[] dataArr = new DataPoint[data.size()];
        dataArr = data.toArray(dataArr);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataArr);
        graph.addSeries(series);

        //Clear database
        db.delete(DepthTimes.TABLE_NAME, null, null);
        db.delete(Entries.TABLE_NAME, null, null);
        db.execSQL("DROP TABLE IF EXISTS " + DepthTimes.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Entries.TABLE_NAME);
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
