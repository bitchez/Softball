package com.example.softballstattracker.Activites;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softballstattracker.R;
import com.example.softballstattracker.DataSources.GameDataSource;
import com.example.softballstattracker.DataSources.PlayerDataSource;
import com.example.softballstattracker.Models.Game;
import com.example.softballstattracker.Models.Player;

public class ChoosePlayersActivity extends ListActivity {

	private PlayerDataSource playerDataSource;
	private GameDataSource gamesDataSource;
	private ArrayList<Player> selectedPlayers = new ArrayList<Player>();
	private List<Player> players;
	private ListView playerListView;
	private String gameDate;
	private String gameName;
	private String opponentName;
	EditText gameNameInput;
	private long currentGameId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_players);

		ActionBar actionBar = getActionBar();
		actionBar.hide();

		getAddGameInformation();
		initializeDataSources();
		setupListView();
	}

	private void getAddGameInformation() {
		TextView gameDateText = (TextView) findViewById(R.id.textView1);
		Button addStats = (Button) findViewById(R.id.button1);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			gameName = bundle.getString("gameName_input");
			gameDate = bundle.getString("gameDate_input");
			opponentName = bundle.getString("opponentName_input");

			if (gameDate != null && !gameDate.isEmpty()) {
				gameDateText.append(" on date: " + gameDate);
			}
			if (opponentName != null && !opponentName.isEmpty()) {
				addStats.append(" to game against: " + opponentName);
			}
		}
	}

	private void setupListView() {
		playerListView = getListView();
		playerListView.setChoiceMode(playerListView.CHOICE_MODE_MULTIPLE);
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this,
				android.R.layout.simple_list_item_checked, players);
		setListAdapter(adapter);
	}

	private void initializeDataSources() {
		playerDataSource = new PlayerDataSource(this);
		gamesDataSource = new GameDataSource(this);
		playerDataSource.open();
		gamesDataSource.open();
		players = playerDataSource.getAllPlayers();
	}

	public void onListItemClick(ListView parent, View view, int position,
			long id) {
		super.onListItemClick(parent, view, position, id);
		Player selectedPlayer = players.get(position);
		Toast.makeText(this, "You have selected player: " + selectedPlayer,
				Toast.LENGTH_SHORT).show();
	}

	public void addPlayerStats(View view) {
		createGamesForSelectedPlayers();
		selectedPlayers = getChosenPlayers();
		Intent intent = new Intent(this, EditStatsActivity.class);
		intent.putExtra("currentGameId", currentGameId);
		intent.putParcelableArrayListExtra("selectedPlayers", selectedPlayers);
		startActivity(intent);
	}

	private ArrayList<Player> getChosenPlayers() {
		int length = playerListView.getCount();
		ArrayList<Player> chosenPlayers = new ArrayList<Player>();

		SparseBooleanArray checked = playerListView.getCheckedItemPositions();
		// loop through entire list of players
		for (int i = 0; i < length; i++)
			if (checked.get(i)) {
				Player player = players.get(i);
				chosenPlayers.add(player);
			}

		return chosenPlayers;
	}

	private void createGamesForSelectedPlayers() {
		Game newGame = new Game();
		newGame.setName(gameName);
		newGame.setDateCreated(gameDate);
		gamesDataSource.createGame(newGame);
		currentGameId = newGame.getId();
	}
}