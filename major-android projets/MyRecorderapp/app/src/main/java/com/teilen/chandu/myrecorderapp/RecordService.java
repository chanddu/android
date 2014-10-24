package com.teilen.chandu.myrecorderapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CHANDU on 24/10/14.
 */
public class RecordService extends Service {
    MediaRecorder mrecorder = null;
    public static final String DEFAULT_STORAGE_LOCATION = Environment.getExternalStorageDirectory().getPath() + "/CallRecord/";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mrecorder = new MediaRecorder();
        try{
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
            Toast.makeText(this, "recording started", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            Toast.makeText(getApplicationContext(), "recording stopped", Toast.LENGTH_SHORT).show();
            mrecorder.stop();
            mrecorder.release();
            mrecorder = null;
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
