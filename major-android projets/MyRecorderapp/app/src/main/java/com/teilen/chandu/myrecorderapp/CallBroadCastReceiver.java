package com.teilen.chandu.myrecorderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by CHANDU on 24/10/14.
 */
public class CallBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(TelephonyManager.EXTRA_STATE_RINGING.equals(state)||TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)){
            Toast.makeText(context, "Recording ur call", Toast.LENGTH_SHORT).show();
            context.startService(new Intent(context, RecordService.class));
        }
        else{
            context.stopService(new Intent(context,RecordService.class));
        }
    }
}
