package com.example.softballstattracker;

import java.util.ArrayList;
import java.util.List;
import com.example.softballstattracker.DataSources.GameDataSource;
import com.example.softballstattracker.DataSources.PlayerDataSource;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

public class ChoosePlayersActivity extends ListActivity {
	
	private PlayerDataSource playerDataSource;
	private GameDataSource gamesDataSource;
	private List<Player> selectedPlayers = new ArrayList<Player>();
	private List<Player> players;
	private ListView playerListView;
	TextView et_date;
	EditText gameNameInput;
	EditText gameDateInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_players);
		et_date = (TextView) findViewById(R.id.textView1);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		Bundle bundle=getIntent().getExtras();
		if (bundle != null)
		{
			//getting the value store in "date_input"
			String myText = bundle.getString("date_input");
			if(myText != null && !myText.isEmpty())
			{
				//appending the value of the contents in et_date or textView1
				et_date.append(" on date: " + myText);
			}
		}
		
		initializeDataSources();
		setupListView();
	}

	private void setupListView() {
		playerListView = getListView();
		playerListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_checked, players);
		setListAdapter(adapter);
	}

	private void initializeDataSources() 
	{
		playerDataSource = new PlayerDataSource(this);
		gamesDataSource = new GameDataSource(this);
		playerDataSource.open();
		gamesDataSource.open();
		players = playerDataSource.getAllPlayers();
	}
	
	public void onListItemClick(ListView parent, View view, int position, long id)
	{
	    super.onListItemClick(parent, view, position, id); 
		Player selectedPlayer = players.get(position);
		Toast.makeText(this, "You have selected player: " + selectedPlayer, Toast.LENGTH_SHORT).show();
	}
	
	public void AddPlayerStats(View view)
	{
		selectedPlayers = getChosenPlayers();
		createGamesForSelectedPlayers(selectedPlayers);
	}

	private List<Player> getChosenPlayers() 
	{
		int length = playerListView.getCount();
		List<Player> chosenPlayers = new ArrayList<Player>();;
				
		SparseBooleanArray checked = playerListView.getCheckedItemPositions();
		//loop through entire list of players 
		for (int i = 0; i < length; i++)
		 if (checked.get(i)) 
		 {
		   Player player = players.get(i);
		   chosenPlayers.add(player);
		 }
		
		return chosenPlayers;
	}

	private void createGamesForSelectedPlayers(List<Player> players) {
		
		for (int i = 0; i < players.size(); i++)
		{
			Player player = players.get(i);
			
			Game newGame = new Game();
			newGame.setName("new fuckin game");
			newGame.setPlayerId(player.getId());
			gamesDataSource.createGame(newGame);
		}
	}
}