package com.example.softballstattracker;

import java.util.List;
import java.util.Calendar;

import com.example.softballstattracker.DataSources.GameDataSource;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.app.DatePickerDialog;
import android.app.Dialog;

public class AddGameActivity extends Activity implements OnClickListener {
	
	 private ImageButton ib;
	 private Calendar cal;
	 private int day;
	 private int month;
	 private int year;
	 private EditText et;

	private GameDataSource gameDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_game);
		ib = (ImageButton) findViewById(R.id.imageButton1);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		et = (EditText) findViewById(R.id.gameDateInput);
		ib.setOnClickListener(this);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		gameDataSource = new GameDataSource(this);
		gameDataSource.open();
	}
	
	@Override
	 public void onClick(View v) {
	  showDialog(0);
	 }

	 @Override
	 @Deprecated
	 protected Dialog onCreateDialog(int id) {
	  return new DatePickerDialog(this, datePickerListener, year, month, day);
	 }

	 private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		 public void onDateSet(DatePicker view, int selectedYear,
		 int selectedMonth, int selectedDay) {
			 et.setText((selectedMonth + 1) + " / " + selectedDay + " / "
			 + selectedYear);
		 }
	 };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_game, menu);
		return true;
	}
	
	public void choosePlayers(View view)
	{
		Intent intent = new Intent(this, ChoosePlayersActivity.class);	
	    startActivity(intent);
	}
	
	public void Back(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}

}
