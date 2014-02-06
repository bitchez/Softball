package com.example.softballstattracker.Activites;

import org.joda.time.DateTime;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import com.example.softballstattracker.R;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Stat;

public class StatsFragmentActivity extends FragmentActivity {

	private static final String TAG = "StatsActivityFragment";
	private StatDataSource statsDataSource;

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
		
		statsDataSource = new StatDataSource(this);
		statsDataSource.open();
		
		Log.d(TAG, "creating test Stat");
		createTestStat();
		Log.d(TAG, "finished creating test Stat");
	}

	private void createTestStat() 
	{
		String dateTime = DateTime.now().toString();
		
		Stat testStat = new Stat();
		testStat.setPlayerId(1);
		testStat.setPlayerName("test bitcth");
		testStat.setAtBats(5);
		testStat.setHits(2);
		testStat.setSingles(1);
		testStat.setDoubles(1);
		testStat.setTriples(0);
		testStat.setHomeRuns(0);
		testStat.setRbis(1);
		testStat.setPutOuts(0);
		testStat.setBeerDrank(10);
		testStat.setGameId(1);
		testStat.setDateCreated(dateTime);
		
		statsDataSource.createStatistic(testStat);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
