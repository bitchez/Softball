package com.example.softballstattracker.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.softballstattracker.R;

public class EditStatsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.stats_fragment, container, false);
			
		return view;
	}

}
