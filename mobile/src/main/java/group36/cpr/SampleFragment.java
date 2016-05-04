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

public class SampleFragment extends Fragment {
    private String selection = "x";
    private String text1 = "x";
    private String text2 = "x";
//    private String text4 = "x";
    TextView T21;
    TextView T22;
    ImageView age_2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_activity_2, container,
                false);
        TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======",selection);

        T21 = (TextView) rootView.findViewById(R.id.T21);
//        T22 = (TextView)rootView.findViewById(R.id.T22);
        if (selection.equals("Adult")) {
            text1 = "Place one hand directly on top of the other";
        } else if (selection.equals("Child")) {
            text1 = "Place one hand directly on top of the other";
        } else {
            text1 = "Use the pads of two or three fingers of your other hand"
                    + " to give chest compressions on the center of the chest"
                    + " just below the nipple line.";

            ImageView img21 = (ImageView) rootView.findViewById(R.id.image_21);
            img21.setImageResource(R.drawable.infant_position);
            TextView t22 = (TextView) rootView.findViewById(R.id.T22);
            t22.setText("If you feel the notch at the end of the infant’s sternum,"
                    + " move your fingers slightly toward the infant’s head");

            TextView t23 = (TextView) rootView.findViewById(R.id.T23);
            t23.setText("");
            TextView t24 = (TextView) rootView.findViewById(R.id.T24);
            t24.setText("");
            TextView t25 = (TextView) rootView.findViewById(R.id.T25);
            t25.setText("");

            ImageView img22 = (ImageView) rootView.findViewById(R.id.image_22);
            img22.setVisibility(View.INVISIBLE);
            ImageView img23 = (ImageView) rootView.findViewById(R.id.image_23);
            img23.setVisibility(View.INVISIBLE);
            ImageView img24 = (ImageView) rootView.findViewById(R.id.image_24);
            img24.setVisibility(View.INVISIBLE);
        }
        T21.setText(text1);

        age_2 = (ImageView)rootView.findViewById(R.id.age_2);
        if (selection.equals("Adult")) {
            age_2.setImageResource(R.drawable.adult);
        } else if (selection.equals("Child")) {
            age_2.setImageResource(R.drawable.child);
        } else {
            age_2.setImageResource(R.drawable.infant);
        }

        return rootView;
    }


}