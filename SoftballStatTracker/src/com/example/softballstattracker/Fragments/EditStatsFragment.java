package com.example.softballstattracker.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.softballstattracker.R;
import com.example.softballstattracker.DataSources.StatDataSource;
import com.example.softballstattracker.Models.Player;
import com.example.softballstattracker.Models.Stat;

public class EditStatsFragment extends Fragment {
	
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
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.edit_stats_fragment, container, false);
		saveStatsButton = (Button)view.findViewById(R.id.saveStats);
		initialize(view);
		
		return view;
	}

	public void updateStatsView(Player selectedPlayer) 
	{
		newStat.setPlayerId(selectedPlayer.getId());
		newStat.setPlayerName(selectedPlayer.getname());
	}
	
	public void saveStats()
	{
		newStat.setAtBats(Integer.parseInt(AtBats.getText().toString()));
		newStat.setSingles(Integer.parseInt(Singles.getText().toString()));
		newStat.setDoubles(Integer.parseInt(Doubles.getText().toString()));
		newStat.setTriples(Integer.parseInt(Triples.getText().toString()));
		newStat.setHomeRuns(Integer.parseInt(Homeruns.getText().toString()));
		newStat.setRbis(Integer.parseInt(RBIs.getText().toString()));
		newStat.setPutOuts(Integer.parseInt(PutOuts.getText().toString()));
		newStat.setBeerDrank(Integer.parseInt(BeersDrank.getText().toString()));
		newStat.setGameId(111);

		Log.v(TAG, "Saving Statistic");
		statsDataSource.createStatistic(newStat);
		Log.v(TAG, "Statistics saved");
	}
	
	private void initialize(View view) 
	{
		saveStatsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveStats();
				
			}
		});
		
		AtBats = (EditText)view.findViewById(R.id.atBatsInput);
		Singles = (EditText)view.findViewById(R.id.singlesInput);
		Doubles = (EditText)view.findViewById(R.id.doublesInput);
		Triples = (EditText)view.findViewById(R.id.triplesInput);
		Homeruns = (EditText)view.findViewById(R.id.homeRunsInput);
		RBIs = (EditText)view.findViewById(R.id.rbisInput);
		BeersDrank = (EditText)view.findViewById(R.id.beersDrankInput);
		PutOuts = (EditText)view.findViewById(R.id.putOutsInput);
		
		statsDataSource = new StatDataSource(getActivity());
		statsDataSource.open();
	}

}
