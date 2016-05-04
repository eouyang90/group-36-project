package group36.cpr;

/**
 * Created by TIANBI on 4/28/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class SampleFragmentPositioning extends Fragment {
    private String selection;
    TextView T21;
    TextView T22;
    private String text1;
    private String text2;
    ImageView age_1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tutorial_activity_positioning, container,
                false);
        TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======", selection);
        age_1 = (ImageView)rootView.findViewById(R.id.age_1);
        if (selection.equals("Adult")) {
            age_1.setImageResource(R.drawable.adult);
        } else if (selection.equals("Child")) {
            age_1.setImageResource(R.drawable.child);
        } else {
            age_1.setImageResource(R.drawable.infant);

            TextView t14 = (TextView) rootView.findViewById(R.id.T14);
            t14.setText("Kneel beside the infant's upper chest. Place the pads of two fingers"
                    + " on the center of the infant's chest, just below the nipple line.");
            ImageView img14 = (ImageView) rootView.findViewById(R.id.img14);
            img14.setImageResource(R.drawable.infant_position);
            TextView t15 = (TextView) rootView.findViewById(R.id.T15);
            t15.setText("Keep one hand on the infant's forehead to maintain an open airway");
            ImageView img15 = (ImageView) rootView.findViewById(R.id.img15);
            img15.setVisibility(View.INVISIBLE);
            TextView t16 = (TextView) rootView.findViewById(R.id.T16);
            t16.setText("");
            ImageView img16 = (ImageView) rootView.findViewById(R.id.img16);
            img16.setVisibility(View.INVISIBLE);
        }

//        try_breath = (Button)rootView.findViewById(R.id.try_breath);
//        try_breath.setText("Try " + selection + " CPR On Watch");
//        try_breath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(rootView.getContext(), tutorial_cpr.class);
//                sendIntent.putExtra("selection", selection);
//                Log.d("tutorial_cpr", "Selected" + selection);
//                startActivity(sendIntent);
//            }
//        });
//
//        T21 = (TextView) rootView.findViewById(R.id.T21);
//        T22 = (TextView)rootView.findViewById(R.id.T22);
//        if (selection.equals("Adult")) {
//            text1 = "At least 2 inches";
//            text2 = "Until the chest clearly rises. \n (about 1 second per breath)";
//        } else if (selection.equals("Chind")) {
//            text1 = "About 2 inches";
//            text2 = "Until the chest clearly rises. \n (about 1 second per breath)";;
//        } else {
//            text1 = "About 1.5 inches";
//            text2 = "Until the chest clearly rises. \n (about 1 second per breath)";;
//        }
//        T21.setText(text1);
//        T22.setText(text2);
        return rootView;
    }
}