package com.example.quiz;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {

	CheckBox cb1,cb2,cb3,cb4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cb1 = (CheckBox) findViewById(R.id.checkBox1);
		cb2 = (CheckBox) findViewById(R.id.checkBox2);
		cb3 = (CheckBox) findViewById(R.id.checkBox3);
		cb4 = (CheckBox) findViewById(R.id.checkBox4);
		
	}
	public void myclick(View v){
		if(cb3.isChecked()&&!cb4.isChecked()&&!cb2.isChecked()&&!cb1.isChecked()){
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
