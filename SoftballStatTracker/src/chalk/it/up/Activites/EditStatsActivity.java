package chalk.it.up.Activites;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import chalk.it.up.R;
import chalk.it.up.Models.Player;

public class EditStatsActivity extends ListActivity implements
		OnItemClickListener {
	private static final String TAG = "EditStatsActivity";
	private ArrayList<Player> selectedPlayers = new ArrayList<Player>();
	private ListView playerListView;
	private long currentGameId;
	private Player selectedPlayer;
	private ArrayAdapter<Player> playerAdapter;
	int request_Code = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		selectedPlayers = this.getIntent().getParcelableArrayListExtra(
				"selectedPlayers");
		currentGameId = this.getIntent().getLongExtra("currentGameId", 0);
		
		initialize();
		setupHeader();
		setupListView();
	}

	private void setupListView() {
		playerListView = getListView();
		playerListView.setChoiceMode(playerListView.CHOICE_MODE_NONE);
		playerAdapter = new ArrayAdapter<Player>(this,
				android.R.layout.simple_selectable_list_item, selectedPlayers);
		setListAdapter(playerAdapter);

	}

	public void onListItemClick(ListView parent, View view, int position,
			long id) {
		super.onListItemClick(parent, view, position, id);
		selectedPlayer = selectedPlayers.get(position);
		Intent intent = new Intent(getApplicationContext(),
				AddStatsDialogActivity.class);
		intent.putExtra("currentGameId", currentGameId);
		intent.putExtra("currentPlayerId", selectedPlayer.getId());
		intent.putExtra("currentPlayerName", selectedPlayer.getname());
		startActivityForResult(intent, request_Code);
	}

	private void initialize() {
		Log.v(TAG, "created StatsActivityFragment");
		setContentView(R.layout.edit_stats_activity);

		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (selectedPlayer != null) {
			if (resultCode == RESULT_OK) {
				selectedPlayers.remove(selectedPlayer);
				playerAdapter.notifyDataSetChanged();
				Toast.makeText(getBaseContext(),
						selectedPlayer + "'s stats saved", Toast.LENGTH_LONG)
						.show();
			}
		}
		if (selectedPlayers.size() == 0) {
			Intent intent = new Intent(this, MainMenuActivity.class);
			Toast.makeText(getBaseContext(), "New stats saved to leaderboard",
					Toast.LENGTH_SHORT).show();
			startActivity(intent);
		}
	}

	public void onPause() {
		super.onPause();
		Log.v(TAG, "In the onPause() event");
	}

	public void onStop() {
		super.onStop();
		Log.v(TAG, "In the onStop() event");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}
	
	private void setupHeader() {
		TextView txt = (TextView) findViewById(R.id.addStatHeader);
		Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
		txt.setTypeface(font);
	
		int[] color = { Color.WHITE, Color.RED };
		float[] position = {0, 1};
		TileMode tile_mode = TileMode.MIRROR; // or TileMode.REPEAT;
		LinearGradient lin_grad = new LinearGradient(0, 0, 0, 75,color,position, tile_mode);
		Shader shader_gradient = lin_grad;
		txt.getPaint().setShader(shader_gradient);
	}
}
