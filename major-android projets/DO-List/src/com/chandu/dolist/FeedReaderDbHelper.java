package com.chandu.dolist;

import java.util.ArrayList;
//import java.util.HashMap;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;



@SuppressWarnings("unused")
public class FeedReaderDbHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "FeedReader.db";
    public static final String LIST_TABLE_NAME = "addnote";
    public static final String LIST_COLUMN_ID = "id";
    public static final String LIST_COLUMN_TITLE = "title";
    public static final String LIST_COLUMN_NOTE = "note";
    public static final String LIST_COLUMN_END_TIME = "duetime";

    //private HashMap hp;
    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table addnote" + "(id integer primary key,title text,note text,duetime text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS addnote");
		onCreate(db);
		
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
   
	public boolean insertNote(String title,String note,String dueTime){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    
	    contentValues.put("title", title);
	    contentValues.put("note", note);
	    contentValues.put("duetime", dueTime);
	    
	    db.insert(LIST_TABLE_NAME, null, contentValues);
		return true;
		
	}
	
	public Integer deleteNote (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("addnote", "id = ? ", new String[] { Integer.toString(id) });
	   }
	
	public boolean updateNote(Integer id,String title,String note){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    
	    contentValues.put("title", title);
	    contentValues.put("note", note);
	    db.update(LIST_TABLE_NAME, contentValues, "id = ?", new String[] {Integer.toString(id)});
		return true;
	}
	
	public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from addnote where id="+id+"", null );
	      return res;
	   }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList getAllNotes(){
		ArrayList list = new ArrayList();
		SQLiteDatabase db = this.getReadableDatabase();
	    Cursor res =  db.rawQuery( "select * from addnote", null );
	    res.moveToFirst();
	    while(res.isAfterLast() == false){
	    	list.add(res.getString(res.getColumnIndex(LIST_COLUMN_TITLE)));
	    	res.moveToNext();
	    }
		return list;
	}
	

}
