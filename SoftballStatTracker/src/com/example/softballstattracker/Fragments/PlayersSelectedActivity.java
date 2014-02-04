package com.example.softballstattracker.Fragments;


import java.util.ArrayList;
import java.util.List;

import com.example.softballstattracker.R;
import com.example.softballstattracker.DataSources.GameDataSource;
import com.example.softballstattracker.DataSources.PlayerDataSource;
import com.example.softballstattracker.Models.Game;
import com.example.softballstattracker.Models.Player;
import com.example.softballstattracker.R.id;
import com.example.softballstattracker.R.layout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Game;


public class PlayersSelectedActivity extends Fragment {

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.activity_players_selected, container, false);
		spinnerList(view);
		return view;
		
	}
	
	private void spinnerList(View view)
	{
		Spinner spinner = (Spinner) view.findViewById(R.id.players_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.players_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
	


}
