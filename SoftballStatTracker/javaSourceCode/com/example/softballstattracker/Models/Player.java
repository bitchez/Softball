package com.example.softballstattracker.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Player implements Parcelable {
	
	private static final String TAG = "Player object";
	private long playerId; 
	private String name;
	private String dateCreated;
	public boolean isPlayerChosen;
	
	 public long getId() 
	 {
	    return playerId;
	 }

	  public void setId(long playerId) {
	    this.playerId = playerId;
	  }
	
	  public String getname() {
	    return name;
	  }
	
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  public String getDateCreated() {
		return dateCreated;
	  }
	  
	  public void setDateCreated(String dateCreated) 
	  {
		    this.dateCreated = dateCreated;
	  }
		  
	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() 
	  {
	    return name;
	  }
	  
	   	//below logic is used for the purpose of serializing object through intent
	   	@Override
	   	public int describeContents() {
	   		return 0;
	   	}
	   	
	   	public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
	   		
	   		@Override
	   		public Player createFromParcel(Parcel in) {
	   			Log.v(TAG, "creating from parcel");
	   			Player newPlayer = new Player();
	   			newPlayer.playerId = in.readLong();
	   			newPlayer.name = in.readString();
	   			newPlayer.dateCreated = in.readString();
	   			return newPlayer;
	   		}
	   
	   		@Override
	   		public Player[] newArray(int size) {
	   		    return new Player[size];
	   		}
	   	};
		   
	   	@Override
	   	public void writeToParcel(Parcel out, int flags) {
	   		Log.v(TAG, "writeToParcel..." + flags);
	   		out.writeLong(playerId);
	   		out.writeString(name);
	   		out.writeString(dateCreated);
	   	}
	   	
	   	 public Player(Parcel source) 
	   	 {
	   		Log.v(TAG, "Player(Player source): time to put back the parcel data");
	   		playerId = source.readInt();
	   		name = source.readString();
	   		dateCreated = source.readString();
	   	}
	   
	   	public Player() 
	   	{
	   		Log.d(TAG, "new player");
	   	}

}
