package com.example.softballstattracker.Activites;
 
import com.example.softballstattracker.R;
import com.example.softballstattracker.DataSources.PlayerDataSource;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.net.Uri;

import android.widget.EditText;

public class AddPlayerActivity extends Activity {
	
	private PlayerDataSource playerDataSource;
	EditText playerNameInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		playerDataSource = new PlayerDataSource(this);
		playerDataSource.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_player, menu);
		return true;
	}
	
	public void savePlayer(View view){
		
		playerNameInput = (EditText)findViewById(R.id.playerNameInput);
		
		if(DoesPlayerNameExist(playerNameInput))
	    {
			playerNameInput.setError("Player name is required");
	    }
		else
		{
		
		String playerName = playerNameInput.getText().toString();
		
		//save new player to DB
		playerDataSource.createPlayer(playerName);
		
		Intent intent = new Intent();
		intent.setData(Uri.parse(playerName)); setResult(RESULT_OK, intent);
	    finish();
		}
	}
	
	 private boolean DoesPlayerNameExist(EditText playerName) {
		return (playerName.getText().toString().trim().equals(""));
		
	}
}
