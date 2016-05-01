package group36.cpr;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Created by austinhle on 4/15/16.
 */
public class TutorialActivity2 extends FragmentActivity {
    private String selection = "Adult";

    private Boolean state = false;
    private String watchMode = "defaultMode";
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        Intent intent = getIntent();
        selection = intent.getStringExtra("selection"); //adult, child, infant




        mViewPager = (ViewPager) findViewById(R.id.pager);
        /** set the adapter for ViewPager */
        mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));
    }

    public String getMyData() {
        return selection;
    }

    /** Defining a FragmentPagerAdapter class for controlling the fragments to be shown when user swipes on the screen. */
    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                return new SampleFragment();
            } else if(position == 1) {
                return new SampleFragmentOne();
            }else if (position == 2) {
                return new SampleFragmentTwo();
            }else{
                return new SampleFragmentThree();

            }
        }

        @Override
        public int getCount() {
            // Show 4 total tutorial pages.
            return 4;
        }
    }

//        ImageButton startCPRButton = (ImageButton) findViewById(R.id.tutorial2);
//        startCPRButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent;
//                sendIntent = new Intent(getBaseContext(), TutorialActivity4.class);
//                Log.d("MainActivity", "Starting up StartCPRActivity1");
//                startActivity(sendIntent);
//            }
//        });
//
//        Button myHistoryButton = (Button) findViewById(R.id.try_compression);
//        myHistoryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //start watch
//                Intent watchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                watchIntent.putExtra("mode", "try_compression");
//                startService(watchIntent);
//            }
//        });


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
