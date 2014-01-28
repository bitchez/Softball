package com.example.softballstattracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void AddPlayer(View view)
	{     
		Intent intent = new Intent(this, AddPlayerActivity.class);
	    startActivity(intent);
	}
	
	public void AddGame(View view)
	{     
		Intent intent = new Intent(this, AddGameActivity.class);
	    startActivity(intent);
	}
}
