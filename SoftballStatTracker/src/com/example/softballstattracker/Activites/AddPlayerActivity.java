package com.example.softballstattracker.Activites;
 
import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.softballstattracker.R;
import com.example.softballstattracker.DataSources.PlayerDataSource;
import com.example.softballstattracker.Models.Player;

public class AddPlayerActivity extends Activity {
	
	private static final int SELECT_PHOTO = 100;
	ImageView playerImageView;
	private PlayerDataSource playerDataSource;
	EditText playerNameInput;
	private String selectedImagePath; 
	private Bitmap playerImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
		
		playerImageView = (ImageView) findViewById(R.id.playerImage);
		
		TextView txt = (TextView) findViewById(R.id.playerInfoHeader);
		Typeface font = Typeface.createFromAsset(getAssets(), "marcsc.ttf");
		txt.setTypeface(font);
		
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
		 
		playerNameInput = (EditText)findViewById(R.id.playerNameInput);
		
		if(DoesPlayerNameExist(playerNameInput))
	    {
			playerNameInput.setError("Player name is required");
	    }
		else
		{
			
		String playerName = playerNameInput.getText().toString();
		
		Player newPlayer = new Player();
		newPlayer.setName(playerName);
		
		playerDataSource.createPlayer(newPlayer, selectedImagePath);
		
		Intent intent = new Intent();
		intent.setData(Uri.parse(playerName)); 
		setResult(RESULT_OK, intent);
	    finish();
	    
		}
	}

	private boolean DoesPlayerNameExist(EditText playerName) {
		return (playerName.getText().toString().trim().equals(""));
		
	}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_player, menu);
		return true;
	}
}
