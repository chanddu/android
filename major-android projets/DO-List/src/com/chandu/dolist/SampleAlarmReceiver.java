package com.chandu.dolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

@SuppressLint("SimpleDateFormat")
public class SampleAlarmReceiver {

	private AlarmManager alarmMgr;
	
	private PendingIntent alarmIntent;
	int notificationCount;	
	public void setAlarm(Context context,String dueTime){
		notificationCount = notificationCount+1;
		alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SampleSchedulingService.class);
        alarmIntent = PendingIntent.getBroadcast(context, notificationCount, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh-mm");  
        Date date = null;
        try {
			date=df.parse(dueTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long endTime = date.getTime();
        
        alarmMgr.set(AlarmManager.RTC_WAKEUP, endTime, alarmIntent);
        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        
	}

}
