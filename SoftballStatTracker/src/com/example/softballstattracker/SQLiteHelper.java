package com.example.softballstattracker;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.*;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_PLAYERS = "Players";
	public static final String ID = "ID";
	public static final String NAME = "Name"; 
	public static final String DATE_CREATED = "DateCreated"; 

	public static final String DATABASE_NAME = "SoftballStatsDB.db";
	private static final int DATABASE_VERSION = 1;

	//DB Creation SQL Statement
  	private static final String DATABASE_CREATE = "create table " + TABLE_PLAYERS + 
  												  "(" 
  												  + 
  												  ID 
  												  + 
  												  " integer primary key autoincrement, " 
  												  + 
  												  NAME
											      + 
											      " text not null" 
  												  +
											      DATE_CREATED	
											      +
											      "text not null"
											      +
											      ");";
	public SQLiteHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, 2);
	}
	  
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		 Log.w(SQLiteHelper.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
			    onCreate(db);
	}

}
