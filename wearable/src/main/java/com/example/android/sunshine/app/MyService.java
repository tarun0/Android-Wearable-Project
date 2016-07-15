package com.example.android.sunshine.app;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by Tarun on 13/07/2016.
 */
public class MyService extends WearableListenerService {
    public MyService() {
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {

        DataMap dataMap;
        for (DataEvent event: dataEvents) {
           // if (event.getType() == DataEvent.TYPE_CHANGED) {
                String path = event.getDataItem().getUri().getPath();
                if (path.equals("/test")) {
                    dataMap = DataMapItem.fromDataItem(event.getDataItem()).getDataMap();
                    Log.e("Data Item received", dataMap.toString());
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra("test", dataMap.toBundle());

                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                }
            //}
        }
    }

    /*@Override
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
    }*/
}