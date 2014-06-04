package com.example.userlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Log extends Activity {

	TextView tv;
	String s;
	String pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		tv = (TextView) findViewById(R.id.textView1);
		Intent i = getIntent();
		s = i.getStringExtra("name");
		pwd = i.getStringExtra("paswd");
		tv.append(s);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	public void myclick(View v){
		Intent ity = new Intent(this,MainActivity.class);
		startActivity(ity);
	}
	public void changeclick(View v){
		Intent itz = new Intent(this,Changep.class);
		itz.putExtra("nam", pwd);
		itz.putExtra("unam", s);
		startActivity(itz);
	}

}
