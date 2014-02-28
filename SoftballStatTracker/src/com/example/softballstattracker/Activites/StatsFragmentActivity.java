package com.example.softballstattracker.Activites;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Player;

public class StatsFragmentActivity extends ListActivity implements OnItemClickListener 
{

	private static final String TAG = "StatsActivityFragment";
	private ArrayList<Player> selectedPlayers = new ArrayList<Player>();
	private ListView playerListView;
	private long currentGameId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		selectedPlayers = this.getIntent().getParcelableArrayListExtra("selectedPlayers");
		currentGameId = this.getIntent().getLongExtra("currentGameId", 0);
		initialize();
		setupListView();
	}
	
	private void setupListView() 
	{
		playerListView = getListView();
		playerListView.setChoiceMode(playerListView.CHOICE_MODE_NONE);
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_checked, selectedPlayers);
		setListAdapter(adapter);
	}
	
	public void onListItemClick(ListView parent, View view, int position, long id)
	{
	    super.onListItemClick(parent, view, position, id); 
		Player selectedPlayer = selectedPlayers.get(position);
		Intent intent = new Intent(getApplicationContext(), AddStatsDialogActivity.class);
		intent.putExtra("currentGameId", currentGameId);
		intent.putExtra("selectedPlayer", selectedPlayer);
		startActivity(intent);
	}

	private void initialize() {
		Log.d(TAG, "created StatsActivityFragment");
		setContentView(R.layout.stats_activity_fragment);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
