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

public class SampleFragmentFour extends Fragment {
    private String selection;
    TextView T21;
    TextView T22;
    private String text1;
    private String text2;
    ImageView age_6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tutorial_activity_5, container,
                false);
        TutorialActivity2 activity = (TutorialActivity2) getActivity();
        selection = activity.getMyData();
        Log.d("Data Passed =======", selection);
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
        age_6 = (ImageView)rootView.findViewById(R.id.age_6);
        if (selection.equals("Adult")) {
            age_6.setImageResource(R.drawable.adult);
        } else if (selection.equals("Child")) {
            age_6.setImageResource(R.drawable.child);
        } else {
            age_6.setImageResource(R.drawable.infant);
        }
//        T21.setText(text1);
//        T22.setText(text2);
        return rootView;
    }
}