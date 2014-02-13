package com.example.softballstattracker.Adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Stat;

public class LeaderBoardArrayAdapter extends ArrayAdapter<Stat> {
		
	    private ArrayList<Stat> items;
	    private Context context;

	    public LeaderBoardArrayAdapter(Context context, int textViewResourceId, ArrayList<Stat> stats) {
	            super(context, textViewResourceId, stats);
	            this.items = stats;
	            this.context = context;
	    }
	    
	    @Override
	    public int getCount() {
			return items.size();
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	        View row = convertView;
	        
	        if (row == null) {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(R.layout.leader_board_row, parent, false);
	        }
	        
	        Stat stat = items.get(position);
	        if (stat != null) 
	        {
	            TextView playerName = (TextView) row.findViewById(R.id.playerName);
	            TextView atBats = (TextView) row.findViewById(R.id.atBats);
	            TextView hits = (TextView) row.findViewById(R.id.hits);
	            TextView average = (TextView) row.findViewById(R.id.average);
	            
	            if (playerName != null) {
	            	playerName.setText("Name: "+ stat.getPlayerName());                            
	            }
	            if(atBats != null){
	                atBats.setText("AB: "+ stat.getAtBats());
	            }
	            if(hits != null){
	            	hits.setText("Hits: " + stat.getHits());
	            }
	            if(average != null){
	            	hits.setText("AVG: " + stat.getAverage(stat.getHits(), stat.getAtBats()));
	            }
	        }
	        
	        return row;
	    }
	    
	}
