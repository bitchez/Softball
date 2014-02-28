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
			TextView gameBeersDrank = (TextView) childRow.findViewById(R.id.gameBeersDrank);

			if (gameSingles != null) {
				gameSingles.setText(String.valueOf(gameStatChild.getSingles()));
			}
			if (gameDoubles != null) {
				gameDoubles.setText(String.valueOf(gameStatChild.getDoubles()));
			}
			if (gameTriples != null) {
				gameTriples.setText("today"); // stat.getDa(teCreated());
			}
			if (gameHomeRuns != null) {
				gameHomeRuns.setText(String.valueOf(gameStatChild.getHomeRuns()));
			}
			if (gamePutOuts != null) {
				gamePutOuts.setText(String.valueOf(gameStatChild.getPutOuts()));
			}
			if (gameBeersDrank != null) {
				gameBeersDrank.setText(String.valueOf(gameStatChild.getBeersDrank()));
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
				gameDateCreated.setText("today"); // stat.getDateCreated());
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