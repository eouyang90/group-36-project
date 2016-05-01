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

public class SampleFragmentOne extends Fragment {
    private String selection;
    TextView T21;
    TextView T22;
    private String text1;
    private String text2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_activity_4, container,
                false);
        TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======", selection);

        T21 = (TextView) rootView.findViewById(R.id.T21);
        T22 = (TextView)rootView.findViewById(R.id.T22);
        if (selection.equals("Adult")) {
            text1 = "Depth : At least 2 inches.\nCompress down and release.";
            text2 = "Make sure breathing pathway is clear.\nPull the chin up. \nBreath in until the chest clearly rises. \n(about 1 second per breath)";
        } else if (selection.equals("Child")) {
            text1 = "Depth : About 2 inches\n" +
                    "Compress down and release.";
            text2 = "Make sure breathing pathway is clear.\n" +
                    "Pull the chin up. \n" +
                    "Breath in until the chest clearly rises. \n(about 1 second per breath)";;
        } else {
            text1 = "Depth : About 1.5 inches\n" +
                    "Compress down and release.";
            text2 = "Make sure breathing pathway is clear.\n" +
                    "Pull the chin up. \n" +
                    "Breath in until the chest clearly rises. \n(about 1 second per breath)";;
        }
        T21.setText(text1);
        T22.setText(text2);
        return rootView;
    }
}