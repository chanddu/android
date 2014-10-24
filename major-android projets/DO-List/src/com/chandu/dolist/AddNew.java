package com.chandu.dolist;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("unused")
@SuppressLint("ShowToast")
public class AddNew extends ActionBarActivity {

	Button b1,b2,b3;
	EditText ed1,ed2;
	//Cursor c;
	static ArrayList<String> titlelog = new ArrayList<String>();
	static ArrayList<String> notelog = new ArrayList<String>();
	SampleAlarmReceiver alarm = new SampleAlarmReceiver();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b1.setBackgroundResource(R.drawable.ic_action_add_alarm);
		b2.setVisibility(View.INVISIBLE);
		b3.setVisibility(View.INVISIBLE);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		String _title = ed1.getText().toString().trim();
		String _note = ed2.getText().toString().trim();
		//SQLiteDatabase sqd = openOrCreateDatabase("notelog", 0, null);
		//sqd.execSQL("create table if not exists addnote (title VARCHAR,note VARCHAR);");
		if(_title.equals("")&&_note.equals("")){
			Toast.makeText(getApplicationContext(), "Empty Note Discarded", 200).show();
		}
		else if(_title.equals("")){
			Toast.makeText(getApplicationContext(), "Title  Not Set", 200).show();
		}
		else{
			//c = sqd.rawQuery("select * from addnote where title='"+_title+"' and note='"+_note+"'", null);
			//if(c.getCount()>0){
				
			//}
			//else{
				/*sqd.execSQL("insert into addnote values ('"+_title+"','"+_note+"')");
				titlelog.add(_title);
				notelog.add(_note);
				sqd.close();*/
		//	}
			String dueTime = DatePickerFragment._date+" "+TimePickerFragment._time;
			FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this) ;
			mDbHelper.insertNote(_title, _note,dueTime);
			Toast.makeText(getApplicationContext(), dueTime, 200).show();
			alarm.setAlarm(getApplicationContext(),dueTime);
			
			
		}
		
	}
	
	public void alarm(View v){
		/*final AlertDialog dialogDetails;
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		View dialogview = inflater.inflate(R.layout.customdialog_view, null);
		AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
		dialogbuilder.setTitle("Set Time");
		dialogbuilder.setNegativeButton("Ok", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialogbuilder.setIcon(R.drawable.ic_action_add_alarm);
		dialogbuilder.setView(dialogview);
		dialogDetails = dialogbuilder.create();
		dialogDetails.show();*/
		switch(v.getId()){
		case R.id.button1:
			b2.setVisibility(View.VISIBLE);
			b3.setVisibility(View.VISIBLE);
			break;
		case R.id.button2:
			DialogFragment dateFragment = new DatePickerFragment();
		    dateFragment.show(getFragmentManager(), "datePicker");
			break;
		case R.id.button3:
			DialogFragment timeFragment = new TimePickerFragment();
		    timeFragment.show(getFragmentManager(), "timePicker");
		    break;
		}
	}
	

}
