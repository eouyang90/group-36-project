package group36.cpr;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by austinhle on 4/15/16.
 */
public class WatchListenerService extends WearableListenerService {
    public static final String DATA = "group36.cpr.DATA";
    private static final String MODE = "/mode";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if( messageEvent.getPath().equalsIgnoreCase( MODE ) ) {
            String mode = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            if (mode.equals("CPR_start")) {
                Intent i = new Intent(this, CompressionActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } else if (mode.equals("CPR_stop")) {
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } else if (mode.equals("try_compression")) {
                Intent i = new Intent(this, CompressionActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            } else if (mode.equals("try_breath")) {
                Intent i = new Intent(this, BreathActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }
    }
}
