package com.example.softballstattracker;
 
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPlayerActivity extends Activity {
	
	private PlayerDataSource playerDataSource;
	EditText playerNameInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
		
		playerDataSource = new PlayerDataSource(this);
		playerDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_player, menu);
		return true;
	}
	
	public void SavePlayer(View view){
		
		playerNameInput = (EditText)findViewById(R.id.playerNameInput);
		String playerName = playerNameInput.getText().toString();
		
		//save new player to DB
		playerDataSource.createPlayer(playerName);
		
		Intent intent = new Intent(this, AddGameActivity.class);
	    startActivity(intent);
	}
	
	public void Back(View view){
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
}
