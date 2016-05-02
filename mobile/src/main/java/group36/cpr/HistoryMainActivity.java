package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * Created by austinhle on 4/15/16.
 */
public class HistoryMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main_activity);

        LayoutInflater inflater = getLayoutInflater();

        /*
        final HistoryDbHelper mDbHelper = new HistoryDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Entries.COLUMN_DATETIME_ID,
                Entries.COLUMN_ELTIME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Entries.COLUMN_DATETIME_ID + " DESC";

        Cursor c = db.query(
                Entries.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        LinearLayout main = (LinearLayout) findViewById(R.id.history_body);



        if (c.moveToFirst()) {

            while (c.isAfterLast() == false) {
                final String time = c.getString(c.getColumnIndexOrThrow(Entries.COLUMN_DATETIME_ID));
                View view = inflater.inflate(R.layout.activity_entry, null);
                TextView text = (TextView) view.findViewById(R.id.text);
                text.setText(time);
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), HistoryDetailedActivity.class);
                        intent.putExtra("time", time);
                        startActivity(intent);
                    }
                });
                main.addView(view);
                c.moveToNext();
            }
        }

        //***REMOVE ON FINAL PUSH***
        //db.delete deletes all the rows of the table but keeps the table:
        db.delete(Entries.TABLE_NAME, null, null);
        //dropping the table removes the table itself, but the db remains:
        //db.execSQL("DROP TABLE IF EXISTS " + Entries.TABLE_NAME);
        */

        //***REMOVE BELOW AFTER DB***
        ImageView startCPRButton = (ImageView) findViewById(R.id.historyBackground);
        startCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent;
                sendIntent = new Intent(getBaseContext(), HistoryDetailedActivity.class);
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
