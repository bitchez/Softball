package com.example.softballstattracker.DataSources;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_PLAYERS = "Players";
	public static final String TABLE_GAMES = "Games";
	public static final String TABLE_STATS = "Stats";
	public static final String NAME = "Name";
	public static final String DATE_CREATED = "DateCreated";
	public static final String PLAYER_ID = "PlayerId";
	public static final String GAME_ID = "GameId";
	public static final String DATABASE_NAME = "SoftballStatsDB.db";
	private static final int DATABASE_VERSION = 14;
	
	public static final String DATABASE_CREATE_PLAYERS = "CREATE TABLE Players (PlayerId INTEGER PRIMARY KEY autoincrement, Name text not null, DateCreated string)";
	public static final String DATABASE_CREATE_GAMES = "CREATE TABLE Games (GameId INTEGER PRIMARY KEY autoincrement, Name TEXT, DateCreated TEXT, PlayerId INTEGER)";
	public static final String DATABASE_CREATE_STATS = "CREATE TABLE Stats (PlayerId INTEGER PRIMARY KEY, AtBats INTEGER, Average REAL, Hits INTEGER, Singles INTEGER, Doubles INTEGER, Triples INTEGER, RBIs INTEGER, Beers INTEGER, PutOuts INTEGER, GAMES INTEGER, GameID INTEGER not null, DateCreated string)";   								  
	
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(DATABASE_CREATE_PLAYERS);
		db.execSQL(DATABASE_CREATE_GAMES);
		db.execSQL(DATABASE_CREATE_STATS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		
		//drop tables and create new ones
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
	    onCreate(db);
		}

}
