package group36.cpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

//        ImageView startCPRButton = (ImageView) findViewById(R.id.history_detail);
//        startCPRButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent;
//                sendIntent = new Intent(getBaseContext(), MainActivity.class);
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
