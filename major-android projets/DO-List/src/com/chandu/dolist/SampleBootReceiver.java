package com.chandu.dolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SampleBootReceiver extends BroadcastReceiver {

	SampleAlarmReceiver alarm = new SampleAlarmReceiver();
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            alarm.setAlarm(context,"");
        }
	}

}
