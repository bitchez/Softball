package com.example.softballstattracker;

import java.util.ArrayList;
import java.util.List;

import com.example.softballstattracker.DataSources.GameDataSource;
import com.example.softballstattracker.DataSources.PlayerDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class ChoosePlayersActivity extends ListActivity {
	
	private PlayerDataSource playerDataSource;
	private GameDataSource gamesDataSource;
	private List<Player> selectedPlayers;
	private List<Player> players;
	private List<Game> games;
	EditText gameNameInput;
	EditText gameDateInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_players);
		
		InitializeDataSources();
		
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, players);
		setListAdapter(adapter);
	}

	private void InitializeDataSources() {
		playerDataSource = new PlayerDataSource(this);
		playerDataSource.open();
		gamesDataSource = new GameDataSource(this);
		gamesDataSource.open();
		
		players = playerDataSource.getAllPlayers();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_players, menu);
		return false;
	}
	
	public void AddPlayerStats(View view)
	{
		CreateGamesForSelectedPlayers();
	}

	private void CreateGamesForSelectedPlayers() 
	{
		//gameNameInput = (EditText)findViewById(R.id.gameNameInput);
		//String gameName = gameNameInput.getText().toString();
		
		//gameDateInput = (EditText)findViewById(R.id.gameNameInput);
		//String dateCreated = gameDateInput.getText().toString();
		
		int gameId = gamesDataSource.getMaxGameId();
		
		Game newGame = new Game();
		//newGame.setName(gameName);
		newGame.setId(gameId);
		
		gamesDataSource.createGame(newGame);
		
	}
}

