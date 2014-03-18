package com.example.softballstattracker.Activites;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Adapters.LeaderBoardArrayAdapter;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Stat;

public class LeaderBoardsActivity extends Activity {

    public ArrayList<Stat> leaderBoardStats = null;
    private StatDataSource statsDataSource;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leader_boards);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	
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
