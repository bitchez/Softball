package com.example.softballstattracker.Activites;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.softballstattracker.R;

public class AddGameActivity extends Activity implements OnClickListener {
	
	 private ImageButton editDateButton;
	 private Calendar calendar;
	 private int day;
	 private int month;
	 private int year;
	 private EditText gameDateInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_game);
		
		editDateButton = (ImageButton) findViewById(R.id.calendarButton);
		editDateButton.setOnClickListener(this);
		
		setDateDefaults();
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	private void setDateDefaults() 
	{
		calendar = Calendar.getInstance();
		day = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH);
		year = calendar.get(Calendar.YEAR);
		gameDateInput = (EditText) findViewById(R.id.gameDateInput);
		gameDateInput.setText((month + 1) + " / " + day + " / " + year);
		
		TextView txt = (TextView) findViewById(R.id.gameInfoHeader);
		Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
		txt.setTypeface(font);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) 
	{
		//todo in future after googling why its crossed out--
		//Control of the dialog (deciding when to show, hide, dismiss it) 
		//should be done through the API here, not with direct calls on the dialog.
		//http://android-developers.blogspot.in/2012/05/using-dialogfragments.html
		showDialog(0);
	}

	@Override
	protected Dialog onCreateDialog(int id) 
	{
		return new DatePickerDialog(this, datePickerListener, year, month, day);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() 
	{
		public void onDateSet(DatePicker view, int selectedYear,
		int selectedMonth, int selectedDay) 
		{
			gameDateInput.setText((month + 1) + " / " + day + " / "
					+ year);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_game, menu);
		return true;
	}

	public void choosePlayers(View view)
	{
		EditText gameNameInput = (EditText) findViewById(R.id.gameNameInput);
		EditText gameDate = (EditText) findViewById(R.id.gameDateInput);
		
		if(DoesGameNameExist(gameNameInput))
	    {
			gameNameInput.setError("Game name is required");
	    }
		else
		{
			String gameNameString = gameNameInput.getText().toString();
			String gameDateString = gameDate.getText().toString();
			
			Intent intent = new Intent(this, ChoosePlayersActivity.class);
			intent.putExtra("gameName_input", gameNameString);
			intent.putExtra("gameDate_input", gameDateString);
			setResult(RESULT_OK, intent);
		    startActivity(intent);
		}
	}
	
	 private boolean DoesGameNameExist(EditText playerName) {
		return (playerName.getText().toString().trim().equals(""));
	}
}
