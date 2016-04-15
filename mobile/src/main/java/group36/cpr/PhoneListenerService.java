package group36.cpr;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by austinhle on 4/15/16.
 */
public class PhoneListenerService extends WearableListenerService {
    public static final String START_PATH = "/start";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("PhoneListenerService", "Path: " + messageEvent.getPath());

        if (messageEvent.getPath().equalsIgnoreCase(START_PATH)) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            // Replace below with actual Activity that we want to start.
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Put extras into Intent as needed.

            startActivity(intent);
        }
        // Put other potential paths here.
    }
}
