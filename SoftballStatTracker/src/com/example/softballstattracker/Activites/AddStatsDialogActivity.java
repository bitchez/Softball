package com.example.softballstattracker.Activites;

import org.joda.time.DateTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.softballstattracker.R;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Stat;

public class AddStatsDialogActivity extends Activity
{
	private static final String TAG = "EditStatsFragment";
	EditText AtBats;
	EditText Singles;
	EditText Doubles;
	EditText Triples;
	EditText Homeruns;
	EditText RBIs;
	EditText PutOuts;
	EditText BeersDrank;
	private Stat newStat = new Stat();
	Button saveStatsButton;
	private StatDataSource statsDataSource;
	private boolean hasErrors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// View view = inflater.inflate(R.layout.edit_stats_fragment, container,
		// false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_stats_dialog);

		initialize();
	}

	public void saveStats() {
		newStat.setAtBats(Integer.parseInt(AtBats.getText().toString()));
		newStat.setSingles(Integer.parseInt(Singles.getText().toString()));
		newStat.setDoubles(Integer.parseInt(Doubles.getText().toString()));
		newStat.setTriples(Integer.parseInt(Triples.getText().toString()));
		newStat.setHomeRuns(Integer.parseInt(Homeruns.getText().toString()));
		newStat.setRbis(Integer.parseInt(RBIs.getText().toString()));
		newStat.setPutOuts(Integer.parseInt(PutOuts.getText().toString()));
		newStat.setBeerDrank(Integer.parseInt(BeersDrank.getText().toString()));

		validateNewStat(newStat);

		Log.v(TAG, "Saving Statistic");
		statsDataSource.createStatistic(newStat);
		Log.v(TAG, "Statistics saved");

		if (hasErrors == false) 
		{
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		}
	}

	private void initialize() 
	{
		long currentGameId = this.getIntent().getLongExtra("currentGameId", 0);
		Parcelable selectedPlayer = this.getIntent().getParcelableExtra("selectedPlayer");

		newStat.setGameId(currentGameId);

		saveStatsButton = (Button) findViewById(R.id.saveStats);

		saveStatsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveStats();
			}
		});

		AtBats = (EditText) findViewById(R.id.atBatsInput);
		Singles = (EditText) findViewById(R.id.singlesInput);
		Doubles = (EditText) findViewById(R.id.doublesInput);
		Triples = (EditText) findViewById(R.id.triplesInput);
		Homeruns = (EditText) findViewById(R.id.homeRunsInput);
		RBIs = (EditText) findViewById(R.id.rbisInput);
		BeersDrank = (EditText) findViewById(R.id.beersDrankInput);
		PutOuts = (EditText) findViewById(R.id.putOutsInput);

		statsDataSource = new StatDataSource(this);
		statsDataSource.open();
	}

	private void validateNewStat(Stat newStat) 
	{
		hasErrors = false;
		newStat.setHits(newStat.getSingles(), newStat.getDoubles(),
				newStat.getTriples(), newStat.getHomeRuns());

		if (newStat.getHits() > newStat.getAtBats()) {
			hasErrors = true;
			AtBats.setError("You have too many hits mother fucker.");
		}

		if (newStat.getBeersDrank() == 0) {
			hasErrors = true;
			BeersDrank.setError("You need to drink ONE beer.");
		}
	}

}
