package chalk.it.up.Activites;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import chalk.it.up.R;

public class AddGameActivity extends Activity implements OnClickListener {
	
	private ImageButton editDateButton;
	private EditText gameDateInput;
	private EditText opponentInput;
	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 999;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_game);
		setupUI(findViewById(R.id.addGameParent));
		setDefaults();
		
		setCurrentDateOnView();
		addListenerOnButton();
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}
	
		// display current date
		public void setCurrentDateOnView() {
	 
			gameDateInput = (EditText) findViewById(R.id.gameDateInput);
	 
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
	 
			// set current date into textview
			gameDateInput.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("/").append(day).append("/")
				.append(year));
	 
		}
		
		public void addListenerOnButton() {
			 
			editDateButton = (ImageButton) findViewById(R.id.btnChangeDate);
			editDateButton.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View v) {
	 
					showDialog(DATE_DIALOG_ID);
				}
			});
		}	
		
		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
			case DATE_DIALOG_ID:
			   // set date picker as current date
			   return new DatePickerDialog(this, datePickerListener, 
	                         year, month,day);
			}
			return null;
		}
	 
		private DatePickerDialog.OnDateSetListener datePickerListener 
	                = new DatePickerDialog.OnDateSetListener() {
	 
			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear,
					int selectedMonth, int selectedDay) {
				year = selectedYear;
				month = selectedMonth;
				day = selectedDay;
	 
				// set selected date into textview
				gameDateInput.setText(new StringBuilder().append(month + 1)
				   .append("/").append(day).append("/").append(year));
			}
		};
		
		private void setupHeader() {
			TextView txt = (TextView) findViewById(R.id.addGameHeader);
			Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
			txt.setTypeface(font);
		
			int[] color = { Color.WHITE, Color.RED };
			float[] position = {0, 1};
			TileMode tile_mode = TileMode.MIRROR; 
			LinearGradient lin_grad = new LinearGradient(0, 0, 0, 75,color,position, tile_mode);
			Shader shader_gradient = lin_grad;
			txt.getPaint().setShader(shader_gradient);
		}

		private void setDefaults() 
		{
			opponentInput = (EditText) findViewById(R.id.opponentInput);
			setupHeader();
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
			String gameOpponentString = opponentInput.getText().toString();
			
			Intent intent = new Intent(this, ChoosePlayersActivity.class);
			intent.putExtra("gameName_input", gameNameString);
			intent.putExtra("gameDate_input", gameDateString);
			intent.putExtra("gameOpponent_input",  gameOpponentString);
			setResult(RESULT_OK, intent);
		    startActivity(intent);
		}
	}
	
	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText)) {

	        view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(AddGameActivity.this);
					return false;
				}

				private void hideSoftKeyboard(Activity activity) {
					 InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
					 inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
				}
	        });
	    }

	    //If a layout container, iterate over children and seed recursion.
	    if (view instanceof ViewGroup) {

	        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

	            View innerView = ((ViewGroup) view).getChildAt(i);

	            setupUI(innerView);
	        }
	    }
	}

	private boolean DoesGameNameExist(EditText playerName) {
		return (playerName.getText().toString().trim().equals(""));
	}
}
