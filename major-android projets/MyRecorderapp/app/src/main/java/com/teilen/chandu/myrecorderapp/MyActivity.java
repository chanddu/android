package com.teilen.chandu.myrecorderapp;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MyActivity extends Activity {

    private MediaRecorder mrecorder = null;
    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final  int MSG_UPDATE_TIMER = 2;
    final int REFRESH_RATE = 100;
    StopWatch timer = new StopWatch();
    public static final String CUSTOM_INTENT = "com.teilen.chandu.CallBroadCastReceiver";
    public static final String DEFAULT_STORAGE_LOCATION = Environment.getExternalStorageDirectory().getPath()+"/VoiceRecord/";
    TextView tv;
    RelativeLayout rl;
    Button bt1,bt2;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_START_TIMER:
                    timer.start();
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;
                case MSG_UPDATE_TIMER:
                    long millis = timer.getElapsedTime();
                    String s = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    tv.setText(s);
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE);
                    break;
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER);
                    timer.stop();
                    long milli = timer.getElapsedTime();
                    String str = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(milli),
                            TimeUnit.MILLISECONDS.toMinutes(milli) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milli)),
                            TimeUnit.MILLISECONDS.toSeconds(milli) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milli)));
                    tv.setText(str);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        tv = (TextView)findViewById(R.id.textView);
        rl = (RelativeLayout)findViewById(R.id.rel);
        rl.setBackgroundColor(Color.LTGRAY);
        //rl.setBackgroundResource(R.drawable.ic_icon);
        bt1 = (Button)findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);
        bt1.setBackgroundResource(R.drawable.ic_action_play);
        bt2.setBackgroundResource(R.drawable.ic_button_stop);
        bt2.setEnabled(false);
        bt2.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    public void play(View v){
        try{
            bt2.setVisibility(View.VISIBLE);
            bt2.setEnabled(true);
            bt1.setVisibility(View.INVISIBLE);
            bt1.setEnabled(false);
            Toast.makeText(this,"Recording Started",Toast.LENGTH_SHORT).show();
            mrecorder = new MediaRecorder();
            SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String s = now.format(date);
            long tm = System.currentTimeMillis();
            DateFormat dft = new SimpleDateFormat("HH:mm:ss");
            Calendar calen = Calendar.getInstance();
            calen.setTimeInMillis(tm);
            String fname = dft.format(calen.getTime());
            File f = new File(DEFAULT_STORAGE_LOCATION+s);
            if(!f.exists()){
                f.mkdirs();
            }
            mrecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            mrecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mrecorder.setOutputFile(f.getAbsolutePath()+ "/"+fname+".amr");
            mrecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mrecorder.prepare();
            mrecorder.start();
            mHandler.sendEmptyMessage(MSG_START_TIMER);

        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View v){
        bt2.setVisibility(View.INVISIBLE);
        bt2.setEnabled(false);
        bt1.setVisibility(View.VISIBLE);
        bt1.setEnabled(true);
        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        mrecorder.stop();
        mrecorder.release();
        mrecorder = null;
        mHandler.sendEmptyMessage(MSG_STOP_TIMER);
    }
}
