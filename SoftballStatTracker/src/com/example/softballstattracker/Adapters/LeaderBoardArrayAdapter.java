package com.example.softballstattracker.Adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Stat;

public class LeaderBoardArrayAdapter extends BaseAdapter {
		
	    private ArrayList<Stat> items;
	    private Context context;

	    public LeaderBoardArrayAdapter(Context context, ArrayList<Stat> stats) {
	            super();
	            this.items = stats;
	            this.context = context;
	    }
	    
	    @Override
	    public int getCount() {
	    	int size = items.size();
			return size;
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return items.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return position;
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
	        	ImageView playerImage = (ImageView) row.findViewById(R.id.playerImage);
	            TextView playerName = (TextView) row.findViewById(R.id.playerName);
	            TextView atBats = (TextView) row.findViewById(R.id.atBats);
	            TextView hits = (TextView) row.findViewById(R.id.hits);
	            TextView average = (TextView) row.findViewById(R.id.average);
	            TextView obp = (TextView) row.findViewById(R.id.obp);
	            TextView rbis = (TextView) row.findViewById(R.id.rbis);
	            TextView runs = (TextView) row.findViewById(R.id.runs);
	            TextView slug = (TextView) row.findViewById(R.id.slug);
	            
	            if(playerImage != null) {
	            	//playerImage.setImageURI(stat.getPlayerImage().);
	            }
	            if (playerName != null) {
	            	playerName.setText(stat.getPlayerName());                            
	            }
	            if(atBats != null){
	            	stat.getAtBats();
	                atBats.setText(String.valueOf(stat.getAtBats()));
	            }
	            if(runs != null){
	            	runs.setText(String.valueOf("runs"));
	            }
	            if(hits != null){
	            	hits.setText(String.valueOf(stat.getHits()));
	            }
	            if(average != null){
	            	int abs = stat.getAtBats();
	            	int hs = stat.getHits();
	            	float avg = stat.getAverage(hs, abs);
	            	String avgString = String.format("%.03f", avg);
	            	average.setText(avgString);
	            }
	            //(Hits + Walks + Hit by Pitch) / (At Bats + Walks + Hit by Pitch + Sacrifice Flies)
	            if(obp != null){
	            	int bases = stat.getHits() + stat.getWalks();
	            	int plateAppearances = stat.getAtBats() + stat.getWalks() + stat.getSacFlys(); //+stat.SacFlies
	            	float onBasePercentage = stat.getOnBasePercentage(bases, plateAppearances);
	            	String obpString = String.format("%.03f", bases / onBasePercentage);
	            	obp.setText(obpString);
	            }
	            if(slug != null){
	            	int bases = stat.getHits() + stat.getWalks();
	            	int plateAppearances = stat.getAtBats() + stat.getWalks() + stat.getSacFlys(); 
	            	float onBasePercentage = stat.getOnBasePercentage(bases, plateAppearances);
	            	String obpString = String.format("%.03f", bases / onBasePercentage);
	            	slug.setText("slug");
	            }
                if(rbis != null){
	            	rbis.setText(String.valueOf(stat.getRunsBattedIn()));
	            }
	        }
	        
	        return row;
	    }
	}
