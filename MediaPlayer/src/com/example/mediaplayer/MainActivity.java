package com.example.mediaplayer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.VideoView;

public class MainActivity extends Activity {

	VideoView vv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vv = (VideoView) findViewById(R.id.videoView1);
		vv.setVideoURI(Uri.parse("android.resource://com.example.mediaplayer/" + R.raw.zuck));
		vv.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
