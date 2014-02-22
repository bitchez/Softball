package com.example.softballstattracker.Activites;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.softballstattracker.R;

public class GameByGameStatActivity extends ListActivity {
	
	float PlayerId;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_by_game_stat);
		
		initialize();
	}

	private void initialize() 
	{
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			PlayerId = bundle.getFloat("playerId");
			
			if(PlayerId != 0)
			{
				
			}
		}
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_by_game_stat, menu);
		return true;
	}

}
