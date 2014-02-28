package com.example.softballstattracker.Activites;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Adapters.GameByGameArrayAdapter;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Stat;

public class GameByGameStatActivity extends ListActivity {
	
	private long playerId;
	public ArrayList<Stat> gameByGameStats = null;
	private StatDataSource statsDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_by_game_stat);
		
		initialize();
	}

	private void initialize() 
	{
		ActionBar actionBar = getActionBar();
		actionBar.hide();	
		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			playerId = bundle.getLong("playerId");
			
			if(playerId != 0)
			{
				gameByGameStats = new ArrayList<Stat>();
				gameByGameStats = loadGameByGameStatistics(playerId);
			}
		}
		
		GameByGameArrayAdapter gameByGameAdapter = new GameByGameArrayAdapter(this, R.layout.game_by_game_row, gameByGameStats);
		setListAdapter(gameByGameAdapter);
	}

	private ArrayList<Stat> loadGameByGameStatistics(long playerId) 
	{
		statsDataSource = new StatDataSource(this);
		return statsDataSource.getGameByGameStatistics(playerId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_by_game_stat, menu);
		return true;
	}

}