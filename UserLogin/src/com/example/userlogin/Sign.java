package com.example.userlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign extends Activity {

	EditText ed1,ed2,ed3;
	Button bt;
	Cursor cs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
		ed1 = (EditText) findViewById(R.id.usn);
		ed2 = (EditText) findViewById(R.id.pass1);
		ed3 = (EditText) findViewById(R.id.pass2);
		bt = (Button) findViewById(R.id.button1);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
	public void myclick(View v){
		String un = ed1.getText().toString();
		String pass = ed2.getText().toString();
		String repass = ed3.getText().toString();
		if(pass.equals(repass)){
			
			try{
				SQLiteDatabase sqd = openOrCreateDatabase("userlog", 0, null);
				sqd.execSQL("create table if not exists signup (uname varchar(10),pass varchar(8));");
				cs = sqd.rawQuery("select * from signup where uname = '"+un+"' and pass = '"+pass+"'", null);
				if(cs.getCount()>0){
					Toast.makeText(this, "already registered", 1000).show();
				}
				else{
					sqd.execSQL("insert into signup values ('"+un+"','"+pass+"')");
					Intent itx = new Intent(this,MainActivity.class);
					startActivity(itx);
				}
			}
			catch(Exception e){
				Toast.makeText(this, e.toString(), 1000).show();
			}
		}
	}

}
