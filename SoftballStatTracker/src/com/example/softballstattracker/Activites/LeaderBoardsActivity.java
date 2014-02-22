package com.example.softballstattracker.Activites;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Adapters.LeaderBoardArrayAdapter;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Stat;

public class LeaderBoardsActivity extends ListActivity {
	

    public ArrayList<Stat> leaderBoardStats = null;
    private StatDataSource statsDataSource;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leader_boards);
	
		leaderBoardStats = new ArrayList<Stat>();
		leaderBoardStats = getLeaderBoardStats();
		LeaderBoardArrayAdapter leaderBoardAdapter = new LeaderBoardArrayAdapter(this, R.layout.leader_board_row, leaderBoardStats);
		setListAdapter(leaderBoardAdapter);
    }
	
	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) 
	  {
	    Stat selectedLeaderBoardItem = (Stat) getListAdapter().getItem(position);
	    
	    Toast.makeText(this, selectedLeaderBoardItem.getPlayerName() + " selected", Toast.LENGTH_LONG).show();
	    
	    NavigatetoGamebyGameStatActivity(selectedLeaderBoardItem);
	  }

	private void NavigatetoGamebyGameStatActivity(Stat selectedLeaderBoardItem) 
	{	
		//Intent gameByGameIntent = new Intent(this, GameByGameStatActivity.class);
		//gameByGameIntent.putExtra("playerId", selectedLeaderBoardItem.getPlayerId());
	    //startActivity(gameByGameIntent);
		
	}

	private ArrayList<Stat> getLeaderBoardStats() 
	{
		statsDataSource = new StatDataSource(this);
		return statsDataSource.getLeaderBoardStatistics();
	}
}
