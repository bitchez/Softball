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
	public static final String TABLE_GAMES = "Games";
	public static final String ID = "ID";
	public static final String NAME = "Name";
	public static final String DATE_CREATED = "DateCreated";
	public static final String PLAYER_ID = "PlayerId";
	public static final String DATABASE_NAME = "SoftballStatsDB.db";
	private static final int DATABASE_VERSION = 7;

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		//db.execSQL(DATABASE_CREATE);
		String test = "test";
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		
		db.execSQL("CREATE TABLE Games (ID INTEGER PRIMARY KEY autoincrement, Name TEXT, DateCreated TEXT, PlayerId NUMERIC)"); 
		}

}
