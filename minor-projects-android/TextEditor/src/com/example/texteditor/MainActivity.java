package com.example.texteditor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements OnItemClickListener{

	private static final File[]  NULL = null;
	private ArrayList<String>  allfiles = new ArrayList<String>();
	String[] extensions = {".txt"};
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		try{
			File f = Environment.getExternalStorageDirectory();
			String root = f.getPath();
			loadAllFiles(root);
			ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allfiles);
			setListAdapter(ad);
			lv = getListView();
			lv.setOnItemClickListener(this);
		}
		catch(Exception e){
			Toast.makeText(this, e.toString(), 500).show();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creatnew, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getTitle().toString().equals("exit")){
			finish();
		}
		else if(item.getTitle().toString().equals("newfile")){
			Intent it = new Intent(this,Crenew.class);
			startActivity(it);
		}
		return super.onOptionsItemSelected(item);
	}

	private void loadAllFiles(String rootfolder){
		File file = new File(rootfolder);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			if(files!= NULL && files.length>0){
				for(File f:files){
					if(f.isDirectory()){
						loadAllFiles(f.getAbsolutePath());
					}
					else{
						for(int i=0;i<extensions.length;i++){
							if(f.getAbsolutePath().endsWith(extensions[i])){
								allfiles.add(f.getAbsolutePath());
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		try{
			String path = allfiles.get(arg2);
			File f = new File(path);
			int a = 0;
			FileInputStream fis = new FileInputStream(f);
			Scanner sc = new Scanner(fis);
			String content = "";
			while((a = fis.read())>-1){
				content +=  (char)a + "";
			}
			Intent it = new Intent(this,EdiAct.class);
			it.putExtra("content", content);
			it.putExtra("path", path);
			startActivity(it);
			sc.close();
			fis.close();
		}
		catch(Exception e){
			Toast.makeText(this, e.toString(), 500).show();
		}
	}

}
