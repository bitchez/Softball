package com.example.softballstattracker;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class ChoosePlayersActivity extends ListActivity {
	
	private PlayerDataSource playerDataSource;
	private List<Player> players;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_players);
		
		playerDataSource = new PlayerDataSource(this);
		playerDataSource.open();
		
		players = playerDataSource.getAllPlayers();
		
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, players);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_players, menu);
		return true;
	}
}
