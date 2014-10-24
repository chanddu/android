package com.example.userlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Changep extends Activity {

	EditText ed1,ed2;
	Cursor cs;
	String u,p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changep);
		ed1 = (EditText) findViewById(R.id.currentp);
		ed2 = (EditText) findViewById(R.id.newp);
		Intent i = getIntent();
		p = i.getStringExtra("nam");
		u = i.getStringExtra("unam");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.changep, menu);
		return true;
	}
	
	public void myclick(View v){
		String s = ed1.getText().toString().trim();
		String np = ed2.getText().toString().trim();
		SQLiteDatabase sqd = openOrCreateDatabase("userlog", 0, null);
		try{
			if(s.equals(p)){
				sqd.execSQL("update signup set pass = '"+np+"' where uname = '"+u+"'");
				Intent it = new Intent(this,MainActivity.class);
				startActivity(it);
			}
			else{
				Toast.makeText(this, "please enter the previous password correctly", 1000).show();
			}
		}
		catch(Exception e){
			Toast.makeText(this, e.toString(),1000).show();
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

}
