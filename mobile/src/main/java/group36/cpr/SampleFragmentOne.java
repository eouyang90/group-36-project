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

public class SampleFragmentOne extends Fragment {
    private String selection;
    TextView T31;
    TextView T22;
    private String text1;
    private String text2;
    ImageView age_3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_activity_4, container,
                false);
        TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======", selection);
        age_3 = (ImageView)rootView.findViewById(R.id.age_3);
        if (selection.equals("Adult")) {
            age_3.setImageResource(R.drawable.adult);
        } else if (selection.equals("Child")) {
            age_3.setImageResource(R.drawable.child);
        } else {
            age_3.setImageResource(R.drawable.infant);
        }
        T31 = (TextView) rootView.findViewById(R.id.T31);
//        T22 = (TextView)rootView.findViewById(R.id.T22);
        if (selection.equals("Adult")) {
            text1 = "Push the sternum down 2 inches in a smooth, and not jerky, motion";
//            text2 = "Make sure breathing pathway is clear.\nPull the chin up. \nBreath in until the chest clearly rises. \n(about 1 second per breath)";
        } else if (selection.equals("Child")) {
            text1 = "Push the sternum down 2 inches in a smooth, and not jerky, motion";
////            text2 = "Make sure breathing pathway is clear.\n" +
//                    "Pull the chin up. \n" +
//                    "Breath in until the chest clearly rises. \n(about 1 second per breath)";;
        } else {
            text1 = "Push the sternum down 1.5 inches in a smooth, and not jerky, motion";
//            text2 = "Make sure breathing pathway is clear.\n" +
//                    "Pull the chin up. \n" +
//                    "Breath in until the chest clearly rises. \n(about 1 second per breath)";;
        }
        T31.setText(text1);
//        T22.setText(text2);
        return rootView;
    }
}