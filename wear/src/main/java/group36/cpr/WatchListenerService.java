package group36.cpr;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by austinhle on 4/15/16.
 */
public class WatchListenerService extends WearableListenerService {
    public static final String DATA = "group36.cpr.DATA";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        String data = new String(messageEvent.getData(), StandardCharsets.UTF_8);

        Intent i = getApplicationContext().getPackageManager()
                .getLaunchIntentForPackage(getApplicationContext().getPackageName() );

        // The Intent.FLAG_ACTIVITY_CLEAR_TOP flag restarts the activity completely from scratch.
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra(DATA, data);
        Log.d("WatchListenerService", "message");
        startActivity(i);
    }
}
