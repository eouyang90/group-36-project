package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.TextView;

/**
 * Created by austinhle on 4/15/16.
 */
public class HistoryMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main_activity);

        ImageButton startCPRButton = (ImageButton) findViewById(R.id.historyBackground);
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

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final HistoryDbHelper mDbHelper = new HistoryDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Entries.COLUMN_NAME_ENTRY_ID,
                Entries.COLUMN_NAME_ELTIME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Entries.COLUMN_NAME_ENTRY_ID + " DESC";

        Cursor c = db.query(
                Entries.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();
        String itemId = c.getString(
                c.getColumnIndexOrThrow(Entries.COLUMN_NAME_ELTIME)
        );

        final TextView text = (TextView) findViewById(R.id.text);
        text.setText(itemId);
    }
     */

    /*

     */

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
