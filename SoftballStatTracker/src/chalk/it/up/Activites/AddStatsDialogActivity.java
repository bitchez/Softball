package chalk.it.up.Activites;

import org.joda.time.DateTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
		
		setAtBats(newStat);
		setSingles(newStat);
		setDoubles(newStat);
		setTriples(newStat);
		setHomeruns(newStat);
		setRBIs(newStat);
		setWalks(newStat);
		setRuns(newStat);
		setSacFlys(newStat);
		setBeers(newStat);
		setErrors(newStat);
		setPutOuts(newStat);

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
	
	private void setAtBats(Stat newStat) {
		int atbats = 0;
		try
		{
			atbats = Integer.parseInt(AtBats.getText().toString());
		}
		catch ( Exception e )
		{
			atbats = 0;
		}
		
		newStat.setAtBats(atbats);
	}
	
	private void setSingles(Stat newStat) {
		int singles = 0;
		try
		{
			singles = Integer.parseInt(Singles.getText().toString());
		}
		catch ( Exception e )
		{
			singles = 0;
		}
		
		newStat.setSingles(singles);
	}
	
	private void setDoubles(Stat newStat) {
		int doubles = 0;
		try
		{
			doubles = Integer.parseInt(Doubles.getText().toString());
		}
		catch ( Exception e )
		{
			doubles = 0;
		}
		
		newStat.setDoubles(doubles);
	}
	
	private void setTriples(Stat newStat) {
		int triples = 0;
		try
		{
			triples = Integer.parseInt(Triples.getText().toString());
		}
		catch ( Exception e )
		{
			triples = 0;
		}
		
		newStat.setTriples(triples);
	}
	
	private void setHomeruns(Stat newStat) {
		int homeruns = 0;
		try
		{
			homeruns = Integer.parseInt(Homeruns.getText().toString());
		}
		catch ( Exception e )
		{
			homeruns = 0;
		}
		
		newStat.setHomeRuns(homeruns);
	}
	
	private void setRBIs(Stat newStat) {
		int rbis = 0;
		try
		{
			rbis = Integer.parseInt(RBIs.getText().toString());
		}
		catch ( Exception e )
		{
			rbis = 0;
		}
		
		newStat.setRbis(rbis);
	}

	private void setWalks(Stat newStat) {
		int walks = 0;
		try
		{
			walks = Integer.parseInt(Walks.getText().toString());
		}
		catch ( Exception e )
		{
			walks = 0;
		}
		
		newStat.setWalks(walks);
	}

	private void setRuns(Stat newStat) {
		int runs = 0;
		try
		{
			runs = Integer.parseInt(Runs.getText().toString());
		}
		catch ( Exception e )
		{
			runs = 0;
		}
		
		newStat.setRuns(runs);
	}
	
	private void setSacFlys(Stat newStat) {
		int sacflys = 0;
		try
		{
			sacflys = Integer.parseInt(SacFlys.getText().toString());
		}
		catch ( Exception e )
		{
			sacflys = 0;
		}
		
		newStat.setSacFlys(sacflys);
	}
	
	private void setPutOuts(Stat newStat) {
		int putouts = 0;
		try
		{
			putouts = Integer.parseInt(PutOuts.getText().toString());
		}
		catch ( Exception e )
		{
			putouts = 0;
		}
		
		newStat.setPutOuts(putouts);
	}
	
	private void setBeers(Stat newStat) {
		int beers = 0;
		try
		{
			beers = Integer.parseInt(BeersDrank.getText().toString());
		}
		catch ( Exception e )
		{
			beers = 0;
		}
		
		newStat.setBeerDrank(beers);
	}
	
	private void setErrors(Stat newStat) {
		int errors = 0;
		try
		{
			errors = Integer.parseInt(Errors.getText().toString());
		}
		catch ( Exception e )
		{
			errors = 0;
		}
		
		newStat.setErrors(errors);
	}
}
