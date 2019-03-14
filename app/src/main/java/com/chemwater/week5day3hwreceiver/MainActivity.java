package com.chemwater.week5day3hwreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.IntentFilter ;

public class MainActivity extends AppCompatActivity {

    public static final String SEND_BROADCAST = "com.chemwater.week5day3hw.SEND_BROADCAST" ;

    MyBroadcastReceiver myBroadcastReceiver ;
    IntentFilter intentFilter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver = new MyBroadcastReceiver() ;
        intentFilter = new IntentFilter( ) ;
        intentFilter.addAction(SEND_BROADCAST) ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(myBroadcastReceiver, intentFilter ) ;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(myBroadcastReceiver) ;
    }
}


/*
Develop 2 applications:
         App 1:
            Have the user enter the following information:
                      ProductName
                      ProductIDNumber
                      ProductInventoryCount
                      ProductDescription
              Have a button that will send the above info to App2 using a broadcast reciever
              Restrict broadcast so only the two apps can use that broadcast
            App2:
                Have a database that will hold the info sent from app1 and display all the info in a recyclerView.
                If the device is a tablet, show the description else do not show the description.
                If the device is a tablet, use font size 28sp for the Name, else use 18sp
 */