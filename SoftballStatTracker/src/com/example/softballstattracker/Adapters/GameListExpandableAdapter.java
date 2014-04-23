package com.example.softballstattracker.Adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.softballstattracker.R;
import com.example.softballstattracker.Models.Stat;

public class GameListExpandableAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<Stat> _games;
	private HashMap<String, List<Stat>> _gameChildStat;

	public GameListExpandableAdapter(Context context,
			List<Stat> listDataHeader, HashMap<String, List<Stat>> listDataChild) {
		this._context = context;
		this._games = listDataHeader;
		this._gameChildStat = listDataChild;
	}

	@Override
	public Stat getChild(int groupPosition, int childPosititon) {
		
		// could really just return _games.get(groupPosition);
		// but for sake of practicing HashMaps
		Stat gameStat = this._games.get(groupPosition);
		String key = gameStat.getPlayerName();
		HashMap<String, List<Stat>> childHashSet = this._gameChildStat;
		List<Stat> matchingItems = childHashSet.get(key);
		return matchingItems.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View childRow, ViewGroup parent) {

		Stat gameStatChild = getChild(groupPosition, childPosition);

		if (childRow == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childRow = infalInflater.inflate(R.layout.game_by_game_child_row, null);
		}

		if (gameStatChild != null) 
		{
			TextView gameSingles = (TextView) childRow.findViewById(R.id.gameSingles);
			TextView gameDoubles = (TextView) childRow.findViewById(R.id.gameDoubles);
			TextView gameTriples = (TextView) childRow.findViewById(R.id.gameTriples);
			TextView gameHomeRuns = (TextView) childRow.findViewById(R.id.gameHomeruns);
			TextView gamePutOuts = (TextView) childRow.findViewById(R.id.gamePutOuts);
			TextView gameSacFlys = (TextView) childRow.findViewById(R.id.gameSacFlys);
			TextView gameBeersDrank = (TextView) childRow.findViewById(R.id.gameBeersDrank);
			TextView gameWalks = (TextView) childRow.findViewById(R.id.gameWalks);
			TextView gameRuns = (TextView) childRow.findViewById(R.id.gameRuns);
			TextView gameOpponent = (TextView) childRow.findViewById(R.id.gameOpponent);
			TextView gameErrors = (TextView) childRow.findViewById(R.id.gameErrors);
			
			if (gameOpponent != null) {
				gameOpponent.setText("vs. " + String.valueOf(gameStatChild.getOpponent()));
			}
			if (gameSingles != null) {
				gameSingles.setText("singles:" + String.valueOf(gameStatChild.getSingles()));
			}
			if (gameDoubles != null) {
				gameDoubles.setText("doubles:" + String.valueOf(gameStatChild.getDoubles()));
			}
			if (gameTriples != null) {
				gameTriples.setText("triples:" + String.valueOf(gameStatChild.getTriples()));
			}
			if (gameHomeRuns != null) {
				gameHomeRuns.setText("homeruns:" + String.valueOf(gameStatChild.getHomeRuns()));
			}
			if (gameWalks != null) {
				gameWalks.setText("walks:" + String.valueOf(gameStatChild.getWalks()));
			}
			if (gameWalks != null) {
				gameWalks.setText("walks:" + String.valueOf(gameStatChild.getWalks()));
			}
			if (gamePutOuts != null) {
				gamePutOuts.setText("putouts:" + String.valueOf(gameStatChild.getPutOuts()));
			}
			if (gameSacFlys != null) {
				gamePutOuts.setText("sacFlys:" + String.valueOf(gameStatChild.getSacFlys()));
			}
			if (gameBeersDrank != null) {
				gameBeersDrank.setText("beers:" + String.valueOf(gameStatChild.getBeersDrank()));
			}
			if (gameRuns != null) {
				gameRuns.setText("runs:" + String.valueOf(gameStatChild.getRuns()));
			}
			if (gameErrors != null) {
				gameErrors.setText("errors:" + String.valueOf(gameStatChild.getErrors()));
			}
		}

		return childRow;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// todo: for future use if we need more than one child
		// Stat gameStat = this._games.get(groupPosition);
		// String key = gameStat.getPlayerName();
		// HashMap<String, List<Stat>> test = this._gameChildStat;
		// List<Stat> item = test.get(key);
		// int size = this._gameChildStat.get(key).size();
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._games.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._games.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View row,
			ViewGroup parent) {
		Stat gameStats = (Stat) getGroup(groupPosition);

		if (row == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = infalInflater.inflate(R.layout.game_by_game_row, null);
		}

		if (gameStats != null) {
			TextView gameName = (TextView) row.findViewById(R.id.gameName);
			TextView gameDateCreated = (TextView) row
					.findViewById(R.id.gameDateCreated);
			TextView gameRBIs = (TextView) row
					.findViewById(R.id.gameRBIs);
			TextView gameHits = (TextView) row.findViewById(R.id.gameHits);
			TextView gameAvg = (TextView) row.findViewById(R.id.gameAverage);

			if (gameHits != null) {
				gameHits.setText(String.valueOf(gameStats.getHits()));
			}
			if (gameName != null) {
				gameName.setText(gameStats.getPlayerName());
				gameName.setTypeface(null, Typeface.BOLD);
			}
			if (gameDateCreated != null) {
				String dateString = gameStats.getDateCreated();
				String year = dateString.substring(7, 9);
				String month = dateString.substring(0, 1);
				String day = dateString.substring(2, 4);
				gameDateCreated.setText(month + "/" + day + "/" + year);
			}
			if (gameRBIs != null) {
				gameRBIs.setText(String.valueOf(gameStats.getRunsBattedIn()));
			}
			if (gameAvg != null) {
				int abs = gameStats.getAtBats();
				int hs = gameStats.getHits();
				float avg = gameStats.getAverage(hs, abs);
				String avgString = String.format(Locale.getDefault(), "%.03f",
						avg);
				gameAvg.setText(avgString);
			}
		}

		return row;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}