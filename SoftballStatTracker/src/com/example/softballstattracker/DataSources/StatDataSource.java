package com.example.softballstattracker.DataSources;

import java.util.ArrayList;
import java.util.List;

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
	
	public ArrayList<Stat> getLeaderBoardStatistics()
	{
		List<Stat> leaderboard = new ArrayList<Stat>();
		String query = "SELECT PlayerId, PlayerName, SUM(AtBats), SUM(Singles) + SUM(Doubles) + SUM(Triples) + SUM(Homeruns) as Hits, SUM(RBIs) " +
						"FROM Stats GROUP By PlayerId";
		
		open();
		Cursor cursor = database.rawQuery(query, null);
		
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
		        	Stat stat = new Stat();
		        	stat.setPlayerId(cursor.getLong(0));
		        	stat.setPlayerName(cursor.getString(1));
		        	stat.setAtBats(cursor.getInt(2));
		        	stat.setHits(cursor.getInt(3));
		        	stat.setAverage(stat.getAtBats(), stat.getHits());
		        	stat.setRbis(cursor.getInt(4));
		        	
		        	leaderboard.add(stat);
	            }		 
	        while (cursor.moveToNext());
	    }
	    
	    // return list for leaderboard
	    return (ArrayList<Stat>) leaderboard;
	  }
	
	public ArrayList<Stat> getGameByGameStatistics(long playerId)
	{
		List<Stat> gameByGameStats = new ArrayList<Stat>();
		String query = "SELECT g.GameName, g.DateCreated, s.AtBats, s.Singles, s.Doubles, s.Triples, s.Homeruns, "
				+ "s.RBIs, s.Walks, s.BeersDrank, s.PutOuts "
				+ "FROM Stats s JOIN Games g ON s.GameId = g.GameId AND PlayerID ="
				+ playerId
				+ "  ORDER BY g.DateCreated desc";
		
		open();
		Cursor cursor = database.rawQuery(query, null);
		
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
		        	Stat stat = new Stat();
		        	stat.setPlayerName(cursor.getString(0));
		        	stat.setDateCreated(cursor.getString(1));
		        	stat.setAtBats(cursor.getInt(2));
		        	stat.setSingles(cursor.getInt(3));
		        	stat.setDoubles(cursor.getInt(4));
		        	stat.setTriples(cursor.getInt(5));
		        	stat.setHomeRuns(cursor.getInt(6));
		        	stat.setRbis(cursor.getInt(7));
		        	stat.setWalks(cursor.getInt(8));
		        	stat.setBeerDrank(cursor.getInt(9));
		        	stat.setPutOuts(cursor.getInt(10));
		        	stat.setHits(stat.getSingles(), stat.getDoubles(), stat.getTriples(), stat.getHomeRuns());
		        	stat.setAverage(stat.getAtBats(), stat.getHits());
		        	
		        	gameByGameStats.add(stat);
	            }		 
	        while (cursor.moveToNext());
	    }
	    
	    // return list of game stats
	    return (ArrayList<Stat>) gameByGameStats;
	  }

	private ContentValues setupContentValues(Stat stat) {
		String dateTime = DateTime.now().toString();

		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.PLAYER_ID, stat.getPlayerId());
		values.put(SQLiteHelper.PLAYER_NAME, stat.getPlayerName());
		values.put(SQLiteHelper.AT_BATS, stat.getAtBats());
		values.put(SQLiteHelper.HITS, stat.getHits());
		values.put(SQLiteHelper.SINGLES, stat.getSingles());
		values.put(SQLiteHelper.DOUBLES, stat.getDoubles());
		values.put(SQLiteHelper.TRIPLES, stat.getTriples());
		values.put(SQLiteHelper.HOMERUNS, stat.getHomeRuns());
		values.put(SQLiteHelper.RUNS_BATTED_IN, stat.getRunsBattedIn());
		values.put(SQLiteHelper.WALKS, stat.getWalks());
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
		stat.setWalks(cursor.getInt(10));
		stat.setPutOuts(cursor.getInt(10));
		stat.setBeerDrank(cursor.getInt(11));
		stat.setGameId(cursor.getInt(12));
		stat.setDateCreated(cursor.getString(13));
		return stat;
	}

}