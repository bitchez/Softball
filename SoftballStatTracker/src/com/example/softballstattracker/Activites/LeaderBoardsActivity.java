package com.example.softballstattracker.Activites;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Adapters.LeaderBoardArrayAdapter;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Stat;

public class LeaderBoardsActivity extends Activity {

    private static final String FFFF0D0D = null;
	public ArrayList<Stat> leaderBoardStats = null;
    private StatDataSource statsDataSource;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leader_boards);
		
		setupActivity();
	
		leaderBoardStats = new ArrayList<Stat>();
		leaderBoardStats = getLeaderBoardStats();
		
		final ListView leadboardListView = (ListView) findViewById(R.id.leaderBoardListView);
		LeaderBoardArrayAdapter leaderBoardAdapter = new LeaderBoardArrayAdapter(this, leaderBoardStats);
		leadboardListView.setAdapter(leaderBoardAdapter);
		leadboardListView.setOnItemClickListener(new OnItemClickListener() {
			 
	            @Override
	            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	                Object obj = leadboardListView.getItemAtPosition(position);
	                Stat selectedLeaderBoardItem = (Stat)obj;
	        	    NavigatetoGamebyGameStatActivity(selectedLeaderBoardItem);
	            }
	        });
    }

	private void setupActivity() 
	{
		setupLeaderboardHeader();
		
		Toast.makeText(this, "To view game by game stats, select a player", Toast.LENGTH_LONG).show();
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	private void setupLeaderboardHeader() {
		TextView txt = (TextView) findViewById(R.id.leaderBoardHeader);
		Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
		txt.setTypeface(font);
	
		int[] color = { Color.WHITE, Color.RED };
		float[] position = {0, 1};
		TileMode tile_mode = TileMode.MIRROR; // or TileMode.REPEAT;
		LinearGradient lin_grad = new LinearGradient(0, 0, 0, 75,color,position, tile_mode);
		Shader shader_gradient = lin_grad;
		txt.getPaint().setShader(shader_gradient);
	}
	
	  private void NavigatetoGamebyGameStatActivity(Stat selectedLeaderBoardItem) 
	{	
		Intent gameByGameIntent = new Intent(this, GameByGameStatActivity.class);
		gameByGameIntent.putExtra("playerId", selectedLeaderBoardItem.getPlayerId());
		startActivity(gameByGameIntent);
	}

	private ArrayList<Stat> getLeaderBoardStats() 
	{
		statsDataSource = new StatDataSource(this);
		return statsDataSource.getLeaderBoardStatistics();
	}
}
