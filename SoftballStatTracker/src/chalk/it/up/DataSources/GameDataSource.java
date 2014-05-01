package chalk.it.up.DataSources;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import chalk.it.up.Models.Game;

public class GameDataSource {

  // Database fields
  private SQLiteDatabase database;
  private SQLiteHelper dbHelper;
  private String[] allColumns = { SQLiteHelper.GAME_ID,
		  						  SQLiteHelper.GAME_NAME,
		  						  SQLiteHelper.DATE_CREATED,
		  						  SQLiteHelper.OPPONENT}; 

  public GameDataSource(Context context) 
  {
    dbHelper = new SQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Game createGame(Game game) 
  {  
	int nextGameId = getMaxGameId() + 1;
    game.setId(nextGameId);
    
    ContentValues values = new ContentValues();
    values.put(SQLiteHelper.GAME_ID, nextGameId);
    values.put(SQLiteHelper.GAME_NAME, game.getname());
    values.put(SQLiteHelper.DATE_CREATED, game.getDateCreated());
    values.put(SQLiteHelper.OPPONENT, game.getOpponent());

    database.insert(SQLiteHelper.TABLE_GAMES, null, values);
    
    Cursor cursor = database.query(SQLiteHelper.TABLE_GAMES,
        allColumns, null, null,
        null, null, null);
    
    cursor.moveToFirst();
    
    Game newGame = cursorToGame(cursor);
    cursor.close();
    return newGame;
  }

  public List<Game> getAllGames() {
    List<Game> Games = new ArrayList<Game>();

    Cursor cursor = database.query(SQLiteHelper.TABLE_GAMES,
        allColumns, null, null, null, null, "DateCreated");

    cursor.moveToFirst();
    
    while (!cursor.isAfterLast()) {
      Game Game = cursorToGame(cursor);
      Games.add(Game);
      cursor.moveToNext();
    }
  
    cursor.close();
    return Games;
  }
  
  public int getMaxGameId() 
  {
    Cursor cursor = database.rawQuery("SELECT MAX(GameID) FROM Games", null);
    cursor.moveToFirst();
    
    int maxId = cursor.getInt(0);
    cursor.close();
    return maxId;
 }

  private Game cursorToGame(Cursor cursor) 
  {
    Game game = new Game();
    game.setId(cursor.getLong(0));
    game.setName(cursor.getString(1));
    game.setDateCreated(cursor.getString(2));
    game.setOpponent(cursor.getString(3));
    return game;
  }
} 