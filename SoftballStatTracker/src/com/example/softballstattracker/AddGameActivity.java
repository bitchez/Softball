package com.example.softballstattracker;

import java.util.List;

import com.example.softballstattracker.DataSources.GameDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class AddGameActivity extends Activity {

	private GameDataSource gameDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_game);
		
		gameDataSource = new GameDataSource(this);
		gameDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_game, menu);
		return false;
	}
	
	public void ChoosePlayers(View view)
	{
		Intent intent = new Intent(this, ChoosePlayersActivity.class);	
	    startActivity(intent);
	}
	
	public void Back(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}

}
