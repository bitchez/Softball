package com.example.softballstattracker.Activites;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Fragments.EditStatsFragment;
import com.example.softballstattracker.Interfaces.OnPlayerSelectedListener;
import com.example.softballstattracker.Models.Player;

public class StatsFragmentActivity extends FragmentActivity implements OnPlayerSelectedListener {

	private static final String TAG = "StatsActivityFragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initialize();
	}

	private void initialize() {
		Log.d(TAG, "created StatsActivityFragment");
		setContentView(R.layout.stats_activity_fragment);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPlayerSelected(Player selectedPlayer) 
	{
		EditStatsFragment statFrag = (EditStatsFragment)
                getSupportFragmentManager().findFragmentById(R.id.stats_fragment);
		
		if(statFrag != null)
		{
			statFrag.updateStatsView(selectedPlayer);
		} 
	}
}
