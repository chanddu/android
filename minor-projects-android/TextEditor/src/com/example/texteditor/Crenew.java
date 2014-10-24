package com.example.texteditor;

import java.io.Externalizable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Crenew extends Activity {

	EditText ed1,ed2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crenew);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crenew, menu);
		return true;
	}
	public void myclick(View v){
		String fname = ed1.getText().toString();
		String content = ed2.getText().toString();
		
		try {
			File f1 = Environment.getExternalStorageDirectory();
			File f = new File(f1.getPath()+"/mydocs");
			f.mkdir();
			File f2 = new File(f.getAbsolutePath()+ "/"+ fname);
			try {
				f2.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "Unable to create a file", 200).show();
			}
			FileOutputStream fos = new FileOutputStream(f2);
			byte[] b = content.getBytes();
			try {
				fos.write(b);
				Toast.makeText(this, "Saved", 200).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "IOException", 500).show();
			}
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "IOExceptionAtClose", 500).show();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this,"File Not Found" , 200).show();
		}
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

}
