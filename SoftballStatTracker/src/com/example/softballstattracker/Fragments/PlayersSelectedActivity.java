package com.example.softballstattracker.Fragments;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Player;


public class PlayersSelectedActivity extends Fragment {

	private List<Player> selectedPlayers = new ArrayList<Player>();
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Bundle statsFragActivity = getActivity().getIntent().getExtras();
		selectedPlayers = statsFragActivity.getParcelableArrayList("selectedPlayers");
		
		View view = inflater.inflate(R.layout.activity_players_selected, container, false);
		spinnerList(view);
		return view;
	}
	
	private void spinnerList(View view)
	{
			Spinner spinner = (Spinner)view.findViewById(R.id.players_spinner);
			
			ArrayAdapter<Player> playerAdapter = new ArrayAdapter<Player>(getActivity(), android.R.layout.simple_spinner_item, selectedPlayers);
			playerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(playerAdapter);
	}
}
