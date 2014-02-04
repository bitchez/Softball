package com.example.softballstattracker.Activites;

import java.util.ArrayList;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Fragments.PlayersSelectedFragment;
import com.example.softballstattracker.Models.Player;

import android.os.Bundle;
import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.view.Menu;

@SuppressWarnings("unused")
public class StatsFragmentActivity extends FragmentActivity {
	
	private final static String TAG = "mainFragment";
	private ArrayList<Player> playerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fragment);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		if(savedInstanceState == null)
		{
		  PlayersSelectedFragment selectPlayersFrag = new PlayersSelectedFragment();
		  selectPlayersFrag.setArguments(getIntent().getExtras());
		  getSupportFragmentManager().beginTransaction().add(android.R.id.content, selectPlayersFrag).commit();
		}
		
		playerList = getIntent().getParcelableArrayListExtra("chosenPlayers");
		Log.d(TAG, "got player list for stats");	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
