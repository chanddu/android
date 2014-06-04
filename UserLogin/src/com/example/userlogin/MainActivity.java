package com.example.userlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText ed1,ed2;
	Button bt1,bt2;
	Cursor cs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1 = (Button) findViewById(R.id.signup);
		bt2 = (Button) findViewById(R.id.login);
		ed1 = (EditText) findViewById(R.id.userid);
		ed2 = (EditText) findViewById(R.id.password);
	}

	public void myClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.signup:
			Intent it1 = new Intent(this,Sign.class);
			startActivity(it1);
			break;
		case R.id.login:
			try{
				String un = ed1.getText().toString();
				String pass = ed2.getText().toString();
				SQLiteDatabase sqd = openOrCreateDatabase("userlog", 0, null);
				cs = sqd.rawQuery("select * from signup where uname = '"+un+"' and pass = '"+pass+"'", null);
				if(cs.getCount()>0){
					Intent it2 = new Intent(this,Log.class);
					it2.putExtra("name", un);
					it2.putExtra("paswd", pass);
					startActivity(it2);
					
				}
				else{
					Toast.makeText(this, "Invalid User", 1000).show();
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

}
