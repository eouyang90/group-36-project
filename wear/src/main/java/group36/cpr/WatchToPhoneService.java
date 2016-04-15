package group36.cpr;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by austinhle on 4/15/16.
 */
public class WatchToPhoneService extends Service {
    private GoogleApiClient mApiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                    }
                })
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mApiClient.disconnect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /* Placeholder example code below. Replace with what we actually want to pass around. */
        Bundle extras = intent.getExtras();
        final String name = extras.getString("");
        final String random = extras.getString("");

        if (name != null) {
            Log.d("WatchToPhoneService", "Retrieved NAME = " + name + ", sending to phone\n");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mApiClient.connect();
                    sendMessage("", name);
                }
            }).start();
        } else if (random != null) {
            Log.d("WatchToPhoneService", "Retrieved random signal, sending to phone\n");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    mApiClient.connect();
                    sendMessage("/some_endpoint", random);
                }
            }).start();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendMessage( final String path, final String text ) {
        new Thread( new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mApiClient ).await();
                for(Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mApiClient, node.getId(), path, text.getBytes() ).await();
                }
            }
        }).start();
    }
}
