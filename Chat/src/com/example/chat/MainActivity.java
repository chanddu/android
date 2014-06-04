package com.example.chat;

import java.io.ObjectOutputStream.PutField;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText ed1,ed2;
	String message,number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void myclick(View v){
		message = ed1.getText().toString().trim();
		number = ed2.getText().toString().trim();
		Intent i = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:"+number));
		i.putExtra("sms_body", message);
		startActivity(i);
	}

}
