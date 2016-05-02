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

public class SampleFragmentThree extends Fragment {
    private String selection;
    TextView T51;
    TextView T22;
    private String text1;
    private String text2;
    ImageView age_5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tutorial_activity_3, container,
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
//        age_5 = (ImageView)rootView.findViewById(R.id.age_5);
//        T51 = (TextView) rootView.findViewById(R.id.T51);
//        if (selection.equals("Adult")) {
//            text1 = "30 chest compressions \nand \n2 rescue breath";
//            age_5.setImageResource(R.drawable.adult);
//        } else if (selection.equals("Child")) {
//            text1 = "30 chest compressions \nand \n2 rescue breath";
//            age_5.setImageResource(R.drawable.child);
//        } else {
//            text1 = "2 rescue breath \nand \n30 chest compressions ";
//            age_5.setImageResource(R.drawable.infant);
//        }
//        T51.setText(text1);
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