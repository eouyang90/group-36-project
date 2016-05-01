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
import android.widget.TextView;

public class SampleFragment extends Fragment {
    private String selection = "x";
    private String text1 = "x";
    private String text2 = "x";
//    private String text4 = "x";
    TextView T11;
    TextView T12;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_activity_2, container,
                false);
        TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======",selection);

        T11 = (TextView) rootView.findViewById(R.id.T11);
        T12 = (TextView)rootView.findViewById(R.id.T12);
        if (selection.equals("Adult")) {
            text1 = "Use two hands -";
            text2 = "Put one on the other in the center of chest.\n(on lower half of sternum)";
        } else if (selection.equals("Child")) {
            text1 = "Use two hands -";
            text2 = "Put one on the other in the center of chest.\n(on lower half of sternum)";
        } else {
            text1 = "Use two Fingers -";
            text2 = "Put one on the other in the center of chest.\n(on lower half of sternum)";
        }
        T11.setText(text1);
        T12.setText(text2);


        return rootView;
    }


}