package chalk.it.up.Activites;

import org.joda.time.DateTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import chalk.it.up.R;
import chalk.it.up.DataSources.StatDataSource;
import chalk.it.up.Models.Stat;


public class AddStatsDialogActivity extends Activity
{
	private static final String TAG = "EditStatsFragment";
	EditText AtBats;
	EditText Singles;
	EditText Doubles;
	EditText Triples;
	EditText Homeruns;
	EditText RBIs;
	EditText Runs;
	EditText PutOuts;
	EditText SacFlys;
	EditText BeersDrank;
	EditText Walks;
	EditText Errors;
	private TextView playerInfoHeader;
	private Stat newStat = new Stat();
	Button saveStatsButton;
	private StatDataSource statsDataSource;
	private boolean hasErrors;
	private long currentPlayerId;
	private long currentGameId;
	private String currentPlayerName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_stats_dialog);
		
		setupUI(findViewById(R.id.addStatsDialogue));
		initialize();
	}

	public void saveStats() 
	{
		newStat.setPlayerId(currentPlayerId);
		newStat.setGameId(currentGameId);
		newStat.setPlayerName(currentPlayerName);
		newStat.setDateCreated(DateTime.now().toString());
		newStat.setAtBats(Integer.parseInt(AtBats.getText().toString()));
		newStat.setSingles(Integer.parseInt(Singles.getText().toString()));
		newStat.setDoubles(Integer.parseInt(Doubles.getText().toString()));
		newStat.setTriples(Integer.parseInt(Triples.getText().toString()));
		newStat.setHomeRuns(Integer.parseInt(Homeruns.getText().toString()));
		newStat.setRbis(Integer.parseInt(RBIs.getText().toString()));
		newStat.setWalks(Integer.parseInt(Walks.getText().toString()));
		newStat.setRuns(Integer.parseInt(Runs.getText().toString()));
		newStat.setPutOuts(Integer.parseInt(PutOuts.getText().toString()));
		newStat.setSacFlys(Integer.parseInt(SacFlys.getText().toString()));
		newStat.setBeerDrank(Integer.parseInt(BeersDrank.getText().toString()));
		newStat.setErrors(Integer.parseInt(Errors.getText().toString()));

		validateNewStat(newStat);

		if (hasErrors == false) 
		{
			Log.v(TAG, "Saving Statistic");
			statsDataSource.createStatistic(newStat);
			Log.v(TAG, "Statistics saved");
			
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		}
	}

	private void initialize() 
	{
		Bundle bundle = getIntent().getExtras();
		
		if(bundle != null)
		{
			currentGameId = bundle.getLong("currentGameId");
			currentPlayerId = bundle.getLong("currentPlayerId");
			currentPlayerName = bundle.getString("currentPlayerName");
		}
		 
		playerInfoHeader = (TextView) findViewById(R.id.playerCardInfo);
		playerInfoHeader.setText(String.format("#%s -  %s", currentPlayerId, currentPlayerName));

		saveStatsButton = (Button) findViewById(R.id.saveStats);
		saveStatsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveStats();
			}
		});

		AtBats = (EditText) findViewById(R.id.atBatsInput);
		Singles = (EditText) findViewById(R.id.singlesInput);
		Doubles = (EditText) findViewById(R.id.doublesInput);
		Triples = (EditText) findViewById(R.id.triplesInput);
		Homeruns = (EditText) findViewById(R.id.homeRunsInput);
		RBIs = (EditText) findViewById(R.id.rbisInput);
		Runs = (EditText) findViewById(R.id.runsInput);
		Walks = (EditText) findViewById(R.id.walksInput);
		BeersDrank = (EditText) findViewById(R.id.beersDrankInput);
		SacFlys = (EditText) findViewById(R.id.sacFlysInput);
		PutOuts = (EditText) findViewById(R.id.putOutsInput);
		Errors = (EditText) findViewById(R.id.errorsInput);

		statsDataSource = new StatDataSource(this);
		statsDataSource.open();
	}

	private void validateNewStat(Stat newStat) 
	{
		hasErrors = false;
		newStat.setHits(newStat.getSingles(), newStat.getDoubles(),
				newStat.getTriples(), newStat.getHomeRuns());

		if (newStat.getAtBats() <= 0)
		{
			hasErrors = true;
			AtBats.setError("You need at least one at bat mother fucker");
		}
		else
		{
			int placeAppearances = newStat.getHits() + newStat.getWalks();
			if(placeAppearances * 4 < newStat.getHits())
			{
				hasErrors = true;
				RBIs.setError("You can't have more RBIs than plate appearances mother fucker");
			}
		}
		
		if (newStat.getHits() > newStat.getAtBats()) {
			hasErrors = true;
			AtBats.setError("You have too many hits per at bats mother fucker.");
		}
		
		checkForNulls(newStat);
		
	}
	
	private void checkForNulls(Stat newStat) {
		if(AtBats.getText().equals("")) {
			newStat.setAtBats(0);}
		if(Singles.getText().equals("")) {
			newStat.setSingles(0);}
		if(Doubles.getText().equals("")) {
			newStat.setDoubles(0);}
		if(Triples.getText().equals("")) {
			newStat.setTriples(0);}
		if(Homeruns.getText().equals("")) {
			newStat.setHomeRuns(0);}
		if(RBIs.getText().equals("")) {
			newStat.setRbis(0);}
		if(Runs.getText().equals("")) {
			newStat.setRuns(0);}
		if(Walks.getText().equals("")) {
			newStat.setWalks(0);}
		if(BeersDrank.getText().equals("")) {
			newStat.setBeerDrank(0);}
		if(SacFlys.getText().equals("")) {
			newStat.setSacFlys(0);}
		if(PutOuts.getText().equals("")) {
			newStat.setPutOuts(0);}
		if(Errors.getText().equals("")) {
			newStat.setErrors(0);}
	}

	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText)) {

	        view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(AddStatsDialogActivity.this);
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
}
