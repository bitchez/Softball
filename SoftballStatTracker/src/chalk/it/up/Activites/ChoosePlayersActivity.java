package chalk.it.up.Activites;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import chalk.it.up.R;
import chalk.it.up.DataSources.GameDataSource;
import chalk.it.up.DataSources.PlayerDataSource;
import chalk.it.up.Models.Game;
import chalk.it.up.Models.Player;


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
	private boolean isPlayerSelected;
	final Context context = this;
	private Player selectedPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_players);

		ActionBar actionBar = getActionBar();
		actionBar.hide();

		getAddGameInformation();
		initializeDataSources();
		setupListView();
		setupHeader();
	}

	private void getAddGameInformation() 
	{
		TextView gameInfoText = (TextView) findViewById(R.id.gameInfoText);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) 
		{
			gameName = bundle.getString("gameName_input");
			gameDate = bundle.getString("gameDate_input");
			opponentName = bundle.getString("gameOpponent_input");
			
			gameInfoText.setText(String.format("These are all the players you've ever created. Select the ones you want to add stats for %s played on: %s", gameName, gameDate));
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
		selectedPlayer = players.get(position);
		Toast.makeText(this, "You have selected player: " + selectedPlayer,
				Toast.LENGTH_SHORT).show();
	}
	
	private void setupHeader() {
		TextView txt = (TextView) findViewById(R.id.choosePlayerHeader);
		Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
		txt.setTypeface(font);
	
		int[] color = { Color.WHITE, Color.RED };
		float[] position = {0, 1};
		TileMode tile_mode = TileMode.MIRROR; 
		LinearGradient lin_grad = new LinearGradient(0, 0, 0, 75,color,position, tile_mode);
		Shader shader_gradient = lin_grad;
		txt.getPaint().setShader(shader_gradient);
	}

	public void addPlayerStats(View view) {
		validatePlayerSelection();
		if (isPlayerSelected == true)
			{
			createGamesForSelectedPlayers();
			selectedPlayers = getChosenPlayers();
			Intent intent = new Intent(this, EditStatsActivity.class);
			intent.putExtra("currentGameId", currentGameId);
			intent.putParcelableArrayListExtra("selectedPlayers", selectedPlayers);
			startActivity(intent);
		}
		else {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("ERROR BITCH!!");
				alertDialogBuilder
					.setMessage("Please have one or more player(s) selected.")
					.setCancelable(false)
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
				
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
								
			}
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
	
	private void validatePlayerSelection() {
		isPlayerSelected = false;
		if (selectedPlayer != null) {
			isPlayerSelected = true;
		}
	}

	private void createGamesForSelectedPlayers() {
		Game newGame = new Game();
		newGame.setName(gameName);
		newGame.setDateCreated(gameDate);
		newGame.setOpponent(opponentName);
		gamesDataSource.createGame(newGame);
		currentGameId = newGame.getId();
	}
}