package com.chandu.dolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SampleSchedulingService extends BroadcastReceiver{

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String note = "You Still Have a Pending Task";
		String head = "Note";
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification n = new Notification(android.R.drawable.stat_notify_chat, "Notification", System.currentTimeMillis());
		Intent i = new Intent(context.getApplicationContext(),MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, i, 1);
		n.setLatestEventInfo(context, head, note, pi);
		n.defaults = Notification.DEFAULT_SOUND;
		nm.notify(0, n);
		
	}



}
