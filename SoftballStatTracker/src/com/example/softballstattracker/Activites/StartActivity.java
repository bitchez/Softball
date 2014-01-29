package com.example.softballstattracker.Activites;

import com.example.softballstattracker.R;
import com.example.softballstattracker.R.layout;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class StartActivity extends Activity {
	
	public void startEvent(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.start, menu);
		return false;
	}
}