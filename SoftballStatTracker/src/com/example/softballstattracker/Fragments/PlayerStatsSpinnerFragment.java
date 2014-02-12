package com.example.softballstattracker.Fragments;


import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.softballstattracker.R;
import com.example.softballstattracker.Interfaces.OnPlayerSelectedListener;
import com.example.softballstattracker.Models.Player;

public class PlayerStatsSpinnerFragment extends Fragment implements OnItemSelectedListener {
	
	OnPlayerSelectedListener mCallBack; 
	private List<Player> selectedPlayers = new ArrayList<Player>();
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Bundle statsFragActivity = getActivity().getIntent().getExtras();
		selectedPlayers = statsFragActivity.getParcelableArrayList("selectedPlayers");
		
		View view = inflater.inflate(R.layout.players_stats_spinner_fragment, container, false);
		spinnerList(view);
		return view;
	}
	
	private void spinnerList(View view)
	{
		Spinner spinner = (Spinner)view.findViewById(R.id.players_spinner);
		spinner.setOnItemSelectedListener(this);
		ArrayAdapter<Player> playerAdapter = new ArrayAdapter<Player>(getActivity(), android.R.layout.simple_spinner_item, selectedPlayers);
		playerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(playerAdapter);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		// This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallBack = (OnPlayerSelectedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
        }
	}
	
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
    {
    	//send the event to the host activity
    	Player selectedPlayer = selectedPlayers.get(position);
    	mCallBack.onPlayerSelected(selectedPlayer);
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}
}
