package com.example.softballstattracker.Activites;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

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

	private ArrayList<Stat> getLeaderBoardStats() 
	{
		statsDataSource = new StatDataSource(this);
		return statsDataSource.getLeaderBoardStatistics();
	}
}
