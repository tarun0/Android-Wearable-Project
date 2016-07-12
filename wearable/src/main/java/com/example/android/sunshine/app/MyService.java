package com.example.android.sunshine.app;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by Tarun on 13/07/2016.
 */
public class MyService extends WearableListenerService {
    public MyService() {
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equals("/test")) {
            Log.e("DATA Received", messageEvent.getData().toString());
            String str = new String(messageEvent.getData());
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra("message", str);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
        else super.onMessageReceived(messageEvent);
    }
}