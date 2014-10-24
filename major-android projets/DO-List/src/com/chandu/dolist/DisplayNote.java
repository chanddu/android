package com.chandu.dolist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

@SuppressWarnings("unused")
public class DisplayNote extends Activity {

	EditText ed1,ed2;
	String title,note;
	private FeedReaderDbHelper mDbHelper; 
	int UPDATE_ID=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		mDbHelper = new FeedReaderDbHelper(this);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			int id = extras.getInt("id");
			if(id>0){
				Cursor rs = mDbHelper.getData(id);
				UPDATE_ID = id;
				rs.moveToFirst();
				title = rs.getString(rs.getColumnIndex(FeedReaderDbHelper.LIST_COLUMN_TITLE));
				note = rs.getString(rs.getColumnIndex(FeedReaderDbHelper.LIST_COLUMN_NOTE));
				ed1.setText(title);
				ed2.setText(note);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
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
		if(id == R.id.action_delete){
			FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
			mDbHelper.deleteNote(UPDATE_ID);
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		String _title = ed1.getText().toString().trim();
		String _note = ed2.getText().toString().trim();
		mDbHelper.updateNote(UPDATE_ID, _title, _note);
			
	}

}
