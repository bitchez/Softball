package com.example.softballstattracker.Activites;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softballstattracker.R;

public class MainMenuActivity extends Activity {
	
	int request_Code = 1;

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addPlayer(View view)
	{     
		startActivityForResult(new Intent(this, AddPlayerActivity.class), request_Code);
	}
	
	public void addGame(View view)
	{     
		Intent intent = new Intent(this, AddGameActivity.class);
	    startActivity(intent);
	}
	
	public void onLeaderBoard(View view) 
	{
		Intent intent = new Intent(this, LeaderBoardsActivity.class);
	    startActivity(intent);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == request_Code) 
		{
			if (resultCode == RESULT_OK) 
			{
				Toast.makeText(this, data.getData().toString() + " has been saved", Toast.LENGTH_LONG).show();
			}
		}
		
	}
}
