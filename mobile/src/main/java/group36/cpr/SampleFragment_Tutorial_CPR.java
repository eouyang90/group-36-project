package group36.cpr;

/**
 * Created by TIANBI on 4/28/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SampleFragment_Tutorial_CPR extends Fragment {
    TextView T3;
    private String text1;
    Button try_compression;
    private String selection = "Adult";
    private String cr = "x";
    private String cd = "x";
    private String br = "x";
    private String bd = "x";
    private Boolean state = false;
    private String watchMode = "defaultMode";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tutorial_cpr_practice, container,
                false);

        final TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======", selection);
//        try_compression = (Button)rootView.findViewById(R.id.try_compression);
//        try_compression.setText("Try " + selection + " CPR On Watch");
//        try_compression.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(rootView.getContext(), tutorial_cpr.class);
//                sendIntent.putExtra("selection", selection);
//                Log.d("tutorial_cpr", "Selected" + selection);
//                startActivity(sendIntent);
//            }
//        });


        //set texts
        TextView compression_reps = (TextView)rootView.findViewById(R.id.compression_reps_t);
        TextView compression_desc = (TextView)rootView.findViewById(R.id.compression_desc_t);
        TextView breath_rep = (TextView)rootView.findViewById(R.id.breath_rep_t);
        TextView breath_desc = (TextView)rootView.findViewById(R.id.breath_desc_t);
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
        final Button start_CPR_button = (Button)rootView.findViewById(R.id.startCPR_t);
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
                    Intent watchIntent = new Intent(rootView.getContext(), PhoneToWatchService.class);
                    watchIntent.putExtra("mode", watchMode);
                    activity.startService(watchIntent);
                    Log.d("start watch", watchMode);
                    Toast toast = Toast.makeText(rootView.getContext(), "The CPR cycle is started\non your watch!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else {
                    state = false;
                    //stop watch
                    watchMode = "CPR_stop";
                    Intent watchIntent = new Intent(rootView.getContext(), PhoneToWatchService.class);
                    watchIntent.putExtra("mode", watchMode);
                    activity.startService(watchIntent);
                    Log.d("stop watch", "watch stop");

                    //TODO: now automatically go to history detail page, should be triggered by watch
                    Intent sendIntent = new Intent(rootView.getContext(), TutorialActivity2.class);
                    sendIntent.putExtra("selection", selection);
                    startActivity(sendIntent);
                }
            }
        });


        return rootView;
    }
}