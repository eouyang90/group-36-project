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
            text1 = "Two fingers from each hand \n" +
                    "Place fingers from one hand directly on top of that from the other";
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