package com.chemwater.week5day3hwreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "BROADCAST_INPUT_TAG" ;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null) {
            String receivedName = intent.getStringExtra("name") ;
            String receivedNumber = intent.getStringExtra("number") ;
            String receivedInventory = intent.getStringExtra("inventory") ;
            String receivedDescription = intent.getStringExtra("description") ;

            if(receivedName != null && receivedNumber != null && receivedInventory != null && receivedDescription != null) {
                Log.d(TAG, "onReceive: " + receivedName) ;
                Log.d(TAG, "onReceive: " + receivedNumber) ;
                Log.d(TAG, "onReceive: " + receivedInventory) ;
                Log.d(TAG, "onReceive: " + receivedDescription) ;

            }
        }
    }
}