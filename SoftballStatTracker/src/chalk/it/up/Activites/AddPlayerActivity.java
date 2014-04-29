package chalk.it.up.Activites;
 
import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import chalk.it.up.R;
import chalk.it.up.DataSources.PlayerDataSource;
import chalk.it.up.Models.Player;


public class AddPlayerActivity extends Activity {
	
	private static final int SELECT_PHOTO = 100;
	ImageView playerImageView;
	private PlayerDataSource playerDataSource;
	EditText playerNameInput;
	EditText playerNumberInput;
	private String selectedImagePath; 
	private Bitmap playerImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
		
		playerImageView = (ImageView) findViewById(R.id.playerImage);
		
		setupHeader();
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		playerDataSource = new PlayerDataSource(this);
		playerDataSource.open();
	}
	
	public void onSelectImage(View view) 
	{
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, SELECT_PHOTO);    
	}
	
	private void setupHeader() {
		TextView txt = (TextView) findViewById(R.id.playerInfoHeader);
		Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
		txt.setTypeface(font);
	
		int[] color = { Color.WHITE, Color.RED };
		float[] position = {0, 1};
		TileMode tile_mode = TileMode.MIRROR;
		LinearGradient lin_grad = new LinearGradient(0, 0, 0, 75,color,position, tile_mode);
		Shader shader_gradient = lin_grad;
		txt.getPaint().setShader(shader_gradient);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case SELECT_PHOTO:
	        if(resultCode == RESULT_OK) 
	        {  
	            Uri selectedImageUri = imageReturnedIntent.getData();
	            selectedImagePath = selectedImageUri.getPath();
	            String[] filePathColumn = {MediaStore.Images.Media.DATA};

	            Cursor cursor = getContentResolver().query(
	            		selectedImageUri, filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String filePath = cursor.getString(columnIndex);
	            cursor.close();

	            playerImage = BitmapFactory.decodeFile(filePath);
	            playerImageView.setImageBitmap(playerImage);
	        }
	    }
	}
	
	public void savePlayer(View view) throws IOException {
		
		playerNumberInput = (EditText)findViewById(R.id.playerNumberInput);
		playerNameInput = (EditText)findViewById(R.id.playerNameInput);
		
		if(DoesPlayerNameExist(playerNameInput, playerNumberInput))
	    {
			playerNameInput.setError("cmon man, a player name is required, duh");
			playerNameInput.setError("Yo, a player number is required fool");
	    }
		else
		{
			String playerName = playerNameInput.getText().toString();
			String playerNumber = playerNumberInput.getText().toString();
			
			Player newPlayer = new Player();
			newPlayer.setName(playerName);
			newPlayer.setId(Integer.parseInt(playerNumber));
			
			if(IsPlayerNumberUnique(newPlayer.getId()))
			{
				try {
					playerDataSource.createPlayer(newPlayer, selectedImagePath);
				}
				catch (SQLiteConstraintException e)  {
					Log.e("TaG", "SQLiteException:" + e.getMessage());
				}	
				
				Intent intent = new Intent();
				intent.setData(Uri.parse(playerName)); 
				setResult(RESULT_OK, intent);
			    finish();
			}
			else
			{
				playerNumberInput.setError("this player number has already been taken");
			}
		}
	}

	private boolean IsPlayerNumberUnique(long id) {
		boolean isUnique = playerDataSource.isPlayerNumberUnique(id);
		return isUnique;
	}

	private boolean DoesPlayerNameExist(EditText playerName, EditText playerNumber) {
		return (playerName.getText().toString().trim().equals("") || 
				playerNumber.getText().toString().trim().equals(""));
	}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_player, menu);
		return true;
	}
}
