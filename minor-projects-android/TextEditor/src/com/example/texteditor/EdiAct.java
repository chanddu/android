package com.example.texteditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EdiAct extends Activity {

	EditText ed;
	String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edi);
		ed = (EditText) findViewById(R.id.editText1);
		Intent i = getIntent();
		String content = i.getStringExtra("content");
		path = i.getStringExtra("path");
		ed.setText(content);
		//Toast.makeText(this, path, 500).show();
	}

	public void myclick(View v){
		String tobesaved = ed.getText().toString();
		File f = new File(path);
		try {
			FileOutputStream fos = new FileOutputStream(f);
			byte b[] = tobesaved.getBytes();
			try {
				fos.write(b);
				Toast.makeText(this, "Saved", 500).show();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.toString(), 500).show();
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

}
