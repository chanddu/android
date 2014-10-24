package com.example.textdisplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText ed1,ed2;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed1 = (EditText) findViewById(R.id.fname);
		ed2 = (EditText) findViewById(R.id.key);
		tv = (TextView) findViewById(R.id.freq);
	}

	public void myclick(View v){
		String fnme = ed1.getText().toString();
		String k = ed2.getText().toString();
		File f = Environment.getExternalStorageDirectory();
		int count=0,a=0;
		try {
			File f1 = new File(f.getPath()+"/"+fnme);
			FileInputStream fis = new FileInputStream(f1);
			Scanner sc = new Scanner(fis);
			String content = sc.nextLine();
			a = fis.read();
			while(a>-1){
				content += " " + sc.nextLine();
				a = fis.read();
			}
			if(content.contains(k)){
				String word[] = content.split(" ");
				for(int i=0;i<word.length;i++){
					if(word[i].equals(k)){
						count++;
					}
				}
				tv.setText(count+"");
				count = 0;
			}
			else{
				Toast.makeText(this, "Word Not Found", 500).show();
			}
				sc.close();
				fis.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Invalid File", 500).show();
		}
		catch (Exception e1){
			Toast.makeText(this, e1.toString(), 500).show();
		}
		
			
		
	}
}
