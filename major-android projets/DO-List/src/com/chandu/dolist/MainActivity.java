package com.chandu.dolist;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

@SuppressWarnings("unused")
public class MainActivity extends ActionBarActivity {

	ListView lv;
	Cursor cs;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);
		ArrayList list = mDbHelper.getAllNotes();
		ArrayAdapter<String> ar = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
		lv = (ListView) findViewById(R.id.listView1);
		ar.notifyDataSetChanged();
		lv.setAdapter(ar);
			lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				int id = position +1;
				Bundle dataBundle = new Bundle();
				dataBundle.putInt("id", id);
				Intent it = new Intent(getApplicationContext(),DisplayNote.class);
				it.putExtras(dataBundle);
				startActivity(it);
			}
		});
			
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		if(id == R.id.action_newsth){
			Intent i = new Intent(this,AddNew.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
	

}
