package com.example.softballstattracker.Activites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Adapters.GameListExpandableAdapter;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Stat;

public class GameByGameStatActivity extends ExpandableListActivity {
	
	private long playerId;
	public ArrayList<Stat> gameByGameStats = null;
	private StatDataSource statsDataSource;
	ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    HashMap<String, List<Stat>> gameSpecificData = new HashMap<String, List<Stat>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_by_game_stats);
		
		initialize();
		getGameByGameStats();
		setupExpandableListview();
	}

	private void initialize() 
	{
		ActionBar actionBar = getActionBar();
		actionBar.hide();	
		
		setupHeader();
		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			playerId = bundle.getLong("playerId");
		}
	}

	private void setupHeader() {
		TextView txt = (TextView) findViewById(R.id.gameByGameHeader);
		Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
		txt.setTypeface(font);

		int[] color = { Color.WHITE, Color.RED };
		float[] position = {0, 1};
		TileMode tile_mode = TileMode.MIRROR; // or TileMode.REPEAT;
		LinearGradient lin_grad = new LinearGradient(0, 0, 0, 75,color,position, tile_mode);
		Shader shader_gradient = lin_grad;
		txt.getPaint().setShader(shader_gradient);
	}
	
	private void setupExpandableListview() 
	{
		expandableListView = getExpandableListView();
		expandableListAdapter = new GameListExpandableAdapter(this, gameByGameStats, gameSpecificData);
		expandableListView.setAdapter(expandableListAdapter);
	}
	
	private void getGameByGameStats() 
	{
		if(playerId != 0)
		{
			gameByGameStats = new ArrayList<Stat>();
			gameByGameStats = loadGameByGameStatistics(playerId);
			
			for (int i = 0; i < gameByGameStats.size(); i++) 
			{
				Stat currentGameStat = gameByGameStats.get(i);
				gameSpecificData.put(currentGameStat.getPlayerName(), gameByGameStats);
			}
		}
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