package com.example.softballstattracker.Adapters;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Stat;

public class GameByGameArrayAdapter extends ArrayAdapter<Stat> {
		
	    private ArrayList<Stat> items;
	    private Context context;

	    public GameByGameArrayAdapter(Context context, int textViewResourceId, ArrayList<Stat> games) {
	            super(context, textViewResourceId, games);
	            this.items = games;
	            this.context = context;
	    }
	    
	    @Override
	    public int getCount() {
	    	int size = items.size();
			return size;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	        View row = convertView;
	        
	        if (row == null) {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(R.layout.game_by_game_row, parent, false);
	        }
	        
	        Stat stat = items.get(position);
	        if (stat != null) 
	        {
	            TextView gameName = (TextView) row.findViewById(R.id.gameName);
	            TextView gameDateCreated = (TextView) row.findViewById(R.id.gameDateCreated);
	            TextView gameHits = (TextView) row.findViewById(R.id.gameHits);
	            TextView gameAvg = (TextView) row.findViewById(R.id.gameAverage);
	            TextView gameRBIs = (TextView) row.findViewById(R.id.gameRBIs);
	            
	            if(gameRBIs != null) {
	            	   gameRBIs.setText(String.valueOf(stat.getRunsBattedIn()));
	            	}
	            if(gameAvg != null) {
	            	int abs = stat.getAtBats();
	            	int hs = stat.getHits();
	            	float avg = stat.getAverage(hs, abs);
	            	String avgString = String.format(Locale.getDefault(), "%.03f", avg);
	            	gameAvg.setText(avgString);
	            }
	            if (gameHits != null) {
	            	gameHits.setText(String.valueOf(stat.getHits()));
	    		}
	            if (gameName != null) {
	            	gameName.setText(stat.getPlayerName());                            
	            }
	            if (gameDateCreated != null) {
	            	
	            	gameDateCreated.setText("today");
	            			//stat.getDateCreated());	            
	            }
	          }        
	        return row;
	    }
	    
	}