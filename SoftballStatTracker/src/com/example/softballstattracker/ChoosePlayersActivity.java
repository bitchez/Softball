package com.example.softballstattracker;

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
	private GameDataSource gameDataSource;
	private List<Player> players;
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
		gameDataSource = new GameDataSource(this);
		gameDataSource.open();
		
		players = playerDataSource.getAllPlayers();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_players, menu);
		return true;
	}
	
	public void AddGame(View view){
		
		gameNameInput = (EditText)findViewById(R.id.gameNameInput);
		String gameName = gameNameInput.getText().toString();
		
		gameDateInput = (EditText)findViewById(R.id.gameNameInput);
		String dateCreated = gameDateInput.getText().toString();
		
//		Game newGame = new Game();
//		newGame.setName(gameName);
//		newGame.setDateCreated(dateCreated);
//		
//		gameDataSource.createGame(newGame);
	}
}

