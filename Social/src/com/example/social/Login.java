package com.example.social;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Login extends Activity {
	CheckBox cb1,cb2,cb3,cb4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		cb1 = (CheckBox) findViewById(R.id.checkBox1);
		cb2 = (CheckBox) findViewById(R.id.checkBox2);
		cb3 = (CheckBox) findViewById(R.id.checkBox3);
		cb4 = (CheckBox) findViewById(R.id.checkBox4);
	}
	
	public void myclick(View v){
		if(v.getId()== R.id.button1){
			Toast.makeText(this, "Sorry next question unavailable", Toast.LENGTH_LONG).show();
		}
		else if(v.getId() == R.id.button2){
			Intent it2 = new Intent(this,MainActivity.class);
			finish();
			startActivity(it2);
		}
		else if(cb3.isChecked()&&!cb4.isChecked()&&!cb2.isChecked()&&!cb1.isChecked()){
			Toast.makeText(this, "Partially Correct try better", Toast.LENGTH_SHORT).show();
		}
		else if(cb4.isChecked()&&!cb3.isChecked()&&!cb2.isChecked()&&!cb1.isChecked()){
			Toast.makeText(this, "Partially Correct try better", Toast.LENGTH_SHORT).show();
		}
		else if(cb3.isChecked()&&cb4.isChecked()&&!cb2.isChecked()&&!cb1.isChecked()){
			Toast.makeText(this,"Correct answer",Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(this,"InCorrect answer",Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	

}
