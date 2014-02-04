package com.example.softballstattracker.Activites;

import com.example.softballstattracker.R;
import com.example.softballstattracker.R.layout;
import com.example.softballstattracker.R.menu;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	int request_Code = 1;

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void AddPlayer(View view)
	{     
		startActivityForResult(new Intent(this, AddPlayerActivity.class), request_Code);
	}
	
	public void AddGame(View view)
	{     
		Intent intent = new Intent(this, AddGameActivity.class);
	    startActivity(intent);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == request_Code) 
		{
			if (resultCode == RESULT_OK) 
			{
				Toast.makeText(this, data.getData().toString() + " added to player list.", Toast.LENGTH_LONG).show();
			}
		}
		
	}
}
