package com.example.softballstattracker.DataSources;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.softballstattracker.Models.Stat;

public class StatDataSource {

	// Database fields
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns = { SQLiteHelper.STAT_ID, SQLiteHelper.PLAYER_ID,
			SQLiteHelper.PLAYER_NAME, SQLiteHelper.AT_BATS, SQLiteHelper.HITS,
			SQLiteHelper.SINGLES, SQLiteHelper.DOUBLES, SQLiteHelper.TRIPLES,
			SQLiteHelper.HOMERUNS, SQLiteHelper.RUNS_BATTED_IN, SQLiteHelper.PUTOUTS,
			SQLiteHelper.BEERS_DRANK, SQLiteHelper.GAME_ID,
			SQLiteHelper.DATE_CREATED };

	public StatDataSource(Context context) {

		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Stat createStatistic(Stat stat) 
	{
		ContentValues values = setupContentValues(stat);
		long insertId = database.insert(SQLiteHelper.TABLE_STATS, null, values);
		Cursor cursor = database.query(SQLiteHelper.TABLE_STATS, allColumns,
						SQLiteHelper.STAT_ID + " = " + insertId, null, null,
						null, null);

		cursor.moveToFirst();

		Stat newStat = cursorToStat(cursor);
		cursor.close();
		return newStat;
	}

	private ContentValues setupContentValues(Stat stat) {
		String dateTime = DateTime.now().toString();

		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.PLAYER_ID, stat.getPlayerId());
		values.put(SQLiteHelper.PLAYER_NAME, stat.getPlayerName());
		values.put(SQLiteHelper.AT_BATS, stat.getAtBats());
		values.put(SQLiteHelper.HITS, stat.getHits());
		values.put(SQLiteHelper.SINGLES, stat.getSingles());
		values.put(SQLiteHelper.DOUBLES, stat.getPlayerId());
		values.put(SQLiteHelper.TRIPLES, stat.getTriples());
		values.put(SQLiteHelper.HOMERUNS, stat.getHomeRuns());
		values.put(SQLiteHelper.RUNS_BATTED_IN, stat.getRunsBattedIn());
		values.put(SQLiteHelper.PUTOUTS, stat.getPutOuts());
		values.put(SQLiteHelper.BEERS_DRANK, stat.getBeersDrank());
		values.put(SQLiteHelper.GAME_ID, stat.getGameId());
		values.put(SQLiteHelper.DATE_CREATED, dateTime);
		return values;
	}

	private Stat cursorToStat(Cursor cursor) {
		Stat stat = new Stat();
		stat.setPlayerId(cursor.getLong(0));
		stat.setPlayerName(cursor.getString(1));
		stat.setAtBats(cursor.getInt(2));
		stat.setHits(cursor.getInt(3));
		stat.setSingles(cursor.getInt(4));
		stat.setDoubles(cursor.getInt(5));
		stat.setTriples(cursor.getInt(6));
		stat.setHomeRuns(cursor.getInt(7));
		stat.setRbis(cursor.getInt(9));
		stat.setPutOuts(cursor.getInt(10));
		stat.setBeerDrank(cursor.getInt(11));
		stat.setGameId(cursor.getInt(12));
		stat.setDateCreated(cursor.getString(13));
		return stat;
	}

}
