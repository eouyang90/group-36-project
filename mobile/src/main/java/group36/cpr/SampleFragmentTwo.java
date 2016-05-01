package group36.cpr;

/**
 * Created by TIANBI on 4/28/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SampleFragmentTwo extends Fragment {
    private String selection;
    TextView T3;
    private String text1;
    Button try_compression;
    private Boolean state = false;
    private String watchMode = "defaultMode";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tutorial_activity_3, container,
                false);

        TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======", selection);
        try_compression = (Button)rootView.findViewById(R.id.try_compression);
        try_compression.setText("Try " + selection + " CPR On Watch");
        try_compression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(rootView.getContext(), tutorial_cpr.class);
                sendIntent.putExtra("selection", selection);
                Log.d("tutorial_cpr", "Selected" + selection);
                startActivity(sendIntent);
            }
        });


        T3 = (TextView) rootView.findViewById(R.id.T3);
        if (selection.equals("Adult")) {
            text1 = "30 chest compressions \nand \n2 rescue breath";
        } else if (selection.equals("Child")) {
            text1 = "30 chest compressions \nand \n2 rescue breath";
        } else {
            text1 = "2 rescue breath \nand \n30 chest compressions ";
        }
        T3.setText(text1);

        return rootView;
    }
}