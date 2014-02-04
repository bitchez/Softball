package com.example.softballstattracker.Fragments;


import java.util.ArrayList;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



public class PlayersSelectedFragment extends Fragment {

	private final static String TAG = "mainFragment";
	private ArrayList<Player> playerList;
	
	public static PlayersSelectedFragment newInstance(int index) {
		PlayersSelectedFragment frag = new PlayersSelectedFragment();
		
		Bundle args = new Bundle();
		args.putInt("index", index);
		frag.setArguments(args);
		
		return frag;
	}
	
	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}
	
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
		//ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_checked, players);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.players_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
}
