package com.example.softballstattracker.DataSources;

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
	private static final int DATABASE_VERSION = 11;

	
	public static final String DATABASE_CREATE_PLAYERS = "CREATE TABLE Players (ID INTEGER PRIMARY KEY autoincrement, Name text not null, DateCreated string)";
	public static final String DATABASE_CREATE_GAMES = "CREATE TABLE Games (ID INTEGER PRIMARY KEY autoincrement, Name TEXT, DateCreated TEXT, PlayerId NUMERIC)";
			    								  
	
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(DATABASE_CREATE_PLAYERS);
		db.execSQL(DATABASE_CREATE_GAMES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		
		//drop tables and create new ones
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
	    onCreate(db);
		//db.execSQL("DELETE FROM Players");
		}

}
